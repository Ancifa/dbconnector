package com.diamond.dbconnector.dao;

import com.diamond.dbconnector.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
public class DbConnectorDAOTest {

    @Autowired
    private DbConnectorDAO dbConnectorDAO;

    @Test
    public void testGetEmailsListByIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<String> emailsListByIds = dbConnectorDAO.getEmailsListByIds(ids);

        System.out.println(emailsListByIds.toString());
        Assert.assertFalse(emailsListByIds.isEmpty());
    }
}