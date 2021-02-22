package com.mydi.container;

import com.mydi.annotation.Bean;

public class Configuration {

	
	@Bean
	public Testtt test() {
		return new Test2();
	}
	
	@Bean
	public Test3 test3() {
		return new Test3();
	}
	
	//빈의ID : 메소드 네임
	//빈의타입 : AUTOWIRED할때 필요한것들..
	//실제객체 : 실제로 리턴되는값.
	//ID가같은지?
	//이름이달라도 되는지?=> AUTOWIRED는 이름가는 상관없음. 
	//클래스와 인터페이스 둘다 참조하지않잖아.
	//참조할수있는객체를찾자. 자동으로 찾아서바인딩해줌.
	//참조할 수 있는 객체를 자동으로 바인딩해줌.. -> 
	//NewLectureExam -> Exam까지 자동으로...
	//1. Return Type 적응되는걸로?
	//2. 일단 RESOLVING하는 방식은 모르겠음 그러ㅣㄴ까 
	//3. 일단 Return Type기준으로 생성하자.
}
