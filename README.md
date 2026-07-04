# g4
## Sistema de Gestão de Orçamento — Documento de Escopo e Planejamento

## Membros 
- Amanda - Amanda8709
- Dalila - lirbparente
- Daniel - Daniel-TeixeiraDS
- Isabela - Isamabuque
- Rafaella - rafaellamodanez

## Seção 1 - Introdução
### Justificativa
O processo de elaboração de orçamentos para produtos sob medida, como toldos e cortinas, exige organização, precisão nos cálculos e controle adequado das informações comerciais. Quando esse processo é realizado de forma manual ou descentralizada, surgem problemas como lentidão no atendimento ao cliente, erros no cálculo de área e preço, dificuldade de rastrear clientes, vendedores e produtos, além da perda de histórico operacional.

Diante desse cenário, o projeto propõe o desenvolvimento de um sistema desktop para gestão de orçamentos, com foco na centralização das informações, automatização dos cálculos e melhoria da organização administrativa. A solução busca apoiar lojas e profissionais que trabalham com produtos sob medida, oferecendo um ambiente mais estruturado para cadastrar clientes, vendedores, produtos e orçamentos.

### Descrição do Problema
Lojas de decoração, coberturas e produtos sob medida lidam diariamente com informações variadas, como dados de clientes, características de produtos, medidas, valores, vendedores responsáveis e status de negociação. Sem um sistema adequado, essas informações podem ficar dispersas em anotações, planilhas ou registros informais, dificultando consultas, exclusões, acompanhamento de propostas e análise do processo comercial.

Além disso, o cálculo de produtos sob medida depende de variáveis como largura, altura, material, tipo, tecido e preço por metro quadrado. A realização manual desses cálculos aumenta o risco de erros, podendo gerar prejuízos financeiros ou inconsistências nos valores apresentados ao cliente.

Outro problema importante está relacionado ao ciclo de vida dos orçamentos. Um orçamento pode estar em análise, aprovado, recusado ou cancelado, e essas mudanças precisam ser controladas de forma clara. A ausência de um controle estruturado compromete a rastreabilidade e dificulta o gerenciamento das operações.

### Motivação
A motivação do projeto está na criação de uma solução prática que una regras de negócio, interface gráfica e persistência de dados em uma aplicação desktop. Inicialmente, o sistema foi desenvolvido com foco na validação das classes principais e dos cálculos. Posteriormente, evoluiu para uma aplicação com interface JavaFX, banco de dados SQLite e separação em camadas. Essa evolução permite demonstrar, de forma prática, a aplicação de conceitos fundamentais de engenharia de software, como encapsulamento, herança, abstração, validação de dados, organização arquitetural, uso de DAOs e integração entre interface e backend.

## Seção 2 - Plano

### Objetivo Geral
Desenvolver um sistema para automação e gerenciamento de orçamentos de toldos e cortinas, utilizando Java, Programação Orientada a Objetos, JavaFX e banco de dados SQLite, com foco na organização dos cadastros, automatização dos cálculos e controle do ciclo de vida dos orçamentos.

### Objetivos Específicos
**Modelar o domínio do problema:** representar, por meio de classes Java, os principais elementos do sistema, como clientes, vendedores, produtos, toldos, cortinas e orçamentos.

**Aplicar Programação Orientada a Objetos:** utilizar conceitos como encapsulamento, herança, abstração, polimorfismo e interfaces para estruturar o funcionamento interno do sistema.

**Automatizar a precificação dos produtos:** calcular área e preço final com base nas dimensões e características dos produtos cadastrados.

**Validar dados essenciais:** aplicar regras de validação para documentos de clientes, percentual de comissão de vendedores e dimensões dos produtos.

**Controlar o ciclo de vida dos orçamentos:** permitir que os orçamentos sejam classificados por status, como Em Analise, Aprovado, Recusado e Cancelado.

**Implementar persistência de dados:** armazenar clientes, vendedores, produtos e orçamentos em banco de dados SQLite.

**Organizar o sistema em camadas:** separar responsabilidades entre interface gráfica, camada de serviço, classes de domínio, repositórios DAO e gerenciamento do banco de dados.

