Relatório Individual

1. Atribuição de Cargo e Tarefas
Atuei como desenvolvedora responsável pelo Backend e Banco de Dados da aplicação. Minhas principais responsabilidades envolveram a modelagem e implementação do banco de dados em SQLite, o desenvolvimento das entidades do sistema, a implementação da camada de persistência (DAO), a criação das regras de negócio e a integração entre a interface gráfica e o banco de dados.

2. Contribuição de acordo com a atribuição
Durante esta etapa do projeto, concentrei meu trabalho na construção da infraestrutura responsável pelo funcionamento interno da aplicação.
Inicialmente, desenvolvi toda a estrutura do banco de dados por meio do arquivo schema.sql, definindo as tabelas clientes, vendedores, produtos, orcamentos e orcamento_produtos, além das chaves primárias e estrangeiras responsáveis pelos relacionamentos entre as entidades.
Também implementei a classe Database, responsável pela conexão com o SQLite utilizando JDBC e pela inicialização automática do banco de dados, executando o arquivo de criação das tabelas sempre que a aplicação é iniciada pela primeira vez.
Na camada de domínio, desenvolvi as principais entidades do sistema (Cliente, Produto, Toldo, Cortina, Vendedor e Orcamento), aplicando conceitos de Programação Orientada a Objetos como herança, abstração, encapsulamento e polimorfismo. Além disso, implementei validações para documentos (CPF/CNPJ), cálculo automático de preços dos produtos, cálculo de comissão dos vendedores e controle dos estados dos orçamentos.
Em seguida, implementei toda a camada de persistência utilizando o padrão DAO, criando as classes ClienteDAO, ProdutoDAO, VendedorDAO e OrcamentoDAO, responsáveis pelas operações de cadastro, consulta, exclusão e atualização dos dados armazenados no banco.
Por fim, desenvolvi a classe SistemaService, que centraliza toda a lógica de negócio da aplicação, funcionando como intermediária entre a interface JavaFX e os DAOs. Nessa camada foram implementadas regras como validação de documentos duplicados, criação de orçamentos, associação entre clientes, vendedores e produtos, atualização de status dos orçamentos e cálculo automático dos valores totais e comissões.

Principais Commits realizados:
Commit 1: Implementação da estrutura inicial do banco de dados e configuração da classe Database, responsável pela criação automática das tabelas e conexão com o SQLite.
https://github.com/poo-ec-2026-1/g4/commit/868dd8387e0b70f2e82841df846efe1fd72e2695

Commit 2: Desenvolvimento das entidades do sistema (Cliente, Produto, Toldo, Cortina, Vendedor e Orcamento) e implementação da camada DAO para persistência dos dados.
https://github.com/poo-ec-2026-1/g4/commit/449a36221f6e044c24a32e623db9ac5880f10b74

Commit 3: Implementação da classe SistemaService, integração completa entre backend e interface gráfica, validações de negócio, criação de orçamentos e gerenciamento dos status da aplicação.
https://github.com/poo-ec-2026-1/g4/commit/7d781816f7cb56a6eee9636501c7b0353032bcd7


3. Considerações Gerais
Nesta segunda etapa do projeto, meu foco foi desenvolver toda a infraestrutura responsável pelo funcionamento da aplicação. Busquei organizar o backend utilizando uma arquitetura em camadas, separando a interface gráfica, as regras de negócio e o acesso ao banco de dados, facilitando futuras manutenções e expansões do sistema.
A implementação do banco de dados e da camada de persistência permitiu que todas as informações fossem armazenadas de forma consistente, enquanto a camada de serviços garantiu que as validações e regras de negócio fossem executadas antes da comunicação com o banco.
Além do aprendizado prático sobre Programação Orientada a Objetos, tive a oportunidade de aplicar conceitos como persistência de dados utilizando SQLite, acesso ao banco com JDBC, padrão DAO, organização em camadas e integração entre backend e interface JavaFX, contribuindo diretamente para que a aplicação funcionasse de forma completa e integrada.
