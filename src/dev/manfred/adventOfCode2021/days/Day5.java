package dev.manfred.adventOfCode2021.days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day5 implements Runnable<Day5> {
    private final Event<Integer> event = new Event<>("src/resources/data/day5.txt", 5, 5167, 17604);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day5 run1() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        List<Line> lines = new ArrayList<>();

        for (String dataLine : data) {
            var split = dataLine.split(" -> ");
            //point a
            var aData = split[0].split(",");
            Point a = new Point(Integer.parseInt(aData[0]), Integer.parseInt(aData[1]));

            //point b
            var bData = split[1].split(",");
            Point b = new Point(Integer.parseInt(bData[0]), Integer.parseInt(bData[1]));

            lines.add(new Line(a, b));
        }

        int maxX = 0, maxY = 0;

        for (Line line : lines) {
            if (line.a.x > maxX)
                maxX = line.a.x;
            if (line.a.y > maxY)
                maxY = line.a.y;
            if (line.b.x > maxX)
                maxX = line.b.x;
            if (line.b.y > maxY) {
                maxY = line.b.y;
            }
        }

        List<Position> positions = new ArrayList<>();
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                positions.add(new Position(new Point(x, y)));
            }
        }

        lines.parallelStream().forEach(line -> {
            if (line.a.x == line.b.x) {
                for (int y = Math.min(line.a.y, line.b.y); y <= Math.max(line.a.y, line.b.y); y++) {
                    var l = new Point(line.a.x, y);
                    positions.stream().filter(position -> position.point.equals(l)).forEach(Position::flag);
                }
            } else if (line.a.y == line.b.y) {
                for (int x = Math.min(line.a.x, line.b.x); x <= Math.max(line.a.x, line.b.x); x++) {
                    var l = new Point(x, line.a.y);
                    positions.stream().filter(position -> position.point.equals(l)).forEach(Position::flag);
                }
            }
        });

        int total = 0;
        for (Position position : positions) {
            if (position.isDangerous())
                total++;
        }


        event.getResultPrinter().formatResult(event, true, total);
        return this;
    }

    @Override
    public Day5 run2() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        List<Line> lines = new ArrayList<>();

        for (String dataLine : data) {
            var split = dataLine.split(" -> ");
            //point a
            var aData = split[0].split(",");
            Point a = new Point(Integer.parseInt(aData[0]), Integer.parseInt(aData[1]));

            //point b
            var bData = split[1].split(",");
            Point b = new Point(Integer.parseInt(bData[0]), Integer.parseInt(bData[1]));

            lines.add(new Line(a, b));
        }

        int maxX = 0, maxY = 0;

        for (Line line : lines) {
            if (line.a.x > maxX)
                maxX = line.a.x;
            if (line.a.y > maxY)
                maxY = line.a.y;
            if (line.b.x > maxX)
                maxX = line.b.x;
            if (line.b.y > maxY) {
                maxY = line.b.y;
            }
        }

        List<Position> positions = new ArrayList<>();
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                positions.add(new Position(new Point(x, y)));
            }
        }

        lines.parallelStream().forEach(line -> {
            if (line.a.x == line.b.x) {
                for (int y = Math.min(line.a.y, line.b.y); y <= Math.max(line.a.y, line.b.y); y++) {
                    var point = new Point(line.a.x, y);
                    positions.stream().filter(position -> position.point.equals(point)).forEach(Position::flag);
                }
            } else if (line.a.y == line.b.y) {
                for (int x = Math.min(line.a.x, line.b.x); x <= Math.max(line.a.x, line.b.x); x++) {
                    var point = new Point(x, line.a.y);
                    positions.stream().filter(position -> position.point.equals(point)).forEach(Position::flag);
                }
            } else if ((line.a.x < line.b.x && line.a.y < line.b.y) || (line.a.x > line.b.x && line.a.y > line.b.y)) {
                for (int x = 0; x <= Math.max(line.a.x, line.b.x) - Math.min(line.a.x, line.b.x); x++) {
                    var point = new Point(Math.min(line.a.x, line.b.x) + x, Math.min(line.a.y, line.b.y) + x);
                    positions.stream().filter(position -> position.point.equals(point)).forEach(Position::flag);
                }
            } else if (line.a.x < line.b.x && line.a.y > line.b.y) {
                for (int x = 0; x <= Math.max(line.a.x, line.b.x) - Math.min(line.a.x, line.b.x); x++) {
                    var point = new Point(line.a.x + x, line.a.y - x);
                    positions.stream().filter(position -> position.point.equals(point)).forEach(Position::flag);
                }
            } else if (line.a.x > line.b.x && line.a.y < line.b.y) {
                for (int x = 0; x <= Math.max(line.a.x, line.b.x) - Math.min(line.a.x, line.b.x); x++) {
                    var point = new Point(line.a.x - x, line.a.y + x);
                    positions.stream().filter(position -> position.point.equals(point)).forEach(Position::flag);
                }
            }
        });

        int total = 0;
        for (Position position : positions) {
            if (position.isDangerous())
                total++;
        }


        event.getResultPrinter().formatResult(event, false, total);
        return this;
    }


    private static class Position {
        Point point;
        int timesFlagged;

        public Position(Point point) {
            this.point = point;
            this.timesFlagged = 0;
        }

        public void flag() {
            timesFlagged++;
        }

        public boolean isDangerous() {
            return timesFlagged > 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(point, position.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(point);
        }
    }

    private static class Line {
        Point a;
        Point b;

        public Line(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
