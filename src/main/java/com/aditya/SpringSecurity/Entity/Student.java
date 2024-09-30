package com.aditya.SpringSecurity.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int marks;
}
