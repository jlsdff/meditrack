package com.mabini.meditrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mabini.meditrack.models.Record;


@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByStudent_LRN(long lrn);
    
}
