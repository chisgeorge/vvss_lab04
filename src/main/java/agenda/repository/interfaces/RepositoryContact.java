package agenda.repository.interfaces;

import java.util.List;

import agenda.model.Contact;

public interface RepositoryContact {

	List<Contact> getContacts();
	void addContact(Contact contact);
	boolean removeContact(Contact contact);
	boolean saveContracts();
	int count();
	Contact getByName(String string);
}
