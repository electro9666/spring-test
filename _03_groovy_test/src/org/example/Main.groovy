package org.example

class Main {
	static void main(def args) {
		def main = new Main()
		main.hello()
		
	}
	void hello() {
		println "안녕하세요"

		// 동적 타입 바인딩 에러??
		def a = 20
		a = "문자열2"
//		b = "문자열3" //  groovy.lang.MissingPropertyException: No such property: b for class: org.example.Main
		println a
		
		// 정적 타입 바인딩
		int b = 20
//		b = "문자열" // 에러
		
		// 자동 형변환
		String c = "문자열"
		c = 20
		println c
		
		// 자바 변수 선언
		java.util.Date d = new java.util.Date()
		Date e = new Date()
		
	}
}
