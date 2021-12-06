package dev.manfred.adventOfCode2021.days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 implements Runnable<Day3> {
    private final Event<Integer> event = new Event<>("src/resources/data/day3.txt", 3, 3885894, 4375225);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day3 run1() {
        List<String> data = eventLoader.getData2(event.getDataPath());

        List<BitCounter> a = new ArrayList<>();
        for (String number : data) {
            for (int j = 0; j < number.length(); j++) {
                if (a.size() <= j)
                    a.add(new BitCounter());

                var number2 = Integer.parseInt("" + number.charAt(j));
                if (number2 == 1) a.get(j).one++;
                if (number2 == 0) a.get(j).zero++;
            }
        }

        event.getResultPrinter().formatResult(event, true, Integer.parseInt(getBinary(a), 2) * Integer.parseInt(getBinaryReverse(a), 2));
        return this;
    }

    @Override
    public Day3 run2() {
        var data = eventLoader.getData2(event.getDataPath());
        var parsed = parseDataToBinary(data);
        var byteSize = data.get(0).length();


        // get oxygen
        var oxygen = List.copyOf(parsed);
        for (int i = 0; i < byteSize; i++) {
            var counter = new BitCounter();
            for (Binary binary : oxygen) {
                counter.count(binary.number.get(i));
            }

            int position = i;
            if (counter.zero == counter.one || counter.zero < counter.one) {
                oxygen = oxygen.stream().filter(b -> b.number.get(position)).collect(Collectors.toList());
            } else {
                oxygen = oxygen.stream().filter(b -> !b.number.get(position)).collect(Collectors.toList());
            }
        }

        // get co2
        var co2 = List.copyOf(parsed);
        for (int i = 0; i < byteSize; i++) {
            var counter = new BitCounter();
            for (Binary binary : co2) {
                counter.count(binary.number.get(i));
            }
            int position = i;
            if (co2.size() > 1) {
                if (counter.zero < counter.one || counter.zero == counter.one) {
                    co2 = co2.stream().filter(b -> !b.number.get(position)).collect(Collectors.toList());
                } else {
                    co2 = co2.stream().filter(b -> b.number.get(position)).collect(Collectors.toList());
                }
            }
        }
        event.getResultPrinter().formatResult(event, false, Integer.parseInt(getBinary2(oxygen.stream().findFirst().get()), 2) * Integer.parseInt(getBinary2(co2.stream().findFirst().get()), 2));
        return this;
    }

    private List<Binary> parseDataToBinary(List<String> data) {
        List<Binary> a = new ArrayList<>();
        for (int j = 0; j < data.size(); j++) {
            String number = data.get(j);
            a.add(new Binary());
            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == '0') a.get(j).number.add(false);
                if (number.charAt(i) == '1') a.get(j).number.add(true);
            }
        }
        return a;
    }

    private String getBinary2(Binary number) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < number.number.size(); i++) {
            if (number.number.get(i)) res.append("1");
            else res.append("0");
        }
        return res.toString();
    }

    private String getBinary(List<BitCounter> nubers) {
        StringBuilder res = new StringBuilder();
        for (BitCounter b : nubers) {
            if (b.zero > b.one)
                res.append("0");
            else
                res.append("1");

        }
        return res.toString();
    }

    private String getBinaryReverse(List<BitCounter> nubers) {
        StringBuilder res = new StringBuilder();
        for (BitCounter b : nubers) {
            if (b.zero < b.one)
                res.append("0");
            else
                res.append("1");
        }
        return res.toString();
    }

    private static class Binary {
        List<Boolean> number = new ArrayList<>();
    }

    private static class BitCounter {
        int zero = 0;
        int one = 0;

        public BitCounter() {
        }

        public void count(boolean bit) {
            if (!bit) zero++;
            else one++;
        }
    }
}
