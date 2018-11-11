package be.vdab.toysforboysWebApplication.services;

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
	private final OrderRepository orderRepository;
	DefaultOrderService(OrderRepository orderRepository) {
		this.orderRepository=orderRepository; 
	}
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll(); 
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
			Set<OrderDetail> orderDetails = optionalOrder.get().getOrderDetails();
			for (OrderDetail detail : orderDetails) {
				Product product = detail.getProduct();
				if(product.getQuantityInStock() < detail.getQuantityOrdered()) {
					throw new ShippingException(); 
				}
				else {
					product.setQuantityInOrder(product.getQuantityInOrder()-detail.getQuantityOrdered()); 
					product.setQuantityInStock(product.getQuantityInStock()-detail.getQuantityOrdered());
				}
			}
		} else {
			throw new OrderNotFoundException(); 
		}
//		Optional<Product> optionalProduct = productRepository.read(id); 
//		if(optionalProduct.isPresent()) {
//			optionalProduct.get().
//		}

	}
}
