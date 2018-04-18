-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: db_sistema_almoxarifado
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (3,'INFORMÁTICA'),(4,'LIMPEZA'),(5,'HIGIÊNICO'),(6,'ESCRITÓRIO'),(7,'DESCARTAVÉIS'),(8,'ALIMENTÍCIOS'),(9,'MÉDICO HOSPITALAR ');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidade`
--

DROP TABLE IF EXISTS `entidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `whatsapp` varchar(20) DEFAULT NULL,
  `cpf_responsavel` varchar(20) NOT NULL,
  `bairro` varchar(60) NOT NULL,
  `cidade` varchar(60) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `pais` varchar(35) NOT NULL,
  `rua` varchar(60) NOT NULL,
  `uf` varchar(35) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `responsavel` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ryt8kl2vwh5e0f3s12r0ckvv7` (`setor_id`),
  CONSTRAINT `FK_ryt8kl2vwh5e0f3s12r0ckvv7` FOREIGN KEY (`setor_id`) REFERENCES `setor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidade`
--

LOCK TABLES `entidade` WRITE;
/*!40000 ALTER TABLE `entidade` DISABLE KEYS */;
INSERT INTO `entidade` VALUES (2,'(83)98196-8885','contato@cookiesoft.com.br','(83)3286-1190','(83)98186-8885','929.300.774-68','CENTRO','CAAPORÃ','S/N','BRASIL','RUA SEBASTIÃO PEDRO PEREIRA','PARAIBA','GERENCIA DE SISTEMAS','COOKIESOFT MANAGER','cookiesoft2017',2),(3,'(83)9999-9999','almoxarifado@caapora.pb.gov.br','(83)9999-9999','','044.210.974-11','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO','PARAIBA','ALMOXARIFADO','ADRIANA SANTOS','adriana123',1),(4,'(83)99999-9999','compras@gmail.com','(83)9999-9999','(83)99999-9999','683.842.758-31','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO','PARAIBA','DIVISÃO  DE COMPRAS','MARIA LUÍZA','1234562017',1),(5,'(83)99195-5650','elienefvalentim@hotmail.com','(83)3286-2117','(83)99195-5650','039.060.634-02','CENTRO','CAPORÃ','31','BRASIL','SALOMÃO VELOSO','PARAIBA','PROTOCOLO ','ELIANE FELIX','1912782017',1),(6,'(83)99197-8003','jbreno.gonzaga@gmail.com','(83)3286-1220','(83)99197-8003','097.184.804-12','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO ','PARAIBA','INFRAESTRUTURA ','JOSÉ BRENO G.VELOSO ','infra@2017',8),(7,'(83)99183-6593','ribeiroleninha10@gmail.com','(83)3286-2130','(83)99183-6593','071.558.684-00','CENTRO','CAAPORÃ','S/N','BRASIL','TANCREDO NEVES','PARAIBA','SEDE SEMADE','CLAUDILENE CANDIDO','680868',6),(8,'(83)99388-3712','samuel.andrae@yahoo.com.br','(83)3286-2195','(83)99388-3712','054.771.094-16','CENTRO','CAAPORÃ','S/N','BRASIL','CLEMENTE FERREIRA ','PARAIBA','CONTABILIDADE','SAMUEL ANDRADE ','samandras',4),(10,'(83)99869-5587','secinto@caapora.pb.gov.br','(83)3286-4789','(83)99869-5587','076.391.844-01','CENTRO','CAAPORÃ ','S/N','BRASIL','CLEMENTE FERREIRA','PARAIBA','SEDE CONTROLE INTERNO','FELIPE JOSÉ F.CHAVES','chves123',9),(11,'(83)99970-5443','betania.felix73@hotmail.com','(83)3328-6125','(83)99970-5443','026.490.754-06','CENTRO','CAAPORÃ','143','BRASIL','TANCREDO NEVES ','PARAIBA','CASA DO EMPREENDEDOR','BETÂNIA FELIX ','empreender',6),(12,'(83)99633-3282','ivaneidethilly@hotmail.com','(83)3286-1259','(83)99633-3282','100.980.044-25','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO','PARAIBA','SEDE DIRETORIA DE TRASPORTES ','IVANEIDE GOMES ','neide7663',8),(13,'(83)99938-5298','leosilva31@yahoo.com.br','(83)3286-7895','(83)99938-5298','051.625.494-44','CENTRO','CAAPORÃ','S/N','BRASIL','ANTONIO AMARAL DA CUNHA','PARAIBA','JUVENTUDE CULTURA TURISMO E EVENTOS ','ISRAEL BEZERRA','alice2017',3),(14,'(83)99931-5000','antoniomello2011@hotmail.com','(83)3286-4597','(83)99931-5000','088.456.964-05','CENTRO','CAAPORÃ','S/N','BRASIL','TANCREDO NEVES','PARAIBA','TRIBUTOS','ANTONIO TAVARES  ','tony1234',4);
/*!40000 ALTER TABLE `entidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidade_grupo`
--

DROP TABLE IF EXISTS `entidade_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidade_grupo` (
  `entidade_id` int(11) NOT NULL,
  `grupo_id` int(11) NOT NULL,
  KEY `FK_hpbiablb7loj4mwggiqnpbg8o` (`grupo_id`),
  KEY `FK_slbrhwjp5x9gjgyv7mpxo53ls` (`entidade_id`),
  CONSTRAINT `FK_hpbiablb7loj4mwggiqnpbg8o` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_slbrhwjp5x9gjgyv7mpxo53ls` FOREIGN KEY (`entidade_id`) REFERENCES `entidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidade_grupo`
