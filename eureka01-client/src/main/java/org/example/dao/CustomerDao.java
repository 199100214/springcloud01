package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

//
/*
实baseMapper的接口
* */
@Mapper
public interface CustomerDao extends BaseMapper<Customer> {

}
