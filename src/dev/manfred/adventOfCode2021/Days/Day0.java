package dev.manfred.adventOfCode2021.Days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.List;

public class Day0 implements Runnable<Day0> {
    private final Event<Integer> event = new Event<>("src/resources/data/day0.txt", 0, -1, -1);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day0 run1() {
        List<String> data = eventLoader.getData2(event.getDataPath());


        event.getResultPrinter().formatResult(event, true, null);
        return this;
    }

    @Override
    public Day0 run2() {
        List<String> data = eventLoader.getData2(event.getDataPath());


        event.getResultPrinter().formatResult(event, false, null);
        return this;
    }

    private enum Temp {

    }

    private static class AClass {
        //int integer = 0;
        //String string = 0;
    }
}
