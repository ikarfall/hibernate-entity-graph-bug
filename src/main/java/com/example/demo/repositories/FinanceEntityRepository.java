package com.example.demo.repositories;


import com.example.demo.models.FinanceEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceEntityRepository extends JpaRepository<FinanceEntity, UUID> {

}
