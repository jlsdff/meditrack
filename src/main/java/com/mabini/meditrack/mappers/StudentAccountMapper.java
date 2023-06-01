package com.mabini.meditrack.mappers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentAccountMapper {

    // min of 12 digits and max of 12 digits
    @Min(value = 100000000000L, message = "LRN must be 12 digits long")
    @Max(value = 999999999999L, message = "LRN must be 12 digits long")
    private long LRN;

    // pattern of email
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;

    // pattern of password (at least 1 uppercase, 1 lowercase, 1 number, 1 special character, and 8 characters long)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Must contain be at least 8 characters long, least 1 uppercase, 1 lowercase, 1 number, and 1 special character")
    private String password;

    private String confirmedPassword;

}
