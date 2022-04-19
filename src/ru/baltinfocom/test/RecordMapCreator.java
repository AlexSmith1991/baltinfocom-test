package ru.baltinfocom.test;

import java.util.*;

/**
 * Класс для создания структуры связей между записями
 */
public class RecordMapCreator {
    /**
     * Содержит значение и список индексов строк, которые содержат это значение
     */
    private Map<String, List<Integer>> values;
    /**
     * Содержит индекс строки и список номеров строк, имеющих с ней пересечение значений
     */
    private Map<Integer, Set<Integer>> records;

    public RecordMapCreator() {
        values = new HashMap<>();
        records = new HashMap<>();
    }

    /**
     * Добавляет значения индекса строки в записи для каждого значения из строки
     * @param newValues - значения, полученные из строки
     * @param index - индекс строки в первоначальном списке записей
     */
    public void addValues(Set<String> newValues, Integer index){
        for (String value: newValues){
            if (values.containsKey(value)){
                values.get(value).add(index);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(index);
                values.put(value, indexes);
            }
        }
    }

    /**
     * Запускает формирование карты пересекающихся строк
     * на основании ранее сформированной карты соответсвий значений и индексов строк
     * @return
     */
    public Map<Integer, Set<Integer>> getRecordsMap(){
        for (Map.Entry<String, List<Integer>> entry: values.entrySet()){
            addRecord(entry.getValue());
        }
        return records;
    }

    /**
     * Добавляет записи о пересечениях значений для строк на основании карты строк, содержащих каждое значение
     * @param indexes - лист пересекающихся строк по наличию определенной переменной
     */
    private void addRecord(List<Integer> indexes){
        for (Integer index: indexes){
            if (records.containsKey(index)){
                records.get(index).addAll(indexes);
            } else {
                Set<Integer> newIndexes = new HashSet<>();
                newIndexes.addAll(indexes);
                records.put(index, newIndexes);
            }
        }
    }
}
