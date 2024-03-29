package com.zhurawell.base.service.user;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.model.user.UserLightView;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface UserService {

    public User findById(BigInteger id);

    public User fetchFullById(BigInteger id);

    public User createUser(User user);

    public User updateUser(User user);

    public List<User> createUsers(List<User> users);

    public List<User> saveAllUsersBatched(List<User> users, int batchSize);

    public void deleteById(BigInteger id);

    public void deleteAllUsersBatched(List<User> users);

    public User findByLogin(String login);

    public List<User> findAllByFirstName(String firstName);

    public List<UserLightView> findByFirstNameLight(String firstName);

    public User findByIdWithRole(BigInteger id);

    public List<User> findByRegistrationDateAfter(Date date);
}
