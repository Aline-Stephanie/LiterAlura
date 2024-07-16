package br.com.aline.LiterAlura.repository;

import br.com.aline.LiterAlura.model.Idioma;
import br.com.aline.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdiomasContaining(Idioma idioma);

    List<Livro> findFirst10ByOrderByNumeroDeDownloadsDesc();
}
