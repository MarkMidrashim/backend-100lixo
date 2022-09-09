CREATE TABLE endereco (
	ID_ENDERECO INT NOT NULL AUTO_INCREMENT,
	NR_CEP CHAR(9) NOT NULL,
	DS_ENDERECO VARCHAR(50) NOT NULL,
	NR_ENDERECO INT NOT NULL,
	DS_COMPLEMENTO VARCHAR(50) NULL,
	NM_BAIRRO VARCHAR(50) NOT NULL,
	CD_UF CHAR(2) NOT NULL,
	NM_CIDADE VARCHAR(35) NOT NULL,
	PRIMARY KEY (ID_ENDERECO),
	KEY NR_CEP(NR_CEP)
);
