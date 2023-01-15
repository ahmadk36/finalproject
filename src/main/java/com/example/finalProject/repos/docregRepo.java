package com.example.finalProject.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.finalProject.Entity.docreg;
import com.example.finalProject.pojo.doctorr;
public interface docregRepo extends JpaRepository <docreg , Integer >{
    docreg findByUsername(String username);
    
    @Query(nativeQuery = true, value = "SELECT * FROM regester_doc ")
	List<doctorr> findByalldoc();
}
