package org.net.erp.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.net.erp.json.MasterJson;
import org.net.erp.json.OperationStatus;
import org.net.erp.model.Master;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Configuration
public class BaseBO {
	@Value("${sms.grid.email}")
	private String smsgrid_email;

	@Value("${sms.grid.url}")
	private String smsgrid_url;

	@Value("${sms.grid.sender_id}")
	private String smsgrid_sender_id;

	@Value("${sms.grid.apikey}")
	private String smsgrid_apiKey;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Value("${message.url}")
	private String messageUrl;

	@Value("${message.sender.id}")
	private String senderId;

	@Value("${message.use.springedge}")
	private String useSpringEdge;

	/*
	 * 
	 * */
	public String setDeleteOperationStatus(boolean flag) {
		Gson gson = null;
		String json = null;
		OperationStatus status = null;
		try {
			gson = new GsonBuilder().setPrettyPrinting().create();
			status = new OperationStatus();
			if(flag) {
				status.setStatus(Constants.OP_STATUS_SUCCESSFUL);
			}else {
				status.setStatus(Constants.OP_STATUS_UNSUCCESSFUL);
			}
			json = gson.toJson(status);
		}catch(Exception e) {
			status.setStatus(Constants.OP_STATUS_UNSUCCESSFUL);
		}
		return json;
	}
	/*
	 * 
	 * */
	public boolean sendMessage(String messageContents,String clientNumber) throws IOException {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = null;
		boolean isSuccess = false;
		try {
			if(!activeProfile.equalsIgnoreCase("dev")) {
				if(useSpringEdge.equalsIgnoreCase("true")) {
					messageContents=URLEncoder.encode(messageContents, "UTF-8");
					URL url = new URL(messageUrl+"&sender="+senderId+"&to=91"+clientNumber+"&message="+messageContents);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.setDoOutput(true);
					con.getOutputStream();
					con.getInputStream();
					BufferedReader rd;
					String line;
					String result = "";
					rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
					while ((line = rd.readLine()) != null)
					{
						result += line;
					}
					rd.close(); 
					if(result.contains("AWAITED-DLR")) {
						isSuccess = true;
					}
				}else {
					httpClient = HttpClients.createDefault();
					HttpGet httpget = new HttpGet(smsgrid_url+"?user="+URLEncoder.encode(smsgrid_email, StandardCharsets.UTF_8.toString())+"&apikey="+ smsgrid_apiKey +"&route=4&sender_id="+smsgrid_sender_id+"&mobile="+ clientNumber +"&message="+URLEncoder.encode(messageContents, StandardCharsets.UTF_8.toString()));
					response = httpClient.execute(httpget);	
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity);    
						isSuccess = parseJson(result);                
					}	
				}
			}else {
				isSuccess = true;
			}		
		}catch(Exception e) {
			isSuccess = false;
			System.out.println("Error in sendMessage :: "+e.getMessage());
		}finally {
			if(null != response) {
				response.close();	
			}if(null != httpClient) {
				httpClient.close();	
			}					
		}		
		return isSuccess;
	}
	/*
	 * 
	 * */
	private boolean parseJson(String json) {
		boolean isSuccess = false;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
			if(jsonObject.get("msg").getAsString().equalsIgnoreCase("Successfully Submitted For Delivery")) {
				isSuccess = true;
			}
		}catch(Exception e) {

		}
		return isSuccess;
	}
	/*
	 * 
	 * */
	public String parseFetchMaster(List<Master> master) {
		Gson gson = null;
		MasterJson masterJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.MASTER_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(master.size());
			masterJson = new MasterJson();
			masterJson.setData(master);
			masterJson.setMeta(meta);
			json = gson.toJson(masterJson);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return json;
	}
}
