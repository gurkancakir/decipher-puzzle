package com.luxoft.decipherpuzzle.core.entity;


import com.luxoft.decipherpuzzle.core.exception.InputNotAcceptException;

import java.math.BigDecimal;
import java.util.Map;

public interface Element {

    BigDecimal value(Map<Character, Integer> values) throws InputNotAcceptException;
}
