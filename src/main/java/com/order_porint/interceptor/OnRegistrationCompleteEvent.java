package com.order_porint.interceptor;

import com.order_porint.model.SystemUser;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by zsx on 2018-09-06.
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private SystemUser user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param user the object on which the event initially occurred (never {@code null})
     */
    public OnRegistrationCompleteEvent(SystemUser user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }
}
