package org.net.erp.controller;

import org.net.erp.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	@Autowired
	private MasterRepository masterRepo;
	
}
