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

### 02 – Excluir Cliente

| Campo | Descrição |
| :--- | :--- |
| **Nome** | excluirCliente |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor remove um cliente permanentemente do sistema de banco de dados. |
| **Pré-condições** | O cliente selecionado deve existir no sistema e o vendedor deve estar na tela de listagem de clientes. |
| **Pós-condições** | O registro do cliente é excluído da tabela `clientes` do banco de dados SQLite e na listagem de clientes. |
| **Fluxo Principal** | 1. O vendedor visualiza a lista de clientes cadastrados.<br>2. O vendedor seleciona o cliente desejado.<br>3. O vendedor aciona o comando para excluir o cliente.<br>4. O sistema processa o comando de exclusão disparando o método `excluir(id)` na `ClienteDAO`.<br>5. A DAO estabelece a conexão através de `Database.getConnection()` e executa o `DELETE`.<br>6. O sistema atualiza a interface e exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Cliente vinculado a orçamentos existentes:**<br>1. O sistema cancela a exclusão e exibe um alerta informando que o cliente possui orçamentos ativos e não pode ser apagado. |

### 03 – Cadastrar Vendedor

| Campo | Descrição |
| :--- | :--- |
| **Nome** | cadastrarVendedor |
| **Ator Principal** | Vendedor |
| **Descrição** | Realiza o cadastro de um novo vendedor no sistema, definindo suas informações de contato |
| **Pré-condições** | O usuário operador deve estar autenticado e na tela de cadastro de vendedores. |
| **Pós-condições** | O vendedor é registrado com sucesso na tabela `vendedores` do banco de dados e recebe um ID. |
| **Fluxo Principal** | 1. O operador solicita a inclusão de um novo vendedor.<br>2. O sistema exibe os campos de entrada (Nome, Telefone, Email, Percentual de Comissão).<br>3. O operador preenche os dados e confirma.<br>4. O sistema instancia o objeto `Vendedor`.<br>5. O sistema repassa o objeto para o método `salvar(vendedor)` da classe `VendedorDAO`.<br>6. O sistema exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Dados inválidos:**<br>1. O sistema captura o erro, interrompe o salvamento no banco e exibe um alerta instruindo o preenchimento correto. |

### 04 – Excluir Vendedor

| Campo | Descrição |
| :--- | :--- |
| **Nome** | excluirVendedor |
| **Ator Principal** | Vendedor |
| **Descrição** | O operador remove um registro de vendedor permanentemente do sistema de banco de dados. |
| **Pré-condições** | O vendedor selecionado deve existir no sistema. |
| **Pós-condições** | O registro do vendedor é excluído da tabela `vendedores` do banco de dados SQLite. |
| **Fluxo Principal** | 1. O operador visualiza a lista de vendedores cadastrados.<br>2. O operador seleciona o vendedor que deseja remover.<br>3. O operador aciona o comando para excluir o registro.<br>4. O sistema executa o método `excluir(id)` na `VendedorDAO`.<br>5. O sistema atualiza a interface e exibe uma mensagem de sucesso confirmando a remoção do colaborador. |
| **Alternativas** | **4a. Vendedor vinculado a orçamentos existentes:**<br>1. O sistema cancela a exclusão e exibe um alerta informando que o vendedor possui orçamentos ativos e não pode ser apagado. |

### 05 – Cadastrar Toldo

| Campo | Descrição |
| :--- | :--- |
| **Nome** | cadastrarToldo |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor realiza a inclusão de um produto do tipo Toldo, definindo suas dimensões e material. |
| **Pré-condições** | O vendedor deve estar autenticado e na tela de adicionar de produtos (aba Toldos) para orçamento. |
| **Pós-condições** | O produto é registrado com sucesso na tabela `produtos` e seus atributos específicos na tabela `toldos` do banco de dados SQLite. |
| **Fluxo Principal** | 1. O vendedor solicita a inclusão de um novo Toldo.<br>2. O sistema exibe o formulário requisitando os dados (Largura, Altura, Material, Tipo e Cor).<br>3. O vendedor preenche as informações e confirma.<br>4. O sistema instancia o objeto `Toldo` e executa a validação das dimensões e valores.<br>5. O sistema exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Dimensões ou valores negativos/zerados:**<br>1. O sistema identifica alguma informação inserida menor ou igual a zero.<br>2. O sistema interrompe o fluxo, lança um alerta de validação na interface e impede a persistência até que os valores sejam corrigidos. |

### 06 – Excluir Toldo

| Campo | Descrição |
| :--- | :--- |
| **Nome** | excluirToldo |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor remove um produto do tipo Toldo permanentemente do catálogo do sistema. |
| **Pré-condições** | O toldo selecionado deve existir no sistema e o vendedor deve estar na tela de listagem de produtos. |
| **Pós-condições** | Os registros associados ao toldo são removidos das tabelas `toldos` e `produtos` no banco SQLite. |
| **Fluxo Principal** | 1. O vendedor visualiza a lista de produtos cadastrados.<br>2. O vendedor seleciona o Toldo que deseja remover.<br>3. O vendedor aciona o comando para excluir o produto.<br>4. O sistema processa o comando chamando o método `excluir(id)` na `ToldoDAO`.<br>5. A DAO estabelece a conexão e remove primeiro o registro da tabela filha `toldos` e, em seguida, remove a linha correspondente da tabela pai `produtos`.<br>6. O sistema atualiza a listagem na interface e exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Toldo vinculado a orçamentos ativos:**<br>1. O sistema interrompe a exclusão e exibe um alerta informando que o produto faz parte de orçamentos ativos e não pode ser deletado. |

