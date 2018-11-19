package softuni.softunigamestore.web.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import softuni.softunigamestore.domain.dtos.game.GameAddDto;
import softuni.softunigamestore.domain.dtos.user.UserLoginDto;
import softuni.softunigamestore.domain.dtos.user.UserLogoutDto;
import softuni.softunigamestore.domain.dtos.user.UserRegisterDto;
import softuni.softunigamestore.domain.entities.User;
import softuni.softunigamestore.repository.GameRepository;
import softuni.softunigamestore.service.game.GameService;
import softuni.softunigamestore.service.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {

    private String loggedInUser;
    private final UserService userService;
    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameStoreController(UserService userService, GameService gameService, GameRepository gameRepository) {
        this.userService = userService;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String inputLine = scanner.nextLine();

            String[] inputParams = inputLine.split("\\|");
            switch (inputParams[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(inputParams[1], inputParams[2], inputParams[3], inputParams[4]);

                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    if (this.loggedInUser == null) {
                        UserLoginDto userLoginDto =
                                new UserLoginDto(inputParams[1], inputParams[2]);

                        String loginResult = this.userService.loginUser(userLoginDto);

                        if (loginResult.contains("Successfully logged in")) {
                            this.loggedInUser = userLoginDto.getEmail();
                        }

                        System.out.println(loginResult);
                    } else {
                        System.out.println("Session is taken.");
                    }

                    break;
                case "Logout":
                    if (this.loggedInUser == null) {
                        System.out.println("Cannot log out. No user was logged in.");
                    } else {
                        String logoutResult = this.userService.logoutUser(new UserLogoutDto(this.loggedInUser));
                        System.out.println(logoutResult);

                        this.loggedInUser = null;
                    }
                    break;

                case "AddGame":
                    GameAddDto gameAddDto =
                            new GameAddDto(inputParams[1], new BigDecimal(inputParams[2]), Double.parseDouble(inputParams[3]), inputParams[4], inputParams[5], inputParams[6], LocalDate.parse(inputParams[7],DateTimeFormatter.ofPattern("d-M-yyyy")));
                    System.out.println(this.gameService.addGame(gameAddDto));
                    break;

                case "EditGame":
                    System.out.println(this.gameService.editGame(inputParams));
                    break;

                case "DeleteGame":
                    Long id = Long.parseLong(inputParams[1]);
                    String gameTitle = this.gameRepository.findById(id).get().getTitle();

                    this.gameRepository.deleteById(id);
                    System.out.println(String.format("Deleted %s",gameTitle));
                    break;

                case "AllGame":
                    StringBuilder sb = new StringBuilder();
                    this.gameService.getAllGamesTitlesAndPrices()
                      .forEach(game -> sb.append
                        (String.format("%s %.2f", game.getTitle(), game.getPrice())));
                    System.out.println(sb.toString());
                    break;

                case "DetailGame":
                    String gameDetails = this.gameService.getDetailGame(inputParams[1]);
                    System.out.println(gameDetails);
                    break;

                case "OwnedGame":
                    break;

            }
        }

    }
}
