package ru.baltinfocom.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileReader {
    /**
     * Считывает из .csv файла строки вида A1;B1;C1
     * создает для каждой строки объект типа Record и собирает их в лист
     * @param location - адрес файла с входной информацией
     * @return
     */
    public static List<Record> readFile(String location){
        List<Record> records = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(location))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                boolean isLineCorrect = Pattern.matches("\"\\w*?\";\"\\w*?\";\"\\w*?\"", line);
                if (isLineCorrect) {//соответствует ли полученная строка заданному формату
                    Record record = new Record(line);
                    records.add(record);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