--

LOCK TABLES `entidade_grupo` WRITE;
/*!40000 ALTER TABLE `entidade_grupo` DISABLE KEYS */;
INSERT INTO `entidade_grupo` VALUES (2,1),(2,2),(2,3),(3,2),(3,3),(4,2),(5,2),(6,2),(7,2),(8,2),(10,2),(11,2),(12,2),(13,2);
/*!40000 ALTER TABLE `entidade_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrada_estoque`
--

DROP TABLE IF EXISTS `entrada_estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrada_estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_recebimento` date NOT NULL,
  `data_validade` date DEFAULT NULL,
  `entrega_integral` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) NOT NULL,
  `nota_fiscal` varchar(100) NOT NULL,
  `perecivel` varchar(5) NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  `quantidade_minima` decimal(10,2) NOT NULL,
  `tipo_embalagem` varchar(30) NOT NULL,
  `tipo_unidade` varchar(30) NOT NULL,
  `valor_notafiscal` decimal(10,2) DEFAULT NULL,
  `estoque_id` int(11) NOT NULL,
  `fornecedor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qme2erwrvl3kgsubutu9vty1s` (`estoque_id`),
  KEY `FK_85kc0d7q3vxl7tur9cf0x0aus` (`fornecedor_id`),
  CONSTRAINT `FK_85kc0d7q3vxl7tur9cf0x0aus` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `FK_qme2erwrvl3kgsubutu9vty1s` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrada_estoque`
--

LOCK TABLES `entrada_estoque` WRITE;
/*!40000 ALTER TABLE `entrada_estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrada_estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_atualizacao` date NOT NULL,
  `quant_total` decimal(10,2) NOT NULL,
  `fk_produto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rmj2ijh5y9phi4nhso2qp254t` (`fk_produto`),
  CONSTRAINT `FK_rmj2ijh5y9phi4nhso2qp254t` FOREIGN KEY (`fk_produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `site` varchar(255) DEFAULT NULL,
  `telefone` varchar(20) NOT NULL,
  `whatsapp` varchar(20) DEFAULT NULL,
  `bairro` varchar(60) NOT NULL,
  `cidade` varchar(60) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `pais` varchar(60) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `uf` varchar(40) NOT NULL,
  `nome_fantasia` varchar(100) NOT NULL,
  `ramo_empresa` varchar(60) NOT NULL,
  `responsavel` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'02.775.367/0001-02','sconterraneo@uol.com.br','','(81)3615-1500','','CENTRO','ITAMBÉ','232','BRASIL','JANUÁRIO FILIZOLA','PERNAMBUCO','COMERCIAL ITAMBÉ LTDA','ATACADO E VAREJO','KINKA'),(2,'21.117.496/0001-58','neregionalnatal@gmail.com','','(85)5289-3376','(85)99784-6582','BARRO VERMELHO ','CAAPORÃ','1302','BRASIL ','DESEMBARGADOR REGULO TINOCO  ','PARAIBA','NATAL DISTRIBUIÇÃO E REVENDA LTDA ','ATACADO ','LEONARDO '),(3,'11.172.643/0001-39','agropecuaria@hotmail.com','','(83)3603-0840','(83)93456-1258','NOVAIS ','JOÃO PESSOA ','298','BRARISL ','GENERAL PEDRO GONÇALVES DE MEDEIRO   ','PARAIBA','VIA CAMPO COMERCIO DE PRODUTOS AGROPECUÁRIO LTA  ','AGROPECUÁRIO ','JORGE '),(4,'12.681.292/0001-54','prodmixsaue@hotmail.com','','(83)3566-9925','(83)99635-2556','TORRE','JOÃO PESSOA ','S/N','BRASIL ','AV JUARES TAVARES ','PARAIBA','RONALDO MARCOLINO GUIMARAES ','MÉDICO HOSPITALAR','RONALDO');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  `nome` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'ADMINISTRADOR','ROLE_ADMINISTRADOR'),(2,'SOLICITANTE','ROLE_SOLICITANTE'),(3,'OPERADOR','ROLE_OPERADOR');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informativo`
--

DROP TABLE IF EXISTS `informativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informativo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_cadastro` date NOT NULL,
  `descricao` text NOT NULL,
  `titulo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informativo`
--

LOCK TABLES `informativo` WRITE;
/*!40000 ALTER TABLE `informativo` DISABLE KEYS */;
/*!40000 ALTER TABLE `informativo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_atendido`
--

DROP TABLE IF EXISTS `item_atendido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_atendido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quant_atendida` decimal(19,2) NOT NULL,
  `estoque_id` int(11) NOT NULL,
  `solicitacao_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o4xbavxl4vox42tvb56kw7c0j` (`estoque_id`),
  KEY `FK_vdbtdldmp247o2ouocwafeob` (`solicitacao_id`),
  CONSTRAINT `FK_o4xbavxl4vox42tvb56kw7c0j` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`),
  CONSTRAINT `FK_vdbtdldmp247o2ouocwafeob` FOREIGN KEY (`solicitacao_id`) REFERENCES `solicitacao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_atendido`
--

LOCK TABLES `item_atendido` WRITE;
/*!40000 ALTER TABLE `item_atendido` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_atendido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_solicitado`
--

DROP TABLE IF EXISTS `item_solicitado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_solicitado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quant_solicitada` decimal(19,2) NOT NULL,
  `estoque_id` int(11) NOT NULL,
  `solicitacao_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pxp5n2rty9stmgut3xfwak6fq` (`estoque_id`),
  KEY `FK_rnrw0jyt5m7vi0a371ntbluv5` (`solicitacao_id`),
  CONSTRAINT `FK_pxp5n2rty9stmgut3xfwak6fq` FOREIGN KEY (`estoque_id`) REFERENCES `estoque` (`id`),
  CONSTRAINT `FK_rnrw0jyt5m7vi0a371ntbluv5` FOREIGN KEY (`solicitacao_id`) REFERENCES `solicitacao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_solicitado`
--

LOCK TABLES `item_solicitado` WRITE;
/*!40000 ALTER TABLE `item_solicitado` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_solicitado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_cadastro` date NOT NULL,
  `descricao` text NOT NULL,
  `status` varchar(20) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacao`
--

LOCK TABLES `notificacao` WRITE;
/*!40000 ALTER TABLE `notificacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_el0d58htywfs914w4grf6aoa0` (`categoria_id`),
  CONSTRAINT `FK_el0d58htywfs914w4grf6aoa0` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (5,'GARRAFA DE 1L','ÁGUA SANITÁRIA IGUAL 1L',4),(6,'GARRAFA PET 1 LITRO','ÁCIDO MURIATO 1L',4),(7,'','AMACIANTE SONHO 2L',4),(8,'','TONER LASER JET 83A',3),(9,'','ESPONJA DE AÇO ',4),(10,'','DESINFETANTE MAX POL 2L',4),(11,'','DETERGENTE MAX POL 2L',4),(12,'','FLANELA 39X59',4),(13,'','FÓSFORO ',4),(14,'','LUVA LÁTEX M SCOTH BRITE',4),(15,'','LUVA LÁTEX G SCOTH BRITE',4),(16,'','DESENGORDURANTE MULTE USO 500ML   ',4),(17,'','ODORIZANTE SANITÁRIA TRI-D',4),(18,'','PÁ P/ LIXO COM CABO LONGO ',4),(19,'','ALGODÃO FLOC ',5),(20,'','PANO DE PRATO ',4),(21,'','PAPEL HIGIÊNICO FLORAL 30M C/ 16X4',4),(22,'','POLIDOR MAX POL 500ML',4),(23,'','PRENDEDOR DE ROUPA MADEIRA',4),(24,'','RODO 40CM VILA LIMPA ',4),(25,'','SABÃO REDENÇÃO EM BARRA ',4),(26,'','SABÃO EM PÓ BEM-TE-VI 500G',4),(27,'','SABONETE LÍQUIDO TRI-D 2L',4),(28,'','SACO PARA LIXO 30L ',4),(29,'','SACO PARA LIXO 50L',4),(30,'','SACO PARA LIXO 100L',4),(31,'','PANO DE CHÃO ',4),(32,'','TOUCAS ALLIM ',4),(33,'','VASSOURA PIAÇAVA G',4),(34,'','VASSOURA DE NYLON G',4),(35,'','ÁLCOOL 1L TUBARÃO ',4),(36,'','ÁLCOOL EM GEL P/ LIMPEZA 500ML ',4),(37,'','ESCOVA OVAL ',4),(38,'','BALDE 10L IGUAL ',4),(39,'','LUSTRA MÓVEIS ',4),(40,'','AVENTAL NAPA  XAVIER ',4),(41,'','BACIA PLÁSTICA 43CM ',4),(42,'','INSETICIDA AEROSOL 300ML',4),(43,'','LIMPA VIDRO 750ML ',4),(44,'','CORDA P/ VARAL 6MT',4),(45,'','BOM AR 360ML',4),(46,'','COLONIA INFANTIL 300ML',5),(47,'','SHAMPOO LORYS KIDS 500ML',5),(48,'','CONDICIONADOR LORYS KIDS 500ML ',5),(49,'','CREME P/ PENTEAR LORY KIDS 300ML',5),(50,'','CREME DENTAL KIDS 50G',5),(51,'','PENTE P/ CABELO COM CABO ',5),(52,'','SABONETE INFANTIL KIDS ',5),(53,'','SABONETEIRA SANREMO ',5),(54,'','TALCO INFANTIL 200G',5),(55,'','COTONETE  75G',5),(56,'','ESCOVA DENTAL KIDS ',5),(57,'','FRALDA DESCARTÁVEIS M C/24',5),(58,'','FRALDA DESCARTÁVEIS G C/24 ',5),(59,'','FRALDA DESCARTÁVEIS XG C/24',5),(60,'','PENTE FINO COM CABO ',5),(61,'','COPO DESCARTÁVEIS 180ML',7),(62,'','BACIA PLÁSTICO 50CM',4),(63,'','ALFINETE P/ MAPA 50UND',6),(64,'','COLA SILICONE GROSSA C/35',6),(65,'','CANETA ESFEROGRÁFICA AZUL ',6),(66,'','CANETA ESFEROGRÁFICA PRETA',6),(67,'','CANETA ESFEROGRÁFICA VERMELHA ',6),(68,'','CARTÃO GUACHE CORES',6),(69,'','CLIPS Nº 2/0 GALV.100UND',6),(70,'','CLIPS Nº4/0 GALV.50UND ',6),(71,'','CLIPS Nº 6/0 GALV.25UND',6),(72,'','COLA BRANCA 500G',6),(73,'','ENVELOPE 185MMX248MM C/ 100',6),(74,'','ENVELOPE 260MMX360MM C/ 100',6),(75,'','ENVELOPE 310MMX410MM C/100',6),(76,'','ESTILETE ESTREITO ',6),(77,'','ESTILETE METAL LARGO ',6),(78,'','EITA EMP 50MMX50MM',6),(79,'','FITA CREPE 25MMX30MM',6),(80,'','FITA DUPLA FACE 25MMX30MM',6),(81,'','GRAMPEADOR METAL 20CM P/25FLS',6),(82,'','GRAMPEADOR METAL 11,5CM P/25FLS ',6),(83,'','GRAMPO 26/6 C/5000UND',6),(84,'','LÁPIS GRAFITE HB C/144',6),(85,'','MARCA TEXTO VERDE',6),(86,'','MARCA TEXTO AZUL ',6),(87,'','MARCA TEXTO ROSA',6),(88,'','PAPEL CAMURÇA 40X60 CORES  ',6),(89,'','PAPEL CREPOM 48X2 CORES ',6),(90,'','PASTA CLASSIF  PAPEL  SIMPLES TRILHO ',6),(91,'','PASTA DE PAPELÃO C/ ABAS DE ELÁSTICO ',6),(92,'','PERCEVEJO LAT C/50UND',6),(93,'','PERFURADOR METAL 2FUROS 20FLS',6),(94,'','PERFURADOR METAL 2FUROS 30FLS',6),(95,'','MARCADOR P/ QUADRO BR CORES ',6),(96,'','PISTOLA P/ COLA QUENTE GRANDE ',6),(97,'','TNT CORES ',6),(98,'','ALMOFADA PARA CARIMBO ',6),(99,'','FITA ADESIVA 12MMX50MM',6),(100,'','EXTRATOR DE GRAMPO METAL',6),(101,'','MARCADOR PERMANENTE  ',6),(102,'','PAPEL A4 C/500',6),(103,'','CORRETIVO LÍQUIDO 18ML',6),(104,'','PASTA AZ REG A4',6),(105,'','CX ARQUIVO PAPELÃO ',6),(106,'','CARTÃO COLOR SETE CORES ',6),(107,'PACOTE COM 1000 LFS','PAPEL INTERFOLHADO ',5),(108,'','PAPEL DE SEDA 48X60 CORES',6),(109,'PACOTE COM 25UND','SACO PARA LIXO 60L',4),(110,'COLETOR A GRANEL  50ML','COLETOR S/PA N ESTERIL A GRANEL 50ML',9),(111,'ESTANTES 60 FUROS ','ESTANTES PERFURADAS 60 FUROS',9),(112,'TERMÔMETRO DIGITAL ','TERMÔMETRO MAX E MINIMO DIGITAL ',9);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES (1,'','SECRETARIA DE ADMINISTRAÇÃO'),(2,'','ADMINISTRADOR'),(3,'','SECRETARIA DE CULTURA'),(4,'','SECRETARIA DE FINANÇAS'),(5,'','SECRETARIA DE AÇÃO SOCIAL'),(6,'','SECRETARIA DE MEIO AMBIENTE E DESENVOLVIMENTO URBANO'),(7,'','SECRETARIA DE EDUCAÇÃO'),(8,NULL,'SECRETARIA DE INFRA-ESTRUTURA'),(9,NULL,'SECRETARIA DE CONTROLE INTERNO');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitacao`
--

DROP TABLE IF EXISTS `solicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_solicitacao` datetime NOT NULL,
  `numero` varchar(255) NOT NULL,
  `observacao` text,
  `quant_item_atendido` decimal(10,2) NOT NULL,
  `quant_item_solicitado` decimal(10,2) NOT NULL,
  `status_solicitacao` varchar(20) NOT NULL,
  `entidade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h5ubv13khtcrb92xd9bts17c2` (`entidade_id`),
  CONSTRAINT `FK_h5ubv13khtcrb92xd9bts17c2` FOREIGN KEY (`entidade_id`) REFERENCES `entidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacao`
--

LOCK TABLES `solicitacao` WRITE;
/*!40000 ALTER TABLE `solicitacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-31 22:44:31
