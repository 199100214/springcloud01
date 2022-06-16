package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_customer_authority")
public class Authority {
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private int id ;
    private int customer_id;
    private int authority_id;
    private List<Customer> customerList;
}
