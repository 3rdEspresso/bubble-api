CREATE EXTENSION IF NOT EXISTS citus;

CREATE OR REPLACE FUNCTION distribute_table(table_name text, distribution_column text)
    RETURNS void AS $$
BEGIN
    IF NOT EXISTS (
        SELECT
        FROM pg_dist_partition
        WHERE logicalrelid = table_name::regclass
    ) THEN
        EXECUTE format('SELECT create_distributed_table(%L, %L)', table_name, distribution_column);
    END IF;
END;
$$ LANGUAGE plpgsql;
