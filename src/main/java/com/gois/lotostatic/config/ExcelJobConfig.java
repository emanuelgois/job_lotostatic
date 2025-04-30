package com.gois.lotostatic.config;

import com.gois.lotostatic.mapper.SorterMapper;
import com.gois.lotostatic.model.SorterDto;
import com.gois.lotostatic.processor.SorterItemProcessor;
import com.gois.lotostatic.reader.ExcelReader;
import com.gois.lotostatic.util.ExcelDownloader;
import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.repository.SorterRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ExcelJobConfig {

    @Bean
    public Job importarExcelJob(JobRepository jobRepository, Step importarStep) {
        return new JobBuilder("importarExcelJob", jobRepository)
                .start(importarStep)
                .build();
    }

    @Bean
    public Step importarStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager,
                             SorterRepository repository,
                             ExcelReader excelReader,
                             SorterMapper mapper,
                             SorterItemProcessor processor) throws Exception {

        String url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/resultados/download?modalidade=Mega-Sena";
        String localPath = "Mega-Sena_temp.xlsx";
        ExcelDownloader.downloadExcel(url, localPath);

        Integer ultimoConcurso = repository.findMaxConcourse().orElse(0);

        List<SorterDto> novosRegistros = excelReader.lerExcel(localPath).stream()
                .filter(dto -> dto.getConcourse() > ultimoConcurso)
                .toList();

        TaskletStep importarStep = new StepBuilder("importarStep", jobRepository)
                .<SorterDto, Sorter>chunk(10, transactionManager)
                .reader(new IteratorItemReader<>(novosRegistros))
                .processor(processor)
                .writer(repository::saveAll)
                .build();
        return importarStep;
    }

}
