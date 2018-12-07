    
CREATE SEQUENCE seq_func
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE funcionario (
    prontuario_func INT PRIMARY KEY,
    endereco VARCHAR2(70) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone INT NOT NULL,
    nome VARCHAR2(50) NOT NULL);
    
CREATE SEQUENCE seq_catLiteraria
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE categoria_literaria(
    codigo_categoria INT PRIMARY KEY ,
    descricao VARCHAR2(70) NOT NULL 
);    

CREATE SEQUENCE seq_obraLiteraria
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE obra_literaria(
    id_obra INT PRIMARY KEY,
    isbn VARCHAR2(50) NOT NULL,
    qtd_exemplares INT NOT NULL,
    nrm_edicao INT NOT NULL,
    data_publicacao DATE NOT NULL,
    editora VARCHAR2(50) NOT NULL,
    titulo_obra VARCHAR2(50) NOT NULL,
    codigo_categoria INT NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria_literaria(codigo_categoria)
    ON DELETE CASCADE
);

CREATE SEQUENCE seq_categoriaLeitor
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE categoria_leitor(
    codigo_categoria INT PRIMARY KEY,
    descricao VARCHAR2(100) NOT NULL,
    tempo_emprestimo INT NOT NULL
);
    
CREATE SEQUENCE seq_leitor
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE leitor(
    prontuario_leitor INT PRIMARY KEY,
    nome VARCHAR2(30) NOT NULL,
    endereco VARCHAR2(50) NOT NULL,
    cidade VARCHAR2(30) NOT NULL,
    estado VARCHAR2(5) NOT NULL,
    telefone INT NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR2(40) NOT NULL,
    status VARCHAR(30) DEFAULT 'DISPONIVEL',
    rg INT NOT NULL,
    codigo_categoria INT NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria_leitor(codigo_categoria) ON DELETE CASCADE
);

CREATE SEQUENCE seq_exemplar
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE exemplar(
    codigo_exemplar INT PRIMARY KEY,
    status varchar2(20) DEFAULT 'DISPONIVEL',
    numero_exemplar INT NOT NULL,
    id_obra INT NOT NULL,
    FOREIGN KEY (id_obra) REFERENCES obra_literaria (id_obra)
);

CREATE SEQUENCE seq_palavraChave
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE palavra_chave(
    id_palavra INT PRIMARY KEY,
    conteudo VARCHAR2(200)NOT NULL
);

CREATE SEQUENCE seq_autor
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE autor(
    id_autor INT PRIMARY KEY,
    nome VARCHAR2(50) NOT NULL
);

    
CREATE TABLE lista_autores(
    id_autor INT,
    id_obra INT NOT NULL,
    FOREIGN KEY (id_obra) REFERENCES obra_literaria(id_obra) ON DELETE CASCADE,
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor) 
    ON DELETE CASCADE
);

CREATE SEQUENCE seq_listaPalavras
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE lista_palavras(
    id_obra INT NOT NULL,
    id_palavra INT NOT NULL,
    PRIMARY KEY(id_obra, id_palavra),
    FOREIGN KEY (id_obra) REFERENCES obra_literaria(id_obra),
    FOREIGN KEY (id_palavra) REFERENCES palavra_chave(id_palavra)
);


CREATE SEQUENCE seq_emprestimo
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE emprestimo (
    codigo_emp INT PRIMARY KEY,
    data_dev DATE NOT NULL,
    data_emp DATE DEFAULT SYSDATE,
    status VARCHAR2(30) NOT NULL,
    codigo_exemplar INT NOT NULL,
    prontuario_leitor INT NOT NULL,
    prontuario_func INT NOT NULL,
    FOREIGN KEY (codigo_exemplar) REFERENCES exemplar(codigo_exemplar),
    FOREIGN KEY (prontuario_leitor) REFERENCES leitor(prontuario_leitor),
    FOREIGN KEY (prontuario_func) REFERENCES funcionario(prontuario_func)
    
);

CREATE SEQUENCE seq_dev
nocycle
start with 1
increment by 1
maxvalue 9999;

CREATE TABLE devolucao (
    id_devolucao INT PRIMARY KEY,
    data_dev DATE NOT NULL,
    codigo_emp INT NOT NULL,
    codigo_exemplar INT NOT NULL,
    prontuario_leitor INT NOT NULL,
    FOREIGN KEY (codigo_emp) REFERENCES emprestimo(codigo_emp),
    FOREIGN KEY (codigo_exemplar) REFERENCES exemplar(codigo_exemplar),
    FOREIGN KEY (prontuario_leitor) REFERENCES leitor(prontuario_leitor)
);

