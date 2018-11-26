
-- CATEGORIA LITERARIA

DELETE FROM categoria_literaria;

INSERT INTO categoria_literaria 
(codigo_categoria,descricao)
VALUES(seq_catLiteraria.nextval,'Livro');

INSERT INTO categoria_literaria 
(codigo_categoria,descricao)
VALUES(seq_catLiteraria.nextval,'Revista');

INSERT INTO categoria_literaria 
(codigo_categoria,descricao)
VALUES(seq_catLiteraria.nextval,'Jornal');

INSERT INTO categoria_literaria 
(codigo_categoria,descricao)
VALUES(seq_catLiteraria.nextval,'Relatório técnico');

INSERT INTO categoria_literaria 
(codigo_categoria,descricao)
VALUES(seq_catLiteraria.nextval,'Trabalho acadêmico');

SELECT * FROM categoria_literaria;
-- OBRA
DELETE obra_literaria;

INSERT INTO obra_literaria
(id_obra,isbn,qtd_exemplares,nrm_edicao,data_publicacao,editora,titulo_obra,codigo_categoria)
VALUES(seq_obraLiteraria.nextval,'123ACD',5,1,'25-05-2003','Sabugosa','Java como Programar',1);

INSERT INTO obra_literaria
(id_obra,isbn,qtd_exemplares,nrm_edicao,data_publicacao,editora,titulo_obra,codigo_categoria)
VALUES(seq_obraLiteraria.nextval,'321DCA',3,3,'20-09-2001','Sabugosa NEW','PHP como sofrer',1);

INSERT INTO obra_literaria
(id_obra,isbn,qtd_exemplares,nrm_edicao,data_publicacao,editora,titulo_obra,codigo_categoria)
VALUES(seq_obraLiteraria.nextval,'456DBA',2,5,'15-02-2012','Sabugosa NEW','C como sofrer, a revolta!',1);

INSERT INTO obra_literaria
(id_obra,isbn,qtd_exemplares,nrm_edicao,data_publicacao,editora,titulo_obra,codigo_categoria)
VALUES(seq_obraLiteraria.nextval,'333BBA',4,1,'21-10-2015','IFSP','Jogos educacionais',5);

INSERT INTO obra_literaria
(id_obra,isbn,qtd_exemplares,nrm_edicao,data_publicacao,editora,titulo_obra,codigo_categoria)
VALUES(seq_obraLiteraria.nextval,'335CAA',4,1,'12-01-2011','IFSP','Auto ajuda para ADS',5);


-- CATEGORIA LEITOR
DELETE FROM categoria_leitor;

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Aluno de curso técnico',15);

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Aluno de graduação',15);

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Aluno de pos graduação',30);

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Professor',30);

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Funcionário',30);

INSERT INTO categoria_leitor (codigo_categoria,descricao,tempo_emprestimo)
VALUES (seq_categoriaLeitor.nextval,'Usuário externo',7);

SELECT * FROM categoria_leitor;

-- Leitor

INSERT INTO leitor(prontuario_leitor,nome,endereco,cidade,estado,telefone,data_nascimento,email,status,rg,codigo_categoria)
VALUES (1710052,'Caio','Av Dom Pedro I','São Carlos','SP',123456,'29-06-1998','caio@gmail.com','DISPONIVEL',1,2);

INSERT INTO leitor(prontuario_leitor,nome,endereco,cidade,estado,telefone,data_nascimento,email,status,rg,codigo_categoria)
VALUES (1710125,'Frodinho','Av Dom Pedro I','São Carlos','SP',121256,'10-02-1999','frodinho@gmail.com','DISPONIVEL',2,2);

INSERT INTO leitor(prontuario_leitor,nome,endereco,cidade,estado,telefone,data_nascimento,email,status,rg,codigo_categoria)
VALUES (1710324,'Diego','Av Dom Pedro I','São Carlos','SP',42216,'05-02-1989','diego@gmail.com','DISPONIVEL',3,2);

INSERT INTO leitor(prontuario_leitor,nome,endereco,cidade,estado,telefone,data_nascimento,email,status,rg,codigo_categoria)
VALUES (171234,'George','Av Dom Pedro II','São Carlos','SP',42216,'06-05-1999','george@gmail.com','DISPONIVEL',4,2);

INSERT INTO leitor(prontuario_leitor,nome,endereco,cidade,estado,telefone,data_nascimento,email,status,rg,codigo_categoria)
VALUES (1710157,'Pita','Av Dom Pedro III','São Carlos','SP',42216,'15-07-1989','pita@gmail.com','DISPONIVEL',5,2);

SELECT * FROM leitor;


-- Funcionario 


INSERT INTO funcionario(prontuario_func,endereco,data_nascimento,telefone,nome)
VALUES (seq_func.nextval,'Paschoal de melo', '25-03-1987',12585,'Rupertson');

INSERT INTO funcionario(prontuario_func,endereco,data_nascimento,telefone,nome)
VALUES (seq_func.nextval,'Avenida dom pedro I', '15-05-1887',0000,'Lucas');


INSERT INTO funcionario(prontuario_func,endereco,data_nascimento,telefone,nome)
VALUES (seq_func.nextval,'Paschoal de melo 3', '22-01-1287',14445,'Geosvaldo');


INSERT INTO funcionario(prontuario_func,endereco,data_nascimento,telefone,nome)
VALUES (seq_func.nextval,'Paschoal de melo 2', '05-02-1687',12585,'Aclanildo');


INSERT INTO funcionario(prontuario_func,endereco,data_nascimento,telefone,nome)
VALUES (seq_func.nextval,'Esquina de frente', '06-06-1666',4444,'Mestre dos magos');