package com.order_porint.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by zsx on 2018-09-03.
 */
public class UserInterceptor extends ChannelInterceptorAdapter {

    @SuppressWarnings("rawtypes")
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String jwtToken = accessor.getFirstNativeHeader("Auth-Token");
            System.out.println("webSocket token is" + jwtToken);
            if (!StringUtils.isEmpty(jwtToken)) {
                Map sessionAttributes = SimpMessageHeaderAccessor.getSessionAttributes(message.getHeaders());
                sessionAttributes.put(CsrfToken.class.getName(),new DefaultCsrfToken("Auth-Token","Auth-Token",jwtToken));

                accessor.setUser(SecurityContextHolder.getContext().getAuthentication());
            }

        }
        return message;
    }
}
