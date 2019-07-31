package com.ssm.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class FileUtils {
	
	@Test
	public void test() throws IOException {
		 
		FileReader fr1 = new FileReader("bbb.txt");
        FileReader fr2 = new FileReader("aaa.txt");
       
        int len;
        System.out.println(fr1.getEncoding());
        System.out.println(fr2.getEncoding());
        //一次读取一个字符 也就是大概2个字节  ，读取中文会是乱码
        //如非要用FileReader的话，可以将要读取的文件改为通用的编码（如UTF-8）.如txt的文件可以在另存为中设置编码。然后读取 显示 写入都是正常的。
        //系统默认是 GB2312编码  具体啥模式 我也不清楚 好像随意的
        //这里的关系是，UTF-8 是 Unicode 的实现方式之一。
        //Unicode 是「字符集」 UTF-8 是「编码规则」
        while((len = fr2.read()) != -1) {
        	 
        	 if(len=='\n') {
        		 System.out.println("换行");
        	 }
        	 else if(len=='\r') {
        		 System.out.println("回车");
        	 }
        	 else {
        		 System.out.println((char)len);
        	 }
        }
        
	}

}
