package org.greg.resteasy.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.greg.resteasy.controller.DemoControllerTest.TestConfig;
import org.greg.resteasy.server.NettyServer;
import org.greg.resteasy.vo.DemoMessage;
import org.greg.resteasy.vo.DemoName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/*
 * TODO This is an integration test
 */
@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoControllerTest {

	@Configuration
	public static class TestConfig {

		@Bean
		public NettyServer nettyServer() {
			return new NettyServer();
		}

		@Bean
		public DemoController homeController() {
			return new DemoController();
		}

		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Autowired
	NettyServer		server;
	@Autowired
	RestOperations	restOps;

	@Before
	public void init() {
		server.start();
	}
	
	@After
	public void shutdown() throws InterruptedException{
		Thread.sleep(5000);//allow controller to finish before cleanup
		server.cleanUp();
	}

	@Test
	public void testHelloworld() {
		DemoMessage resp = restOps.getForObject(buildUrl("hello/world"), DemoMessage.class);
		assertNotNull(resp);
		assertTrue(StringUtils.hasText(resp.getMessage()));
	}

	@Test
	public void testPOST() {
		int id = 44;
		String name = "NAME";
		DemoName resp = restOps.postForObject(
				buildUrl("hello"),
				new DemoName(id, name),
				DemoName.class);
		assertNotNull(resp);
		assertTrue(resp.getId().equals(id));
		assertTrue(resp.getName().equals(name));
	}

	public String buildUrl(String target) {
		return String.format("http://localhost:%d/%s/%s",
				server.getPort(), server.getRootResourcePath(), target);
	}

}
