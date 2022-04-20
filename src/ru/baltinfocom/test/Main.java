package ru.baltinfocom.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Record> records = FileReader.readFile("C:\\lng\\lng.csv");
        RecordMapCreator recordMapCreator = new RecordMapCreator();
        for (int i = 0; i < records.size(); i++) {
            recordMapCreator.addValues(records.get(i).getValues(), i);
        }
        Map<Integer, Set<Integer>> recordMap = recordMapCreator.getRecordsMap();
        GroupIndexesListCreator groupIndexesListCreator = new GroupIndexesListCreator(recordMap);
        groupIndexesListCreator.createGroupList();
        List<Set<Integer>> groupIndexesList = groupIndexesListCreator.getGroupIndexesList();
        List<Group> groups = GroupsCreator.createGroups(records, groupIndexesList);
        Integer bigGroupsNumber = GroupsCreator.getBigGroupsNumber(groups);
        FileWriter.writeToFile(groups, bigGroupsNumber, "C:\\lng\\result.csv");
    }
}
