INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('1', 'Vadim', 'Kazak', 'Viktorovich', 'male', '1993-04-06', 'AB3202396', '3060493C010PB4',
        '2017-06-19', '2027-06-19', 'REPUBLIC OF BELARUS', 'MINISTRY OF INTERNAL AFFAIRS',
        'REPUBLIC OF BELARUS', 'Brest');



INSERT INTO `car_rent`.`user` (`username`, `password`, `email`, `phone_number`, `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('admin', '$2a$10$qkvOI97twvl9Q/tDZwA16O30Xj7pl7Yeu.bmgzO8KNfNOknj5g3lC', 'admin@admin.by',
        '+375293590590', '1', '1', 'ACTIVE', '2021-06-28');

INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`, `car_transmission_id`, `engine_type_id`, `engine_volume`, `number_of_seats`, `fuel_consumption`, `rental_cost`) VALUES ('1', '1', 'A8', 'red', '1', '1', '1', '1', '2', '4', '4.4', '5000');
INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`, `car_transmission_id`, `engine_type_id`, `engine_volume`, `number_of_seats`, `fuel_consumption`, `rental_cost`) VALUES ('2', '2', 'w124', 'blue', '2', '2', '2', '2', '2', '5', '5.6', '6000');
INSERT INTO `car_rent`.`car` (`id`, `car_brand_id`, `model`, `color`, `car_body_id`, `car_class_id`, `car_transmission_id`, `engine_type_id`, `engine_volume`, `number_of_seats`, `fuel_consumption`, `rental_cost`) VALUES ('3', '3', 'x7', 'black', '3', '3', '1', '3', '2', '7', '2.3', '7000');



