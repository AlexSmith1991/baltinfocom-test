package ru.baltinfocom.test;

import java.util.*;

/**
 * Создает список групп строк в виде множеств индексов
 */
public class GroupIndexesListCreator {
    /**
     * Список групп строк с указанием индексов
     */
    private List<Set<Integer>> groupIndexesList;

    /**
     * Карта индексов строк и соответствующих номеров строк, имеющих с ней пересечение значений
     */
    private Map<Integer, Set<Integer>> recordMap;

    /**
     * Количество групп, имеющих больше одной строки
     */
    private Integer bigGroupsNumber;

    public GroupIndexesListCreator(Map<Integer, Set<Integer>> records) {
        this.recordMap = records;
        groupIndexesList = new ArrayList<>();
        bigGroupsNumber = 0;
    }

    public List<Set<Integer>> getGroupIndexesList(){
        return groupIndexesList;
    }

    public Integer getBigGroupsNumber() {
        return bigGroupsNumber;
    }

    /**
     * Создание списка групп в виде множеств индексов строк, входящих в группу
     */
    public void createGroupList(){
        for (Map.Entry<Integer, Set<Integer>> entry: recordMap.entrySet()){
            int listSize = entry.getValue().size();
            Set<Integer> group = new HashSet<>();
            if (listSize > 1){//если строка имеет пересечение с несколькими строками - запускается рекурсивный поиск всех прямых и косвенных пересечений
                bigGroupsNumber++;
                readLevel(entry.getKey(), group);
                groupIndexesList.add(group);
            } else if (listSize == 1) {//если строка имеет только одно пересечение - это пересечение с самой собой, строка ни в какую большую группу не входит
                group.add(entry.getKey());
                groupIndexesList.add(group);
            }
        }
    }

    /**
     * Рекурсивный поиск множества строк, имеющих пересечение хотя бы одним значением с другими строками из множества
     * @param index - индекс текущей строки, для которой запускается считывание
     * @param indexes - накопительное множество строк, по которым уже найдено пересечение
     */
    private void readLevel(Integer index, Set<Integer> indexes){
        indexes.add(index);
        Set<Integer> recordIndexes = recordMap.get(index);//список строк, с которыми текущая строка имеет прямое пересечение
        recordIndexes.removeAll(indexes);//исключение строк, по которым уже была запущено считывание, из дальнейшей обработки
        if (recordIndexes.size() > 0){
            //запуск дальнейшего считывания для полученного списка строк с пересечением, для получения строк,
            //имеющих опосредованное пересечение с обрабатываемой
            recordIndexes.forEach(recordIndex -> readLevel(recordIndex, indexes));
        }
        recordIndexes.clear();//исключение обработанных на текущем шаге строк из дальнейшей обработки
    }
}
