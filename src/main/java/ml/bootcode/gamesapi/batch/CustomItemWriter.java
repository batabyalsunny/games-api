/**
 * 
 */
package ml.bootcode.gamesapi.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ml.bootcode.gamesapi.models.Game;
import ml.bootcode.gamesapi.repositories.GameRepository;

/**
 * @author sunnyb
 *
 */
@Component
public class CustomItemWriter implements ItemWriter<Game> {

	@Autowired
	private GameRepository gameRepository;

	@Override
	public void write(List<? extends Game> items) throws Exception {
		gameRepository.saveAll(items);
	}

}
