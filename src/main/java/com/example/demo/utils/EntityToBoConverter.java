package com.example.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityToBoConverter {

    public static <T, U> U convert(T entity, Class<U> boClass) {
        if (entity == null) {
            return null;
        }

        try {
            U bo = boClass.getDeclaredConstructor().newInstance();

            Field[] entityFields = entity.getClass().getDeclaredFields();
            Field[] boFields = boClass.getDeclaredFields();

            for (Field entityField : entityFields) {
                entityField.setAccessible(true);
                Object value = entityField.get(entity);

                for (Field boField : boFields) {
                    if (boField.getName().equals(entityField.getName()) &&
                            boField.getType().equals(entityField.getType())) {
                        boField.setAccessible(true);
                        boField.set(bo, value);
                        break;
                    }
                }
            }

            return bo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert entity to BO", e);
        }
    }

    public static <T, U> List<U> convertList(List<T> entityList, Class<U> boClass) {
        List<U> boList = new ArrayList<>();
        for (T entity : entityList) {
            boList.add(convert(entity, boClass));
        }
        return boList;
    }
}

