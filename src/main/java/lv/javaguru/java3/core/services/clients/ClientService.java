package lv.javaguru.java3.core.services.clients;

import lv.javaguru.java3.core.domain.Client;

@Deprecated
public interface ClientService {

    Client update(Long clientId,
                  String newLogin,
                  String newPassword);

    Client get(Long clientId);

}
