package com.zhurawell.base.service.user.impl;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.model.user.view.UserLightView;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

//    @Autowired
//    @Qualifier("UserKafkaIntServiceImpl")
//    UserBrokerIntService userBroker;

    @Override
    @Transactional
    public User createUser(User user) {
//        userBroker.createUser(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Transactional
    public List<User> saveAllUsersBatched(List<User> users, int batchSize) {
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        List<User> savedUsers = userRepository.saveAll(users);
        return savedUsers;
    }

    @Override
    public void deleteById(BigInteger id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllUsersBatched(List<User> users) {
        userRepository.deleteAllInBatch(users);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> findAllByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    public List<UserLightView> findByFirstNameLight(String firstName) {
        return userRepository.findByFirstNameLight(firstName);
    }

    public User findById(BigInteger id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User fetchFullById(BigInteger id) {
        return userRepository.fetchFullById(id);
    }

    public User findByIdWithRole(BigInteger id) {
        EntityGraph eg = em.createEntityGraph(User.class);
        eg.addAttributeNodes("role");
        return em.find(User.class, id, Collections.singletonMap(
                "javax.persistence.loadgraph",
                eg
        ));
    }

    @Override
    public List<User> findByRegistrationDateAfter(Date date) {
        return userRepository.findByRegistrationDateAfter(date);
    }
}
