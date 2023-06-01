package com.mabini.meditrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mabini.meditrack.models.EmergencyRecord;

@Repository
public interface EmergencyRecordRepository extends JpaRepository<EmergencyRecord, Long>{
    
}
