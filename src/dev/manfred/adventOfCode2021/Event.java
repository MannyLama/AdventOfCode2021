package dev.manfred.adventOfCode2021;

public class Event<T> {
    private ResultPrinter<T> resultPrinter;
    private String dataPath;
    private int day;
    private T result1;
    private T result2;


    public Event(String dataPath, int day, T result1, T result2) {
        this.resultPrinter = new ResultPrinter<>();
        this.dataPath = dataPath;
        this.day = day;
        this.result1 = result1;
        this.result2 = result2;
    }

    public boolean isDone(boolean firstPuzzle, T answer) {
        if (firstPuzzle)
            return result1.equals(answer);
        else
            return result2.equals(answer);
    }

    public String getDataPath() {
        return dataPath;
    }

    public int getDay() {
        return day;
    }

    public T getResult1() {
        return result1;
    }

    public T getResult2() {
        return result2;
    }

    public ResultPrinter<T> getResultPrinter() {
        return resultPrinter;
    }
}
