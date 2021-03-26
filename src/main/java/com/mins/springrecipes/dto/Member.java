package com.mins.springrecipes.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class Member {

    @NotNull
    @Size(min = 2)
    private String name;

    @Min(14)
    @Max(150)
    private int age;

}
