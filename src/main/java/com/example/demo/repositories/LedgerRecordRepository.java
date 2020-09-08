package com.example.demo.repositories;


import com.example.demo.models.LedgerRecord;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRecordRepository extends JpaRepository<LedgerRecord, UUID> {

  @EntityGraph(attributePaths = {"budgetRecord", "ledgerRecordItems.financeEntity"})
  List<LedgerRecord> findAll();

}
