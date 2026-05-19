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

![Diagrama de Sequência de Cadastro de Cliente](https://img.plantuml.biz/plantuml/png/TP7B2W8n34Nt_Oei5KRSkn3HJn3SnpPAe9CCRKR4hxS-wZWmixqvzt9ATzamMTz6XzuqmOaaKD3akXApvmx5uCZPgCLHjaVnwbpbORMj66pWnhd7o0zCXyWaHdcnTFLSo7UvS5UoF-2JhaPB_di6Zm6p9GoQXwM2_WGsG7S-Svgkp8XMf7ekH5jgm7UN1gHlAUcajrV9VRIXLWhJ_e5pfrAy8WSC145VutCl-Ah_cSFwnOEWrgaRHRShbV9dJm00)
