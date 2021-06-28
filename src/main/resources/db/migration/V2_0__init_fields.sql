INSERT INTO `car_rent`.`passport_data` (`id`, `first_name`, `last_name`, `middle_name`, `sex`,
                                        `date_of_birth`, `passport_number`, `identification_number`,
                                        `date_of_issue`, `date_of_expiry`, `place_of_birth`,
                                        `authority`, `nationality`, `registration`)
VALUES ('1', 'Vadim', 'Kazak', 'Viktorovich', 'male', '1993-04-06', 'AB3202396', '3060493C010PB4',
        '2017-06-19', '2027-06-19', 'REPUBLIC OF BELARUS', 'MINISTRY OF INTERNAL AFFAIRS',
        'REPUBLIC OF BELARUS', 'Brest');

INSERT INTO `car_rent`.`user_role` (`name`)
VALUES ('ADMIN');
INSERT INTO `car_rent`.`user_role` (`name`)
VALUES ('MANAGER');
INSERT INTO `car_rent`.`user_role` (`name`)
VALUES ('USER');

INSERT INTO `car_rent`.`user` (`username`, `password`, `email`, `phone_number`, `passport_data_id`,
                               `user_role_id`, `status`, `created_at`)
VALUES ('admin', '$2a$10$qkvOI97twvl9Q/tDZwA16O30Xj7pl7Yeu.bmgzO8KNfNOknj5g3lC', 'admin@admin.by',
        '+375293590590', '1', '1', 'ACTIVE', '2021-06-28');
