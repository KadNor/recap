package com.frequentis.training;

import java.beans.PropertyDescriptor;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.BeanUtils;

public class PropertyAnalyzer {

    public double getPropertyComplexityRatio(Class<?> clazz) {
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
        int simpleTypeCount = 0;
        int complexTypeCount = 0;

        for (int i = 1; i < propertyDescriptors.length; ++i) {
            if (BeanUtils.isSimpleProperty(propertyDescriptors[i].getPropertyType())) {
                simpleTypeCount++;
            } else {
                complexTypeCount++;
            }
        }

        return ((double) simpleTypeCount) / (simpleTypeCount + complexTypeCount) * 100;
    }

    public double getPackagePropertyComplexityRatio(final String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        return classes.stream()
                      .mapToDouble(this::getPropertyComplexityRatio)
                      .average()
                      .orElse(0.0);
    }
}
