package home.reference;

import com.home.reference.ManyToManyAnswer;
import com.home.reference.ManyToOneAnswer;
import com.home.reference.ManyToOneQuestion;
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

public class ManyToOneTest {

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
    public void testManyToOne() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        ManyToOneAnswer ans1 = new ManyToOneAnswer();
        ans1.setAnswerName("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        ManyToOneQuestion q1 = new ManyToOneQuestion();
        q1.setQname("What is Java?");
        q1.setAnswer(ans1);

        ManyToOneQuestion q2 = new ManyToOneQuestion();
        q2.setQname("What is Servlet?");
        q2.setAnswer(ans1);
        Set<ManyToOneQuestion> set = new HashSet<>();
        set.add(q1);
        set.add(q2);
        ans1.setQuestions(set);

        session.persist(ans1);
        session.persist(q1);
        session.persist(q2);

        t.commit();
        System.out.println("successfully saved");
        session.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from ManyToOneQuestion");
        List<ManyToOneQuestion> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<ManyToOneQuestion> itr = list.iterator();
        while(itr.hasNext()){
            ManyToOneQuestion question = itr.next();
            Assert.assertNotNull(question.getAnswer());
        }
        session2.close();

        Session session3 = sessionFactory.openSession();

        Query query2 = session3.createQuery("from ManyToOneAnswer");
        List<ManyToOneAnswer> list2 = query2.list();

        Assert.assertEquals(list2.size(), 1);

        Iterator<ManyToOneAnswer> itr2 = list2.iterator();
        while(itr2.hasNext()){
            ManyToOneAnswer question = itr2.next();
            Assert.assertEquals(question.getQuestions().size(), 2);
        }

        session3.close();
    }
}
