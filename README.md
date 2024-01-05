
# API RFilmes

Uma api de filmes, com o objetivo de gerar alguns dados (mocks) sobre filmes.


## Instalação

Certifique-se que você possui instalado:
```
- Java e JDK
- Maven
- Intellij, Eclipse ou VScode
```
    
## Funcionalidades

- Cadastrar Filme
- Atualizar Filme
- Buscar por todos os Filmes
- Buscar Filme pelo título
- Criar conta
- Ver informações do Filme



## Tecnologias

Esse projeto utiliza as seguintes tecnologias:

- Java
- Spring Boot
- Junit
- Hateoas
- JWT
- MySQL


## Endpoints da API

#### Cadastra Usuário

```http
  POST /usuario/registro
```
| Descrição                           |
| :---------------------------------- |
 **Os dados do Usuário devem ser feitos em JSON** |

```json
{
  "nome": "string",
  "email": "string",
  "senha": "stringst" (senha precisa conter no mínimo 8 caracteres)
}
```

#### Login Usuário

```http
  POST /usuario/login
```
| Descrição                           |
| :---------------------------------- |
 **Os dados do Usuário devem ser feitos em JSON** |

```json
{
  "email": "string",
  "senha": "stringst"
}
```

#### Retorna todos os Filmes

```http
  GET /api/v1/filmes
```

| Descrição                           |
| :---------------------------------- |
 **Retorna todos os filmes do sistema** |

#### Retorna um Filme por ID

```http
  GET /api/v1/filmes/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Retorna o Filme do ID que foi passado**. |
| `titulo` | `String`|
| `categoria` | `String`|
| `urlDaCapa` | `String`|
| `diretor` | `String`|
| `descricao` | `String`|
| `anoDeLancamento` | `Integer`|
| `tempoEmMinutos` | `Integer`|

#### Adiciona um Filme

```http
  POST /api/v1/filmes
```
| Descrição                           |
| :---------------------------------- |
 **Os dados do Livro devem ser feitos em JSON** |

```json
{
  "titulo": "string",
  "descricao": "string",
  "urlDaCapa": "string",
  "categoria": "string",
  "diretor": "string",
  "anoDeLancamento": 0,
  "tempoEmMinutos": 0
}
```

#### Deleta um Filme (Somente ADMINs)

```http
  DELETE /api/v1/filmes/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Deleta o Filme do ID que foi passado**. |

#### Atualiza um Filme

```http
  PUT /api/v1/filmes/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Atualiza o nome do Filme cujo ID foi passado**. |

```json
{
  "titulo": "string",
  "descricao": "string",
  "urlDaCapa": "string",
  "categoria": "string",
  "diretor": "string",
  "anoDeLancamento": 0,
  "tempoEmMinutos": 0
}
```


