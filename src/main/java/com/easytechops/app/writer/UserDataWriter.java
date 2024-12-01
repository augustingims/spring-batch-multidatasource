package com.easytechops.app.writer;

import com.easytechops.app.model.User;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/1/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/1/24
 */
@Configuration
public class UserDataWriter {

  @Bean
  public FlatFileItemWriter<User> writer() {
    FlatFileItemWriter<User> writer = new FlatFileItemWriter<>();

    // Définir le fichier de sortie
    writer.setResource(new FileSystemResource("output.txt"));

    // Configurer l'agrégation des lignes
    DelimitedLineAggregator<User> lineAggregator = new DelimitedLineAggregator<>();
    lineAggregator.setDelimiter(","); // Délimiteur CSV

    // Configurer l'extraction des champs
    BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<>();
    fieldExtractor.setNames(new String[] {"id", "nom", "prenom"}); // Champs à extraire

    lineAggregator.setFieldExtractor(fieldExtractor);
    writer.setLineAggregator(lineAggregator);

    return writer;
  }
}
