package com.solvd.booking.company;

@FunctionalInterface
public interface IFindableCriteria<T extends Findable> {
     boolean meets(T element);
}
