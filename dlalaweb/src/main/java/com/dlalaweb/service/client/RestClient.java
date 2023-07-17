package com.dlalaweb.service.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dlalacore.dlala.entities.Utilisateur;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;








@SuppressWarnings("serial")
public class RestClient<T extends Serializable> implements Serializable {

	private String				server	= "http://" + System.getenv("MYDLALA");

	
  private RestTemplate rest;
  private HttpHeaders  headers;
  private HttpStatus   status;

	public RestClient() {
	   this.rest = new RestTemplate();
	    this.headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");
	}

	  public T get(String uri, ParameterizedTypeReference<T> responseType) {
	    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
	    ResponseEntity<T> responseEntity = rest.exchange(server+ uri, HttpMethod.GET, requestEntity, responseType);
	    this.setStatus(responseEntity.getStatusCode());
	    responseEntity.getBody();
	    return responseEntity.getBody();
	  }
	  
	  public List<T> getAll(String uri, ParameterizedTypeReference<List<T>> responseType) {
	    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
	    ResponseEntity<List<T>> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, responseType);
	    this.setStatus(responseEntity.getStatusCode());
	    return responseEntity.getBody();
	  }
	  
	  public T post(String uri, T object, ParameterizedTypeReference<T> responseType) {
	    HttpEntity<T> requestEntity = new HttpEntity<T>(object, headers);
	    ResponseEntity<T> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, responseType);
	    this.setStatus(responseEntity.getStatusCode());
	    return responseEntity.getBody();
	  }
	  
	  
	  
	  
	  public void setStatus(HttpStatus status) {
	    this.status = status;
	  }





		public HttpStatus getStatus() {
			
			return this.status;
		}
		

	

}
