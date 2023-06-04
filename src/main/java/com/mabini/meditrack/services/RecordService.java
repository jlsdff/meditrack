package com.mabini.meditrack.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mabini.meditrack.repositories.RecordRepository;
import com.mabini.meditrack.models.Record;

@Service
public class RecordService implements IRecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findAllByStudent_LRN(long lrn) {

        return recordRepository.findAllByStudent_LRN(lrn);
        
    }

    @Override
    public Record save(Record record) {
        return recordRepository.saveAndFlush(record);
    }
    
}
