## Interface

A interface do app é dividida em 3 partes e deve ser desenvolvida conforme os pontos abaixo. Para iPad gostariamos de ver o uso do Splitview.

### Home - Characters

 Listagem dos personagens ordenados por nome.
- [x] Botão para favoritar nas células.
- [x] Barra de busca para filtrar lista de jogos por nome.
- [x] Pull-to-refresh para atualizar a lista.
- [x] Paginação na lista: Carregar 20 personagens por vez, baixando a próxima página ao chegar no fim da lista.
- [x] Interface de lista vazia, erro ou sem internet.

### Detalhes do personagem

- [ ] Botão de favorito.
- [ ] Foto em tamanho maior 
- [ ] Nome do personagem na barra de navegação
- [ ] Descrição (se houver).
- [ ] Lista horizontal de Comics (se houver).
- [ ] Lista horizontal de Series (se houver).

### Favoritos

- [x] Listagem dos personagens favoritados pelo usuário.
- [x] Favoritos devem ser persistidos para serem acessados offline.
- [x] Interface de lista vazia, erro ou sem internet.

## Requisitos Essenciais

- [x] Usar Kotlin.
- [ ] App universal, desenvolva uma interface que se adapte a telas maiores.
- [x] Tratamento para falha de conexão.
- [x] Desenvolver o App em uma arquitetura robusta.
- [?] O teste não pode apresenter crash.
- [ ] Testes unitários e interface.

## Bônus

- [x] Gerar coverage com Jacoco.
- [x] Usar Clean Architecture com MVP.
- [x] Usar RxJava para requisições.
- [x] Usar Extension Functions.
- [x] Usar Dagger2 para injeções de dependência.
- [x] Usar Mockito para os testes unitários.
- [ ] Usar Espresso para os testes de interface.