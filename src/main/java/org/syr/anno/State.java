package org.syr.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.syr.validation.StateValidation;

import java.lang.annotation.*;

@Documented//元注解
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ElementType.FIELD})//元注解
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    //提供校验失败的信息
    String message() default "state参数的值只能是已发布或草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
