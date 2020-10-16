package com.julieandco.bookcrossing.customer.repo;

import com.julieandco.bookcrossing.customer.entity.Customer;
import com.julieandco.bookcrossing.customer.entity.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    //@Query("SELECT u FROM User u WHERE u.username = :username")
    Customer findByUsername(String username);
}
