package ru.baltinfocom.test;

import java.util.Comparator;
import java.util.List;

/**
 * Описание объекта группы, предназначенного для удобного вывода информации в файл
 */
public class Group {
    /**
     * Наименование группы
     */
    private String header;

    /**
     * Список строк, подлежащих выводу
     */
    private List<String> rows;

    /**
     * Количество уникальных строк в группе
     */
    private Integer rowsNumber;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public Integer getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(Integer rowsNumber) {
        this.rowsNumber = rowsNumber;
    }
}

/**
 * Компаратор для нисходящей сортировки групп по количеству строк
 */
class GroupComparator implements Comparator<Group>{
    @Override
    public int compare(Group o1, Group o2) {
        return o2.getRowsNumber().compareTo(o1.getRowsNumber());
    }
}
