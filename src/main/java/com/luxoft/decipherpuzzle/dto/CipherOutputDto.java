package com.luxoft.decipherpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CipherOutputDto {

    private String input;
    private PatternDto result;
}
