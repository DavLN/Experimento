package es.udc.rs.telco.test.model.telcoservice;

import com.sun.istack.internal.Nullable;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TelcoServiceTest {

    private static TelcoService telcoService = null;

    @BeforeAll
    public static void init() {

        telcoService = TelcoServiceFactory.getService();

    }

    @Test
    public void testAddCustomerAndFindCustomer()  {
        assertTrue(telcoService != null);
    }














}
