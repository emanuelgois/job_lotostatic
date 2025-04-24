package com.gois.lotostatic;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class LotostaticApplicationTest {



	@Mock
	private JobLauncher jobLauncher;

	@Mock
	private Job importarExcelJob;

	@InjectMocks
	private LotostaticApplication application;

	@Test
	void testJobIsLaunched() throws Exception {
		// Arrange
		when(importarExcelJob.getName()).thenReturn("importarExcelJob");
		when(jobLauncher.run(eq(importarExcelJob), any(JobParameters.class)))
				.thenReturn(mock(JobExecution.class));

		// Act
		application.run();

		// Assert
		verify(jobLauncher, times(1)).run(eq(importarExcelJob), any(JobParameters.class));
	}
}