package com.gal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gal.model.Cab;

public interface CabRepository extends JpaRepository<Cab, Integer> {

}
