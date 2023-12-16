package com.ismailcet.ordermanagment.cargoservice.repository;

import com.ismailcet.ordermanagment.cargoservice.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Long> {
}
