package com.jmisnaza.callcenter;

import com.jmisnaza.callcenter.logic.services.EmployedServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterApplicationTests {

	MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Autowired
	Dispatcher dispatcher;

	@MockBean
	EmployedServices employedServices;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.dispatcher).build();// Standalone context
		// mockMvc = MockMvcBuilders.webAppContextSetup(wac)
		// .build();
	}

	@Test
	public void test10Request() throws Exception {
		mockMvc.perform(get("/dispatcher/"));
	}
}
