package com.ssm.utils;
/*
 * DOM4J 解析XML文档 以下内容基本全包括了
 * 
 * */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.ssm.entity.TestCase;
import com.ssm.entity.TestCaseSuite;

public class XmlUtils {
	public Document  document= null;
	public Element root=null;
	public Element curruntnode=null;
	public Element nextnode;
	public Element prevnode;
	public String tpname;
	public String creatername;
	public HashMap map=null ; 
	
	
	public void  load(String xmlpath) throws DocumentException {
		SAXReader reader = new SAXReader();  
	     document = reader.read(new File(xmlpath));  
	}
	public  String readTPname()  {
		Node selectnode=document.selectSingleNode("/TestPlan/TestPlanname");
		tpname=selectnode.getText();
		return tpname;
	}
	public  String readCreater()  {
		Node selectnode=document.selectSingleNode("/TestPlan/Creater");
		creatername=selectnode.getText();
		return creatername;
	}
	
	public  HashMap readGlobalPrameter()  {
		map = new HashMap();
		Node globalprametermanagernode=document.selectSingleNode("/TestPlan/GlobalSet/GloabalParameterManager");
		List prameterlist=globalprametermanagernode.selectNodes("globalelement");// 不能输入带\  否则是 从根节点的儿子节点中选择名称为 globalelement 的节点。
		Iterator iter = prameterlist.iterator();  
        while (iter.hasNext()) {  
            Element prameterElement = (Element) iter.next();  	
            Node globalnamenode=prameterElement.selectSingleNode("globalname");
            Node globalvalue=prameterElement.selectSingleNode("globalvalue");
			System.out.println(globalnamenode.getText());	
			System.out.println(globalvalue.getText());
			map.put(globalnamenode.getText(), globalvalue.getText());
        }

        System.out.println(map.get("global1").toString());
		return map;
	}
	/*读取测试用例集和步骤 
	  *返回测试用例集集合  当测试用例集集合 的size()《=0 代表测试计划为空 
	 */
	public ArrayList<TestCaseSuite> readTestCaseSuite() {
		List testsuitelist=document.selectNodes("/TestPlan/TestPlanStep/TestCaseSuite");
		Iterator iter = testsuitelist.iterator();
		ArrayList<TestCaseSuite> testCaseSuiteList=new ArrayList();
		int testcasesuitenum=0;
		while (iter.hasNext()) {  
			Element testsuiteElement = (Element) iter.next();
			TestCaseSuite  testsasesuite=new TestCaseSuite();
			//获取测试用例集合属性  目前只想到了一个ID
			testsasesuite.setTestCaseSuiteID(testcasesuitenum);
			//获取当前测试用例集 下的所有测试步骤
			List testcaselist=testsuiteElement.selectNodes("TestCase");
			Iterator iter2 = testcaselist.iterator();//测试用例步骤集合迭代器
			int testcasenum=0;
			ArrayList<TestCase> testCaseList=new ArrayList();
			while (iter2.hasNext()) {
				Element testcaseElement = (Element)iter2.next();
				TestCase testcase=new TestCase();
				//获取属性，根据属性进行不同处理
				if(testcaseElement.attributeValue("type").equals("http")) {
					testcase.setTestCaseID(testcasenum);
					testcase.setTestType(1);
					Node urlpathnode =testcaseElement.selectSingleNode("urlpath");
					testcase.setHttpRequestUrlPath(urlpathnode.getText());
					Node HTTPmethodnode =testcaseElement.selectSingleNode("HTTPmethod");
					testcase.setHttpRequestMethod(HTTPmethodnode.getText());
					
					Node HTTBodynode =testcaseElement.selectSingleNode("HTTBody");
					if(HTTBodynode!=null) {
						testcase.setHttpRequestBody(HTTBodynode.getText());
					}

					Node ResponseAssertionStatusCodenode =testcaseElement.selectSingleNode("ResponseAssertionStatusCode");
					if(ResponseAssertionStatusCodenode!=null) {
						testcase.setResponseAssertionStatusCode(ResponseAssertionStatusCodenode.getText());
						System.out.println("ResponseAssertionStatusCodenode.getText()="+ResponseAssertionStatusCodenode.getText());
					}
					
					Node ResponseAssertionMsgnode =testcaseElement.selectSingleNode("ResponseAssertionMsg");
					if(ResponseAssertionMsgnode!=null) {
						testcase.setResponseAssertionMsg(ResponseAssertionMsgnode.getText());
						System.out.println("ResponseAssertionMsgnode.getText()="+ResponseAssertionMsgnode.getText());

					}

				}
				else if(testcaseElement.attributeValue("type").equals("httphead")){
					testcase.setTestCaseID(testcasenum);
					testcase.setTestType(0);
					HashMap<String, String>  HeadValueMap=new HashMap<>();
					List HeadValuelist=testcaseElement.selectNodes("httphead/headelement");
					Iterator iter3 = HeadValuelist.iterator();//报文头键值对集合迭代器
					while (iter3.hasNext()) {
						Element headerElement = (Element)iter3.next();
						Node headername =headerElement.selectSingleNode("headername");
						Node headervalue=headerElement.selectSingleNode("headervalue");
						HeadValueMap.put(headername.getText(),headervalue.getText());
						System.out.println("headername=="+headername.getText());
						System.out.println("headervalue=="+headervalue.getText());
						
					}
					testcase.setHeadValueMap(HeadValueMap);
				}
				//测试步骤集合 添加一个步骤
				testCaseList.add(testcase);
				testcasenum++;
			
			}
			testsasesuite.setTestCaseList(testCaseList);
			testCaseSuiteList.add(testsasesuite);
			testcasesuitenum++;//循环至下一个测试用例集
			
		}
		return testCaseSuiteList;
	}
	
	
	
