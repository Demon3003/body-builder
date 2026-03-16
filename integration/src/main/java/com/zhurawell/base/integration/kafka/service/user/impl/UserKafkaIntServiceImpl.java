package com.zhurawell.base.integration.kafka.service.user.impl;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.integration.kafka.constants.Topics;
import com.zhurawell.base.integration.kafka.model.CreateUserMessage;
import com.zhurawell.base.integration.kafka.service.user.UserBrokerIntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserKafkaIntServiceImpl")
public class UserKafkaIntServiceImpl implements UserBrokerIntService {

    @Autowired
    private KafkaTemplate<String, Object> kt;

    @Override
    public void createUser(User u) {
        try {
            CreateUserMessage m = new CreateUserMessage();
            m.setId(u.getId());
            m.setFirstName(u.getFirstName());
            m.setLastName(u.getLastName());
            m.setEmail(u.getEmail());
            m.setLogin(u.getLogin());
            m.setRegistrationDate(u.getRegistrationDate());
            kt.send(Topics.USER_CRUD_TOPIC, m);
        } catch (Exception ex) {
            log.error("Unsuccessful message to create user with id {}, error: {}", u.getId(), ex);
        }
    }
}
