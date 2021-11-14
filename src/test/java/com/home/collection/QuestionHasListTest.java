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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionHasListTest {

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
    public void testList() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        ArrayList<String> list1=new ArrayList<String>();
        list1.add("Java is a programming language");
        list1.add("Java is a platform");

        ArrayList<String> list2=new ArrayList<String>();
        list2.add("Servlet is an Interface");
        list2.add("Servlet is an API");

        QuestionHasList question1 = new QuestionHasList();
        question1.setQname("What is Java?");
        question1.setAnswers(list1);

        QuestionHasList question2 = new QuestionHasList();
        question2.setQname("What is Servlet?");
        question2.setAnswers(list2);

        session1.persist(question1);
        session1.persist(question2);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from QuestionHasList");
        List<QuestionHasList> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<QuestionHasList> itr = list.iterator();
        while(itr.hasNext()){
            QuestionHasList question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();
    }
}
