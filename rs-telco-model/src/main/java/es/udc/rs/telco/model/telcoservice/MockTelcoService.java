package es.udc.rs.telco.model.telcoservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;
import es.udc.rs.telco.model.exceptions.MonthNotEnded;

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
		Long id = getNextClientId();
		customer.setCustomerId(id); // Almacenar id

		clientsMap.put(id, customer); // Insertar en mapa
		return new Customer(customer); // Simular regreso de BBDD??
	}

	public void removeCustomer(Long id) {
		clientsMap.remove(id);
	}

	public Customer updateCustomer(Customer customer) {
		Long id = customer.getCustomerId(); // Recuperar id

		clientsMap.put(id, customer); // Actualizar mapa
		return new Customer(customer);
	}

	public Customer getCustomerData(Long id) {
		Customer searchedCustomer = clientsMap.get(id);

		if (searchedCustomer == null)
			return null;
		else
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

	public List<PhoneCall> getPhoneCallsInMonth(Customer customer, LocalDateTime month) throws MonthNotEnded {
		if (month.isBefore(LocalDateTime.now())) {
			List<PhoneCall> calls = new ArrayList<>();
			List<PhoneCall> list = phoneCallsByUserMap.get(customer);

			for (PhoneCall call: list) {
				if (call.getStartDate().isAfter(month) && call.getStartDate().isBefore(month.plusMonths(1))) {
					calls.add(new PhoneCall(call));
				}
			}
			return calls;
		} else throw new MonthNotEnded();
	}

	public PhoneCall updatePhoneCall(PhoneCall call) {
		return addPhoneCall(call);
	}

	public List<PhoneCall> getPhoneCallsFromTo(Customer customer, LocalDateTime  begin, LocalDateTime end,
											   @Nullable PhoneCallType type) throws MonthNotEnded {
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
		} else throw new MonthNotEnded();
	}
}
