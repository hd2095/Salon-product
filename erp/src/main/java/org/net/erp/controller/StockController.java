/*
 * package org.net.erp.controller;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.net.erp.bo.StockBO; import org.net.erp.repository.StockRepository;
 * import org.net.erp.util.Constants; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * @Controller
 * 
 * @RequestMapping("inventory") public class StockController {
 * 
 * @Autowired StockBO stockBO;
 * 
 * @Autowired StockRepository stockRepo;
 * 
 * @RequestMapping("/stock") public String showStocksPage() { return
 * "display/display-stock-page"; }
 * 
 * @RequestMapping("/stock/getAllStock") public ResponseEntity<?>
 * getAllStock(HttpServletRequest request){ int id = (int)
 * request.getSession().getAttribute(Constants.SESSION_ORGANIZATION_KEY); String
 * jsonValue = stockBO.parseFetchStock(stockRepo.findByMasterId(id)); return
 * ResponseEntity.ok(jsonValue); } }
 */