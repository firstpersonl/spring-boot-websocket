package com.order_porint.controller;

import com.order_porint.model.SystemUser;
import com.order_porint.model.UserDto;
import com.order_porint.service.EmailExistsException;
import com.order_porint.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/",""})
    public String index() {
        return "/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/createOrder")
    public String createOrder() {
        return "/createOrder";
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
        if (registerUser == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", userDto);
        }
        else {
            return new ModelAndView("successRegister", "user", userDto);
        }
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
