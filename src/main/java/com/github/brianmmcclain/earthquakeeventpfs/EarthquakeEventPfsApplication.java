package com.github.brianmmcclain.earthquakeeventpfs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EarthquakeEventPfsApplication {

	@Bean
    public Function<String, String> getevents() {
        return s -> {
			
			// Parse environment variables
			String dbHostEnv = System.getenv("PGHOST");
			String dbPortEnv = System.getenv("PGPORT");
			String dbDatabaseEnv = System.getenv("PGDATABASE");
			String dbUserEnv = System.getenv("PGUSER");
			String dbPasswordEnv = System.getenv("PGPASSWORD");

			// Process defaults of environment variable isn't defined
			String dbHost = (dbHostEnv == null) ? "localhost" : dbHostEnv;
			String dbPort = (dbPortEnv == null) ? "5432" : dbPortEnv;
			String dbDatabase = (dbDatabaseEnv == null) ? "geocode" : dbDatabaseEnv;
			String dbUser = (dbUserEnv == null) ? "postgres" : dbUserEnv;
			String dbPassword = (dbPasswordEnv == null) ? "password" : dbPasswordEnv;

			// Connect to Postgres
			String dbString = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbDatabase);
			Properties props = new Properties();
			props.setProperty("user", dbUser);
			props.setProperty("password", dbPassword);
			Connection conn =  null;
			try {
				conn = DriverManager.getConnection(dbString, props);
				Statement statement = conn.createStatement();
				String queryStatement = "select * from events where timestamp > 'now'::timestamp - '24 hours'::interval;";
				ResultSet rs = statement.executeQuery(queryStatement);
			} catch (SQLException e) {
				// TODO: Process error
			}

			return "json response";
			
		};
    }
	public static void main(String[] args) {
		SpringApplication.run(EarthquakeEventPfsApplication.class, args);
	}
}
