package com.q.hivetools.apps;

import com.q.hivetools.mammut.PfHiveSite;
import com.q.hivetools.mappers.MammutMapper;
import com.q.hivetools.service.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.List;

/**
 * Created by hzliuxun on 16/11/11.
 */
public class Mammut {
    private static final Logger logger = Logger.getLogger(Mammut.class.getName());

    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure(Resources.getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyBatisUtil.sourceName = "mammut";
        MammutMapper mammutMapper = new MammutMapper("mammut");
        List<PfHiveSite> list = mammutMapper.getPfHivesite();
    }
}
