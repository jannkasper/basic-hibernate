package home.reference;

import com.home.reference.OneToOneAnswer;
import com.home.reference.OneToOneQuestion;
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

public class OneToOneTest {

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
    public void testOneToOne() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        OneToOneAnswer ans1 = new OneToOneAnswer();
        ans1.setAnswerName("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        OneToOneQuestion q1 = new OneToOneQuestion();
        q1.setQname("What is Java?");
        q1.setAnswer(ans1);
        ans1.setQuestion(q1);

        OneToOneAnswer ans2 = new OneToOneAnswer();
        ans2.setAnswerName("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        OneToOneQuestion q2 = new OneToOneQuestion();
        q2.setQname("What is Servlet?");
        q2.setAnswer(ans2);
        ans2.setQuestion(q2);


        session.persist(q1);
        session.persist(q2);

        t.commit();
        System.out.println("successfully saved");
        session.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from OneToOneQuestion");
        List<OneToOneQuestion> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<OneToOneQuestion> itr = list.iterator();
        while(itr.hasNext()){
            OneToOneQuestion question = itr.next();
            Assert.assertNotNull(question.getAnswer());
        }
        session2.close();

        Session session3 = sessionFactory.openSession();

        Query query2 = session3.createQuery("from OneToOneAnswer");
        List<OneToOneAnswer> list2 = query2.list();

        Assert.assertEquals(list2.size(), 2);

        Iterator<OneToOneAnswer> itr2 = list2.iterator();
        while(itr2.hasNext()){
            OneToOneAnswer question = itr2.next();
            Assert.assertNotNull(question.getQuestion());
        }
        session3.close();
    }
}
