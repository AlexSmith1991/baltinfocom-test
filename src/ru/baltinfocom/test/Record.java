package ru.baltinfocom.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс для хранения введенной строки и множества значений, полученных из нее
 */
public class Record {
    /**
     * разделитель значений в строке для .csv файла
     */
    private final static String COMMA_DELIMITER = ";";
    private String line;
    private Set<String> values;

    public Record(String line) {
        this.line = line;
        this.values = new HashSet<>(Arrays.asList(line.split(COMMA_DELIMITER)));
        this.values.remove("\"\"");//пустые значения требуется исключить из дальнейшей обработки
    }

    public String getLine() {
        return line;
    }

    public Set<String> getValues() {
        return values;
    }

}
