package br.com.aline.LiterAlura.repository;

import br.com.aline.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE (a.anoDeFalecimento > :anoDeReferencia OR a.anoDeFalecimento IS NULL) AND a.anoDeNascimento IS NOT NULL")
    List<Autor> findAutoresVivosEmDeterminadoAno(int anoDeReferencia);
}
