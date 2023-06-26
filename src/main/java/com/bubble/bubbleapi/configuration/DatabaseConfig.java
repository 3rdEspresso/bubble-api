package com.bubble.bubbleapi.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class DatabaseConfig {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void executeSQLScript() throws IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sqlScript = new String(Files.readAllBytes(Paths.get(getClass().getResource("/citus-distribute-tables.sql").getPath())), StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}

