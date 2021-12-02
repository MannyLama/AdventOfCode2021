package dev.manfred.adventOfCode2021;

public class ResultPrinter<T> {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void formatResult(Event<T> event, boolean first, T result) {
        String feedback = (event.isDone(first, result) ? ANSI_GREEN + "done!" : ANSI_RED + "not done!") + ANSI_RESET;

        if (first) {
            System.out.printf("%s%d.1%s -> %s %s%n", ANSI_YELLOW, event.getDay(), ANSI_RESET, result, feedback);
        } else
            System.out.printf("%s%d.2%s -> %s %s%n", ANSI_YELLOW, event.getDay(), ANSI_RESET, result, feedback);
    }
}
