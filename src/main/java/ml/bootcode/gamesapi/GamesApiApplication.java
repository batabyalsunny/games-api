package ml.bootcode.gamesapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesApiApplication.class, args);
	}

	/**
	 * Runs the batch job to load data.
	 * 
	 * @param categoryRepository
	 * @param productRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner loadData(JobLauncher jobLauncher, Job job) {

		return args -> {

			// Just putting current timestamp.
			Map<String, JobParameter> map = new HashMap<>();
			map.put("time", new JobParameter(System.currentTimeMillis()));

			JobParameters jobParameters = new JobParameters(map);

			// Run the jobs.
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);

			// Print status.
			System.out.println(jobExecution.getStatus());
		};
	}
}
