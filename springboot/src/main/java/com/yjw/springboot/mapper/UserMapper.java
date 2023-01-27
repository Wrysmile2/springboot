package com.yjw.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjw.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /*@Select("SELECT * from user")
    List<User> findAll();

    @Insert("insert into user(username,`password`,nickname,email,phone,address) " +
            "values(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from user where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Select("select * from user where username like #{username} limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize,String username);

    @Select("select count(*) from user where username like #{username}")
    Integer selectTotal(String username);*/
}