**Criar interface gráfica com JavaFX:** disponibilizar uma aplicação organizada em abas para facilitar o cadastro, consulta, exclusão e gerenciamento das informações.

**Produzir diagramas UML:** documentar a estrutura e os principais fluxos do sistema por meio de diagramas de caso de uso, classes e sequência.

### Tecnologias Utilizadas

O projeto foi desenvolvido utilizando ferramentas como Java, JavaFX, SQLite, Conceitos de POO, Conceitos de Arquitetura em Camadas, UML e PlantUML.

## Seção 3 - Funcionalidades 

O sistema permite o gerenciamento de clientes, vendedores, produtos e orçamentos relacionados à venda de toldos e cortinas sob medida.

### Gestão de Clientes

O sistema permite cadastrar clientes com informações como nome, documento, telefone, email e endereço. Também, realiza a validação do documento informado e se o documento já está cadastrado antes de salvar como novo cliente.

### Gestão de Vendedores

O sistema permite cadastrar vendedores responsáveis pelos orçamentos e possui nome, telefone, email e percentual de comissão. Também, realiza a validação do percentual de comissão de 0 a 100 e a comissão sobre o valor do orçamento.

### Gestão de Produtos

O sistema permite cadastrar produtos sob medida dos tipos Toldo e Cortina, ambos possuem largura, altura e preço por metro quadrado. O preço é calculado baseado no material e no tipo.

### Gestão de Orçamentos 

O sistema permite criar orçamentos associando cliente, vendedor e lista de produtos. Cada orçamento possui status, produtos vinculados e cálculo automático do valor total, além da comissão do vendedor com base no total do orçamento. Além disso, possui um ciclo de status e que permite atualização, facilitando o acompanhamento do ciclo comercial da proposta.

### Interface Gráfica

A aplicação desenvolvida e organizada em Clientes, Vendedores, Produtos e Orçamentos, em que cada aba reune campos, botões e tabelas necessárias para as operações de cada aba.

## Seção 4 - Divisão de Tarefas

### Tarefas (Issues)
O desenvolvimento do projeto foi dividido em tarefas menores para facilitar a evolução incremental da solução e permitir melhor acompanhamento das entregas.

As principais tarefas realizadas foram:

**Levantamento do escopo:** definição do problema, justificativa, motivação e objetivos do sistema.

**Modelagem UML inicial:** construção dos primeiros diagramas de classes e sequência para representar as entidades principais e seus relacionamentos.

**Implementação do núcleo do domínio:** criação das classes iniciais do sistema, como Cliente, Produto, Toldo, Cortina, Pedido e componentes de validação.

**Validação dos cálculos:** implementação e teste da lógica de cálculo de área e preço dos produtos sob medida.

**Evolução do modelo de negócio:** substituição da ideia inicial de Pedido pela classe Orcamento, mais adequada ao domínio do sistema.

**Criação da entidade Vendedor:** implementação do cadastro de vendedores e do cálculo de comissão sobre o valor dos orçamentos.

**Implementação do controle de status:** criação do enum StatusOrcamento para representar o ciclo de vida dos orçamentos.

**Criação da camada de persistência:** desenvolvimento dos DAOs responsáveis pelas operações de banco de dados.

**Configuração do banco SQLite:** criação da classe Database e do script schema.sql para inicialização das tabelas do sistema.

**Criação da camada de serviço:** implementação da classe SistemaService, responsável por centralizar os principais casos de uso da aplicação.

**Desenvolvimento da interface gráfica:** criação de abas para clientes, vendedores, produtos e orçamentos.

**Revisão dos diagramas UML:** atualização dos diagramas para refletir a arquitetura final do sistema e os principais fluxos implementados.

## Seção 5 - Modelagem

Os diagramas UML foram utilizados para representar visualmente a estrutura e o comportamento do sistema. 
**OBS:** As imagens dos diagramas vão estar disponíveis neste README, enquanto os códigos que refletem exatamente a mesma imagem estará no repositório específico e organizado como "Diagramas/Diagramas Etapa 2", assim como as descrições de casos de uso completas.

