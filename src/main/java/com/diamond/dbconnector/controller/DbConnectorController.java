package com.diamond.dbconnector.controller;

import com.diamond.dbconnector.dao.DbConnectorDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DbConnectorController {

    private DbConnectorDAO connectorDAO;

    public DbConnectorController(DbConnectorDAO connectorDAO) {
        this.connectorDAO = connectorDAO;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping("/get-email")
    public String getEmail(@RequestParam(value = "param", defaultValue = "defaultValue") String param) {
        return connectorDAO.getEmail(Integer.parseInt(param));
    }

    @RequestMapping("/get-email-row")
    public String getEmailRow(@RequestParam(value = "param", defaultValue = "defaultValue") String param) {
        return connectorDAO.getEmailRow(Integer.parseInt(param)).toString();
    }

    @RequestMapping("/get-listing")
    public String getListing(@RequestParam(value = "listingId") Long listingId) {
        return connectorDAO.getListing(listingId);
    }

    @RequestMapping("/get-emails")
    public ResponseEntity<List<String>> getEmailsList(@RequestBody List<Long> ids) {
        List<String> emailsListByIds = connectorDAO.getEmailsListByIds(ids);

        return emailsListByIds.isEmpty()
                ? ResponseEntity.status(HttpStatus.CONFLICT).build()
                : ResponseEntity.ok(emailsListByIds);
    }
}
