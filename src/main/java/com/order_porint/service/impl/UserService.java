package com.order_porint.service.impl;

import com.order_porint.model.SystemUser;
import com.order_porint.model.UserDto;
import com.order_porint.repository.SystemUserRepository;
import com.order_porint.service.EmailExistsException;
import com.order_porint.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by zsx on 2018-09-05.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private SystemUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SystemUser registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())){
            throw  new EmailExistsException("There is an account with that email address:"  + accountDto.getEmail());
        }

        SystemUser user = new SystemUser();
        user.setEmail(accountDto.getEmail());
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setRoles(Arrays.asList("ROLE_USER"));
        // 加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return user;
    }

    private boolean emailExist(String email) {
        SystemUser user = repository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }
}