### Diagrama de Classes

O diagrama de classe básico apresenta uma visão simplificada do domínio, destacando as principais entidades do sistema e seus relacionamentos e está disponível logo abaixo apenas para entendimento geral do projeto. O diagrama de classe arquitetural apresentando uma visão mais completa da aplicação, mostrando a separação entre interface, serviço, domínio, repositórios e banco de dados está na pasta designada aos diagramas.

<img width="1290" height="486" alt="DiagramaDeClasses-RD-V2" src="https://github.com/user-attachments/assets/852d1d87-fd28-4375-8e52-7971d6b34200" />

### Diagrama de Sequência 

Os diagramas de sequência detalham os principais fluxos de execução do sistema, como cadastro de cliente, cadastro de vendedor, cadastro de produtos, criação de orçamento, atualização de status e exclusões.

### Cadastrar Vendedor 
<img width="970" height="357" alt="DiagramaDeSequencia-CadastroDeVendedor" src="https://github.com/user-attachments/assets/870594fc-b1cd-49df-b595-7330d2a4f40f" />

### Excluir Vendedor
<img width="530" height="342" alt="DiagramaDeSequencia-ExcluirVendedor" src="https://github.com/user-attachments/assets/70bc0640-409a-4bb5-b5ff-2c4824db473c" />

### Cadastrar Cliente
<img width="921" height="415" alt="DiagramaDeSequencia-CadastroDeCliente" src="https://github.com/user-attachments/assets/1b97b24a-b1f8-4b71-8730-728aa64a3049" />

### Excluir Cliente
<img width="457" height="342" alt="DiagramaDeSequencia-ExcluirCliente" src="https://github.com/user-attachments/assets/f03d13e8-3fc4-45ad-949c-735dca6d80e0" />

### Cadastrar Toldo
<img width="539" height="299" alt="DiagramaDeSequencia-CadastroDeToldo" src="https://github.com/user-attachments/assets/586a2365-7a86-4ecd-8d18-bc62ad3b819a" />

### Cadastrar Cortina
<img width="560" height="307" alt="DiagramaDeSequencia-CadastroDeCortina" src="https://github.com/user-attachments/assets/5f7d2d42-1c68-4166-b81b-3b3c0be73c5c" />

### Excluir Produto
<img width="481" height="342" alt="DiagramaDeSequencia-ExcluirProduto" src="https://github.com/user-attachments/assets/d3717b13-8852-40b4-ac07-1c6528beee95" />

### Criar Orçamento
<img width="1125" height="532" alt="DiagramaDeSequencia-CriarOrcamento" src="https://github.com/user-attachments/assets/8d428a78-2394-4328-ad6a-4c00d61356f5" />

### Atualizar Status Orçamento
<img width="646" height="385" alt="DiagramaDeSequencia-AtualizarStatus" src="https://github.com/user-attachments/assets/1f03c6ea-d751-4f7a-8087-2de81273d8df" />

### Excluir Orçamento
<img width="569" height="342" alt="DiagramaDeSequencia-ExcluirOrcamento" src="https://github.com/user-attachments/assets/1a3811a2-a4b0-4014-83c7-c6cef81aebea" />


### Descrição de Caso de Uso

O diagrama de caso de uso específico apresenta as funcionalidades disponíveis ao usuário, como cadastrar clientes, cadastrar vendedores, cadastrar produtos, criar orçamentos, atualizar status e excluir registros.

<img width="2019" height="222" alt="DiagramaEspecificoCasoDeUso" src="https://github.com/user-attachments/assets/156e46bd-bcff-44b6-ac6e-f7ded3895ede" />

### 01 – Cadastrar Cliente

