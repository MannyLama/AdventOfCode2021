package dev.manfred.adventOfCode2021.days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day7 implements Runnable<Day7> {
    private final Event<Long> event = new Event<>("src/resources/data/day7.txt", 7, 355989L, 102245489L);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day7 run1() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        List<Integer> crabPositions = Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        int maxPosition = crabPositions.stream().mapToInt(p -> p).max().getAsInt();
        Map<Integer, Long> feulsCosts = new HashMap<>();
        for (int i = 0; i < maxPosition; i++) {
            Long feulCosts = 0L;
            for (Integer crabPosition : crabPositions) {
                if (crabPosition > i) {
                    feulCosts += crabPosition - i;
                } else {
                    feulCosts += i - crabPosition;

                }
            }
            feulsCosts.put(i, feulCosts);
        }
        event.getResultPrinter().formatResult(event, true, Collections.min(feulsCosts.values()));
        return this;
    }

    @Override
    public Day7 run2() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        List<Integer> crabPositions = Arrays.stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        int maxPosition = crabPositions.stream().mapToInt(p -> p).max().getAsInt();
        Map<Integer, Long> feulsCosts = new HashMap<>();
        for (int i = 0; i < maxPosition; i++) {
            Double feulCosts = 0.0;
            for (Integer crabPosition : crabPositions) {
                int distance = Math.abs(crabPosition - i);
               // feulCosts += (double) (distance * (distance + 1)) / 2;
                feulCosts += getCost(i, distance);
            }
            feulsCosts.put(i, feulCosts.longValue());
        }

        event.getResultPrinter().formatResult(event, false, Collections.min(feulsCosts.values()));
        return this;
    }

    public int getCost(int num, int moves) {
        int burn = 1;
        int sum = 0;
        for (int i = 0; i < moves; i++) {
            sum += burn;
            burn++;
            num--;
        }
        return sum;
    }
}
