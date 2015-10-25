package lv.javaguru.java3.core.services.clients.handlers;

import lv.javaguru.java3.core.commands.clients.CreateClientCommand;
import lv.javaguru.java3.core.commands.clients.CreateClientResult;
import lv.javaguru.java3.core.domain.Client;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.clients.ClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateClientCommandHandler
		implements DomainCommandHandler<CreateClientCommand, CreateClientResult> {

	@Autowired
	private ClientFactory clientFactory;


	@Override
	public CreateClientResult execute(CreateClientCommand command) {
		Client client = clientFactory.create(
				command.getLogin(),
				command.getPassword()
		);
		return new CreateClientResult(client);
	}

	@Override
	public Class getCommandType() {
		return CreateClientCommand.class;
	}
	
}
