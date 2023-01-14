package com.luxoft.decipherpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DecipherOutputDto {

    private String input;

    List<PatternDto> result;
}
