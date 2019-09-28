package com.incentives.piggyback.location;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.incentives.piggyback.location.controller.LocationController;
import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.publisher.LocationEventPublisher;
import com.incentives.piggyback.location.service.LocationService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LocationTest {

	private MockMvc mvc;

	@Mock
	private LocationService notificationService;

	@Mock
	private LocationEventPublisher.PubsubOutboundGateway messagingGateway;

	@InjectMocks
	LocationController notificationController;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(notificationController).build();
	}

	@Test
	public final void saveLocation() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Location location = new Location();
		location.setDeviceId("131231");
		location.setGpsAccuracy(98);
		location.setLatitude(1.18);
		location.setLongitude(102.22);
		location.setUserId("1");
		Gson gson = new Gson();
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/location")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(gson.toJson(location));
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void getNearbyUsers() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/location/user")
				.param("userId", "1")
				.param("latitude", "1.18")
				.param("longitude", "102.22")
				.param("optimizedRadius", "100")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}