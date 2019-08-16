package com.ssm.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

//测试所有容器类
public class TestContainer {
	
	@Test
	public void testArrarList()
	{
		ArrayList<String> heros = new ArrayList<String>();
		 
		//添加  可以添加空字符串
        // 把5个对象加入到ArrayList中
        for (int i = 0; i < 5; i++) {
            heros.add(new String("hero " + i));
        }
        System.out.println(heros);
 
        // 在指定位置增加对象
        String specialHero = new String("special hero");
        heros.add(3, specialHero);
 
        //可以添加空对象
        heros.add(null);
        System.out.println(heros.toString());
        
        //包含功能
        System.out.print("虽然一个新的对象名字也叫 hero 1，但是contains的返回是:");
        System.out.println(heros.contains(new String("hero 1")));
        System.out.print("而对specialHero的判断，contains的返回是:");
        System.out.println(heros.contains(specialHero));
        
        
        //根据获取指定位置的对象
        System.out.println(heros.get(5));
        //如果超出了范围，依然会报错
        //System.out.println(heros.get(6));
		
        //获取位置
        System.out.println("specialHero所处的位置:"+heros.indexOf(specialHero));
        
        
        //删除
        heros.remove(2);
        System.out.println("删除下标是2的对象");
        System.out.println(heros);
        
        //获取大小
        System.out.println("获取ArrayList的大小：");
        System.out.println(heros.size());
        
        //转换为数组
        String hs[] = (String[])heros.toArray(new String[]{});
        System.out.println("数组:" +hs[0]);
        
        // 第一种遍历 for循环
        System.out.println("--------for 循环-------");
        for (int i = 0; i < heros.size(); i++) {
        	String h = (String) heros.get(i);
            System.out.println(h);
        }
        
        
        Iterator<String> it= heros.iterator();
        //从最开始的位置判断"下一个"位置是否有数据
        //如果有就通过next取出来，并且把指针向下移动
        //直到"下一个"位置没有数据
        while(it.hasNext()){
        	String h = it.next();
            System.out.println(h);
        }
        
       // 第三种，增强型for循环
        System.out.println("--------增强型for循环-------");
        for (String h : heros) {
            System.out.println(h);
        }
        
        //清空
        System.out.println("使用clear清空");
        heros.clear();
	}
	
	
	@Test
	public void testLinkList()
	{
		
		LinkedList llist = new LinkedList();
        //---- 添加操作 ----
        // 依次添加1,2,3
        llist.add("1");
        llist.add("2");
        llist.add("3");
 
        // 将“4”添加到第一个位置
        llist.add(1, "4");
		
	}

}
