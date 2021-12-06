package dev.manfred.adventOfCode2021.days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 implements Runnable<Day1> {
    private final Event<Integer> event = new Event<>("src/resources/data/day1.txt", 1, 1581, 1618);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<Integer> getEvent() {
        return event;
    }

    @Override
    public Day1 run1() {
        int answer = 0;
        List<Integer> nums = eventLoader.getData2(event.getDataPath()).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                answer += 1;
            }
        }
        event.getResultPrinter().formatResult(event, true, answer);
        return this;
    }

    @Override
    public Day1 run2() {
        int answer = 0;
        List<Integer> nums = eventLoader.getData2(event.getDataPath()).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> parsed = new ArrayList<>();

        for (int i = 0; i < nums.size() - 2; i++) {
            parsed.add(nums.get(i) + nums.get(i + 1) + nums.get(i + 2));
        }
        for (int i = 1; i < parsed.size(); i++) {
            if (parsed.get(i) > parsed.get(i - 1)) {
                answer += 1;
            }
        }
        event.getResultPrinter().formatResult(event, false, answer);
        return this;
    }
}
