package com.frequentis.training;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.BeanUtils;

public class PropertyAnalyzer {

    public double getPropertyComplexityRatio(Class<?> clazz) {
        PropertyDescriptor[] allPropertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
        List<PropertyDescriptor> filteredPropertyDescriptors = removeClassPropertyDescriptor(allPropertyDescriptors);

        long simpleTypeCount = filteredPropertyDescriptors.stream()
                                                          .filter(p -> BeanUtils.isSimpleProperty(p.getPropertyType()))
                                                          .count();

        return ((double) simpleTypeCount) / filteredPropertyDescriptors.size() * 100;
    }

    public double getPackagePropertyComplexityRatio(final String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        return classes.stream()
                      .mapToDouble(this::getPropertyComplexityRatio)
                      .average()
                      .orElse(0.0);
    }

    /**
     * The first element in the list is always the Class property descriptor.
     */
    private List<PropertyDescriptor> removeClassPropertyDescriptor(final PropertyDescriptor[] propertyDescriptors) {
        return Arrays.stream(propertyDescriptors)
                     .skip(1)
                     .collect(Collectors.toList());
    }
}
