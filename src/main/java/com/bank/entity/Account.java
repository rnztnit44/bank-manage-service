package com.bank.entity;

import com.bank.enums.AccountType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
public class Account {
//    AccountType accountType;
//    String accountNo;
//    int amount;
    @Id
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "account_type")
    private String accountType;

    private Account(Builder builder) {
        this.accountNo = builder.accountNo;
        this.customerId = builder.customerId;
        this.amount = builder.amount;
        this.accountType = builder.accountType;
    }

    public static Builder newAccount() {
        return new Builder();
    }


    public static final class Builder {
        private String accountNo;
        private String customerId;
        private int amount;
        private String accountType;

        public Builder() {
        }

        public Account build() {
            return new Account(this);
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }
    }
}