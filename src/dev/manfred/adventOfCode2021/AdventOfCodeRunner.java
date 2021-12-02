package dev.manfred.adventOfCode2021;

import dev.manfred.adventOfCode2021.Days.Day1;
import dev.manfred.adventOfCode2021.Days.Day2;

public class AdventOfCodeRunner {
    public static void main(String[] args) {
        System.out.println(
                "   .-.                " + ResultPrinter.ANSI_RED + "Advent of code 2021" + ResultPrinter.ANSI_RESET + "                \\ /\n" +
                        "  ( (                                |                  - * -\n" +
                        "   '-`                              -+-                  / \\\n" +
                        "            \\            o          _|_          \\\n" +
                        "            ))          }^{        /___\\         ))\n" +
                        "          .-#-----.     /|\\     .---'-'---.    .-#-----.\n" +
                        "     ___ /_________\\   //|\\\\   /___________\\  /_________\\  \n" +
                        "    /___\\ |[] _ []|    //|\\\\    | A /^\\ A |    |[] _ []| _.O,_\n" +
                        "....|\"#\"|.|  |*|  |...///|\\\\\\...|   |\"|   |....|  |*|  |..(^).... \n");
        new Day1().run1().run2();
        new Day2().run1().run2();
    }
}
