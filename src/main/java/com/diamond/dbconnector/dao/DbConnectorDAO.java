package com.diamond.dbconnector.dao;

import com.diamond.dbconnector.model.Email;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class DbConnectorDAO {
    public static final String RECORD_NOT_FOUND = "Record not found.";
    private JdbcTemplate jdbcTemplate;

    public DbConnectorDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getEmail(int id) {
        String query = "select e.email from email e where e.id = ?";
        String result = RECORD_NOT_FOUND;
        try {
            result = jdbcTemplate.queryForObject(query, String.class, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return result;
        }
        return result;
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
                .orElse(new Email().setResult(RECORD_NOT_FOUND));
    }
}
