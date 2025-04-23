package com.gois.lotostatic;

import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.repository.SorterRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ExcelJobConfig {

    @Bean
    public Job importarExcelJob(JobRepository jobRepository, Step importarStep) {
        return new JobBuilder("importarExcelJob", jobRepository)
                .start(importarStep)
                .build();
    }

    @Bean
    public Step importarStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                             SorterRepository repository) throws Exception {

        String url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/resultados/download?modalidade=Mega-Sena";
        String localPath = "Mega-Sena_temp.xlsx";
        ExcelDownloader.downloadExcel(url, localPath);

        ExcelItemReader reader = new ExcelItemReader(localPath);

        return new StepBuilder("importarStep", jobRepository)
                .<Sorter, Sorter>chunk(10, transactionManager)
                .reader(reader)
                .writer(repository::saveAll)
                .build();
    }

}
