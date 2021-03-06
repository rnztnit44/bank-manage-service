package com.bank.repository;

import com.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "Select * from customer C where C.customer_id=:cId", nativeQuery = true)
    Customer findByCustomerId(int cId);

    @Query(value = "Select * from customer C where C.aadhaar=:aadhaarId", nativeQuery = true)
    Customer findByAadhaarId(String aadhaarId);
}
