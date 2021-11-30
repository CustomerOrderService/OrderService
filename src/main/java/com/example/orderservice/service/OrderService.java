package com.example.orderservice.service;

import com.example.orderservice.VO.Customer;
import com.example.orderservice.VO.ResponseTemplateVO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
    public Customer saveCustomer(Customer customer){
        return restTemplate.postForObject("http://localhost:9001/customers/",customer, Customer.class);
    }
    public void deleteCustomer(Long cusId){
        Map< String, String > params = new HashMap<>();
        params.put("id", "5");
        restTemplate.delete("http://localhost:9001/customers/{id}", params);
    }

    public ResponseTemplateVO getOrderByCustomer(Long cusId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Order order = orderRepository.findById(cusId).get();
        vo.setOrder(order);
         Customer customer =
                restTemplate.getForObject("http://localhost:9001/customers/"
                                + order.getOrderId(),
                        Customer.class);

        vo.setCustomer(customer);

        return vo;
    }
    public List<Customer> getAllCustomer() {
        Customer[] cus = restTemplate.getForObject("http://localhost:9001/customers/", Customer[].class);
        List<Customer> customers = Arrays.asList(cus);
        return customers;
    }
}
