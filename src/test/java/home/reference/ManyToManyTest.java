package home.reference;

import com.home.reference.ManyToManyAnswer;
import com.home.reference.ManyToManyQuestion;
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

public class ManyToManyTest {

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
    public void testManyToMany() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        ManyToManyAnswer ans1 = new ManyToManyAnswer();
        ans1.setAnswerName("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        ManyToManyAnswer ans2 = new ManyToManyAnswer();
        ans2.setAnswerName("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        ManyToManyQuestion q1 = new ManyToManyQuestion();
        q1.setQname("What is Java?");
        Set<ManyToManyAnswer> l1 = new HashSet<>();
        l1.add(ans1);
        l1.add(ans2);
        q1.setAnswers(l1);

        ManyToManyQuestion q2 = new ManyToManyQuestion();
        q2.setQname("What is Servlet?");
        Set<ManyToManyAnswer> l2 = new HashSet<>();
        l2.add(ans1);
        l2.add(ans2);
        q2.setAnswers(l2);
        session.persist(q1);
        session.persist(q2);

        t.commit();
        System.out.println("successfully saved");
        session.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from ManyToManyQuestion");
        List<ManyToManyQuestion> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<ManyToManyQuestion> itr = list.iterator();
        while(itr.hasNext()){
            ManyToManyQuestion question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();

        Session session3 = sessionFactory.openSession();

        Query query2 = session3.createQuery("from ManyToManyAnswer");
        List<ManyToManyAnswer> list2 = query2.list();

        Assert.assertEquals(list2.size(), 2);

        Iterator<ManyToManyAnswer> itr2 = list2.iterator();
        while(itr2.hasNext()){
            ManyToManyAnswer question = itr2.next();
            Assert.assertEquals(question.getQuestions().size(), 2);
        }
        session3.close();
    }
}
