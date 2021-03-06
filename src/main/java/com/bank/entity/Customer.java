package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int customerId;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "aadhaar")
    private String aadhaar;

    @Column(name = "email")
    private String email;

    public Customer(){}
    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.addressId = builder.addressId;
        this.name = builder.name;
        this.mobileNo = builder.mobileNo;
        this.aadhaar = builder.aadhaar;
        this.email = builder.email;
    }

    public static Builder newCustomer() {
        return new Builder();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class Builder {
        private int customerId;
        private int addressId;
        private String name;
        private String mobileNo;
        private String aadhaar;
        private String email;

        public Builder() {
        }

        public Customer build() {
            return new Customer(this);
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder addressId(int addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder aadhaar(String aadhaar) {
            this.aadhaar = aadhaar;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }
}
