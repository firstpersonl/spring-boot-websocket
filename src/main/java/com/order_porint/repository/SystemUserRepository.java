package com.order_porint.repository;

import com.order_porint.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zsx on 2018-09-05.
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser,String> {
    SystemUser findByEmail(String email);
}