| Campo | Descrição |
| :--- | :--- |
| **Nome** | cadastrarCliente |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor realiza a inclusão de um novo cliente no sistema para permitir gerar orçamentos futuros. |
| **Pré-condições** | O vendedor deve estar cadastrado e na tela de gerenciamento de clientes. |
| **Pós-condições** | O cliente é registrado com sucesso no banco de dados e recebe um ID único. |
| **Fluxo Principal** | 1. O vendedor solicita a inclusão de um novo cliente.<br>2. O sistema exibe o formulário de cadastro requisitando os dados (Nome, Documento, Telefone, Email, Endereço).<br>3. O vendedor insere as informações obrigatórias e confirma.<br>4. O sistema valida a consistência dos dados inseridos.<br>5. O sistema persiste as informações no banco de dados através da `ClienteDAO`.<br>6. O sistema exibe uma mensagem confirmando o sucesso do cadastro. |
| **Alternativas** | **4a. Dados obrigatórios em branco ou inválidos:**<br>1. O sistema identifica que campos obrigatórios não foram preenchidos ou estão incorretos.<br>2. O sistema exibe um alerta apontando os erros e impede o salvamento até a correção.<br><br>**4b. Cliente já cadastrado:**<br>1. O sistema consulta o banco de dados e identifica que já existe um cliente com o mesmo documento.<br>2. O sistema interrompe a operação, informa o vendedor sobre a duplicidade e retorna ao formulário. |

OBS: Lembrando que as descrições de casos de uso completas está no repositório específico e organizado como "Diagramas/Diagramas Etapa 2", segue apenas um exemplo de como está estruturado.

## Seção 6 - Evolução entre Versões e Destaques

### Versão 1 - Validação Inicial do Domínio

A primeira versão do projeto teve como objetivo validar as regras de negócio principais em uma aplicação simples executada via console. Nessa etapa, o foco estava na construção do núcleo lógico do sistema, sem interface gráfica e sem persistência em banco de dados.

A V1 contemplava classes como Cliente, Produto, Toldo, Cortina, Pedido e VerificadorCadastro. Essa estrutura permitiu validar conceitos como herança, abstração, encapsulamento, cálculo de área, cálculo de preço e verificação de documentos já cadastrados.

Nessa fase, o sistema já permitia criar clientes, adicionar produtos a um pedido e calcular o valor total com base nas dimensões e características dos produtos. O objetivo principal era garantir que a lógica de domínio estivesse funcionando antes da evolução para uma aplicação mais completa.

### Versão 2 - Aplicação Desktop com Arquitetura em Camadas

A segunda versão expandiu o projeto para uma aplicação com interface gráfica, persistência em banco de dados e arquitetura organizada em camadas. A aplicação passou a utilizar JavaFX para interação com o usuário e SQLite para armazenamento dos dados.

A classe Pedido, utilizada na V1, foi substituída por Orcamento, que representa melhor o domínio comercial do sistema. Além disso, foram adicionadas as entidades Vendedor e StatusOrcamento, permitindo associar cada orçamento a um vendedor, calcular comissão e controlar o estado da proposta.

A V2 também introduziu a camada de serviço por meio da classe SistemaService, responsável por centralizar os principais fluxos do sistema, e a camada de repositórios com os DAOs ClienteDAO, VendedorDAO, ProdutoDAO e OrcamentoDAO.

### Principais Melhorias da V1 para a V2

**Interface gráfica:** o sistema deixou de operar apenas em console e passou a ter uma interface JavaFX organizada em abas.

**Persistência de dados:** os registros passaram a ser armazenados em banco SQLite, permitindo manter informações entre execuções da aplicação.

**Arquitetura em camadas:** o projeto passou a separar melhor as responsabilidades entre interface, serviço, domínio, repositórios e banco de dados.

**Cadastro de vendedores:** a V2 passou a permitir o cadastro e exclusão de vendedores, além do cálculo de comissão.

**Controle de status:** os orçamentos passaram a possuir estados definidos, como Em Analise, Aprovado, Recusado e Cancelado.

**Relacionamento entre orçamento e produtos:** foi criada uma associação entre orçamentos e produtos, permitindo que um orçamento contenha múltiplos itens.

**DAOs especializados:** a persistência passou a ser organizada em classes específicas para cada entidade principal.

**Validações aprimoradas:** o sistema manteve validações de documentos e dimensões, além de incluir validação do percentual de comissão dos vendedores.

**Organização visual da aplicação:** a interface passou a agrupar funcionalidades por abas: clientes, vendedores, produtos e orçamentos.
