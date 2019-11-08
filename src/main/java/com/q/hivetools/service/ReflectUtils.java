package com.q.hivetools.service;

import com.q.hivetools.meta.Dbs;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author EX-QINCIDONG001
 */
public class ReflectUtils {

    /**
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object obj = reflectSetInit("com.q.hivetools.meta.Dbs", "db_id");
        System.out.println(obj);
        System.out.println(obj.getClass());
        List<Dbs> dbsList = new LinkedList<>();
        dbsList.add((Dbs) obj);
        System.out.println(dbsList.get(0));
    }

    static Map<String, Object> initVals = new HashMap<>();
    static {
        initVals.put("int", -1);
        initVals.put("long", -1l);
        initVals.put("java.lang.String", "-1");
        initVals.put("java.lang.Integer", new Integer(-1));
        initVals.put("java.lang.Long", new Long(-1));
        initVals.put("java.math.BigInteger", new BigInteger("-1"));
    }

    public static boolean reflectSet(Object obj, String col, Object val){
        if (obj == null) return false;
        boolean res = false;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            if(!fields[j].isAccessible()){
                fields[j].setAccessible(true);
            }
            if (fields[j].getName().equals(col)){
                try {
                    fields[j].set(obj, val);
                    res = true;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return res;
    }

    public static Object reflectSetInit(String clsName, String col) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cls = Class.forName(clsName);
        Object obj = cls.newInstance();
//        System.out.println(obj);
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean isSet = false;
        for (int j = 0; j < fields.length; j++) {
            if(!fields[j].isAccessible()){
                fields[j].setAccessible(true);
            }
            if (fields[j].getName().equals(col)){
                try {
                    fields[j].set(obj, initVals.get(fields[j].getType().getName()));
//                    System.out.println(obj);
                    isSet = true;

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println(String.format("isSet=%s.", isSet));
        return obj;
    }


}
