package com.kzhou.springhook.factorybean;

public interface UserMapper {

    @Select(value = "SELECT * FROM USER WHERE VALID = 1")
    String testUserMapper();
}
