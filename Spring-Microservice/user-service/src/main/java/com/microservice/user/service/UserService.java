package com.microservice.user.service;

import com.microservice.user.entity.Users;
import com.microservice.user.repository.UserRepository;
import com.microservice.user.valueObject.Department;
import com.microservice.user.valueObject.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Users saveUser(Users users) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(users);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Users users = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://Department-Service/departments/" + users.getDepartmentId()
                        ,Department.class);

        vo.setUsers(users);
        vo.setDepartment(department);

        return  vo;
    }

//    public ResponseTemplateVO saveUserWithDepartment(Users users) {
//        ResponseTemplateVO vo = new ResponseTemplateVO();
//       vo.setUsers(userRepository.save(users));
//       vo.setDepartment( restTemplate.getForObject("http://Department-Service/departments/"
//               ,Department.class));
//       return  vo;
//    }
}