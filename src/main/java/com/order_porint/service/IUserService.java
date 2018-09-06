package com.order_porint.service;

import com.order_porint.model.SystemUser;
import com.order_porint.model.UserDto;
import com.order_porint.model.VerificationToken;

/**
 * Created by zsx on 2018-09-05.
 */
public interface IUserService {
    SystemUser registerNewUserAccount(UserDto userDto) throws EmailExistsException;

    SystemUser getUser(String verificationToken);

    void saveRegisteredUser(SystemUser user);

    void createVerificationToken(SystemUser user, String token);

    VerificationToken getVerificationToken(String verificationToken);
}
