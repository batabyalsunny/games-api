package ml.bootcode.gamesapi.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import ml.bootcode.gamesapi.models.Game;

/**
 * 
 * @author sunnyb
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<Game, Game> {

	@Override
	public Game process(Game item) throws Exception {
		return item;
	}
}
