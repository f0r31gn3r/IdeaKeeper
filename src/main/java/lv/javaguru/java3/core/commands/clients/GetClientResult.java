package lv.javaguru.java3.core.commands.clients;

import lv.javaguru.java3.core.commands.DomainCommandResult;
import lv.javaguru.java3.core.dto.ClientDTO;

@Deprecated
public class GetClientResult implements DomainCommandResult {

    private ClientDTO client;

    public GetClientResult(ClientDTO client) {
        this.client = client;
    }

    public ClientDTO getClient() {
        return client;
    }

}
