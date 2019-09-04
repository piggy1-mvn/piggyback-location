package com.incentives.piggyback.location;

import com.incentives.piggyback.location.serviceImpl.LocationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LocationApplicationTests {

	@InjectMocks
	private LocationServiceImpl locationService;

	@Before
	public void setUp() {
		MockMvcBuilders.standaloneSetup(locationService).build();
	}

	@Test
	public void contextLoads() {
	}

}