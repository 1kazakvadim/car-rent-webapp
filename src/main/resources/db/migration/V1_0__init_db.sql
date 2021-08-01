CREATE TABLE `car_brand`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `car_brand_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `car_body`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `body_type_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `car_class`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `class_name_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `engine_type`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `engine_type_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `car_transmission`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

CREATE TABLE `car`
(
    `id`                  int                                                    NOT NULL AUTO_INCREMENT,
    `car_brand_id`        int                                                    NOT NULL,
    `model`               varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `color`               varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `car_body_id`         int                                                    NOT NULL,
    `car_class_id`        int                                                    NOT NULL,
    `car_transmission_id` int                                                    NOT NULL,
    `engine_type_id`      int                                                    NOT NULL,
    `engine_volume`       double unsigned                                        NOT NULL,
    `number_of_seats`     int unsigned                                           NOT NULL,
    `fuel_consumption`    double                                                 NOT NULL,
    `rental_cost`         double unsigned                                        NOT NULL,
    PRIMARY KEY (`id`),
    KEY `car_body_id_idx` (`car_body_id`),
    KEY `car_brand_id_idx` (`car_brand_id`),
    KEY `car_class_id_idx` (`car_class_id`),
    KEY `car_transmission_id_idx` (`car_transmission_id`),
    KEY `engine_type_id_idx` (`engine_type_id`),
    CONSTRAINT `car_body_id` FOREIGN KEY (`car_body_id`) REFERENCES `car_body` (`id`),
    CONSTRAINT `car_brand_id` FOREIGN KEY (`car_brand_id`) REFERENCES `car_brand` (`id`),
    CONSTRAINT `car_class_id` FOREIGN KEY (`car_class_id`) REFERENCES `car_class` (`id`),
    CONSTRAINT `car_transmission_id` FOREIGN KEY (`car_transmission_id`) REFERENCES `car_transmission` (`id`),
    CONSTRAINT `engine_type_id` FOREIGN KEY (`engine_type_id`) REFERENCES `engine_type` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `passport_data`
(
    `id`                    int                                                     NOT NULL AUTO_INCREMENT,
    `first_name`            varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `last_name`             varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `middle_name`           varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `sex`                   varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `date_of_birth`         date                                                    NOT NULL,
    `passport_number`       varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `identification_number` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `date_of_issue`         date                                                    NOT NULL,
    `date_of_expiry`        date                                                    NOT NULL,
    `place_of_birth`        varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `authority`             varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `nationality`           varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `registration`          varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `identification_number_UNIQUE` (`identification_number`),
    UNIQUE KEY `passport_number_UNIQUE` (`passport_number`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `user_role`
(
    `id`   int                                                    NOT NULL AUTO_INCREMENT,
    `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_group_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `user`
(
    `id`               int                                                     NOT NULL AUTO_INCREMENT,
    `username`         varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `password`         varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `email`            varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `phone_number`     varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `passport_data_id` int                                                     NOT NULL,
    `user_role_id`     int                                                     NOT NULL,
    `status`           varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci  NOT NULL,
    `created_at`       date                                                    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_UNIQUE` (`username`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
    KEY `passport_data_id_idx` (`passport_data_id`),
    KEY `user_role_id_idx` (`user_role_id`),
    CONSTRAINT `passport_data_id` FOREIGN KEY (`passport_data_id`) REFERENCES `passport_data` (`id`),
    CONSTRAINT `user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `car_order`
(
    `id`                     int             NOT NULL AUTO_INCREMENT,
    `user_id`                int             NOT NULL,
    `car_id`                 int             NOT NULL,
    `date_of_issue`          date            NOT NULL,
    `date_of_return`         date            NOT NULL,
    `cancellation`           tinyint         NOT NULL                                DEFAULT '0',
    `reason_of_cancellation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    `total_cost`            double unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    KEY `car_id_idx` (`car_id`),
    CONSTRAINT `car_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `car_repair`
(
    `id`                 int                                  NOT NULL AUTO_INCREMENT,
    `order_id`           int                                  NOT NULL,
    `damage_information` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
    `repair_cost`        double unsigned                      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `order_id_idx` (`order_id`),
    CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `car_order` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;