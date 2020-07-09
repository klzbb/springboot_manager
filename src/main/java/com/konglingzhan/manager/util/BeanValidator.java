package com.konglingzhan.manager.util;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.assertj.core.util.Preconditions;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    public static <T>Map<String,String> validate(T t,Class... groups){
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t,groups);
        if(validateResult.isEmpty()){
            return Collections.emptyMap();
        } else{
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();

            while(iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String,String> validateList(Collection<?> collection){
        Objects.requireNonNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do{
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }

    public static Map<String,String> validateObjec(Object first,Object... objects){
        if(objects != null && objects.length > 0){
            return validateList(Lists.asList(first,objects));
        } else{
            return validate(first,new Class[0]);
        }
    }
}