### 07 – Cadastrar Cortina

| Campo | Descrição |
| :--- | :--- |
| **Nome** | cadastrarCortina |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor realiza a inclusão de um produto do tipo Cortina, definindo suas dimensões e material. |
| **Pré-condições** | O vendedor deve estar autenticado e na tela de adicionar de produtos (aba Cortina) para orçamento. |
| **Pós-condições** | O produto é registrado com sucesso na tabela `produtos` e seus atributos específicos na tabela `cortina` do banco de dados SQLite. |
| **Fluxo Principal** | 1. O vendedor solicita a inclusão de uma nova Cortina.<br>2. O sistema exibe o formulário requisitando os dados (Largura, Altura e Tecido).<br>3. O vendedor preenche as informações e confirma.<br>4. O sistema instancia o objeto `Cortina` e executa a validação das dimensões e valores.<br>5. O sistema exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Dimensões ou valores negativos/zerados:**<br>1. O sistema identifica alguma informação inserida menor ou igual a zero.<br>2. O sistema interrompe o fluxo, lança um alerta de validação na interface e impede a persistência até que os valores sejam corrigidos. |

### 08 – Excluir Cortina

| Campo | Descrição |
| :--- | :--- |
| **Nome** | excluirCortina |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor remove um produto do tipo Cortina permanentemente do catálogo do sistema. |
| **Pré-condições** | A cortina selecionada deve existir no sistema e o vendedor deve estar na tela de listagem de produtos. |
| **Pós-condições** | Os registros associados a cortina são removidos das tabelas `cortinas` e `produtos` no banco SQLite. |
| **Fluxo Principal** | 1. O vendedor visualiza a lista de produtos cadastrados.<br>2. O vendedor seleciona a Cortina que deseja remover.<br>3. O vendedor aciona o comando para excluir o produto.<br>4. O sistema processa o comando chamando o método `excluir(id)` na `CortinaDAO`.<br>5. A DAO estabelece a conexão e remove primeiro o registro da tabela filha `cortinas` e, em seguida, remove a linha correspondente da tabela pai `produtos`.<br>6. O sistema atualiza a listagem na interface e exibe uma mensagem de sucesso. |
| **Alternativas** | **4a. Cortina vinculado a orçamentos ativos:**<br>1. O sistema interrompe a exclusão e exibe um alerta informando que o produto faz parte de orçamentos ativos e não pode ser deletado. |

### 09 – Criar Orçamento

| Campo | Descrição |
| :--- | :--- |
| **Nome** | criarOrcamento |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor registra uma nova proposta de orçamento associando um cliente, o vendedor responsável e a listagem de produtos selecionados com seus respectivos valores. |
| **Pré-condições** | O vendedor deve estar logado e autenticado no sistema com uma sessão ativa. |
| **Pós-condições** | O orçamento é persistido na tabela `orcamentos` com status "Em Análise" e a relação de itens é salva na tabela intermediária `orcamento_produtos`. |
| **Fluxo Principal** | 1. O vendedor inicia a criação de um novo orçamento.<br>2. O vendedor seleciona o cliente e o vendedor responsável pela venda.<br>3. O vendedor escolhe os produtos desejados e os adiciona ao orçamento.<br>4. O vendedor clica no botão para salvar o orçamento.<br>5. O sistema processa e grava os dados do orçamento e de todos os produtos vinculados no banco de dados.<br>6. O sistema atualiza a tela e exibe uma mensagem de sucesso para o vendedor. |
| **Alternativas** | **4a. Orçamento sem nenhum produto adicionado:**<br>1. O sistema verifica que a lista de itens está vazia.<br>2. O fluxo é interrompido antes de acessar o banco de dados, e um alerta é exibido notificando que é obrigatório incluir pelo menos um produto. |

### 10 – Atualizar Status do Orçamento

| Campo | Descrição |
| :--- | :--- |
| **Nome** | atualizarStatus |
| **Ator Principal** | Vendedor |
| **Descrição** | O vendedor altera a situação atual de um orçamento (como de "Em Análise" para "Aprovado" ou "Recusado" ou "Cancelado") conforme a negociação avança. |
| **Pré-condições** | O vendedor deve estar logado no sistema e o orçamento em questão já deve ter sido criado anteriormente. |
| **Pós-condições** | O novo status do orçamento é salvo com sucesso e atualizado no histórico do sistema. |
| **Fluxo Principal** | 1. O vendedor localiza e seleciona o orçamento desejado.<br>2. O vendedor escolhe a nova situação do orçamento (ex: Aprovado, Recusado, Cancelado, Em Analise).<br>3. O vendedor confirma a alteração do status.<br>4. O sistema modifica o status no orçamento em memória.<br>5. O sistema grava o novo status do orçamento diretamente no banco de dados usando o ID do registro.<br>6. O sistema exibe uma mensagem na tela confirmando que o status foi atualizado. |
| **Alternativas** | **4a. Erro ao salvar a atualização:**<br>1. O sistema interrompe o processo, mantém o status antigo e exibe uma mensagem avisando o vendedor que não foi possível salvar a alteração. |
