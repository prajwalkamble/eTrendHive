package com.etrendhive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etrendhive.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
