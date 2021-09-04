package home.inheritance;

import com.home.inheritance.JobContract;
import com.home.inheritance.Job;
import com.home.inheritance.JobRegular;
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

import java.util.Iterator;
import java.util.List;

public class ConcreteTest {

    private final String JOB_CLASS = "Job";
    private final String CONTRACT_JOB_CLASS = "ContractJob";
    private final String REGULAR_JOB_CLASS = "RegularJob";

    private final String JOB_NAME = "Software Engineer";

    private final String REGULAR_JOB_NAME = "Develop Engineer";
    private final float REGULAR_JOB_SALARY = 50000;
    private final int REGULAR_JOB_BONUS = 5;

    private final String CONTRACT_JOB_NAME = "Architect";
    private final float CONTRACT_JOB_PER_HOUR = 1000;
    private final String CONTRACT_JOB_DURATION = "15 hours";


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
    public void testConcrete() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        Job j1 = new Job();
        j1.setName(JOB_NAME);

        JobRegular j2 = new JobRegular();
        j2.setName(REGULAR_JOB_NAME);
        j2.setSalary(REGULAR_JOB_SALARY);
        j2.setBonus(REGULAR_JOB_BONUS);

        JobContract j3 = new JobContract();
        j3.setName(CONTRACT_JOB_NAME);
        j3.setPay_per_hour(CONTRACT_JOB_PER_HOUR);
        j3.setContract_duration(CONTRACT_JOB_DURATION);

        session1.persist(j1);
        session1.persist(j2);
        session1.persist(j3);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from Job");
        List<Job> list = query.list();

        Assert.assertEquals(list.size(), 3);

        Iterator<Job> itr=list.iterator();
        while(itr.hasNext()){
            Job employee = itr.next();
            switch (employee.getClass().getSimpleName()) {
                case JOB_CLASS:
                    Assert.assertEquals(employee.getName(), JOB_NAME);
                    break;
                case REGULAR_JOB_CLASS:
                    Assert.assertEquals(employee.getName(), REGULAR_JOB_NAME);
                    Assert.assertEquals(((JobRegular)employee).getSalary(), REGULAR_JOB_SALARY, 0);
                    Assert.assertEquals(((JobRegular)employee).getBonus(), REGULAR_JOB_BONUS);
                    break;
                case CONTRACT_JOB_CLASS:
                    Assert.assertEquals(employee.getName(), CONTRACT_JOB_NAME);
                    Assert.assertEquals(((JobContract)employee).getContract_duration(), CONTRACT_JOB_DURATION);
                    Assert.assertEquals(((JobContract)employee).getPay_per_hour(), CONTRACT_JOB_PER_HOUR, 0);
            }
        }
        session2.close();
    }
}
