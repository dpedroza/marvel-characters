[![CircleCI](https://circleci.com/gh/dpedroza/marvel-characters.svg?style=svg)](https://circleci.com/gh/dpedroza/marvel-characters)

## Interface

A interface do app é dividida em 3 partes e deve ser desenvolvida conforme os pontos abaixo.

### Home - Characters

![list](https://user-images.githubusercontent.com/9497411/85237315-4e006280-b3fc-11ea-8276-4a5691c24f50.png)
- [x] Listagem dos personagens ordenados por nome.
- [x] Botão para favoritar nas células.
- [x] Barra de busca para filtrar lista de personagens por nome.
- [x] Pull-to-refresh para atualizar a lista.
- [x] Paginação na lista: Carregar 20 personagens por vez, baixando a próxima página ao chegar no fim da lista.
- [x] Interface de lista vazia, erro ou sem internet.

### Favoritos

- [x] Listagem dos personagens favoritados pelo usuário.
- [x] Favoritos devem ser persistidos para serem acessados offline.
- [x] Interface de lista vazia, erro ou sem internet.

## Requisitos Essenciais

- [x] Usar Kotlin.
- [x] App universal, desenvolva uma interface que se adapte a telas maiores.
- [x] Tratamento para falha de conexão.
- [x] Desenvolver o App em uma arquitetura robusta.
- [x] Testes unitários e interface.

## Bônus

- [x] Gerar coverage com Jacoco.
- [x] Usar Clean Architecture com MVP.
- [x] Usar RxJava para requisições.
- [x] Usar Extension Functions.
- [x] Usar Dagger2 para injeções de dependência.
- [x] Usar Mockito para os testes unitários.
- [x] Usar Espresso para os testes de interface.

## TODO

### Detalhes do personagem

- [ ] Botão de favorito.
- [ ] Foto em tamanho maior 
- [ ] Nome do personagem na barra de navegação
- [ ] Descrição (se houver).
- [ ] Lista horizontal de Comics (se houver).
- [ ] Lista horizontal de Series (se houver).

## TBD
- [ ] O teste não pode apresentar crash.
