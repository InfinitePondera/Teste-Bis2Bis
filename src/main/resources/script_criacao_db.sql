CREATE DATABASE teste_bis2bis;

CREATE TABLE produto(id int, nome varchar(256), descricao varchar(256), mercado_alvo varchar(256), tecnologias varchar array);

INSERT INTO produto VALUES (1, 'Gubee E-Commerce', 'Plataforma de E-Commerce da Gubee', 'Desktop', ARRAY['Angular', 'Java']);
INSERT INTO produto VALUES (2, 'Gubee Portal Mobile', 'Portal Mobile para Servicos Gubee', 'Mobile', ARRAY['Java', 'Kotlin']);
INSERT INTO produto VALUES (3, 'Gubee Anti-Fraudes', 'Servico anti-fraudes Gubee', 'Web', ARRAY['Kotlin', 'Angular']);