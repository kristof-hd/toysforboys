package be.vdab.toysforboysWebApplication.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.services.OrderService;
import be.vdab.toysforboysWebApplication.valueobjects.FormattedNumber;

@Controller
@RequestMapping("orders")
class OrderController {
	private final static String VIEW = "order";
	private final static String REDIRECT_ORDER_NOT_FOUND = "redirect:/";
	private final OrderService orderService;

	OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("{id}")
	ModelAndView order(@PathVariable long id) {
		Optional<Order> order = orderService.read(id);
		if (order.isPresent()) {
			ModelAndView modelAndView = new ModelAndView(VIEW);
			modelAndView.addObject(order.get());
			modelAndView.addObject("totalValue", new FormattedNumber(order.get().getTotalValue()));
			return modelAndView;
		}
		return new ModelAndView(REDIRECT_ORDER_NOT_FOUND);
	}
}
