package ru.baltinfocom.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Для получения всех групп с заголовком и списком строк в нужном порядке
 */
public class GroupsCreator {
    /**
     * Создает список строк, разбитых по группам, для дальнейшего вывода в файл
     * @param records - первоначальный список записей из файла
     * @param groupIndexesList - список групп в виде индексов строк
     * @return
     */
    public static List<Group> createGroups(List<Record> records, List<Set<Integer>> groupIndexesList){
        List<Group> groups = new ArrayList<>();
        for (Set<Integer> groupIndexes: groupIndexesList){
            Set<String> rowsSet = new HashSet<>();
            //включает строки в Set для исключения дублирующихся строк
            groupIndexes.forEach(groupIndex -> rowsSet.add(records.get(groupIndex).getLine()));
            List<String> rowsList = new ArrayList<>(rowsSet);
            Group group = new Group();
            group.setRows(rowsList);
            group.setRowsNumber(rowsList.size());
            groups.add(group);
        }
        groups.sort(new GroupComparator());
        Integer groupNumber = 1;
        for (Group group: groups){
            group.setHeader("Группа " + groupNumber.toString());
            groupNumber++;
        }
        return groups;
    }

    public static Integer getBigGroupsNumber(List<Group> groups){
        Long bigGroupsNumber = groups.stream().filter(group -> group.getRowsNumber() > 1).count();
        return bigGroupsNumber.intValue();    }
}
