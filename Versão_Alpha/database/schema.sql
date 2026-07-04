CREATE TABLE IF NOT EXISTS clientes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    documento TEXT NOT NULL UNIQUE,
    telefone TEXT,
    email TEXT,
    endereco TEXT
);

CREATE TABLE IF NOT EXISTS vendedores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    telefone TEXT,
    email TEXT,
    percentual_comissao REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    categoria TEXT NOT NULL,
    largura REAL NOT NULL,
    altura REAL NOT NULL,
    material TEXT,
    tipo TEXT,
    cor TEXT,
    tecido TEXT,
    preco_m2 REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS orcamentos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cliente_id INTEGER NOT NULL,
    vendedor_id INTEGER NOT NULL,
    status TEXT NOT NULL,
    criado_em TEXT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (vendedor_id) REFERENCES vendedores(id)
);

CREATE TABLE IF NOT EXISTS orcamento_produtos (
    orcamento_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY (orcamento_id, produto_id),
    FOREIGN KEY (orcamento_id) REFERENCES orcamentos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
