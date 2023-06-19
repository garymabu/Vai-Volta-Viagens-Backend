CREATE TABLE `localization` (
    `cityId` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `airportName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `airportCode` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;