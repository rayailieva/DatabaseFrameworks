package softuni.softunigamestore.service.game;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.softunigamestore.domain.dtos.game.GameAddDto;
import softuni.softunigamestore.domain.dtos.game.GameEditDto;
import softuni.softunigamestore.domain.dtos.view.GameTitleAndPriceViewDto;
import softuni.softunigamestore.domain.entities.Game;
import softuni.softunigamestore.repository.GameRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(GameAddDto gameAddDto) {
        javax.validation.Validator validator =  Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        StringBuilder sb = new StringBuilder();

        if(validator.validate(gameAddDto).size() > 0){
            for (ConstraintViolation<GameAddDto> violation : validator.validate(gameAddDto)) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        }else{
            Game entity = this.modelMapper.map(gameAddDto, Game.class);
            this.gameRepository.saveAndFlush(entity);
            System.out.println("Added " + entity.getTitle());
        }

        return sb.toString().trim();
    }

    @Override
    public String editGame(String[] tokens) {

        javax.validation.Validator validator =  Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

       StringBuilder sb = new StringBuilder();
       Long id = Long.parseLong(tokens[1]);

       Game game = this.gameRepository.findById(id).orElse(null);

       if(game == null){
           return "Game not found!";
       }

       GameEditDto gameToEdit = modelMapper.map(game, GameEditDto.class);
       gameToEdit.setId(id);

        for (int i = 2; i < tokens.length; i++) {
            String[] propertiesElements = tokens[i].split("=");
            String propertyName = propertiesElements[0];
            String propertyKey = propertiesElements[1];
            switch (propertyName) {
                case "title":
                    return "Title cannot be edited!";
                case "price":
                    gameToEdit.setPrice(new BigDecimal(propertyKey));
                    break;
                case "size":
                    gameToEdit.setSize(Double.parseDouble(propertyKey));
                    break;
                case "trailer":
                    gameToEdit.setTrailer(propertyKey);
                    break;
                case "thumbnailUrl":
                    gameToEdit.setImageThumbnail(propertyKey);
                    break;
                case "description":
                    gameToEdit.setDescription(propertyKey);
                    break;
            }
        }
        Set<ConstraintViolation<GameEditDto>> violations = validator.validate(gameToEdit);

        if (violations.size() > 0) {
            for (ConstraintViolation<GameEditDto> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
            return sb.toString().trim();
        } else {
            Game editedGame = this.modelMapper.map(gameToEdit, Game.class);
            if (editedGame != null) {
                Game savedGame = this.gameRepository.saveAndFlush(editedGame);
                if (savedGame != null) {
                    return String.format("Edited %s", savedGame.getTitle());
                }
            }
        }

        return null;
    }

    @Override
    public void update(Long id, GameEditDto gameEditDto) {
        Game game = modelMapper.map(gameEditDto, Game.class);
        game.setId(id);
        this.gameRepository.save(game);
    }

    @Override
    public String delete(Long id) {
        String gameTitle = this.gameRepository.findById(id).get().getTitle();
        this.gameRepository.deleteById(id);
        return String.format("Deleted %s",gameTitle);
    }

    @Override
    public Set<GameTitleAndPriceViewDto> getAllGamesTitlesAndPrices() {

        return this.gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameTitleAndPriceViewDto.class))
                .collect(Collectors.toSet());

    }

    @Override
    public String getDetailGame(String title) {
        Game game = this.gameRepository.findByTitle(title);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Title: %s%n", game.getTitle()));
        sb.append(String.format("Price: %.2f%n", game.getPrice()));
        sb.append(String.format("Description: %s%n", game.getDescription()));
        sb.append(String.format("Release date: %s", game.getReleaseDate()));

        return sb.toString();
    }
}
