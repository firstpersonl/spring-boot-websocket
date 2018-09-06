package com.order_porint.controller;

import com.order_porint.interceptor.OnRegistrationCompleteEvent;
import com.order_porint.model.SystemUser;
import com.order_porint.model.UserDto;
import com.order_porint.model.VerificationToken;
import com.order_porint.service.EmailExistsException;
import com.order_porint.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class IndexController {

    @Autowired
    private MessageSource messages;

    @Autowired
    private IUserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = {"/","index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/createOrder")
    public String createOrder() {
        return "createOrder";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistantrionForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto);
        return "registration";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAcount(
            @ModelAttribute("user") @Valid UserDto userDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {
        SystemUser registerUser = new SystemUser();
        if (!result.hasErrors()) {
            registerUser = createUserAcount(userDto);
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registerUser, request.getLocale(), appUrl));
        } catch (Exception me) {
            return new ModelAndView("registration", "user", userDto);
        }
        return new ModelAndView("successRegister", "user", userDto);

    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistrantion
            (WebRequest request, Model model ,@RequestParam(name = "token") String token) {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null,locale);
            model.addAttribute("message", message);
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        SystemUser user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login?lang=" + request.getLocale().getLanguage();
    }

    private SystemUser createUserAcount(UserDto userDto) {
        SystemUser user = null;
        try {
            user = userService.registerNewUserAccount(userDto);
        } catch (EmailExistsException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
