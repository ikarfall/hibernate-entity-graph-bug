package com.example.demo.repositories;

import com.example.demo.models.BudgetRecord;
import com.example.demo.models.FinanceEntity;
import com.example.demo.models.LedgerRecord;
import com.example.demo.models.LedgerRecordItem;
import com.example.demo.models.Trigger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LedgerRecordRepositoryTest {

  @Autowired
  private LedgerRecordRepository ledgerRecordRepository;
  @Autowired
  private TriggerRepository triggerRepository;
  @Autowired
  private BudgetRecordRepository budgetRecordRepository;
  @Autowired
  private FinanceEntityRepository financeEntityRepository;

  @Test
  void graphTest() {

    var trigger = Trigger.builder().name("trigger").build();
    triggerRepository.save(trigger);
    var budgetRecord = BudgetRecord.builder()
        .amount(100)
        .trigger(trigger)
        .build();

    var client = FinanceEntity.builder().name("client").build();
    var vendor = FinanceEntity.builder().name("vendor").build();
    financeEntityRepository.save(client);
    financeEntityRepository.save(vendor);

    budgetRecordRepository.save(budgetRecord);
    var ledgerRecord = LedgerRecord.builder()
        .budgetRecord(budgetRecord)
        .ledgerRecordItem(LedgerRecordItem.builder()
            .financeEntity(client)
            .build())
        .ledgerRecordItem(LedgerRecordItem.builder()
            .financeEntity(vendor)
            .build())
        .trigger(trigger).build();
    ledgerRecord.getLedgerRecordItems().forEach(i -> i.setLedgerRecord(ledgerRecord));

    ledgerRecordRepository.save(ledgerRecord);

    //this line write warning system out and you can see one extra select of fetching trigger entity
    // that is not specified in @EntityGraph
    // you can switch hibernate version in gradle to see that this issue appeared in hibernate 4.5.20
    var ledgerRecords = ledgerRecordRepository.findAll();

  }
}
