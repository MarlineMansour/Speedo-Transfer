package com.example.demo.entity;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.enums.AccountCurrency;
import com.example.demo.DTO.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;

    private String accountName;

    private String accountDescription;
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Transaction> transactions = new HashSet<>();

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .id(this.id)
                .accountNumber(this.accountNumber)
                .accountType(this.accountType)
                .balance(this.balance)
                .currency(this.currency)
                .accountName(this.accountName)
                .accountDescription(this.accountDescription)
                .active(this.active)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .transactions(this.transactions.stream().map(Transaction::toDTO).collect(Collectors.toSet()))
                .build();
    }


}
