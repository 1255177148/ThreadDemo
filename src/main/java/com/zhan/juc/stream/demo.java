package com.zhan.juc.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:59
 * 使用 stream流 来计算
 *
 * 题目要求：只用一行代码完成下列需求
 * 现在有5个用户，筛选：
 * 1、id 必须是偶数；
 * 2、年龄必须大于23岁；
 * 3、用户名转为大写字母；
 * 4、用户名字母 逆序排序；
 * 5、只输出一个用户。
 */
public class demo {

    public static void main(String[] args) {
        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(6, "e", 25);
        // 转成集合存储
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);

        // 使用 stream 流 来计算
        list.stream()
                .filter(user -> user.getId() % 2 == 0) // 过滤掉 id 不是偶数的
                .filter(user -> user.getAge() > 23) // 过滤掉年龄 小于 23岁的
                .map(user -> {               // 使用 map映射，将原对象映射到一个新对象，并且将 用户名 转大写
                    User u = new User();
                    u.setId(user.getId());
                    u.setName(user.getName().toUpperCase());
                    u.setAge(user.getAge());
                    return u;
                })
                .sorted((u1, u2) -> u2.getName().compareTo(u1.getName())) // 按 用户名 逆序排序
                .limit(1) // 设置返回的流的长度，只返回一个
                .forEach(System.out::println);
    }
}
