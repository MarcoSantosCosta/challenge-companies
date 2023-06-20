# Challenge Companies API

Este é um repositório criado para um processo seletivo, com o objetivo de demonstrar minhas habilidades em Java, bem
como meus conhecimentos em arquitetura de software, padrões de código e boas práticas de desenvolvimento. A API foi
desenvolvida utilizando o framework Spring Boot 3 e utiliza o Spring Data JPA para integração com o banco de dados.

## Arquitetura e Padrões

O código foi desenvolvido seguindo os princípios da Arquitetura Limpa (Clean Architecture) e os princípios do SOLID. A
Arquitetura Limpa promove a separação de responsabilidades em camadas, garantindo uma maior flexibilidade e
testabilidade do código. O SOLID é um conjunto de princípios de programação que auxiliam na criação de código limpo e de
fácil manutenção.

Além disso, foram aplicados testes unitários e testes de integração para garantir a qualidade do código e a
funcionalidade correta das principais funcionalidades da API.

## Tecnologias Usadas

- Java 17
- Spring Boot 3
- JPA Repository
- PostgreSQL (bancdo de dados executando em um container Docker)

## Funcionalidades

A API possui as seguintes funcionalidades:

- CRUD de empresas: permite criar, ler, atualizar e excluir informações sobre empresas.
- CRUD de fornecedores: permite criar, ler, atualizar e excluir informações sobre fornecedores.
- Validações com API externas: realiza validações utilizando serviços de terceiros, como por exemplo, validação de CEP.
- Adição de fornecedores para empresas: permite adicionar fornecedores a empresas existentes.
- Listagem de forncedores de uma empresa.

## Documentação da API

A documentação da API, incluindo os endpoints disponíveis, parâmetros e exemplos de requisições, pode ser encontrada no
link a
seguir: [Documentação da API](https://github.com/MarcoSantosCosta/challenge-companies/blob/main/API_DOCUMENTATION.md)

## Executando o Projeto

Para executar o projeto em sua máquina local, siga as instruções abaixo:

1. Certifique-se de ter o Docker instalado, pois o banco de dados PostgreSQL será executado em um container.

2. Instale as dependências do projeto executando o seguinte comando no terminal:

   ```shell
   mvn install
   ```

3. Execute o projeto utilizando o Maven:

   ```shell
   mvn spring-boot:run
   ```

Com essas etapas concluídas, a API estará disponível para acesso localmente.

## Testes

Foram desenvolvidos testes de integração e testes unitários para garantir a qualidade e a robustez da API. Esses testes
abrangem diferentes cenários e garantem que o código esteja funcionando corretamente.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE). Sinta-se livre para utilizá-lo da maneira que desejar.