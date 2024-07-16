package br.com.aline.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (@JsonAlias({"title"}) String titulo,
                          @JsonAlias({"authors"}) Set<DadosAutor> autores,
                          @JsonAlias("languages") Set<String> idiomas,
                          @JsonAlias("download_count") Long numeroDownloads){
}

