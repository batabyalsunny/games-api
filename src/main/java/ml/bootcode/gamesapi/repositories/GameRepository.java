/**
 * 
 */
package ml.bootcode.gamesapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ml.bootcode.gamesapi.models.Game;

/**
 * @author sunnyb
 *
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByTitleContaining(String title);

	List<Game> findByGenre(String genre);

	List<Game> findByEditorsChoice(String editorsChoice);

	List<Game> findByPlatform(String platform);
}
