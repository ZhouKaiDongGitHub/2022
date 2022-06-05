package com.kzhou.netty.demo6.com.kzhou.protobuf;

import com.kzhou.protobuf.DataInfo;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/21 9:47
 **/
public class DemoTest {
    public static void main(String[] args) throws Exception{
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("周开冬")
                .setAge(26)
                .setAddress("苏州市")
                .setIdCard(DataInfo.Student.IdCard.newBuilder()
                        .setIdNumber("320923199511636327")
                        .setIdType("1").build())
                .setType(DataInfo.Student.PhoneType.WORK)
                .build();
        byte[] studentBytes = student.toByteArray();

        DataInfo.Student student1 = DataInfo.Student.parseFrom(studentBytes);
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
        System.out.println(student1.getIdCard().getIdNumber());
        System.out.println(student1.getIdCard().getIdType());
        System.out.println(student1.getType());
    }
}
