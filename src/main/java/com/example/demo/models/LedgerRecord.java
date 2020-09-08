package com.example.demo.models;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LedgerRecord {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  private BudgetRecord budgetRecord;

  @OneToMany(mappedBy = "ledgerRecord", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @Fetch(FetchMode.SUBSELECT)
  @Singular
  private List<LedgerRecordItem> ledgerRecordItems;

  @ManyToOne
  private Trigger trigger;

}
