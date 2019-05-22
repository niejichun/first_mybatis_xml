package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

//用户持久层接口
public interface IUserDao {
    List<User> findAll();

    User findOne(Integer userId);

    List<User> findAllByName(String username);

    List<User> findUserByVo(QueryVo vo);

    List<User> findUserCondition(User user);

    List<User> findUserInIds(QueryVo vo);

    //user 一对多 account
    List<User> findAllRelevanceAccount();

    int findTotal();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);
}
