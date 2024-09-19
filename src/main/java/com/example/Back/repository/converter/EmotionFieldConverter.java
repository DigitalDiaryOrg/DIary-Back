package com.example.Back.repository.converter;

import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmotionFieldConverter  implements AttributeConverter<List<String>, String> {

    static final String SEPARATOR = ",";
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return String.join(SEPARATOR, stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return new ArrayList<>(Arrays.asList(s.split(SEPARATOR)));
    }
}
