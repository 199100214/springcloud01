package org.example.controller;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.Authority;
import org.example.service.impl.AuthorityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthorityController {
    @Resource
    AuthorityServiceImpl authorityService;
    @GetMapping("/authority/{id}")
    public Authority SelectId(@PathVariable("id") Integer id){
        return authorityService.selectAll(id);
    }
}
