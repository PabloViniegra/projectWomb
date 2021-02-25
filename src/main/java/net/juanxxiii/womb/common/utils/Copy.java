package net.juanxxiii.womb.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class Copy {

    public Copy() {
        throw new IllegalArgumentException("don't instance util class");
    }

    public static void copyNonNullProperties(Object s, Object t) {
        BeanUtils.copyProperties(s, t, getNullPropertyNames(s));
    }
    public static void copyNullProperties(Object s, Object t) {
        BeanUtils.copyProperties(s, t );
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> src.getPropertyValue(name) == null)
                .toArray(String[]::new);
    }


}
