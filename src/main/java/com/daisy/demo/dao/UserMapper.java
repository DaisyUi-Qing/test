package com.daisy.demo.dao;

import com.daisy.demo.dto.UserDTO;
import com.daisy.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dxm
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void add(User user);

    List<User> all(UserDTO user);

    int insertBatch(@Param("list") List<User> userList);

    int count(UserDTO user);
}
