/**
 * 
 */
package ml.bootcode.gamesapi.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ml.bootcode.gamesapi.exceptions.ApiException;
import ml.bootcode.gamesapi.models.Game;
import ml.bootcode.gamesapi.repositories.GameRepository;
import ml.bootcode.gamesapi.services.IGameService;

/**
 * @author sunnyb
 *
 */
@Service
public class GameServiceImpl implements IGameService {

	private GameRepository gameRepository;

	/**
	 * @param gameRepository
	 */
	public GameServiceImpl(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public List<Game> getAll() {
		return gameRepository.findAll();
	}

	@Override
	public Game getGame(Long id) {
		return validateGame(id);
	}

	@Override
	public Game addGame(Game game) {
		return gameRepository.save(game);
	}

	@Override
	public Game updateGame(Long id, Game game) {
		validateGame(id);
		game.setId(id);
		return gameRepository.save(game);
	}

	@Override
	public void deleteGame(Long id) {
		Game game = validateGame(id);
		gameRepository.delete(game);
	}

	@Override
	public List<Game> searchByName(String name) {
		return gameRepository.findByTitleContaining(name);
	}

	@Override
	public List<Game> getByGenre(String genre) {
		return gameRepository.findByGenre(genre);
	}

	@Override
	public List<Game> getByEditorsChoice(String choice) {
		return gameRepository.findByEditorsChoice(choice);
	}

	@Override
	public List<Game> getByPlatform(String platform) {
		return gameRepository.findByPlatform(platform);
	}

	/**
	 * Validator for game by id.
	 * 
	 * @param id
	 * @return
	 */
	public Game validateGame(Long id) {
		Optional<Game> gameOptional = gameRepository.findById(id);

		if (!gameOptional.isPresent()) {
			throw new ApiException("Requested resource not found.", HttpStatus.NOT_FOUND);
		}

		return gameOptional.get();
	}
}
