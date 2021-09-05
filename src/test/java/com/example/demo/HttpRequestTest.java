package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import java.net.URI;
import java.util.Arrays;
import java.util.List;

/*Setting up application context for testing using spring boot features*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private NumberModel numberModel;
	
	/*Function Name: makeAPICall
	 * @Param-1: NumberModel numberModel
	 * @Return: ResponseEntity<String>
	 * Function makeAPICall is invoked to make Rest API calls, with the given inputs.
	 * And the result is returned to the invoking function.*/
	public ResponseEntity<String> makeAPICall(NumberModel numberModel) throws Exception{
		final String baseUrl = "http://localhost:"+port+"/calculate";
		URI uri = new URI(baseUrl);
		
		HttpEntity<NumberModel> request = new HttpEntity<>(numberModel);
        
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		return result;
	}
	
	/*Simple sanity check test that will fail if the application context cannot start.*/
	@Test
	public void contextLoads() {
	}
	
	//Default test case. Input: {"data" : [5, 4, 6, 1]}
	@Test
	public void defaultTestCase() throws Exception {
		List<Integer> data = Arrays.asList(5, 4, 6, 1);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"8.77"+"}", result.getBody());
	}
	
	//Secondary test case. Input: {"data" : [1, 2, 3, 4, 5]}
	@Test
	public void testCase2() throws Exception {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"7.07"+"}", result.getBody());
	}
	
	//Test case for large numbers in data Array list. Input: {"data" : [123, 99, 100, 95, 50]}
	@Test
	public void largeNumbersDataListTest() throws Exception {
		List<Integer> data = Arrays.asList(123, 99, 100, 95, 50);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"186.90"+"}", result.getBody());
	}
	
	//Test case for data Array list of size=3. Input: {"data" : [5, 5, 5]}
	@Test
	public void dataListSizeThreeTest() throws Exception {
		List<Integer> data = Arrays.asList(5, 5, 5);
		numberModel.setData(data);
			
		ResponseEntity<String> result = makeAPICall(numberModel);
			
	    Assertions.assertEquals(200, result.getStatusCodeValue());
	    Assertions.assertEquals("{\"output\":"+"8.66"+"}", result.getBody());
	}
	
	//Test case for data Array list of size=2. Input: {"data" : [15, 44]}
	@Test
	public void dataListSizeTwoTest() throws Exception {
		List<Integer> data = Arrays.asList(15, 44);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"46.49"+"}", result.getBody());
	}
	
	//Test case for data Array list of size=1. Input: {"data" : [5]}
	@Test
	public void dataListSizeOneTest() throws Exception {
		List<Integer> data = Arrays.asList(5);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"5.00"+"}", result.getBody());
	}
	
	//Test case for empty data Array list. Input: {"data" : []}
	@Test
	public void emptyDataTest()  throws Exception{
		List<Integer> data = Arrays.asList();
		numberModel.setData(data);
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"0.00"+"}", result.getBody());
	}
	
	//Test case for data Array list of containing multiple zeros. Input: {"data" : [0, 0, 0, 0, 0]}
	@Test
	public void zerosTest() throws Exception {
		List<Integer> data = Arrays.asList(0, 0, 0, 0, 0);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"0.00"+"}", result.getBody());
	}
	
	//Test case for data Array list of containing negative numbers. Input: {"data" : [-5, -4, -6, -1]}
	@Test
	public void negativeNumsTest() throws Exception {
		List<Integer> data = Arrays.asList(-5, -4, -6, -1);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"6.48"+"}", result.getBody());
	}
	
	//Test case for data Array list of containing negative numbers. Input: {"data" : [-5, -4, 0, 10]}
	@Test
	public void negativePostiveMixNumsTest() throws Exception {
		List<Integer> data = Arrays.asList(-5, -4, 0, 10);
		numberModel.setData(data);
		
		ResponseEntity<String> result = makeAPICall(numberModel);
		
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("{\"output\":"+"10.77"+"}", result.getBody());
	}
	
	//Test case for REST API call with body.}
	@Test
	public void emptyBodyTest() throws Exception {
		final String baseUrl = "http://localhost:"+port+"/calculate";
		URI uri = new URI(baseUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<>("", headers);
		
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		
        Assertions.assertEquals(415, result.getStatusCodeValue());
        Assertions.assertEquals("Invalid Input.", result.getBody());
	}
	
	/*Test Invalid input string. Ex: "abcdef"*/
	@Test
	public void testInvalidInputString() throws Exception{
		final String baseUrl = "http://localhost:"+port+"/calculate";
		URI uri = new URI(baseUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<>("abcdef", headers);
        
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		Assertions.assertEquals(400, result.getStatusCodeValue());
        Assertions.assertEquals("Invalid Input or Content-Type.", result.getBody());
	}
}
