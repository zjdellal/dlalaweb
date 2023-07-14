package com.dlalaweb.service.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

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
	  
	  public void setStatus(HttpStatus status) {
	    this.status = status;
	  }





		public HttpStatus getStatus() {
			
			return this.status;
		}
		
//		
//		 HttpClient client = new DefaultHttpClient();
//		  HttpGet get = new HttpGet("Http://localhost:8081/utilisateur/27");
//		  
//
//		  HttpResponse response = client.execute(get);
//		  Class<? extends InputStream> var = response.getEntity().getContent().getClass();
//		  System.out.println(var);
//		  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		  
//		  String line = "";
//		  while ((line = rd.readLine()) != null) {
//		   System.out.println(line);
//		  }
		 



		
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		 HttpClient client = new DefaultHttpClient();
//		  HttpGet request = new HttpGet("Http://localhost:8081/utilisateur/27");
//		  HttpResponse response = client.execute(request);
//		  InputStream var = response.getEntity().getContent();
//		  ObjectInputStream ois = new ObjectInputStream(var);
//		  Utilisateur user = (Utilisateur) ois.readObject();
//		  System.out.println(user);
//		  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
//		  String line = "";
//		  while ((line = rd.readLine()) != null) {
//		    System.out.println(line);
//		  }
		
//		try {
//			HttpGet getRequest = new HttpGet("Http://localhost:8081/utilisateur/27");
//			getRequest.addHeader("accept", "application/jason");
//			HttpResponse response = httpClient.execute(getRequest);
//			int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode != 200) {
//				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
//			}
//			HttpEntity httpEntity = response.getEntity();
//			String apiOutput = EntityUtils.toString(httpEntity);
//			System.out.println(apiOutput);
//
//			JAXBContext jaxbContext = JAXBContext.newInstance(Utilisateur.class);
//			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			Utilisateur user = (Utilisateur) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
//			System.out.println(user.getId());
//			System.out.println(user.getNom_utilisateur());
//			System.out.println(user.getPrenom_utilisateur());
//		} finally {
//			// Important: Close the connect
//			httpClient.getConnectionManager().shutdown();
//		}

	

}
