package com.q.hivetools.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXMLFile {


    public static Element addEle(Document doc, Element rootElement, String eleStr, String text) {
        Element staff = doc.createElement(eleStr);
        rootElement.appendChild(staff);
        staff.appendChild(doc.createCDATASection(text));
        return staff;
    }

    public static void main(String argv[]) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("mapper");
            doc.appendChild(rootElement);
            rootElement.setAttribute("namespace", "com.q.hivetools.mappers.MetaDataMapper");

            String eleName = "select";

            String text = "        SELECT * FROM CDS ORDER BY CD_ID\n" ;
            Element ele = addEle(doc, rootElement, eleName, text);
            ele.setAttribute("id", "getCdsRecords");
            ele.setAttribute("resultType", "com.q.hivetools.meta.Cds");



            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/home/wangdawei/github/cs/java8/src/main/resources/file.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}