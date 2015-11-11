package lv.javaguru.java3.core.commands.clients;

import lv.javaguru.java3.core.domain.Client;
import lv.javaguru.java3.core.dto.ClientDTO;
import org.springframework.stereotype.Component;

import static lv.javaguru.java3.core.dto.ClientDTOBuilder.createClientDTO;

@Component
public class ClientConverter {

    public ClientDTO convert(Client client) {
        return createClientDTO()
                .withId(client.getId())
                .withLogin(client.getLogin())
                .withPassword(client.getPassword()).build();
    }


}
