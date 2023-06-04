package com.mabini.meditrack.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.mabini.meditrack.models.Record;

@Service
public interface IRecordService {

    List<Record> findAllByStudent_LRN(long lrn);

    Record save(Record record);
    
}
