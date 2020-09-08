package com.example.demo.repositories;


import com.example.demo.models.BudgetRecord;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRecordRepository extends JpaRepository<BudgetRecord, UUID> {


}
