package com.q.hivetools.apps;

import com.q.hivetools.mammut.PfHiveSite;
import com.q.hivetools.mappers.MammutMapper;
import com.q.hivetools.mappers.TestMapper;
import com.q.hivetools.meta.Dbs;
import com.q.hivetools.service.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by hzliuxun on 16/11/11.
 */
public class Test1 {
  private static final Logger logger = Logger.getLogger(Test1.class.getName());

  public static void main(String[] args) throws IOException {
    File file = Resources.getResourceAsFile("hive-tools.properties");
    BufferedReader br = new BufferedReader(new FileReader(file));
    System.out.println(br.readLine());

    PropertyConfigurator.configure(Resources.getResourceAsStream("log4j.properties"));

    MyBatisUtil.sourceName = "localpg";
    TestMapper testMapper = new TestMapper("localpg");
    List<Dbs> list = testMapper.getDbs();
  }
}
