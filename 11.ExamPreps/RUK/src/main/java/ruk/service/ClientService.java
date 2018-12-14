package ruk.service;

import ruk.domain.dtos.ClientImportDto;

public interface ClientService {

    String importClients(ClientImportDto[] clientImportDtos);
}
