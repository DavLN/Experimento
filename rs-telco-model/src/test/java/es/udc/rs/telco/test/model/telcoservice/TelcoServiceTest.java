package es.udc.rs.telco.test.model.telcoservice;

import es.udc.rs.telco.model.customer.Customer;
import es.udc.rs.telco.model.phonecall.PhoneCallStatus;
import es.udc.rs.telco.model.phonecall.PhoneCallType;
import es.udc.rs.telco.model.telcoservice.TelcoService;
import es.udc.rs.telco.model.telcoservice.TelcoServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.ws.util.sql.DataSourceLocator;
import es.udc.ws.util.sql.SimpleDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TelcoServiceTest {

    private static TelcoService telcoService = null;
    private Customer c1 = new Customer("c1","11111111X","Direccion A", "666123456"); // Validator -> dni & direction?

    @BeforeAll
    public static void init() {

        telcoService = TelcoServiceFactory.getService();
    }

    @Test
    public void testAddCustomerAndFindCustomer()  {
        assertNotNull(telcoService);

        Customer newClient = telcoService.createCustomer(c1);

        assertEquals(c1, newClient);
        assertEquals(c1,telcoService.getCustomerData(1L)); // Adaptar a ids de BBDD?
    }


    @Test
    public void testRemoveCustomer() {
        assertNotNull(telcoService);

        Customer deletedClient = null;

        telcoService.createCustomer(c1);

        telcoService.removeCustomer(1L);
        deletedClient = telcoService.getCustomerData(1L);

        assertNull(deletedClient);

    }

    /*
    @Test
    public void testUpdateCustomer() {

    }

    @Test
    public void testsearchForCustomer() {

    }

    @Test
    public void testaddPhoneCall() {

    }

    @Test
    public void testgetPhoneCallsInMonth() {

    }

    @Test
    public void testupdatePhoneCall() {

    }

    @Test
    public void testgetPhoneCallFromTo() {

    }*/













}
