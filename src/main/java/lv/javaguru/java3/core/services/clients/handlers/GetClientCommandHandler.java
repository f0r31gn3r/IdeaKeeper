package lv.javaguru.java3.core.services.clients.handlers;

import lv.javaguru.java3.core.commands.clients.GetClientCommand;
import lv.javaguru.java3.core.commands.clients.GetClientResult;
import lv.javaguru.java3.core.domain.Client;
import lv.javaguru.java3.core.services.DomainCommandHandler;
import lv.javaguru.java3.core.services.clients.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GetClientCommandHandler
        implements DomainCommandHandler<GetClientCommand, GetClientResult> {

    @Autowired
    private ClientService clientService;


    @Override
    public GetClientResult execute(GetClientCommand command) {
        Client client = clientService.get(command.getClientId());
        return new GetClientResult(client);
    }

    @Override
    public Class getCommandType() {
        return GetClientCommand.class;
    }

}
