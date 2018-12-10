package ruk.service;

import ruk.domain.dtos.ClientImportDto;

public interface ClientService {

    void importClients(ClientImportDto[] clientImportDtos);
}
