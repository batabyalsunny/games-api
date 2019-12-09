/**
 * 
 */
package ml.bootcode.gamesapi.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ml.bootcode.gamesapi.models.Game;

/**
 * @author sunnyb
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job getJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<Game> itemReader, ItemProcessor<Game, Game> itemProcessor, ItemWriter<Game> itemWriter) {

		// Create step and define chunk size.
		Step step = stepBuilderFactory.get("load-game-step").<Game, Game>chunk(10).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();

		// Create job.
		return jobBuilderFactory.get("load-games").incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public ItemReader<Game> getItemReader(@Value("${ml.bootcode.gamesapi.inputfile}") Resource resource) {
		FlatFileItemReader<Game> flatFileItemReader = new FlatFileItemReader<>();

		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("csv-reader");
		flatFileItemReader.setLinesToSkip(1); // Skip first line
		flatFileItemReader.setLineMapper(getLineMapper());

		return flatFileItemReader;
	}

	@Bean
	public LineMapper<Game> getLineMapper() {

		DefaultLineMapper<Game> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "title", "platform", "score", "genre", "editors_choice" });

		BeanWrapperFieldSetMapper<Game> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Game.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}
}
