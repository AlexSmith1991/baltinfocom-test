package ru.baltinfocom.test;

import java.io.*;
import java.util.List;

public class FileWriter {
    /**
     * Записывает полученные группы в .csv файл
     * @param groups - список групп с заголовками и списками строк
     * @param bigGroupsNumber - количество групп с более чем одним элементом
     * @param location - адрес файла с итоговой информацией
     */
    public static void writeToFile(List<Group> groups, Integer bigGroupsNumber, String location){
        try (PrintWriter printWriter = new PrintWriter(new File(location))) {
            printWriter.println( "Групп с более чем одним элементом - " + bigGroupsNumber.toString());
            printWriter.println( "Всего групп - " + groups.size());
            for (Group group: groups){
                printWriter.println(group.getHeader());
                group.getRows().forEach(printWriter::println);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
