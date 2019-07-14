package com.diamond.dbconnector.controller;

import com.diamond.dbconnector.dao.DbConnectorDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
