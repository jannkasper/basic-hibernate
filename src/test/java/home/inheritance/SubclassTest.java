package home.inheritance;

import com.home.inheritance.BookContract;
import com.home.inheritance.Book;
import com.home.inheritance.BookRegular;
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

public class SubclassTest {

    private final String BOOK_CLASS = "Book";
    private final String CONTRACT_BOOK_CLASS = "ContractBook";
    private final String REGULAR_BOOK_CLASS = "RegularBook";

    private final String BOOK_NAME = "Document";

    private final String REGULAR_BOOK_NAME = "Travel";
    private final float REGULAR_BOOK_SALARY = 50000;
    private final int REGULAR_BOOK_BONUS = 5;

    private final String CONTRACT_BOOK_NAME = "Contract";
    private final float CONTRACT_BOOK_PER_HOUR = 1000;
    private final String CONTRACT_BOOK_DURATION = "15 hours";


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
    public void testSubclass() {
        Session session1 = sessionFactory.openSession();
        Transaction t = session1.beginTransaction();

        Book b1 = new Book();
        b1.setName(BOOK_NAME);

        BookRegular b2 = new BookRegular();
        b2.setName(REGULAR_BOOK_NAME);
        b2.setSalary(REGULAR_BOOK_SALARY);
        b2.setBonus(REGULAR_BOOK_BONUS);

        BookContract b3 = new BookContract();
        b3.setName(CONTRACT_BOOK_NAME);
        b3.setPay_per_hour(CONTRACT_BOOK_PER_HOUR);
        b3.setContract_duration(CONTRACT_BOOK_DURATION);

        session1.persist(b1);
        session1.persist(b2);
        session1.persist(b3);
        t.commit();
        System.out.println("successfully saved");
        session1.close();


        Session session2 = sessionFactory.openSession();

        Query query = session2.createQuery("from Book");
        List<Book> list = query.list();

        Assert.assertEquals(list.size(), 3);

        Iterator<Book> itr=list.iterator();
        while(itr.hasNext()){
            Book employee = itr.next();
            switch (employee.getClass().getSimpleName()) {
                case BOOK_CLASS:
                    Assert.assertEquals(employee.getName(), BOOK_NAME);
                    break;
                case REGULAR_BOOK_CLASS:
                    Assert.assertEquals(employee.getName(), REGULAR_BOOK_NAME);
                    Assert.assertEquals(((BookRegular)employee).getSalary(), REGULAR_BOOK_SALARY, 0);
                    Assert.assertEquals(((BookRegular)employee).getBonus(), REGULAR_BOOK_BONUS);
                    break;
                case CONTRACT_BOOK_CLASS:
                    Assert.assertEquals(employee.getName(), CONTRACT_BOOK_NAME);
                    Assert.assertEquals(((BookContract)employee).getContract_duration(), CONTRACT_BOOK_DURATION);
                    Assert.assertEquals(((BookContract)employee).getPay_per_hour(), CONTRACT_BOOK_PER_HOUR, 0);
            }
        }
        session2.close();
    }
}
