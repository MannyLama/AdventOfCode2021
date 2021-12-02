package dev.manfred.adventOfCode2021.Days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.ResultPrinter;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.List;

public class Day2 implements Runnable<Day2> {
    private final Event<Integer> event = new Event<>("src/resources/data/day2.txt", 2, 1813801, 1960569556);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day2 run1() {
        List<String> nums = eventLoader.getData2(event.getDataPath());

        Submarine sub = new Submarine();
        for (int i = 1; i < nums.size(); i++) {
            var command = nums.get(i).split(" ")[0];
            var value = Integer.parseInt(nums.get(i).split(" ")[1]);

            switch (Commandos.valueOf(command)) {
                case up -> sub.depth -= value;
                case down -> sub.depth += value;
                case forward -> sub.horizon += value;
            }
        }

        event.getResultPrinter().formatResult(event, true, sub.horizon * sub.depth);
        return this;
    }

    @Override
    public Day2 run2() {
        List<String> nums = eventLoader.getData2(event.getDataPath());

        Submarine sub = new Submarine();
        for (int i = 1; i < nums.size(); i++) {
            var command = nums.get(i).split(" ")[0];
            var value = Integer.parseInt(nums.get(i).split(" ")[1]);

            switch (Commandos.valueOf(command)) {
                case up -> sub.aim -= value;
                case down -> sub.aim += value;
                case forward -> {
                    sub.horizon += value;
                    sub.depth += sub.aim * value;
                }
            }
        }
        event.getResultPrinter().formatResult(event, false, sub.horizon * sub.depth);
        return this;
    }

    private enum Commandos {
        forward, down, up
    }

    private static class Submarine {
        int horizon = 0;
        int depth = 0;
        int aim = 0;
    }
}
