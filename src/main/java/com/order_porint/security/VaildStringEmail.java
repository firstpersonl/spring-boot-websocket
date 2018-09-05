package com.order_porint.security;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by zsx on 2018-09-05.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface VaildStringEmail {
    String message() default "Invalid email";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
