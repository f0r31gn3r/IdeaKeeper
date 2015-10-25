package lv.javaguru.java3.core.commands.clients;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.domain.Client;

public class CreateClientResult implements DomainCommandResult {

	private Client client;

	public CreateClientResult(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

}
