CREATE TABLE `tb_usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativa` bit(1) DEFAULT NULL,
  `conta_nao_bloqueada` bit(1) DEFAULT NULL,
  `conta_nao_expirada` bit(1) DEFAULT NULL,
  `credenciais_nao_bloqueadas` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hymsg6hpnk88xrsy9kdsuhur9` (`email`)
);
