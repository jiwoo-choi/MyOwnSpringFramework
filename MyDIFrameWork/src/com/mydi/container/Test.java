package com.mydi.container;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import com.mydi.annotation.Autowired;

public class Test {

	
	static Testtt getTest() {
		return null;
		
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
//		MyDIApplication.run();
		// 시작하기..
		
		Container ct = new Container();

		// 어떻게처리한건지? //Resolving 처리방식에 대해서 Tree구조로?
		
		Test3 a = ct.getBean(Test3.class);
		System.out.println(a.test);
		
	}
	
}
