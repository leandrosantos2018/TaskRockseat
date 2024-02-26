# API REST de Gerenciamento de Tarefas

Esta é uma API REST desenvolvida com Spring Boot para gerenciar tarefas de usuários.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Banco de Dados: H2
- Jakarta Servlet (para manipulação de requisições HTTP)

## Endpoints

A API possui os seguintes endpoints:

- `POST /Task/create`: Cria uma nova tarefa para o usuário autenticado.
  - Requer um corpo de requisição contendo os dados da tarefa a ser criada.
- `GET /Task/findAll`: Retorna todas as tarefas do usuário autenticado.
  - Requer o ID do usuário como parâmetro na requisição.
- `PUT /Task/atualizar/{id}`: Atualiza uma tarefa existente com o ID fornecido para o usuário autenticado.
  - Requer o ID da tarefa a ser atualizada e um corpo de requisição contendo os novos dados da tarefa.

## Autenticação

 Os endpoints esperam que o usuário esteja autenticado para acessá-los.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests com melhorias, correções de bugs, etc.


