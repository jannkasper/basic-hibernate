package home.inheritance;

import com.home.inheritance.entity.ContractEmployee;
import com.home.inheritance.entity.Employee;
import com.home.inheritance.entity.RegularEmployee;
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

public class HierarchyTest {

    private final String EMPLOYEE_CLASS = "Employee";
    private final String CONTRACT_EMPLOYEE_CLASS = "ContractEmployee";
    private final String REGULAR_EMPLOYEE_CLASS = "RegularEmployee";

    private final String EMPLOYEE_NAME = "Gaurav Chawla";

    private final String REGULAR_EMPLOYEE_NAME = "Vivek Kumar";
    private final float REGULAR_EMPLOYEE_SALARY = 50000;
    private final int REGULAR_EMPLOYEE_BONUS = 5;

    private final String CONTRACT_EMPLOYEE_NAME = "Arjun Kumar";
    private final float CONTRACT_EMPLOYEE_PER_HOUR = 1000;
    private final String CONTRACT_EMPLOYEE_DURATION = "15 hours";


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
    public void testHierarchy() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        Employee e1 = new Employee();
        e1.setName(EMPLOYEE_NAME);

        RegularEmployee e2 = new RegularEmployee();
        e2.setName(REGULAR_EMPLOYEE_NAME);
        e2.setSalary(REGULAR_EMPLOYEE_SALARY);
        e2.setBonus(REGULAR_EMPLOYEE_BONUS);

        ContractEmployee e3 = new ContractEmployee();
        e3.setName(CONTRACT_EMPLOYEE_NAME);
        e3.setPay_per_hour(CONTRACT_EMPLOYEE_PER_HOUR);
        e3.setContract_duration(CONTRACT_EMPLOYEE_DURATION);

        session1.persist(e1);
        session1.persist(e2);
        session1.persist(e3);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from Employee");
        List<Employee> list = query.list();

        Assert.assertEquals(list.size(), 3);

        Iterator<Employee> itr=list.iterator();
        while(itr.hasNext()){
            Employee employee = itr.next();
            switch (employee.getClass().getSimpleName()) {
                case EMPLOYEE_CLASS:
                    Assert.assertEquals(employee.getName(), EMPLOYEE_NAME);
                    break;
                case REGULAR_EMPLOYEE_CLASS:
                    Assert.assertEquals(employee.getName(), REGULAR_EMPLOYEE_NAME);
                    Assert.assertEquals(((RegularEmployee)employee).getSalary(), REGULAR_EMPLOYEE_SALARY, 0);
                    Assert.assertEquals(((RegularEmployee)employee).getBonus(), REGULAR_EMPLOYEE_BONUS);
                    break;
                case CONTRACT_EMPLOYEE_CLASS:
                    Assert.assertEquals(employee.getName(), CONTRACT_EMPLOYEE_NAME);
                    Assert.assertEquals(((ContractEmployee)employee).getContract_duration(), CONTRACT_EMPLOYEE_DURATION);
                    Assert.assertEquals(((ContractEmployee)employee).getPay_per_hour(), CONTRACT_EMPLOYEE_PER_HOUR, 0);
            }
        }
        session2.close();
    }
}
