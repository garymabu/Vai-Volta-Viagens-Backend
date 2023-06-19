CREATE TABLE `trip` (
    `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `arrivalDatetime` datetime,
    `departureDatetime` datetime,
    `arrivalLocalId` varchar(36),
    `originLocalId` varchar(36),
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`),
    FOREIGN KEY (`arrivalLocalId`) REFERENCES `localization` (`id`),
    FOREIGN KEY (`originLocalId`) REFERENCES `localization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;