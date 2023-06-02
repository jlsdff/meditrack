package com.mabini.meditrack.mappers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StudentLrnMapper {

    // min of 12 digits
    @Min(value = 100000000000L, message = "LRN must be 12 digits")
    @Max(value = 999999999999L, message = "LRN must be 12 digits")
    private long LRN;
}
