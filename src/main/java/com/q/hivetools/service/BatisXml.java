package com.q.hivetools.service;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.io.Resources;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BatisXml {

    Document doc = null;
    Element root = null;
    String pkgName = "com.q.hivetools.meta.";
    String mapperSeg = "MetaDataMapper";
    String xmlSeg = "MetaDataSelectMapper";
    boolean isMysql = true;

    public BatisXml(boolean isMysql) throws ParserConfigurationException {
        this.isMysql = isMysql;
        if (!isMysql){
            mapperSeg = "MetaDataMapperPg";
            xmlSeg = "MetaDataSelectMapperPg";
        }
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        root = doc.createElement("mapper");
        doc.appendChild(root);
        // my, pg change,
        root.setAttribute("namespace", "com.q.hivetools.mappers." + mapperSeg);

        /**
         * <!DOCTYPE mapper PUBLIC '-//mybatis.org//DTD Mapper 3.0//EN'
         *         'http://mybatis.org/dtd/mybatis-3-mapper.dtd'>
         */


    }

    public Element addEle(String eleStr, String text) {
        Element staff = doc.createElement(eleStr);
        root.appendChild(staff);
        staff.appendChild(doc.createCDATASection(text));
        return staff;
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
        boolean isMysql = true;
        BatisXml batisXml = new BatisXml(isMysql);
        FileUtils.readLines(new File("src/main/resources/TBLS_SELECT.txt"))
                .stream().forEach(line -> batisXml.addEle(line));
        batisXml.save();
    }

    public void addEle(String line){
        String[] lineSpli = line.split(";", 4);
        String tbSrc = lineSpli[0];
        String tbSrcCls = lineSpli[1];
        String[] cols = lineSpli[2].split(",");
        String[] fks = lineSpli[3].split(":");
        List<String[]> fksList = Arrays.stream(fks).map(x-> x.split(",",3)).collect(Collectors.toList());

        Element select = doc.createElement("select");
        root.appendChild(select);
        select.setAttribute("id", String.format("get%sRecordsCondition", tbSrcCls));
        select.setAttribute("parameterType", "java.util.Map");
        select.setAttribute("resultType", pkgName+tbSrcCls);
        if (tbSrc.equals("DBS")) {
            select.setTextContent(String.format("\nselect * from %s where %s=#{dbname}\n", isMysql ? tbSrc : String.format("\"%s\"", tbSrc), isMysql?"NAME":"\"NAME\""));
        }else{
            select.setTextContent(String.format("\nselect * from %s \n", isMysql ? tbSrc : String.format("\"%s\"", tbSrc)));
        }

        if(!fks[0].equals("")) {

            Element where = doc.createElement("where");
            select.appendChild(where);
            final int[] ind = {0};
            fksList.stream().forEach(fk -> {
                String colS = fk[0];
                if(!isMysql){ colS = String.format("\"%s\"", colS);}
                String colT = fk[2].toLowerCase();
                String tbDst = fk[1];
                String key = tbDst + "List";
                Element if1 = doc.createElement("if");
                if1.setAttribute("test", String.format("%s!=null and %s.size!=0", key, key));
                if (ind[0] == 0) {
                    if1.setTextContent(String.format("\n %s in \n", colS));
                }else {
                    String logic = "and";
                    if (tbSrc.equals("SDS")){
                        logic = "or";
                    }
                    if1.setTextContent(String.format("\n %s %s in \n", logic, colS));
                }

                Element foreach1 = doc.createElement("foreach");
                /**
                 * <foreach collection="employeeIdsArray" item="employeeId"
                 *          index="index" open="(" close=")" separator=",">
                 *           #{employeeId}
                 * </foreach>
                 */
                String item = "item";
                foreach1.setAttribute("index", "index");
                foreach1.setAttribute("open", "(");
                foreach1.setAttribute("close", ")");
                foreach1.setAttribute("separator", ",");
                foreach1.setAttribute("collection", key);
                foreach1.setAttribute("item", item);
                foreach1.setTextContent(String.format("\n#{%s%s}\n", item, colT.equals("")?"":"."+colT));
                if1.appendChild(foreach1);
                ind[0] += 1;

                where.appendChild(if1);
            });
        }

    }

    public void save() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("mapper",
                "-//mybatis.org//DTD Mapper 3.0//EN",
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        DOMSource source = new DOMSource(doc);
        // my, pg change,
        StreamResult result = new StreamResult(new File("src/main/resources/"+xmlSeg+".xml"));
        transformer.transform(source, result);
        System.out.println("File saved!");
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }
}
