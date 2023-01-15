package com.example.finalProject.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.finalProject.Entity.patientEnt;
import com.example.finalProject.pojo.patientq;

@Repository
public interface paiRepo extends JpaRepository <patientEnt , Integer> {
    patientEnt findByUsername(String username);
    
    @Query(nativeQuery = true, value = "SELECT patientid , username , pai_name ,phone_num , age , gender FROM regester_patinet ")
	List<patientq> findByall();

    
    
}
