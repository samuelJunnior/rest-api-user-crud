# User CRUD - API

>Uma API REST em Spring Boot que disponibilizará um CRUD de usuário.

**Sumário**

- [Tecnologias](#-tecnologias)
  - [Ambiente](#ambiente)
  - [Desenvolvimento](#desenvolvimento)
- [Pré-requisitos](#-pr-requisitos)
- [Build e Execução local](#-build-e-execuo-local)
- [Usando a API](#-usando-a-api)
- [Colaboradores](#-colaboradores)

## 💻 Tecnologias

### Ambiente
* Java 11
* Maven
* Docker

### Desenvolvimento
* Versionamento e hospedágem de código com `Git` / [`Github`](https://github.com/samuelJunnior/rest-api-user-crud)
* Fluxo de trabalho com `GitFlow`.
* Banco de dados relacional `Postgres`.
* Versionamento do banco com `Liquibase`.
* Produtividade com `Lombook` e `MapStruct`.
* Integrações com APIs externas com `Spring Cloud OpenFeing`.
* Documentação com `OpenApi/Swagger`.
* Envio de e-mail de forma assincrona com `@Async` e com `Spring Boot Starter Mail`
* Pesquisa por filtro utilizando API do Spring Data `QueryByExemple`.
* `Paginação e Ordenação` utilizando API do Spring Data.

## 💻 Pré-requisitos

* Você precisa ter o JAVA instalado e configurado.
* Você precisa ter o Docker instalado e configurado.
* Você precisa ter o Maven instalado e configurado.

Para executar as soluções em ambiente `localhost`, pode ser interessante ter instâncias de banco de dados localmente.

Caso tenho sua instância de banco já configurada, apenas ajustar as configurações de `datasource` no arquivo [`application.yaml`](/src/main/resources/application.yaml)

Caso não tenha, execute o arquivo `docker-compose.yml` dentro do diretório [deployments](/deployments/docker-compose.yml) com o comando:
```bash
docker compose up -d
```
Serão criados os containers para utilização do banco postgres e mailhog.

## 🚀 Build e Execução local

Para gerar a versão executável do projeto com a extensão .jar é necessário executar o comando abaixo no diretório raiz:
```bash
mvn clean package
```

Execute o comando abaixo para iniciar o projeto
```bash
java -jar target\nome-do-seu-projeto.jar
```

## ☕ Usando a API

>Após a inicialização do projeto, acessar pelo endereço:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="#">
         <img src="https://avatars.githubusercontent.com/u/33516411?v=4" width="100px;" alt="Foto do Samuel Junior no GitHub"/><br>
        <sub>
          <b>Samuel Junior</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://s2.glbimg.com/FUcw2usZfSTL6yCCGj3L3v3SpJ8=/smart/e.glbimg.com/og/ed/f/original/2019/04/25/zuckerberg_podcast.jpg" width="100px;" alt="Foto do Mark Zuckerberg"/><br>
        <sub>
          <b>Mark Zuckerberg</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://miro.medium.com/max/360/0*1SkS3mSorArvY9kS.jpg" width="100px;" alt="Foto do Steve Jobs"/><br>
        <sub>
          <b>Steve Jobs</b>
        </sub>
      </a>
    </td>
  </tr>
</table>