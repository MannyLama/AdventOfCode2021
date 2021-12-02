package dev.manfred.adventOfCode2021;

public interface Runnable<T> {
    Event<?> getEvent();

    T run1();

    T run2();
}
