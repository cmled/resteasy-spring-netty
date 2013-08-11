package com.github.cmled.resteasy.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.github.cmled.resteasy.controller.DemoController;

public class Main {

	public static void main(String[] args)
			throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext("root-context.xml");
		Assert.notNull(ac);
		Assert.notNull(ac.getBean(DemoController.class));

		NettyServer netty = ac.getBean(NettyServer.class);

		netty.start();

	}
}
