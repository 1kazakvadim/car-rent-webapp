package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderRepository extends JpaRepository<CarOrder, Integer> {

  List<CarOrder> findAllByUser(User user);

  List<CarOrder> findAllByCar_Id(Integer carId);

}
