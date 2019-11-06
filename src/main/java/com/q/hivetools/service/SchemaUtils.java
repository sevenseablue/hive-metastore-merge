package com.q.hivetools.service;

import edu.princeton.cs.algs4.Topological;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SchemaUtils {

    public static String readSchemaFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbFileContext = new StringBuffer("");
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sbFileContext.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return sbFileContext.toString();
    }

    public static String formatTableColumnName(String name, boolean firstUpper) {
        // 格式化字段名
        String[] names = name.split("_");
        String newName = "";
        int numNames = names.length;

        if (numNames < 2) {
            if (false == firstUpper) {
                newName = name.toLowerCase();
            } else {
                String firstChar = name.substring(0, 1);

                String tmp = name.substring(1, name.length()).toLowerCase();
                newName = firstChar.toUpperCase() + tmp;
            }
        } else {
            for (int n = 0; n < names.length; n++) {
                String tmp = names[n];
                tmp = tmp.toLowerCase();

                if (false == firstUpper && n == 0) {
                    newName = newName + tmp;
                    continue;
                }
                int len = tmp.length();
                String firstChar = tmp.substring(0, 1);
                newName = newName + firstChar.toUpperCase() + tmp.substring(1, len);
            }
        }

        return newName;
    }

    public static void topological(String fileName, String delimiter) {
        String[] args = new String[]{
                fileName, delimiter
        };
        Topological.main(args);
    }

    public static void main(String[] args) {
        topological("tables.fk.txt", ",");
    }
}
