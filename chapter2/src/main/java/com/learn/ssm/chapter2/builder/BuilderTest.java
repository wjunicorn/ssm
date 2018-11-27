package com.learn.ssm.chapter2.builder;

public class BuilderTest {
	public static void main(String[] args) {
		TicketHelper helper = new TicketHelper();
		helper.buildAdult("成人票");
		helper.buildChildrenForSeat("有座儿童票");
		helper.buildchildrenNoSeat("无座儿童票");
		helper.buildElderly("老年人票");
		helper.buildSoldier("军人及其家属票");
		Object ticket = TicketBuilder.builder(helper);
	}
}
