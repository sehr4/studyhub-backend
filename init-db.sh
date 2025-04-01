#!/bin/bash
set -e

# Connect to PostgreSQL and create the studyhub_admin user
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER studyhub_admin WITH PASSWORD 'studyhubPass';
    ALTER DATABASE "studyhub-db" OWNER TO studyhub_admin;
    \c studyhub-db
    GRANT ALL ON SCHEMA public TO studyhub_admin;
    GRANT ALL ON ALL TABLES IN SCHEMA public TO studyhub_admin;
    GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO studyhub_admin;
    -- Set default privileges for future objects
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO studyhub_admin;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO studyhub_admin;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON FUNCTIONS TO studyhub_admin;
    ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TYPES TO studyhub_admin;
EOSQL