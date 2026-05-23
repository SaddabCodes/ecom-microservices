package com.sadcodes.ecomapplication.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"id", "firstName", "lastName", "email", "phone", "address"})
@Data
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String  phone;
    private AddressDto address;

}
