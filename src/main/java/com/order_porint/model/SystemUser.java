package com.order_porint.model;

import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zsx on 2018-09-05.
 */

@Entity
@Table(name = "system_user",
        indexes = {@Index(name = "IX_user_email", columnList = "email", unique = true)})
public class SystemUser {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<String> roles;
    private String role;

    private boolean enabled;

    public SystemUser() {
        super();
        this.enabled = false;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public List<String> getRoles() {
        if (roles == null || roles.isEmpty()) {
            roles = new ArrayList<>();
            if (!StringUtils.isEmpty(role)) {
                roles.addAll(Arrays.asList(role.split(",")));
            }
        }
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
        StringBuilder sb = new StringBuilder();
        for (String r : roles)
            sb.append(r).append(',');
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        this.role = sb.toString();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "enabled")
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
