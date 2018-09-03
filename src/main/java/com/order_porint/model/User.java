package com.order_porint.model;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by zsx on 2018-09-03.
 */
public class User implements Principal {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
