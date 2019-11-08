package com.q.hivetools.apps;

import com.google.inject.internal.cglib.core.$Constants;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.io.Resources;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Func {

    public static Set<String> findModifyTables() throws IOException {
        List<String> lines = FileUtils.readLines(Resources.getResourceAsFile("TBLS_FK_TBLS.txt"));
        Set<String> tables = new HashSet<>();
        lines.stream().forEach(x->{
            String[] spli = x.split(":");
            if (spli.length >= 2){
                tables.add(spli[0]);
                tables.addAll(Arrays.asList(spli[1].split(",")));
            }
        });
        System.out.println(Arrays.deepToString(tables.toArray()));
        System.out.println(tables.size());
        List<String> res = lines.stream().filter(x->tables.contains(x.split(":")[0])).collect(Collectors.toList());
        FileUtils.writeLines(new File("/home/wangdawei/github/hive/hive-metastore-merge/src/main/resources/SELECTTABLES.txt"), res);
        return tables;
    }

    public static void order() throws IOException {
        File selectTables = Resources.getResourceAsFile("SELECTTABLES.txt");
        List<String> lines = FileUtils.readLines(selectTables);
        HashMap<String, List<String>> fks = new HashMap<>();
        HashMap<String, List<Object>> selects = new HashMap<>();
        selects.put("", new LinkedList<>());
        while (selects.size()-1<lines.size()) {
            System.out.println("##########");
            lines.stream().forEach(x -> {
                String[] spli = x.split(":", 2);
                String tb = spli[0];
                if (selects.containsKey(tb)){
                    return;
                }
                if (Arrays.stream(spli[1].split(",")).allMatch(t1 -> selects.containsKey(t1))) {
                    System.out.println(tb);
                    selects.put(tb, new LinkedList<>());
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
//        findModifyTables();
        order();
    }

}
