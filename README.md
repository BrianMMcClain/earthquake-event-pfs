# earthquake-event-pfs

[PFS function](https://pivotal.io/platform/pivotal-function-service) that takes request from the [GeoQuake Frontend](https://github.com/BrianMMcClain/geoquake-pfs) and responds with all earthquake events from the last 24 hours.

Expected Environment Variables
---
- **PGHOST** = Hostname for Postgres server
- **PGPORT** = Port for Postgres server
- **PGDATABASE** = Name of database in Postgres server
- **PGUSER** = Postgres username
- **PGPASSWORD** = Postgres password
