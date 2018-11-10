package be.vdab.toysforboysWebApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.repositories.OrderRepository;

@Service
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
}
