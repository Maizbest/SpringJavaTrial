package com.sparkequation.spring.trial.api.validation;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AfterNowImpl implements ConstraintValidator<AfterNow, Date> {

  private Duration plusDuration;

  @Override
  public void initialize(AfterNow constraintAnnotation) {
    final String duration = constraintAnnotation.plusDuration();
    this.plusDuration = duration.isEmpty() ? Duration.ZERO : Duration.parse(duration);
  }

  @Override
  public boolean isValid(Date value, ConstraintValidatorContext context) {
    return value == null || OffsetDateTime.now().plus(plusDuration).toInstant()
        .isBefore(value.toInstant());
  }
}
