package org.example.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class Customer implements Serializable {

   private int id;
   private String username;
   private String password;
   private String jobs;
   private String phone;
   private int sex;
}
