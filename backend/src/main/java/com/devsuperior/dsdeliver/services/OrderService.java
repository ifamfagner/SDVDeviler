package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repository.OrderRepository;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductRepository productRepositoy;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		return this.repository.findOrdersWithProducts().stream().
				map(p-> new OrderDTO(p)).
				collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		Order order = new Order(null,dto.getAddress(),
				dto.getLatitude(),dto.getLongitude(),Instant.now(),OrderStatus.PENDING);
		
		for(ProductDTO p :dto.getProducts()) {
			Product product = this.productRepositoy.getOne(p.getId());
			order.getProducts().add(product);
		}		
		order = this.repository.save(order);
		return new OrderDTO(order);
	}
	
	
}
