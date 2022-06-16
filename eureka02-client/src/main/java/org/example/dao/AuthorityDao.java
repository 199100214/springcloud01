package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Authority;

@Mapper
public interface AuthorityDao extends BaseMapper<Authority> {
}
