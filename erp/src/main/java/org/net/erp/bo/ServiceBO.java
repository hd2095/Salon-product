package org.net.erp.bo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.net.erp.json.ServiceJson;
import org.net.erp.model.Services;
import org.net.erp.repository.CategoryRepository;
import org.net.erp.repository.ServiceRepository;
import org.net.erp.model.Category;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class ServiceBO extends BaseBO{

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ServiceRepository serviceRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBO.class);
	
	/*
	 * 
	 * */
	public String parseFetchService(Map<String,List<Services>> services) {
		Gson gson = null;
		ServiceJson serviceJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.SERVICE_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(services.size());
			serviceJson = new ServiceJson();
			serviceJson.setData(services);
			serviceJson.setMeta(meta);
			json = gson.toJson(serviceJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchService :: "+e.getMessage());
		}		
		return json;
	}
	/*
	 * 
	 * */
	public String parseFetchService(List<Services> services) {
		Gson gson = null;
		ServiceJson serviceJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.SERVICE_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(services.size());
			serviceJson = new ServiceJson();
			serviceJson.setListData(services);
			serviceJson.setMeta(meta);
			json = gson.toJson(serviceJson);
		}catch(Exception e) {
			LOGGER.error("Exception in parseFetchService overloaded :: "+e.getMessage());
		}		
		return json;
	}
	/*
	 * 
	 * */
	public Map<Category,List<Services>> serviceWithCategory(int key){
		Map<Category,List<Services>> mapToDisplay = new LinkedHashMap<Category,List<Services>>();
		try {
			List<Category> categories = categoryRepo.findByMasterId(key);		
			List<Services> services = serviceRepo.findByMasterId(key);
			for(Category category : categories) {					
				mapToDisplay.put(category,services
						.stream()
						.filter(service -> service.getCategory().getCategoryId() == category.getCategoryId())
						.collect(Collectors.toList()));
			}
		}catch(Exception e) {
			LOGGER.error("Exception in serviceWithCategory :: "+e.getMessage());
		}
		return mapToDisplay;
	}
	/*
	 * 
	 * */
	public Map<String,List<Services>> serviceWithCategoryJson(int key){
		Map<String,List<Services>> mapToDisplay = new LinkedHashMap<String,List<Services>>();
		try {
			List<Category> categories = categoryRepo.findByMasterId(key);		
			List<Services> services = serviceRepo.findByMasterId(key);
			for(Category category : categories) {					
				mapToDisplay.put(category.getCategoryName(),services
						.stream()
						.filter(service -> service.getCategory().getCategoryId() == category.getCategoryId())
						.collect(Collectors.toList()));
			}
		}catch(Exception e) {
			LOGGER.error("Exception in serviceWithCategoryJson :: "+e.getMessage());
		}
		return mapToDisplay;
	}
	/*
	 * 
	 * */
	public String parseService(Map<String,Services> services) {
		Gson gson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			json = gson.toJson(services);
		}catch(Exception e) {
			LOGGER.error("Exception in parseService :: "+e.getMessage());
		}		
		return json;
	}
}
