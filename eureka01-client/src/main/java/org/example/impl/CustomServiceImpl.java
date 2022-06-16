package org.example.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dao.CustomerDao;
import org.example.pojo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomServiceImpl {
    @Resource
    private CustomerDao customerDao;
    public List<Customer> SelectAll(){
        QueryWrapper<Customer> customerWrapper = new QueryWrapper<>();
        return customerDao.selectList(customerWrapper);
    }
}
