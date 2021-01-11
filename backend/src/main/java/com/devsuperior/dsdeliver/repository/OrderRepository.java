package com.devsuperior.dsdeliver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
