package ruk.web.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import ruk.domain.dtos.*;
import ruk.service.*;
import ruk.util.FileIOUtil;
import ruk.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {

    private final static String BRANCHES_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\RUK\\src\\main\\resources\\files\\json\\input\\branches.json";
    private final static String EMPLOYEES_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\RUK\\src\\main\\resources\\files\\json\\input\\employees.json";
    private final static String CLIENTS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\RUK\\src\\main\\resources\\files\\json\\input\\clients.json";
    private final static String BANK_ACCOUNTS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\RUK\\src\\main\\resources\\files\\xml\\input\\bank-accounts.xml";
    private final static String CARDS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\RUK\\src\\main\\resources\\files\\xml\\input\\cards.xml";


   private final BranchService branchService;
   private final EmployeeService employeeService;
   private final ClientService clientService;
   private final BankAccountService bankAccountService;
   private final CardService cardService;
   private final FileIOUtil fileIOUtil;
   private final Gson gson;
   private final XmlParser xmlParser;

    public AppController(BranchService branchService, EmployeeService employeeService, ClientService clientService, BankAccountService bankAccountService, CardService cardService, FileIOUtil fileIOUtil, Gson gson, XmlParser xmlParser) {
        this.branchService = branchService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
        this.fileIOUtil = fileIOUtil;
        this.gson =  gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
       //this.importBranches();
        //this.importEmployees();
        //this.importClients();
        //this.importBankAccounts();
        //this.importCards();
    }

    private void importCards() throws JAXBException, FileNotFoundException {
        CardImportRootDto cardImportRootDto =
                this.xmlParser.parseXml(CardImportRootDto.class, CARDS_FILE_PATH);

        this.cardService.importCards(cardImportRootDto);
    }

    private void importBankAccounts() throws JAXBException, FileNotFoundException {
        BankAccountImportRootDto bankAccountImportRootDto =
                this.xmlParser.parseXml(BankAccountImportRootDto.class, BANK_ACCOUNTS_FILE_PATH);

        this.bankAccountService.importBankAccounts(bankAccountImportRootDto);
    }

    private void importClients() throws IOException {
        String clientsFileContent = this.fileIOUtil.readFile(CLIENTS_FILE_PATH);

        ClientImportDto[] clientImportDtos =
                this.gson.fromJson(clientsFileContent, ClientImportDto[].class);
        this.clientService.importClients(clientImportDtos);
    }

    private void importEmployees() throws IOException {
        String employeesFileContent = this.fileIOUtil.readFile(EMPLOYEES_FILE_PATH);

        EmployeeImportDto[] employeeImportDtos =
                this.gson.fromJson(employeesFileContent, EmployeeImportDto[].class);
        this.employeeService.importEmployees(employeeImportDtos);
    }

    private void importBranches() throws IOException {
        String branchesFileContent = this.fileIOUtil.readFile(BRANCHES_FILE_PATH);

        BranchImportDto[] branchImportDtos =
                this.gson.fromJson(branchesFileContent, BranchImportDto[].class);
        this.branchService.importBranches(branchImportDtos);
    }


}
