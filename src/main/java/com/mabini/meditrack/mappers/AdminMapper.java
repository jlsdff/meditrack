package com.mabini.meditrack.mappers;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminMapper {
    

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email")
    private String email;   

    private String password;
    

}
