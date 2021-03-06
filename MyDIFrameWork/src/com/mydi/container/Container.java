package com.mydi.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

import com.mydi.annotation.Autowired;
import com.mydi.annotation.Bean;

public class Container {

	private static Container instance = null;
	public HashMap<String, Object> innerContainer =new HashMap<>();
	
	public Container() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		// 1차로 컨테이너에 모두 등록.
		Configuration config = new Configuration();
		for(Method m : config.getClass().getDeclaredMethods()) {
			if(m.getAnnotation(Bean.class) != null) {
				Object returned = m.invoke(config);
				innerContainer.put(returned.getClass().getTypeName(), returned);
				innerContainer.put(m.getReturnType().getName(),returned);
			}
		}
		// 2차로 모두 등록된 것들을 돌아보면서 AutoWired해결.
		for(String key : this.innerContainer.keySet()) {
			
			Object obj = innerContainer.get(key);
			
			for(Field f : obj.getClass().getDeclaredFields()) {
				if (f.getAnnotation(Autowired.class) != null) {
					//type으로찾습니다.
					Object obj2 = innerContainer.get(f.getType().getTypeName());
					f.setAccessible(true);
					f.set(obj, obj2);
				}
			}
			
			for(Method f : obj.getClass().getDeclaredMethods()) {
				if (f.getAnnotation(Autowired.class) == null) continue;
				for (Parameter p : f.getParameters()) {
					Object obj2 = innerContainer.get(p.getType().getTypeName());
					f.setAccessible(true);
					f.invoke(obj, obj2);
				}
			}
		
			for(Constructor<?> f : obj.getClass().getDeclaredConstructors()) {
				for (Parameter p : f.getParameters()) {
					if (p.getAnnotation(Autowired.class) != null) {
						Object obj2 = innerContainer.get(p.getType().getTypeName());
						innerContainer.put(key, f.newInstance(obj2));
					}
				}
			}
		}
		
//		for(Object obj : this.innerContainer.values()) {
//			for(Field f : obj.getClass().getDeclaredFields()) {
//				if (f.getAnnotation(Autowired.class) != null) {
//					//type으로찾습니다.
//					Object obj2 = innerContainer.get(f.getType().getTypeName());
//					f.setAccessible(true);
//					f.set(obj, obj2);
//				}
//			}
//		}
		System.out.println(innerContainer.toString());		
	}
	
	public <T> T getBean(Class<T> type){
		return (T) innerContainer.get(type.getTypeName());
	}
	
}
