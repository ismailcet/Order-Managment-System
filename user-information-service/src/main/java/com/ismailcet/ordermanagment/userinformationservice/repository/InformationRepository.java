package com.ismailcet.ordermanagment.userinformationservice.repository;

import com.ismailcet.ordermanagment.userinformationservice.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

}
