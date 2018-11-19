package softuni.softunigamestore.service.game;

import softuni.softunigamestore.domain.dtos.game.GameAddDto;
import softuni.softunigamestore.domain.dtos.game.GameEditDto;
import softuni.softunigamestore.domain.dtos.view.GameTitleAndPriceViewDto;

import java.util.Set;

public interface GameService {
    String addGame(GameAddDto gameAddDto);

    String editGame(String[] tokens);

    void update(Long id, GameEditDto gameEditDto);

    String delete(Long id);

    Set<GameTitleAndPriceViewDto> getAllGamesTitlesAndPrices();

    String getDetailGame(String title);
}
