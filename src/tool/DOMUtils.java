package tool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.*;

public class DOMUtils {  
  
    /** 
     * ͨ���ļ���·����ȡxml��document���� 
     *  
     * @param path  �ļ���·�� 
     * @return      �����ĵ����� 
     */  
    public static Document getXMLByFilePath(String path) {  
        if (null == path) {  
            return null;  
        }  
        Document document = null;  
        try {  
            SAXReader reader = new SAXReader();  
            document = reader.read(new File(path));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return document;  
    }  
      
    /** 
     * ͨ��xml�ַ���ȡdocument�ĵ� 
     * @param xmlstr    Ҫ���л���xml�ַ� 
     * @return          �����ĵ����� 
     * @throws DocumentException 
     */  
    public static Document getXMLByString(String xmlstr) throws DocumentException{  
        if(xmlstr==""||xmlstr==null){  
            return null;  
        }  
        Document document = DocumentHelper.parseText(xmlstr);  
        return document;  
    }  
  
    /** 
     * ��ȡĳ��Ԫ�ص����е��ӽڵ� 
     * @param node  �ƶ��ڵ� 
     * @return      �������е��ӽڵ� 
     */  
    public static List<Element> getChildElements(Element node) {  
        if (null == node) {  
            return null;  
        }  
        @SuppressWarnings("unchecked")  
        List<Element> lists = node.elements();  
        return lists;  
    }  
      
    /** 
     * ��ȡָ���ڵ���ӽڵ� 
     * @param node          ���ڵ� 
     * @param childnode     ָ�����Ƶ��ӽڵ� 
     * @return              ����ָ�����ӽڵ� 
     */  
    public static Element getChildElement(Element node,String childnode){  
        if(null==node||null == childnode||"".equals(childnode)){  
            return null;  
        }  
        return node.element(childnode);  
    }  
      
    /** 
     * ��ȡ���е�����ֵ 
     * @param node 
     * @param arg 
     * @return 
     */  
    public static Map<String, String> getAttributes(Element node,String...arg){  
        if(node==null||arg.length==0){  
            return null;  
        }  
        Map<String, String> attrMap = new HashMap<String,String>();  
        for(String attr:arg){  
            String attrValue = node.attributeValue(attr);  
            attrMap.put(attr, attrValue);  
        }  
        return attrMap;  
    }  
      
    /** 
     * ��ȡelement�ĵ������� 
     * @param node      ��Ҫ��ȡ���ԵĽڵ���� 
     * @param attr      ��Ҫ��ȡ������ֵ 
     * @return          �������Ե�ֵ 
     */  
    public static String getAttribute(Element node,String attr){  
        if(null == node||attr==null||"".equals(attr)){  
            return "";  
        }  
        return node.attributeValue(attr);  
    }  
  
    /** 
     * ��Ӻ��ӽڵ�Ԫ�� 
     *  
     * @param parent 
     *            ���ڵ� 
     * @param childName 
     *            ���ӽڵ����� 
     * @param childValue 
     *            ���ӽڵ�ֵ 
     * @return �����ڵ� 
     */  
    public static Element addChild(Element parent, String childName, String childValue) {  
        Element child = parent.addElement(childName);// ��ӽڵ�Ԫ��  
        child.setText(childValue == null ? "" : childValue); // ΪԪ����ֵ  
        return child;  
    }  
  
    /** 
     * DOM4j��Document����תΪXML���Ĵ� 
     *  
     * @param document 
     * @param charset 
     * @return �����������xml�ַ��� 
     */  
    public static String documentToString(Document document, String charset) {  
        StringWriter stringWriter = new StringWriter();  
        OutputFormat format = OutputFormat.createPrettyPrint();// ��ø�ʽ�������  
        format.setEncoding(charset);// �����ַ���,Ĭ��ΪUTF-8  
        XMLWriter xmlWriter = new XMLWriter(stringWriter, format);// д�ļ���  
        try {  
            xmlWriter.write(document);  
            xmlWriter.flush();  
            xmlWriter.close();  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
        return stringWriter.toString();  
    }  
  
    /** 
     * ȥ������ͷ��(��<?xml...?>ȥ��) 
     *  
     * @param document 
     * @param charset 
     * @return 
     */  
    public static String documentToStringNoDeclaredHeader(Document document, String charset) {  
        String xml = documentToString(document, charset);  
        return xml.replaceFirst("\\s*<[^<>]+>\\s*", "");  
    }  
  
    /** 
     * ����XMLΪDocument���� 
     *  
     * @param xml 
     *            ��������XMl 
     * @return Document 
     * @throws ParseMessageException 
     */  
    public final static Element parseXml(String xml) {  
        StringReader sr = new StringReader(xml);  
        SAXReader saxReader = new SAXReader();  
        Document document = null;  
        try {  
            document = saxReader.read(sr);  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        Element rootElement = document != null ? document.getRootElement() : null;  
        return rootElement;  
    }  
  
    /** 
     * ��ȡelement�����text��ֵ 
     *  
     * @param e 
     *            �ڵ�Ķ��� 
     * @param tag 
     *            �ڵ��tag 
     * @return 
     */  
    public final static String getText(Element e, String tag) {  
        Element _e = e.element(tag);  
        if (_e != null)  
            return _e.getText();  
        else  
            return null;  
    }  
  
    /** 
     * ��ȡȥ���ո���ַ��� 
     *  
     * @param e 
     * @param tag 
     * @return 
     */  
    public final static String getTextTrim(Element e, String tag) {  
        Element _e = e.element(tag);  
        if (_e != null)  
            return _e.getTextTrim();  
        else  
            return null;  
    }  
  
    /** 
     * ��ȡ�ڵ�ֵ.�ڵ���벻��Ϊ�գ������״� 
     *  
     * @param parent    ���ڵ� 
     * @param tag       ��Ҫ��ȡ���ӽڵ� 
     * @return          �����ӽڵ� 
     * @throws ParseMessageException 
     */  
    public final static String getTextTrimNotNull(Element parent, String tag) {  
        Element e = parent.element(tag);  
        if (e == null)  
            throw new NullPointerException("�ڵ�Ϊ��");  
        else  
            return e.getTextTrim();  
    }  
  
    /** 
     * �ڵ���벻��Ϊ�գ������״� 
     *  
     * @param parent    ���ڵ� 
     * @param tag       ��Ҫ��ȡ���ӽڵ� 
     * @return          �ӽڵ� 
     * @throws ParseMessageException 
     */  
    public final static Element elementNotNull(Element parent, String tag) {  
        Element e = parent.element(tag);  
        if (e == null)  
            throw new NullPointerException("�ڵ�Ϊ��");  
        else  
            return e;  
    }  
      
    /** 
     * ���ĵ�����д���Ӧ���ļ��� 
     * @param document      �ĵ����� 
     * @param path          д���ĵ���·�� 
     * @throws IOException   
     */  
    public final static void writeXMLToFile(Document document,String path) throws IOException{  
        if(document==null||path==null){  
            return;  
        }  
        XMLWriter writer = new XMLWriter(new FileWriter(path));  
        writer.write(document);  
        writer.close();  
    }  
       
}  