package br.com.aline.LiterAlura.principal;

import br.com.aline.LiterAlura.model.*;
import br.com.aline.LiterAlura.repository.AutorRepository;
import br.com.aline.LiterAlura.repository.LivroRepository;
import br.com.aline.LiterAlura.service.ConsumoApi;
import br.com.aline.LiterAlura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while(opcao != 0){
            var MenuOpcoes = """
                    
                    *** Catálogo de livros LiterAlura ***
                    
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em determinado ano
                    5- Listar livros em determinado idioma
                    6- Listar top 10 livros mais baixados
                    7- Buscar livros agrupados por idioma
                    0- Sair
                    """;
            System.out.println(MenuOpcoes);
            System.out.println("Escolha uma opção:");

            try {
                opcao = leitura.nextInt();
            }catch (Exception e){
                System.out.println("A opção escolhida é inválida.");
            }
            leitura.nextLine();

            switch (opcao){
                case 1:
                    buscarLivrosApi();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmCertoAno();
                    break;
                case 5:
                    listarLivrosEmUmIdioma();
                    break;
                case 6:
                    listarTop10MaisBaixados();
                    break;
                case 7:
                    listarLivrosAgrupadosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosAgrupadosPorIdioma() {
        livros = livroRepository.findAll();
        for(Idioma idioma : Idioma.values()){
            System.out.println("Livros em " + idioma + ":");
            livros.stream()
                    .filter(livro -> livro.getIdiomas().contains(idioma))
                    .forEach(livro -> System.out.println(livro.getTitulo()));
            System.out.println();
        }
    }

    private void listarTop10MaisBaixados() {
        livros = livroRepository.findFirst10ByOrderByNumeroDeDownloadsDesc();
        livros.forEach(System.out::println);
    }

    private void listarLivrosRegistrados(){
        livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores = new ArrayList<>(autores.stream().collect(Collectors.toMap(Autor::getNome, autor -> autor, (a, b) -> a))
                .values());
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosEmCertoAno() {
        System.out.println("Digite o ano de referência:");
        var ano = -1;
        try {
            ano = leitura.nextInt();
        }catch (Exception e){
            System.out.println("A opção escolhida é inválida.");
        }
        leitura.nextLine();

        autores = autorRepository.findAutoresVivosEmDeterminadoAno(ano);
        autores = new ArrayList<>(autores.stream().collect(Collectors.toMap(Autor::getNome, autor -> autor, (a, b) -> a))
                .values());
        System.out.println("Autores vivos em " + ano + ":");
        autores.forEach(System.out::println);
    }

    private void listarLivrosEmUmIdioma() {
        System.out.println("Deseja buscar livros em qual idioma? ");
        var nomeIdioma = leitura.nextLine();
        Idioma idioma = Idioma.fromPortugues(nomeIdioma);
        List<Livro> livrosPorIdioma = livroRepository.findByIdiomasContaining(idioma);
        System.out.println("Livros em " + nomeIdioma);
        livrosPorIdioma.forEach(System.out::println);
    }

    private DadosLivroApi getDadosLivros() {
        System.out.println("Digite o nome do livro");
        var nomeLivro = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeLivro.replaceAll(" ", "+"));
        DadosLivroApi resultadoLivro = conversor.obterDados(json, DadosLivroApi.class);
        return resultadoLivro;
    }

    private void buscarLivrosApi() {
        DadosLivroApi dados = getDadosLivros();
        if(!dados.results().isEmpty()){
            try{
                DadosLivro dadosLivro = dados.results().iterator().next();
                Livro livro = new Livro(dadosLivro);
                livroRepository.save(livro);
                System.out.println(livro);
            }catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        else {
            System.out.println("Livro não encontrado");
        }
    }
}
