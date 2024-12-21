CREATE DATABASE  IF NOT EXISTS `trabalho_lucas` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trabalho_lucas`;

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE `estoque` (
  `vencimentos_id` int NOT NULL AUTO_INCREMENT,
  `data_fabricao` varchar(10) NOT NULL,
  `data_vencimento` varchar(10) NOT NULL,
  `aviso_previo` varchar(255) NOT NULL,
  PRIMARY KEY (`vencimentos_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;


LOCK TABLES `estoque` WRITE;
INSERT INTO `estoque` VALUES (1,'2023-01-01','2023-05-15','2023-04-15');
UNLOCK TABLES;


CREATE DATABASE  IF NOT EXISTS `trabalho_lucas` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trabalho_lucas`;

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE `produtos` (
  `produtos_id` int NOT NULL,
  `data_fabricao` varchar(10) NOT NULL,
  `local_armazamento` varchar(255) NOT NULL,
  `nome_produto` varchar(255) NOT NULL,
  `quantidades` varchar(45) NOT NULL,
  `data_chegada` varchar(10) NOT NULL,
  KEY `produtos_vencimentos_id_idx` (`produtos_id`),
  CONSTRAINT `funcionarios_produtos_id` FOREIGN KEY (`produtos_id`) REFERENCES `usuarios` (`funionarios_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `produtos_vencimentos_id` FOREIGN KEY (`produtos_id`) REFERENCES `estoque` (`vencimentos_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `produtos` WRITE;
INSERT INTO `produtos` VALUES (1,'2024-02-15','rubia','bolacha','5','2024-02-14');
UNLOCK TABLES;


CREATE DATABASE  IF NOT EXISTS `trabalho_lucas` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trabalho_lucas`;

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `funionarios_id` int NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) NOT NULL,
  `data_de_admicao` varchar(45) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`funionarios_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` VALUES (1,'op','2024-01-15','lucas');
UNLOCK TABLES;
