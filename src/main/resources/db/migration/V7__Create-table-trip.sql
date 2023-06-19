CREATE TABLE `trip` (
    `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `arrival_datetime` datetime,
    `departure_datetime` datetime,
    `arrival_localization_id` varchar(36),
    `departure_localization_id` varchar(36),
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`),
    FOREIGN KEY (`arrival_localization_id`) REFERENCES `localization` (`id`),
    FOREIGN KEY (`departure_localization_id`) REFERENCES `localization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;