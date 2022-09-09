CREATE TABLE IF NOT EXISTS usuario_permissao (
  ID_USUARIO INT UNSIGNED NOT NULL,
  ID_PERMISSAO INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_USUARIO`,`ID_PERMISSAO`),
  KEY `fk_usuario_permissao_permissao` (`ID_PERMISSAO`),
  CONSTRAINT `fk_usuario_permissao` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID_USUARIO`),
  CONSTRAINT `fk_usuario_permissao_permissao` FOREIGN KEY (`ID_PERMISSAO`) REFERENCES `permissoes` (`ID_PERMISSAO`)
) ENGINE=InnoDB;