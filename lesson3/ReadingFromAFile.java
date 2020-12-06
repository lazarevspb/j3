package lesson3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

    /*
    2. После загрузки клиента показывать ему последние 100 строк чата.
    */
public class ReadingFromAFile {
    private static final int LIMIT_LINES = 100;
    public static void main(String[] args) throws IOException {
        String fileName;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = bufferedReader.readLine();
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<String> strings = Files
                .lines(Paths.get(fileName))
                .map(s -> s + " \n")
                .collect(Collectors.toList());

        int i = strings.size() > LIMIT_LINES ? strings.size() - LIMIT_LINES : 0;
        for (int j = i; j < strings.size(); j++) {
            stringBuilder.append(strings.get(j));
        }
        System.out.println(stringBuilder.toString());
    }
}

