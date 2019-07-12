package com.ssm.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringUtil {
	public static int  findStringandreplace(String str) {
	    Map map = new HashMap();     
	    map.put("name", "Jame Gosling");     
	    map.put("alias", "Rod Johnson"); 
	    map.put("city",  "南京"); 
	    
	  //在java中，\是转译字符，\\表示要插入一个正则表达式的\
	  //所以下列中真正的正则表达式是:\$\{([^\}]+)\}
	  // 第一个 \ 代表下面的是一个特殊字符 不需要进行正则表达式转换 就是说以 $开头 （$ 在正则表达式中有特殊意义）
	  // 同理 第二个 \ 接下来是符号{
	  // () 子表达式 
	  //  [] 代表 字符集   \\} 意思就是 任意 字符以}结尾的
	 //^表示字符串的开头，但它还有另外一个含义。当在一组方括号([ ])里使用 ^ 时，它表示"非"或"排除"的意思，常常用来剔除某个字符,此处剔除符号 \
	    
        //以 $开头 + 下一个符号{  再加 子表达式  
	    //子表达式 为匹配非} 可多次 ， 结束以 }
	    String regex = "\\$\\{([^\\}]+)\\}"; 
	    
         //表示将给定的正则表达式编译到具有给定标志的模式中。
		 Pattern p = Pattern.compile(regex); 
		 //创建匹配给定输入与此模式的匹配器。
		 Matcher m = p.matcher(str);   
		 String g;   
		 //尝试查找与该模式匹配的输入序列的下一个子序列
		 while (m.find()) { 
			    //返回由以前匹配操作所匹配的输入子序列。
		        g = m.group(1);
		        System.out.println("g=="+g);
		        str = m.replaceFirst(map.get(g) + "");     
		        m = p.matcher(str);     
		    }     
		    System.out.println(str);    
		
		//本人写的正则表达式 
		String line2 = "${name} did a great job, so ${alias} did.";  
		//本人写的正则表达式 以 ${开头 中间任意字符  结尾是}   答案是错误的
	   // String myregex="\\$\\{([....])\\}";
	    // String myregex="\\$\\{.{4}\\}";  不少于四个字符
		
	    String myregex="\\$\\{(^\\})\\}";
		Pattern p2 = Pattern.compile(myregex);  
		Matcher m2 = p2.matcher(line2);  
		String g2;     
		 while (m2.find()) {     
				System.out.println("find" ); 
				
		        g2 = m2.group(1);//此处报错因为我没有 匹配子序列 ，group 必须有子序列 ,换言之，正在表达式中必须要有 （）才行，而中间匹配的 才是group返回的
		        System.out.println("g2=="+g2); 
		        System.out.println("m2.start=="+m2.start());  
		        System.out.println("m2.end=="+m2.end());  
		        //替换整个正则表达式 匹配的内容
		        line2 = m2.replaceFirst(map.get(g2) + "");     
		        m2 = p2.matcher(line2);    
		        System.out.println("line2 中间=="+line2);  
		 }     
		System.out.println("line2=="+line2);    
		    
		return 0;
	}
	
	@Test
	public void testZZBDS(){
		String line = "${name} did a great job, so ${alias} did."; 
		
		findStringandreplace(line);
		
	}


}
