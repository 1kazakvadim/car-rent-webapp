INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('1', 'Vadim', 'Kazak', 'Viktorovich', 'male', '1993-04-06', 'AB3202396', '3060493C010PB4',
        '2017-06-19', '2027-06-19', 'REPUBLIC OF BELARUS', 'MINISTRY OF INTERNAL AFFAIRS',
        'REPUBLIC OF BELARUS', 'BREST');

INSERT INTO `car_rent`.`user` (`username`, `password`, `email`, `phone_number`, `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('admin', '$2a$10$qkvOI97twvl9Q/tDZwA16O30Xj7pl7Yeu.bmgzO8KNfNOknj5g3lC', 'admin@admin.by',
        '+375293590590', '1', '1', 'ACTIVE', '2021-06-28');

INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('2', 'Vasya', 'Pupkin', 'Vladimirovich', 'male', '1991-06-08', 'AB3212396',
        '3060543C010PB4',
        '2017-07-19', '2027-07-19', 'REPUBLIC OF BELARUS', 'MINISTRY OF INTERNAL AFFAIRS',
        'REPUBLIC OF BELARUS', 'MINSK');

INSERT INTO `car_rent`.`user` (`id`, `username`, `password`, `email`, `phone_number`,
                               `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('2', 'manager', '$2a$10$6OVt.pHvcISc5Cb7cqQLvuDvUF75XKmpYUGA0OH3krQCt5vqo6xxO',
        'manager@manager.by',
        '+375293590591', '2', '2', 'ACTIVE', '2021-06-30');

INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('3', 'Nastya', 'Petrovskaya', 'Romanovna', 'female', '1994-07-06', 'AB1232396',
        '4060435C010PB4',
        '2017-08-12', '2027-08-20', 'REPUBLIC OF BELARUS', 'MINISTRY OF INTERNAL AFFAIRS',
        'REPUBLIC OF BELARUS', 'GRODNO');

INSERT INTO `car_rent`.`user` (`id`, `username`, `password`, `email`, `phone_number`,
                               `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('3', 'user1', '$2a$10$Y8QJOuspZvYqeKyrWo00cO47X8o0FhO/0FnYOMpej8ynmykdedpxe',
        'user1@user1.by',
        '+375293590592', '3', '3', 'ACTIVE', '2021-06-30');

INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('4', 'Sergey', 'Shukalo', 'Sergeevich', 'male', '1994-05-06', 'AB3202131', '3060547C010PB4',
        '2017-06-20', '2027-06-20', 'REPUBLIC OF BELARUS',
        'MINISTRY OF INTERNAL AFFAIRS', 'REPUBLIC OF BELARUS', 'IVANOVO');

INSERT INTO `car_rent`.`user` (`id`, `username`, `password`, `email`, `phone_number`,
                               `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('4', 'user2', '$2a$10$cXgI6AoNvRM7YlSvyUFa8eCGMpynDamRcv5EJDVsRhWJpcFP0qCk2',
        'user2@user2.by',
        '+375293590593', '4', '3', 'ACTIVE', '2021-06-30');



INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`,
                              `car_transmission_id`, `engine_type_id`, `engine_volume`,
                              `number_of_seats`, `fuel_consumption`,
                              `rental_cost`)
VALUES ('1', '1', 'A8', 'red', '1', '1', '1', '1', '2', '4', '4.4', '5000');
INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`,
                              `car_transmission_id`, `engine_type_id`, `engine_volume`,
                              `number_of_seats`, `fuel_consumption`,
                              `rental_cost`)
VALUES ('2', '2', 'w124', 'blue', '2', '2', '2', '2', '2', '5', '5.6', '6000');
INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`,
                              `car_transmission_id`, `engine_type_id`, `engine_volume`,
                              `number_of_seats`, `fuel_consumption`,
                              `rental_cost`)
VALUES ('3', '3', 'x7', 'black', '3', '3', '1', '3', '2', '7', '2.3', '7000');

INSERT INTO `car_rent`.`car_order` (`id`, `user_id`, `car_id`, `date_of_issue`, `date_of_return`,
                                    `cancellation`, `reason_of_cancellation`, `total_cost`)
VALUES ('1', '1', '1', '2021-07-08', '2021-07-09', '0', ' ', '100');
INSERT INTO `car_rent`.`car_order` (`user_id`, `car_id`, `date_of_issue`, `date_of_return`,
                                    `cancellation`, `reason_of_cancellation`, `total_cost`)
VALUES ('2', '2', '2021-07-06', '2021-07-11', '0', ' ', '200');







