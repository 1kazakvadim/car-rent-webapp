package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderRepository extends JpaRepository<CarOrder, Integer> {

}
