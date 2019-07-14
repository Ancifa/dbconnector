package com.diamond.dbconnector.dao;

import com.diamond.dbconnector.model.Email;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

public class DbConnectorDAO {
    private JdbcTemplate jdbcTemplate;

    public DbConnectorDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getEmail(int id) {
        String query = "select e.email from email e where e.id = ?";

        return jdbcTemplate.queryForObject(query, String.class, id);
    }

    public Email getEmailRow(int id) {
        String query = "select * from email e where e.id = ?";
        BeanPropertyRowMapper<Email> rowMapper = new BeanPropertyRowMapper<>(Email.class);
        Email result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[] {id}, rowMapper);
        } catch (DataAccessException e) {
            new Email();
        }

        return Optional.ofNullable(result)
                .orElse(new Email().setResult("Record not found."));
    }
}
