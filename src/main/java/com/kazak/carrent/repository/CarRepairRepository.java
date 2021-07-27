package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepairRepository extends JpaRepository<CarRepair, Integer> {

  List<CarRepair> findAllByCarOrder_User(User user);

}
