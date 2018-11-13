package be.vdab.toysforboysWebApplication.services;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.entities.Product;
import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.exceptions.OrderNotFoundException;
import be.vdab.toysforboysWebApplication.exceptions.ShippingException;
import be.vdab.toysforboysWebApplication.repositories.OrderRepository;
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail;

@Service
@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
class DefaultOrderService implements OrderService {
	private Set<Long> unshippableOrders=new LinkedHashSet<>(); 
	private final OrderRepository orderRepository;
	DefaultOrderService(OrderRepository orderRepository) {
		this.orderRepository=orderRepository; 
	}
	
	@Override
	public Set<Long> getUnshippableOrders() {
		return unshippableOrders;
	}
	
	@Override
	public List<Order> findAllUnshippedOrders() {
		return orderRepository.findAllUnshippedOrders(); 
	}
	
	@Override
	public Optional<Order> read(long id) {
		return orderRepository.read(id); 
	}
	
	@Override
	@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
	public void setStatus(long id, Status status) {
		Optional<Order> optionalOrder = orderRepository.read(id); 
		if(optionalOrder.isPresent()) {
			optionalOrder.get().setStatus(status);
			optionalOrder.get().setShippedDate(LocalDate.now());					
			Set<OrderDetail> orderDetails = optionalOrder.get().getOrderDetails();
			for (OrderDetail detail : orderDetails) {
				Product product = detail.getProduct();
				product.adjustQuantities(detail); 
//				product.setQuantityInOrder(product.getQuantityInOrder()-detail.getQuantityOrdered()); 
//				product.setQuantityInStock(product.getQuantityInStock()-detail.getQuantityOrdered());
				if(product.getQuantityInStock() < 0) {
					//System.out.println("an unshippable order");
					unshippableOrders.add(id); 
					throw new ShippingException("There is insufficient stock for one of the products of the order."); 
 				}
			}
		} else {
			throw new OrderNotFoundException(); 
		}
	}
}
