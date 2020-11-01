package example.awesomeproj.areas.user.models.binding.validations;


import example.awesomeproj.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailRegisteredValidator implements ConstraintValidator<IsEmailRegistered,String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsEmailRegistered isEmailRegister) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userService.isEmailTaken(s);
    }
}
