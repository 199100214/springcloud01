package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName("t_customer")
public class Customer implements Serializable {
        /*
    *   AUTO(0),
    NONE(1),
    INPUT(2),
    ASSIGN_ID(3),
    ASSIGN_UUID(4);*/
        //value主键的名称，如果主键的名称是id就可以不用写
        //type 指定的是主键的类型
//可以 extends Model 类名直接就可以点 方法
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
   private int id;
   private String username;
   private String password;
   private String jobs;
   private String phone;
   private int sex;
}
