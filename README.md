
# Projeto 6

## Novos recursos

- Incluir GitHub Actions

arquivo do CI em '.github/workflows/ci-testes.yml'

- Fazer deploy no Heroku

## Nova funcionalidade

Além de implementar os recursos citados anteriormente, você também deverá implementar a funcionalidade de envio de email, de maneira assíncrona, ao cadastrar um novo usuário na API, similar ao que foi demonstrado durante as aulas.

Lembre-se de criar duas classes para o envio de emails, sendo que uma delas deve ser ativada apenas em ambiente de produção e a outra, que não envia email de verdade, apenas imprimirá as informações do email no console, deve ser ativada nos ambientes de desenvolvimento e testes.

## Testes via REST Client no VS Code

1. Instalar a extensão: REST Client

[VS Code Marketplace](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)

2. Abrir o arquivo teste.http

3. Clicar nas requests

![requests](./restclient.png)
