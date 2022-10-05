package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.DocumentDao;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.util.TransactionUtil;
import com.sismics.docs.core.util.authentication.InternalAuthenticationHandler;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the persistance layer.
 * 
 * @author jtremeaux
 */
public class TestJpa extends BaseTransactionalTest {
    @Test
    public void testJpa() throws Exception {
        // Create a user
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("username");
        user.setPassword("12345678");
        user.setEmail("toto@docs.com");
        user.setRoleId("admin");
        user.setStorageQuota(10L);
        String id = userDao.create(user, "me");
        
        TransactionUtil.commit();

        // Search a user by his ID
        user = userDao.getById(id);
        Assert.assertNotNull(user);
        Assert.assertEquals("toto@docs.com", user.getEmail());

        // Authenticate using the database
        Assert.assertNotNull(new InternalAuthenticationHandler().authenticate("username", "12345678"));

        // Create a document
        DocumentDao documentDao = new DocumentDao();
        Document document = new Document();
        document.setUserId(id);
        document.setLanguage("en");
        document.setTitle("Test Document");
        document.setSkills(0);
        document.setExperience(0);
        document.setGpa(0);
        document.setScores(0);
        document.setCreateDate(new Date());
        String documentId = documentDao.create(document, id);

        TransactionUtil.commit();
        
        document = documentDao.getById(documentId);
        Assert.assertNotNull(document);
        Assert.assertEquals(0, document.getSkills());
        Assert.assertEquals(0, document.getExperience());
        Assert.assertEquals(0, document.getGpa());
        Assert.assertEquals(0, document.getScores());
    }
}