	public Document  creatxml() {
		 document = DocumentHelper.createDocument();      
		return document;
	}
	public Element  creatrootnode(String rootnodename) {
		root= document.addElement(rootnodename);
        return root;
	}
	
	public Element  addnode(Element element,String nodename,String nodetxt) {
		Element child=null;
		if(document==null) {
			return null;
		}
		if(element==null) {
			return null;
		}
		
		if((nodename!=null)&&(!"".equals(nodename))){
			child=element.addElement(nodename);
            child.setText(nodetxt);
		}	
		return 	child;
	}
	public int  addattribute(Element element,String attributename,String attributetxt) {
		Element child=null;
		if(document==null) {
			return 1;
		}
		if(element==null) {
			return 1;
		}
		
		if((attributename!=null)&&(!"".equals(attributename))){
			element.setAttributeValue(attributename, attributetxt);
		}	
		return 	0;
	}
	private  void writeToNewXMLDocument(Document document,String filename) throws IOException {
		XMLWriter writer = new XMLWriter(new FileWriter(filename));
		    writer.write(document);
		    writer.close();
		
	}
	
	
	
	
	@Test
	public void  TestxmlFunc1() {
		Document document=creatxml();  
		Element root=creatrootnode("root");//添加根节点
        root.setText("lumin");//设置节点值
        addnode(root,"child1","child1");
        addnode(root,"child2","child2");
        addnode(root,"child3","child3");
        
        Element child4=root.addElement("child4");
        root.remove(child4);//去除节点
        Element book=root.addElement("book");
        book.setText("book1");
        book=root.addElement("book");
        book.setText("book2");
        book=root.addElement("book");
        book.setText("book3");
        book=root.addElement("book");
        book.setText("book4");
        try {
			writeToNewXMLDocument(document,"newbooks.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void  TestxmlFunc2() {
		Document document = null ;  
		try {  
	        SAXReader saxReader = new SAXReader();  
	        document = saxReader.read(new File("newbooks.xml")); // 读取XML文件,获得document对象  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	        System.out.println("222");
	    } 
		System.out.println("11111");	
		
		Element root = document.getRootElement(); 
		List list=root.selectNodes("book");
		Iterator iter = list.iterator();  
		  
        while (iter.hasNext()) {  
            Element bookElement = (Element) iter.next();  		
			System.out.println(bookElement.getText());	
        }
        
        List list2= document.selectNodes("/root/book");
        Iterator iter2 = list2.iterator(); 
        
        while (iter2.hasNext()) {  
            Element bookElement = (Element) iter2.next();  		
			System.out.println(bookElement.getText());	
        }
       
	}

	

}
