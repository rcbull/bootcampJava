
# Projeto 3

## Nova funcionalidade

1. Implementar persistência com JPA

2. Incluir migrations com o Flyway

3. Gerar relatório dos autores, com a saída no formato JSON e os dados abaixo:

```
[{
    “autor” : "André da Silva”,
    “quantidadeLivros” : 2,
    “percentual” : 28.57
},
{
    “autor” : "Juliana Carvalho”,
    “quantidadeLivros” : 1,
    “percentual” : 14.29
}]
```

## Testes via REST Client no VS Code

1. Instalar a extensão: REST Client

[VS Code Marketplace](https://marketplace.visualstudio.com/items?itemName=humao.rest-client)

2. Abrir o arquivo teste.http

3. Clicar nas requests

![requests](./restclient.png)
