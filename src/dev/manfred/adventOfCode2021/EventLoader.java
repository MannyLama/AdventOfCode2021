package dev.manfred.adventOfCode2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventLoader {

    public List<String> getData(String uri) {
        List<String> data = new ArrayList<>();
        try {
            File file = new File(uri);
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                data.add(in.next());
            }
            in.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getData2(String uri) {
        try {
            Stream<String> lines = Files.lines(Path.of(uri));
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
