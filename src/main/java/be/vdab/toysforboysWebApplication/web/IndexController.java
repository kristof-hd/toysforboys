package be.vdab.toysforboysWebApplication.web;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.OptimisticLockException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboysWebApplication.exceptions.ShippingException;
import be.vdab.toysforboysWebApplication.services.OrderService;

@Controller
@RequestMapping("/")
class IndexController {

	private final static String VIEW = "index";
	private final OrderService orderService;

	IndexController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	ModelAndView orders() {
		return new ModelAndView(VIEW, "orders", orderService.findAllUnshippedOrders());
	}

	@PostMapping
	ModelAndView shipOrders(long[] shipid) {
		Set<Long> unshippableOrders=new LinkedHashSet<>();
		ModelAndView modelAndView = new ModelAndView(VIEW);
		if (shipid != null) {
			for (long id : shipid) {
				try {
					orderService.shipOrder(id);

				} catch (ShippingException | OptimisticLockException ex) {
					ex.getMessage();
					unshippableOrders.add(id); 
				}
			}

		}
		modelAndView.addObject("orders", orderService.findAllUnshippedOrders());
		modelAndView.addObject("unshippableOrders", unshippableOrders); 
		return modelAndView;
	}
	
}