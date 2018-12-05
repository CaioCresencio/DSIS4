SELECT status, prontuario FROM leitor;
SELECT status FROM leitor WHERE prontuario = 1710052;
SELECT tempo_emprestimo FROM categoria_leitor JOIN leitor USING(codigo_categoria)
WHERE prontuario = 1710052;  

INSERT INTO emprestimo (codigo_emp,data_dev,data_emp,status,codigo_exemplar,prontuario_leitor,prontuario_func)
VALUES (1,'25-10-2018','23-10-2018','EM ANDAMENTO',1,1710052,1);
INSERT INTO emprestimo (codigo_emp,data_dev,data_emp,status,codigo_exemplar,prontuario_leitor,prontuario_func)
VALUES (2,'25-10-2018','23-10-2018','EM ANDAMENTO',2,1710052,1);

insert into exemplar values(1,'DISPONIVEL',1,1);
insert into exemplar values(2,'DISPONIVEL',1,2);
insert into exemplar values(3,'DISPONIVEL',1,3);
 
select * from obra_literaria;
select * from emprestimo;
select * from devolucao;
UPDATE emprestimo SET data_dev = '25-11-18', status = 'EM ANDAMENTO' where codigo_emp = 2;
UPDATE exemplar SET status = 'EMPRESTADO' WHERE status = 'DISPONIVEL' AND codigo_exemplar = 1;
select * from exemplar;
select * from funcionario;
select * from leitor;

SELECT COUNT(codigo_emp) FROM emprestimo WHERE status = 'EM ANDAMENTO' AND prontuario_leitor = 1710052 AND 
codigo_exemplar = (SELECT codigo_exemplar FROM obra_literaria JOIN(exemplar) USING (codigo_exemplar));

SELECT COUNT(codigo_emp) FROM obra_literaria JOIN exemplar USING(id_obra)
JOIN emprestimo USING(codigo_exemplar) WHERE prontuario_leitor = 1710052
AND id_obra =  (SELECT id_obra FROM obra_literaria JOIN exemplar USING (id_obra) WHERE codigo_exemplar = 3);

SELECT COUNT(codigo_emp) FROM emprestimo WHERE status = 'EM ANDAMENTO' AND codigo_exemplar <>  1 AND prontuario_leitor = 
            (SELECT prontuario_leitor FROM emprestimo  WHERE codigo_exemplar = 1); 

UPDATE leitor SET status = 'DISPONIVEL';
UPDATE  leitor SET status  = 'BLOQUEAO' WHERE prontuario_leitor = 
(SELECT prontuario_leitor FROM leitor JOIN emprestimo USING(prontuario_leitor) WHERE codigo_exemplar = 1);
