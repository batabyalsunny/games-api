/**
 * 
 */
package ml.bootcode.gamesapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ml.bootcode.gamesapi.models.Game;
import ml.bootcode.gamesapi.services.IGameService;

/**
 * @author sunnyb
 *
 */
@RestController
@RequestMapping("games")
public class GameController {

	private IGameService gameService;

	/**
	 * @param gameService
	 */
	public GameController(IGameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping
	public List<Game> getGames() {
		return gameService.getAll();
	}

	@GetMapping("{id}")
	public Game getGames(@PathVariable Long id) {
		return gameService.getGame(id);
	}

	@PostMapping
	public Game addGame(@RequestBody Game game) {
		return gameService.addGame(game);
	}

	@PutMapping("{id}")
	public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
		return gameService.updateGame(id, game);
	}

	@DeleteMapping("{id}")
	public void deleteGame(@PathVariable Long id) {
		gameService.deleteGame(id);
	}

	@GetMapping("search/{name}")
	public List<Game> searchByName(@PathVariable String name) {
		return gameService.searchByName(name);
	}

	@GetMapping("genre/{genre}")
	public List<Game> getByGenre(@PathVariable String genre) {
		return gameService.getByGenre(genre);
	}

	@GetMapping("editors-choice/{choice}")
	public List<Game> getByEditorsChoice(@PathVariable String choice) {
		return gameService.getByEditorsChoice(choice);
	}

	@GetMapping("platform/{platform}")
	public List<Game> getByPlatform(@PathVariable String platform) {
		return gameService.getByPlatform(platform);
	}
}
