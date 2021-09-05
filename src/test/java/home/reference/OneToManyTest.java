package home.reference;

import com.home.reference.OneToManyAnswer;
import com.home.reference.OneToManyQuestion;
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

public class OneToManyTest {

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
    public void testOneToMany() {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        OneToManyAnswer ans1 = new OneToManyAnswer();
        ans1.setAnswerName("Java is a programming language");
        ans1.setPostedBy("Ravi Malik");

        OneToManyAnswer ans2 = new OneToManyAnswer();
        ans2.setAnswerName("Java is a platform");
        ans2.setPostedBy("Sudhir Kumar");

        OneToManyAnswer ans3 = new OneToManyAnswer();
        ans3.setAnswerName("Servlet is an Interface");
        ans3.setPostedBy("Jai Kumar");

        OneToManyAnswer ans4 = new OneToManyAnswer();
        ans4.setAnswerName("Servlet is an API");
        ans4.setPostedBy("Arun");

        ArrayList<OneToManyAnswer> list1 = new ArrayList<>();
        list1.add(ans1);
        list1.add(ans2);

        ArrayList<OneToManyAnswer> list2 = new ArrayList<>();
        list2.add(ans3);
        list2.add(ans4);

        OneToManyQuestion question1 = new OneToManyQuestion();
        question1.setQname("What is Java?");
        question1.setAnswers(list1);

        OneToManyQuestion question2 = new OneToManyQuestion();
        question2.setQname("What is Servlet?");
        question2.setAnswers(list2);

        session.persist(question1);
        session.persist(question2);
        
        t.commit();
        System.out.println("successfully saved");
        session.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from OneToManyQuestion");
        List<OneToManyQuestion> list = query.list();

        Assert.assertEquals(list.size(), 2);

        Iterator<OneToManyQuestion> itr = list.iterator();
        while(itr.hasNext()){
            OneToManyQuestion question = itr.next();
            Assert.assertEquals(question.getAnswers().size(), 2);
        }
        session2.close();
    }
}
