package com.home.collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionHasSetTest {

    private static SessionFactory sessionFactory = null;

    @BeforeAll
    public static void setUp() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = meta.getSessionFactoryBuilder().build();
    }

    @AfterAll
    public static void tearDown() {
        sessionFactory.close();
    }

    @Test
    public void testSet() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        Set<String> set1 = new HashSet<String>();
        set1.add("Java is a programming language");
        set1.add("Java is a platform");

        Set<String> set2 = new HashSet<String>();
        set2.add("Servlet is an Interface");
        set2.add("Servlet is an API");

        QuestionHasSet question1 = new QuestionHasSet();
        question1.setQname("What is Java?");
        question1.setAnswers(set1);

        QuestionHasSet question2 = new QuestionHasSet();
        question2.setQname("What is Servlet?");
        question2.setAnswers(set2);

        session1.persist(question1);
        session1.persist(question2);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from QuestionHasSet");
        List<QuestionHasSet> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<QuestionHasSet> itr = list.iterator();
        while(itr.hasNext()){
            QuestionHasSet question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();
    }
}
