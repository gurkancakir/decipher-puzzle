package com.luxoft.decipherpuzzle.controller;

import com.luxoft.decipherpuzzle.core.Cipher;
import com.luxoft.decipherpuzzle.core.Decipher;
import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;
import com.luxoft.decipherpuzzle.dto.DecipherOutputDto;
import com.luxoft.decipherpuzzle.dto.InputDto;
import com.luxoft.decipherpuzzle.dto.CipherOutputDto;
import com.luxoft.decipherpuzzle.dto.PatternDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ApiController {

    private final Decipher decipher;
    private final Cipher cipher;


    @PostMapping("cipher")
    public CipherOutputDto cipher(@RequestBody InputDto inputDto) {
        String encoded = cipher.encode(inputDto.getPattern());
        return new CipherOutputDto(inputDto.getPattern(), new PatternDto(encoded));
    }

    @PostMapping("decipher")
    public DecipherOutputDto decipher(@RequestBody InputDto inputDto) throws InputNotAcceptException {
        List<String> possibleResultList = decipher.decode(inputDto.getPattern());
        List<PatternDto> patternDtoList = possibleResultList.stream()
                .map(PatternDto::new)
                .toList();
        return new DecipherOutputDto(inputDto.getPattern(), patternDtoList);
    }
}
