package org.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dao.AuthorityDao;
import org.example.pojo.Authority;
import org.example.pojo.Customer;
import org.example.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Resource
    AuthorityDao authorityDao;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Override
    public Authority selectAll(Integer id) {
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.select("id,customer_id,authority_id");//写的 select * 中的 *
        List<Authority> authoritiesList = authorityDao.selectList(queryWrapper);
        Authority authorities = authoritiesList.get(0);
        authorities.setCustomerList(selectIdByDiscoverClient02());
        return authorities;
    }

    private List<Customer> selectIdByDiscoverClient01() {
        StringBuffer buffer = null;
        //获取服务列表
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("eureka-server-client");
        if (CollectionUtils.isEmpty(serviceInstanceList))
            return null;
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        System.out.println(serviceInstance.getHost());
        buffer = new StringBuffer();
        buffer.append("http://"+serviceInstance.getHost()+":"+"9000"+"/list");
//        buffer.append("http://"+"192.168.117.1:9000"+"/list");
        System.out.println(buffer.toString());

        //responseEntity 封装返回数据
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
                buffer.toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});


        return response.getBody();
    }
//    采用loadBalancerClient返回数据
    private List<Customer> selectIdByDiscoverClient02(){
        StringBuffer buffer = null;
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-server-client");
        if (null == serviceInstance)
            return null;
        buffer = new StringBuffer();
        buffer.append("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/list");
//        buffer.append("http://"+"192.168.117.1:9000"+"/list");
//        System.out.println("http://"+"192.168.117.1:9000"+"/list");
        System.out.println(buffer.toString());//查看轮询的方式
        //responseEntity 封装返回数据
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
                buffer.toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});


        return response.getBody();
    }
    //通过注解的方式 重新
    private List<Customer> selectIdByLoadBalancerAnnotation(){
//        1.在restTemplate的Bean对象上添加@
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
                "http://eureka-server-client/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Customer>>(){});
        return response.getBody();
    }
}
