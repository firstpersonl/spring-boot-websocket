package com.order_porint.service;

import com.order_porint.model.SystemUser;
import com.order_porint.model.UserDto;

/**
 * Created by zsx on 2018-09-05.
 */
public interface IUserService {
    SystemUser registerNewUserAccount(UserDto userDto) throws EmailExistsException;
}
