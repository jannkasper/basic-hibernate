package home.collection;

import com.home.collection.QuestionHasBag;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionHasBagTest {

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
    public void testBag() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        ArrayList<String> list1=new ArrayList<String>();
        list1.add("Java is a programming language");
        list1.add("Java is a platform");

        ArrayList<String> list2=new ArrayList<String>();
        list2.add("Servlet is an Interface");
        list2.add("Servlet is an API");

        QuestionHasBag question1 = new QuestionHasBag();
        question1.setQname("What is Java?");
        question1.setAnswers(list1);

        QuestionHasBag question2 = new QuestionHasBag();
        question2.setQname("What is Servlet?");
        question2.setAnswers(list2);

        session1.persist(question1);
        session1.persist(question2);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from QuestionHasBag");
        List<QuestionHasBag> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<QuestionHasBag> itr = list.iterator();
        while(itr.hasNext()){
            QuestionHasBag question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();
    }
}
