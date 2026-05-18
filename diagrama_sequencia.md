Exemplo de diagrama de sequência (Cadastro de Cliente):

/
@startuml
actor Vendedor
participant Sistema
participant Banco

Vendedor -> Sistema : visualizarClientes()
Sistema -> Banco : getClientes()
Banco --> Sistema : clientes cadastrados
Sistema --> Vendedor : exibir clientes cadastrados

Vendedor -> Sistema : cadastrarCliente(nome, cpf, numero)
Sistema -> Banco : consultarCliente(cpf)
Banco --> Sistema : disponibilidade do cpf
Sistema --> Vendedor : cliente cadastrado / cpf nao disponivel
@enduml
/

![Diagrama de Sequência de Cadastro de Cliente](//www.plantuml.com/plantuml/png/TO_DIeD134NtynHPLcXnxqAAFeBWFiv42SmamqocY4zl_0bjuUlsvjpBoN4Q3bkj9ioc0rv92XKTgNlCcJkAmGjFeuPNsHDArfI2XyDZOF009vuBAt_XUAvCOZHtTodEJlwMdViWkm3-qiFLKlu_GyQ2qmOMdUSbHqF0MVhaDnwRbLkYWOJdJhJH7d9_tuEiHaCtlRFANDNEDIzi-nUUNSMzAXSi14N1sHiVXFg5ETp_y22eCNMYcevUMQr-0m00)