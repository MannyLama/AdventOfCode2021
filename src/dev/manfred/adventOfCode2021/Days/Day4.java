package dev.manfred.adventOfCode2021.Days;

import dev.manfred.adventOfCode2021.Event;
import dev.manfred.adventOfCode2021.EventLoader;
import dev.manfred.adventOfCode2021.Runnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 implements Runnable<Day4> {
    private final Event<Integer> event = new Event<>("src/resources/data/day4.txt", 4, 16716, 4212);
    private final EventLoader eventLoader = new EventLoader();

    @Override
    public Event<?> getEvent() {
        return event;
    }

    @Override
    public Day4 run1() {

        List<String> data = eventLoader.getData2(event.getDataPath());
        BingoNumbers bingoNumbers = new BingoNumbers(Arrays.stream(data.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList()));
        List<BingoBoard> bingoBoards = loadBoards(data);

        BingoBoard winner = null;
        while (winner == null && bingoNumbers.hasNext()) {
            int number = bingoNumbers.getNext();

            bingoBoards.forEach(
                    board -> board.board.forEach(
                            row -> row.forEach(
                                    bingoNumber -> {
                                        if (bingoNumber.number == number) {
                                            bingoNumber.flagged = true;
                                        }
                                    }
                            )
                    )
            );
            for (BingoBoard board : bingoBoards) {
                if (board.checkBoard()) {
                    winner = board;
                }
            }
        }

        //calculate
        var sum1 = 0;
        for (List<Number> row : winner.board) {
            sum1 += row.stream().filter(n -> !n.flagged).mapToInt(n -> n.number).sum();
        }

        event.getResultPrinter().formatResult(event, true, sum1 * bingoNumbers.getCurrent());
        return this;
    }

    @Override
    public Day4 run2() {
        List<String> data = eventLoader.getData2(event.getDataPath());
        BingoNumbers bingoNumbers = new BingoNumbers(Arrays.stream(data.get(0).split(",")).map(Integer::valueOf).collect(Collectors.toList()));
        List<BingoBoard> bingoBoards = loadBoards(data);

        while (bingoBoards.size() > 1 && bingoNumbers.hasNext()) {
            int number = bingoNumbers.getNext();

            for (BingoBoard bingoBoard : bingoBoards) {
                bingoBoard.board.forEach(
                        row -> row.forEach(
                                bingoNumber -> {
                                    if (bingoNumber.number == number) {
                                        bingoNumber.flagged = true;
                                    }
                                }
                        )
                );
            }
            bingoBoards.removeIf(BingoBoard::checkBoard);
        }

        //calculate
        var sum1 = 0;
        for (List<Number> row : bingoBoards.stream().findFirst().get().board) {
            sum1 += row.stream().filter(n -> !n.flagged).mapToInt(n -> n.number).sum();
        }

        event.getResultPrinter().formatResult(event, false, sum1 * bingoNumbers.getCurrent());
        return this;
    }

    private static class BingoNumbers {
        List<Integer> numbers;
        int position;

        public BingoNumbers(List<Integer> numbers) {
            this.position = 0;
            this.numbers = numbers;
        }

        public boolean hasNext() {
            return position < numbers.size();
        }

        public int getNext() {
            if (position < numbers.size()) {
                int next = numbers.get(position);
                position++;
                return next;
            }
            return -1;
        }

        public int getCurrent() {
            return numbers.get(position - 1);
        }

        public int getCurrent2() {
            return numbers.get(position);
        }
    }

    private static class BingoBoard {
        List<List<Number>> board;

        public BingoBoard() {
            board = new ArrayList<>();
        }

        public void addRow(List<Number> row) {
            board.add(List.copyOf(row));
        }

        public boolean checkBoard() {
            //rows
            for (int i = 0; i < 5; i++) {
                if (board.get(i).get(0).flagged && board.get(i).stream().map(n -> n.flagged).distinct().count() == 1) {
                    return true;
                }
            }

            //columns
            for (int j = 0; j < 5; j++) {
                List<Boolean> column = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    column.add(board.get(i).get(j).flagged);
                }
                if (board.get(0).get(j).flagged && column.stream().distinct().count() == 1) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "BingoBoard{" +
                    "board=" + board.size() +
                    '}';
        }
    }


    private static class Number {
        int number;
        boolean flagged;

        public Number(int number) {
            this.number = number;
            this.flagged = false;
        }
    }

    private static List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<String>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }

    private List<BingoBoard> loadBoards(List<String> input) {
        List<BingoBoard> bingoBoards = new ArrayList<>();

        BingoBoard board = new BingoBoard();
        for (int i = 1; i < input.size(); i++) {

            if (!input.get(i).equals("")) {
                board.addRow(getParts(input.get(i), 3).stream().map(a -> new Number(Integer.parseInt(a.trim()))).collect(Collectors.toList()));
            }
            if (i % 6 == 0) {
                bingoBoards.add(board);
                board = new BingoBoard();
            }
        }
        return bingoBoards;
    }
}
