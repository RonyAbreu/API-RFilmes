CREATE TABLE `tb_filmes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ano_de_lancamento` int DEFAULT NULL,
  `categoria` varchar(250) DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  `diretor` varchar(255) DEFAULT NULL,
  `tempo_em_minutos` int DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `url_da_capa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);