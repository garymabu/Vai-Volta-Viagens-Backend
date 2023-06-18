CREATE TABLE `modal` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `capacity` varchar(100) NOT NULL,
  `yearManufacture` varchar(10) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `active` boolean NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;