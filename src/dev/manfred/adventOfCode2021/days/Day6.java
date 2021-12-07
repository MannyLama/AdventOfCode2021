package dev.manfred.adventOfCode2021.days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 implements Runnable<Day6> {
    private final Event<Long> event = new Event("src/resources/data/day6.txt", 6, 386640L, 1733403626279L);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day6 run1() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        var data2 = data.get(0).split(",");
        Fish[] fishes = new Fish[data2.length];

        for (int i = 0; i < data2.length; i++) {
            fishes[i] = new Fish(Integer.parseInt(data2[i]));
        }

        for (int i = 0; i < 80; i++) {
            var newFishCount = 0;
            for (Fish fish : fishes) {
                if (fish.getDays() == 0) {
                    newFishCount++;
                    fish.reset();
                } else {
                    fish.deceremntByDay();
                }
            }
            Fish[] newFish = new Fish[newFishCount];
            for (int j = 0; j < newFishCount; j++) {
                newFish[j] = new Fish(8);
            }
            if (newFish.length > 1)
                fishes = Stream.concat(Arrays.stream(fishes), Arrays.stream(newFish)).toArray(Fish[]::new);

        }

        event.getResultPrinter().formatResult(event, true, (long) fishes.length);
        return this;
    }

    @Override
    public Day6 run2() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        var fishes = Arrays.stream(data.get(0).split(",")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
        HashMap<Long, Long> fishmap = new HashMap<>();

        for (var fish : fishes)
            fishmap.put(fish, fishmap.containsKey(fish) ? fishmap.get(fish) + 1 : 1);

        HashMap<Long, Long> fishMap2 = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            for (var e : fishmap.entrySet()) {
                if (e.getKey() == 0) {
                    fishMap2.put(8L, fishMap2.containsKey(8L) ? fishMap2.get(8L) + e.getValue() : e.getValue());
                    fishMap2.put(6L, fishMap2.containsKey(6L) ? fishMap2.get(6L) + e.getValue() : e.getValue());
                } else {
                    fishMap2.put(e.getKey() - 1, fishMap2.containsKey(e.getKey() - 1) ? fishMap2.get(e.getKey() - 1) + e.getValue() : e.getValue());
                }
            }
            fishmap = fishMap2;
            fishMap2 = new HashMap<>();
        }
        event.getResultPrinter().formatResult(event, false, fishmap.values().stream().mapToLong(count -> count).sum());
        return this;
    }

    private static class Fish {
        byte days;

        public Fish(int days) {
            this.days = (byte) days;
        }

        public byte getDays() {
            return days;
        }

        public void deceremntByDay() {
            this.days--;
        }

        public void reset() {
            days = 6;
        }

        @Override
        public String toString() {
            return days + ",";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fish fish = (Fish) o;
            return days == fish.days;
        }

        @Override
        public int hashCode() {
            return Objects.hash(days);
        }
    }
}
