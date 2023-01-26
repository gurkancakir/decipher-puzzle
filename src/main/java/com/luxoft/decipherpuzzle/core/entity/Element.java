package com.luxoft.decipherpuzzle.core.entity;



import java.math.BigDecimal;
import java.util.Map;

public interface Element {

    BigDecimal value(Map<Character, Integer> values);
}
