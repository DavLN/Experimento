package es.udc.rs.telco.model.telcoservice;

import com.sun.istack.internal.Nullable;
import es.udc.rs.telco.model.customer.Customer;
import es.udc.rs.telco.model.phonecall.PhoneCall;
import es.udc.rs.telco.model.phonecall.PhoneCallStatus;
import es.udc.rs.telco.model.phonecall.PhoneCallType;

import java.time.LocalDateTime;
import java.util.List;

public interface TelcoService {

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void removeCustomer(Long id);
    Customer getCustomerData(Long id);
    Customer searchForCustomer(String text);
    PhoneCall addPhoneCall(PhoneCall call);
    List<PhoneCall> getPhoneCallsInMonth(Customer customer, LocalDateTime month);
    PhoneCall updatePhoneCall(PhoneCall call);
    List<PhoneCall> getPhoneCallsFromTo(Customer customer, LocalDateTime  begin, LocalDateTime end, @Nullable PhoneCallType type);
}
