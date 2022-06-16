package org.example.controller;

import org.example.impl.CustomServiceImpl;
import org.example.pojo.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CustomerController {
    @Resource
    private CustomServiceImpl customService;
    @GetMapping("/list")
    public List<Customer> customerList(){
        return customService.SelectAll();
    }
}
