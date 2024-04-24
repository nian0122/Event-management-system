package org.syr.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.syr.anno.State;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext constraintValidatorContext in which the constraint is evaluated
     *
     * @return 如果返回false,则校验不通过,如果返回true,则校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return false;
        }
        if(s.equals("已发布") || s.equals("草稿")){
            return true;
        }
        return false;
    }
}
