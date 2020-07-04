package com.sparkequation.spring.trial.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AfterNowImpl.class)
public @interface AfterNow {

  String message() default "Date must be after now.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String plusDuration() default "";

}
