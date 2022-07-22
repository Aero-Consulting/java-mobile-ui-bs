package api.services;


import api.services.clients.Auth;
import api.utils.ServiceFactory;

public interface ClientsService extends Service, Auth {

    String PATH_SERVICE = "users";

    static ClientsService create() {
        return ServiceFactory.create(ClientsService.class);
    }

}
