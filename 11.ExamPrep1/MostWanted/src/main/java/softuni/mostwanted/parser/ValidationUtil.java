package softuni.mostwanted.parser;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class ValidationUtil {
    private Validator validator;

    public ValidationUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    public <O> Boolean isValid(O object) {
        return this.validator.validate(object).size() == 0;
    }


    public <O> Set<ConstraintViolation<O>> violations(O object) {
        return this.validator.validate(object);
    }
}
