package com.example.orderservice.controller;

import com.example.orderservice.VO.Customer;
import com.example.orderservice.VO.ResponseTemplateVO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Customer> customerList = orderService.getAllCustomer();

        model.addAttribute("customers", customerList);
        return "index";
    }
    @RequestMapping(value = "add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveCustomer(Customer customer) {
        orderService.saveCustomer(customer);
        return "redirect:/orders/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam("id") Long cusId, Model model) {
        orderService.deleteCustomer(cusId);
        return "redirect:/orders/";
    }

//    @PostMapping("/")
//    public Order saveOrder(@RequestBody Order order){
//
//        return orderService.saveOrder(order);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseTemplateVO getOrderByCustomer(@PathVariable("id")
//                                                            Long orderId){
//        return orderService.getOrderByCustomer(orderId);
//    }
}
