package com.diamond.dbconnector.dao;

import com.diamond.dbconnector.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DbConnectorDAO {
    private static final String RECORD_NOT_FOUND = "Record not found.";
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

    public String getListing(Long listingId) {
        String query = "SELECT COUNT(*) FROM listing WHERE yp_id = ?";
        String result = RECORD_NOT_FOUND;
        try {
            result = jdbcTemplate.queryForObject(query, String.class, listingId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public List<String> getEmailsListByIds(List<Long> ids) {
        String query = "select email from email where id in (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);

        return namedParameterJdbcTemplate.queryForList(query, parameters, String.class);
    }
}
