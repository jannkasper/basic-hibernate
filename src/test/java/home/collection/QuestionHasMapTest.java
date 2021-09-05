package home.collection;

import com.home.collection.QuestionHasMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class QuestionHasMapTest {

    private static SessionFactory sessionFactory = null;

    @BeforeClass
    public static void setUp() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = meta.getSessionFactoryBuilder().build();
    }

    @AfterClass
    public static void tearDown() {
        sessionFactory.close();
    }

    @Test
    public void testMap() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        Map<String, String> map1 = new HashMap<>();
        map1.put("Java is a programming language", "John Milton");
        map1.put("Java is a platform", "Ashok Kumar");

        Map<String, String> map2 = new HashMap<>();
        map2.put("Servlet is an Interface", "Ashok Kumar");
        map2.put("Servlet is a package", "Rahul Kumar");

        QuestionHasMap question1 = new QuestionHasMap();
        question1.setQname("What is Java?");
        question1.setAnswers(map1);

        QuestionHasMap question2 = new QuestionHasMap();
        question2.setQname("What is Servlet?");
        question2.setAnswers(map2);

        session1.persist(question1);
        session1.persist(question2);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from QuestionHasMap");
        List<QuestionHasMap> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<QuestionHasMap> itr = list.iterator();
        while(itr.hasNext()){
            QuestionHasMap question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();
    }
}
