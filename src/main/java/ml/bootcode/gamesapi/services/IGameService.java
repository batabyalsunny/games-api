/**
 * 
 */
package ml.bootcode.gamesapi.services;

import java.util.List;

import ml.bootcode.gamesapi.models.Game;

/**
 * @author sunnyb
 *
 */
public interface IGameService {

	List<Game> getAll();

	Game getGame(Long id);

	Game addGame(Game game);

	Game updateGame(Long id, Game game);

	void deleteGame(Long id);

	List<Game> searchByName(String name);

	List<Game> getByGenre(String genre);

	List<Game> getByEditorsChoice(String choice);

	List<Game> getByPlatform(String platform);
}
