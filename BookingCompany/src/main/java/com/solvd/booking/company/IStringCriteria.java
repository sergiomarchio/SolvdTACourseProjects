package com.solvd.booking.company;

import java.util.Collection;

@FunctionalInterface
public interface IStringCriteria <T>{
    T find(Collection<T> collection, String string);
}
