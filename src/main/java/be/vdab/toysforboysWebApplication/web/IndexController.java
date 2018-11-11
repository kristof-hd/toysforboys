package be.vdab.toysforboysWebApplication.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {
	
	private final static String VIEW="index"; 
	private final OrderService orderService; 
	private static final String REDIRECT_NA_VERWIJDEREN="redirect:/";

	IndexController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping
	ModelAndView orders() {
		return new ModelAndView(VIEW, "orders", orderService.findAll()); 
	}

	@PostMapping
	String actionsSetAsShipped(long[] shipid) {
		if(shipid!=null) {
			for (long id: shipid) {
				orderService.setStatus(id, Status.SHIPPED);
			}
		}		
		return REDIRECT_NA_VERWIJDEREN; 
	}	
	
}