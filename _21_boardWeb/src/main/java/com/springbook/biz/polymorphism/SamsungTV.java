package com.springbook.biz.polymorphism;

public class SamsungTV implements TV {
	private SonySpeaker sonySpeaker;
	private int price;
	
	public SamsungTV() {
		
	}
	
	public SamsungTV(SonySpeaker sonySpeaker, int price) {
		System.out.println("Samsung 생성자");
		this.sonySpeaker = sonySpeaker;
		this.price = price;
	}

	public void initMethod() {
		System.out.println("초기화 작업");
	}
	public void destroyMethod() {
		System.out.println("삭제 직전 작업");
	}
	
	
	public void setSonySpeaker(SonySpeaker sonySpeaker) {
		this.sonySpeaker = sonySpeaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.println("samsung powerOn");
	}
	
	@Override
	public void volumeUp() {
		sonySpeaker.volumeUp();
	}
}
