# LiterAlura

Neste projeto de catálogo de livros, utilizamos:
- a linguagem Java, 
- as dependências PostgreSQL Driver e Spring Data JPA, 
- Maven e SpringBoot 3.3.1
- a IDE IntelliJ IDEA
- e o [Gutendex](https://gutendex.com/)

## Funcionalidades principais
Ao executar o projeto, é apresentado um Menu com sete opções. Cinco delas são as funcionalidades principais e duas secundárias que foram implementadas de forma Opcional. Dentre as principais temos:

1. Ao selecionar a opção 1 (Buscar livro pelo título), será solicitado o nome do livro.
   Como resultado, teremos o livro (caso encontrado) e a persistência do dado no banco de dados.
![image](https://github.com/user-attachments/assets/6604e358-4abb-4923-9f54-d1bd0b38a1dd)

2. Ao selecionar a opção 2 (Listar livros registrados), são listados os livros salvos no banco de dados.
![image](https://github.com/user-attachments/assets/05635589-e76a-4486-b7bc-85efd0a4d70d)

3. Ao selecionar a opção 3 (Listar autores registrados), são listados os autores salvos no banco de dados.
![image](https://github.com/user-attachments/assets/2ef6add5-b6b5-46a9-8fb1-0390b8a54c40)

4.  Ao selecionar a opção 4 (Listar autores vivos em determinado ano), será solicitado o ano de referência para a pesquisa.
   Como resultado, teremos os autores vivos naquele ano.
![image](https://github.com/user-attachments/assets/337ffed8-0c64-4f52-9e0b-34e894d4231a)

5. Ao selecionar a opção 5 (Listar livros em determinado idioma), será apresentado um menu com opções de idiomas e solicitado que o usuário escolha dentre elas.
   Como resultado, teremos os livros que possuam o idioma selecionado.
![image](https://github.com/user-attachments/assets/342e7a9b-c70f-447d-a509-1e3d9355ebdd)

## Funcionalidades adicionais

6. Ao selecionar a opção 6 (Listar top 10 livros mais baixados), serão listados os 10 livros que possuem os maiores números de downloads.
7. Ao selecionar a opção 7 (Buscar livros agrupados por idioma), serão listados os livros agrupados pelo idioma.
