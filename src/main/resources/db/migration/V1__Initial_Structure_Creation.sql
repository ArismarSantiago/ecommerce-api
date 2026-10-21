CREATE TABLE `category_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `name` varchar(200) NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKrb94bheg622eimoa60fl8of79` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `customer_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `city` varchar(30) NOT NULL,
                               `neighborhood` varchar(50) NOT NULL,
                               `number` varchar(10) NOT NULL,
                               `state` varchar(30) NOT NULL,
                               `street` varchar(200) NOT NULL,
                               `zip_code` varchar(10) NOT NULL,
                               `email` varchar(200) NOT NULL,
                               `name` varchar(200) NOT NULL,
                               `phone_number` varchar(14) NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKdh8j49uagmqf7t0699diw2m8m` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `products_tb` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `description` varchar(400) NOT NULL,
                               `name` varchar(250) NOT NULL,
                               `price` decimal(38,2) NOT NULL,
                               `status` enum('DISCONTINUED','IN_STOCK','OUT_STOCK') DEFAULT NULL,
                               `stock` int NOT NULL,
                               `products_id` bigint DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKi58d76lxxst1c4a5ih6qjcp9a` (`name`),
                               KEY `FK6qrmcatyeqeio6hhj12nyo6q3` (`products_id`),
                               CONSTRAINT `FK6qrmcatyeqeio6hhj12nyo6q3` FOREIGN KEY (`products_id`) REFERENCES `category_tb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_tb` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_at` datetime(6) DEFAULT NULL,
                            `status` enum('CANCELED','COMPLETED','IN_TRANSIT','PREPARING','PROCESSING','RETURNING','SHIPPED','WAITING_PAYMENT') DEFAULT NULL,
                            `total` decimal(38,2) DEFAULT NULL,
                            `customer_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FK63okldqsuv2kdxqaqyoe309ee` (`customer_id`),
                            CONSTRAINT `FK63okldqsuv2kdxqaqyoe309ee` FOREIGN KEY (`customer_id`) REFERENCES `customer_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_items_tb` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `quantity` int NOT NULL,
                                  `status` enum('CANCELED','COMPLETED','WAITING_PAYMENT') DEFAULT NULL,
                                  `sub_total` decimal(38,2) NOT NULL,
                                  `unit_price` decimal(38,2) NOT NULL,
                                  `order_id` bigint DEFAULT NULL,
                                  `product_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKpdkdhpjxtjtiswwcto263mkir` (`order_id`),
                                  KEY `FK6r77a9odkx5x7wdxt557e539t` (`product_id`),
                                  CONSTRAINT `FK6r77a9odkx5x7wdxt557e539t` FOREIGN KEY (`product_id`) REFERENCES `products_tb` (`id`),
                                  CONSTRAINT `FKpdkdhpjxtjtiswwcto263mkir` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment_tb` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `amount` decimal(38,2) NOT NULL,
                              `payment_date` datetime(6) DEFAULT NULL,
                              `status` enum('CANCELED','COMPLETED','PAID','PENDING','REFUND_PROCESSING') DEFAULT NULL,
                              `order_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `UKpordxuc5af4e0cy9m12loq1va` (`order_id`),
                              CONSTRAINT `FKmghf68sa00o27s0o8ry9p3lf` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `shipping` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `city` varchar(30) NOT NULL,
                            `neighborhood` varchar(50) NOT NULL,
                            `number` varchar(10) NOT NULL,
                            `state` varchar(30) NOT NULL,
                            `street` varchar(200) NOT NULL,
                            `zip_code` varchar(10) NOT NULL,
                            `shipped_at` datetime(6) DEFAULT NULL,
                            `status` enum('CANCELED','DELIVERED','IN_TRANSIT','PENDING','PREPARING','RETURNING','SHIPPED','WAITING_PAYMENT') DEFAULT NULL,
                            `tracking_code` varchar(20) NOT NULL,
                            `order_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK5tqgqx54jwxmib2c1f49l677a` (`order_id`),
                            CONSTRAINT `FKqu86bhpbvohq2buy7jiwfcnjv` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
