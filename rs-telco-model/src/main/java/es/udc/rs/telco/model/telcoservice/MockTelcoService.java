package es.udc.rs.telco.model.telcoservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sun.istack.internal.Nullable;
import es.udc.rs.telco.model.customer.Customer;
import es.udc.rs.telco.model.phonecall.PhoneCall;
import es.udc.rs.telco.model.phonecall.PhoneCallType;

public class MockTelcoService implements TelcoService {

	private static Map<Long, Customer> clientsMap = new LinkedHashMap<Long, Customer>();
	private static Map<Long,PhoneCall> phoneCallsMap = new LinkedHashMap<Long,PhoneCall>();
	private static Map<Long,List<PhoneCall>> phoneCallsByUserMap = new LinkedHashMap<Long,List<PhoneCall>>();

	private static long lastClientId = 0;
	private static long lastPhoneCallId = 0;
	

	private static synchronized long getNextClientId() {
		return ++lastClientId;
	}
	
	private static synchronized long getNextPhoneCallId() {
		return ++lastPhoneCallId;
	}


	public Customer createCustomer(Customer customer) {
		return clientsMap.put(getNextClientId(), customer);
	}

	public void removeCustomer(Long id) {
		clientsMap.remove(id);
	}

	public Customer updateCustomer(Customer customer) {
		return createCustomer(customer);
	}

	public Customer getCustomerData(Long id) {
		return new Customer(clientsMap.get(id));
	}

	public Customer searchForCustomer(String text) {
		for (Map.Entry<Long,Customer> entry : clientsMap.entrySet()) {
			if (entry.getValue().getName().contains(text))
				return entry.getValue();
		}
		return null;
	}


	public PhoneCall addPhoneCall(PhoneCall call) {
		return phoneCallsMap.put(getNextPhoneCallId(), call);
	}

	public List<PhoneCall> getPhoneCallsInMonth(Customer customer, LocalDateTime month) {
		if (month.isBefore(LocalDateTime.now())) {
			List<PhoneCall> calls = new ArrayList<>();
			List<PhoneCall> list = phoneCallsByUserMap.get(customer);

			for (PhoneCall call: list) {
				if (call.getStartDate().isAfter(month) && call.getStartDate().isBefore(month.plusMonths(1))) {
					calls.add(new PhoneCall(call));
				}
			}
			return calls;
		} else throw new NullPointerException();
	}

	public PhoneCall updatePhoneCall(PhoneCall call) {
		return addPhoneCall(call);
	}

	public List<PhoneCall> getPhoneCallsFromTo(Customer customer, LocalDateTime  begin, LocalDateTime end, @Nullable PhoneCallType type) {
		if (begin.isBefore(LocalDateTime.now())) {

			List<PhoneCall> calls = new ArrayList<>();
			List<PhoneCall> list = phoneCallsByUserMap.get(customer);

			for (PhoneCall call: list) {
				if (call.getStartDate().isAfter(begin) && call.getStartDate().isBefore(end.plusMonths(1))) {
					if (type == null || call.getPhoneCallType() == type) {
						calls.add(new PhoneCall(call));
					}
				}
			}
			return calls;
		} else throw new NullPointerException();
	}
}
