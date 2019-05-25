package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IRoleDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.QueryVo;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private IAccountDao accountDao;
    private IRoleDao roleDao;

    @Before
    public void init() throws Exception {
        //1 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2 创建SqlSessionFactory工厂，创建者模式
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3 使用工厂生产SqlSession对象  工厂模式
        sqlSession = factory.openSession();
        //4 使用SqlSession创建Dao接口的代理对象    代理模式
        userDao = sqlSession.getMapper(IUserDao.class);
        accountDao = sqlSession.getMapper(IAccountDao.class);
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After
    public void destroy() throws Exception {
        //插入时必须加上提交事务，否则不生效
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    //---------------------------select-----------------------------------------------------
    //all
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    //one
    @Test
    public void testFindOne() {
        User user = userDao.findOne(2);
        System.out.println(user.getAddress());
        System.out.println(user.getUsername());
    }

    //by nameuser
    @Test
    public void testFindAllByNamne() {
        List<User> users = userDao.findAllByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    //聚合
    @Test
    public void testFindTotal() {
        int count = userDao.findTotal();
        System.out.println(count);
    }

    //很多参数
    @Test
    public void testFindAllByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%李%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

    //很多参数
    @Test
    public void testFindCondition() {
        User user = new User();
        user.setUsername("李");
        List<User> users = userDao.findUserCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    //in
    @Test
    public void testFindUserInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(5);
        vo.setIds(list);
        List<User> users = userDao.findUserInIds(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }


    @Test
    public void testAccountFindAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    //    一对一
    @Test
    public void testfindUserAcountAll() {
        List<Account> accounts = accountDao.findUserAcountAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    //    一对多
    @Test
    public void testFindAllRelevanceAccount() {
        List<User> users = userDao.findAllRelevanceAccount();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    //      多对多
    @Test
    public void testFindAllRole() {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @Test
    public void findAllUserRole() {
        List<User> users = userDao.findAllUserRole();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

    //--------------------------------------------------------------------------------
    @Test
    public void testSave() {
        User user = new User();
        user.setAddress("55555");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("王五");

        userDao.saveUser(user);
        System.out.println("新增记录之后的主键ID" + user.getId());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(4);
        user.setAddress("33333");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("王五");

        userDao.updateUser(user);
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser(4);
    }


}
