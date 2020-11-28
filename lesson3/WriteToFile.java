package lesson3;

import java.io.*;

/**
 * Homework for lesson #3
 *
 * @author Valeriy Lazarev
 * @since 29.11.2020
 */

public class WriteToFile {

    /*
    1. Создать логгер локальной истории в текстовый файл.
    Считываем данные введенные из консоли и добавляем в файл с новой строки.
    3. * Не использовать сканер в процессе считывания данных из консоли
    */
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("log");
        String inputStrings = "";

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
             FileWriter fileWriter = new FileWriter(file, true)) {

            while (!inputStrings.equals("exit")) {
                inputStrings = bufferedReader.readLine();
                fileWriter.write(inputStrings + "\n");
            }

        }
    }
}
