E-commerce

Requisitos:
 
- Login do usuário não deve ser case sensitive.
- Senha do usuário deve ser mantida criptografada.
- O carrinho de produtos deve ser mantido no banco de dados.
- Todo pedido do cliente deve ser realizado com sucesso.
- O sistema deve conter ao menos um cliente registrado na base para testes.
- O sistema deve conter ao menos 4 produtos registrados com valores (100, 250.99, 1999.99 e 849.50).
- O sistema deve conter o estoque de 2 itens de cada produto.
- Não deve ser possível o cliente adicionar o produto caso não tenha estoque.
- O cliente pode ver o produto mesmo que não tenha estoque dele.
- Cliente pode ver a lista de produtos sem estar logado.
- Cliente só pode interagir com o carrinho estando logado.

               

Ações do cliente:
- realizar login com usuário e senha.
- adicionar produtos no carrinho.
- remover produtos do carrinho.
- alterar quantidades de produtos no carrinho.
- finalizar pedido.
- ver histórico de pedidos com lista de produtos, valores e data do pedido.
- ver lista de produtos.
- filtrar produtos por nome.
 
Tecnologias:
- Banco de Dados de sua preferência.
- Java 8 ou superior
- Spring Boot
- String Data
- Autenticação com Aauth 2.0
- Api utilizando padrões REST
- Angular 7+
- Git
- Possuir testes unitários (principais classes)
- Possuir testes integrados (principais classes)

 

Estrutura de dados:
 
Cliente:
- id: <>,
- nome: <>,
- usuario: <>,
- senha: <>,
- dataCadastro: <> - data e hora
 
Produto:
- id: <>,
- nome: <>, 
- descricao: <>, 
- valor: <> - precisao de 2 digitos
 
Estoque:

 - produto: <>
 - quantidade: <>
 - dataAtualizacao: <>  - data e hora

HistoricoPedido:

- cliente: <>
- produtos: <>
- dataCadastro: <>  - data e hora

Carrinho:

- cliente: <>
- produtos: <>
- dataCadastro: <>  - data e hora 




Se ocorrer o exited(78) - sudo sysctl -w vm.max_map_count=262144
