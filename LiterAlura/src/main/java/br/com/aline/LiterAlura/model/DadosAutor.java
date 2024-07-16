package br.com.aline.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor (@JsonAlias("birth_year") Integer anoDeNascimento,
                          @JsonAlias("death_year") Integer anoDeFalecimento,
                          @JsonAlias("name") String nome){
}