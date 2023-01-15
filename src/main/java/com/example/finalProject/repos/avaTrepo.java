package com.example.finalProject.repos;

import com.example.finalProject.Entity.avatime;
import com.example.finalProject.pojo.available;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface avaTrepo extends JpaRepository<avatime, Integer> {
  @Query(
    nativeQuery = true,
    value = "select  at_id, from_time,to_Time from (select current_date avaDate, ava_time.*  from ava_time ) atime left join  appointment on  atime.from_time = appointment.time and avaDate  = date where id is null"
  )
  List<available> availabletime();
}
