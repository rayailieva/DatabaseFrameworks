package ruk.services;

import ruk.domain.dtos.ClientImportDto;

public interface ClientService {

    void importClients(ClientImportDto[] clientImportDtos);
}
