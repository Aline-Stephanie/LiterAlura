package br.com.aline.LiterAlura.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name="livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Idioma.class)
    @CollectionTable(name = "livro_idioma", joinColumns = @JoinColumn(name = "livro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private Set<Idioma> idiomas;
    private Long numeroDeDownloads;

    public Livro() {}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.numeroDeDownloads = dadosLivro.numeroDownloads();
        this.idiomas = dadosLivro.idiomas().stream()
                .map(Idioma::fromCodigo)
                .collect(Collectors.toSet());
        this.autores = dadosLivro.autores().stream()
                .map(Autor::new)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Long numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return """
                *** Livro ***
                Título: %s
                Autores: %s
                Idiomas: %s
                Número de downloads: %s
                """.formatted(
                        titulo,
                        autores.stream().map(Autor::getNome).collect(Collectors.toSet()),
                        idiomas.stream().map(Idioma::name).collect(Collectors.toSet()),
                        numeroDeDownloads
        );
    }
}
