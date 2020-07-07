//package com.konglingzhan.manager.util;
//
//import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
//
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.util.*;
//
//public class BeanValidator {
//    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//    public static <T>Map<String,String> validate(T t,Class... groups){
//        Validator validator = validatorFactory.getValidator();
//        Set validateResult = validator.validate(t,groups);
//        if(validateResult.isEmpty()){
//            return Collections.emptyMap();
//        } else{
//            LinkedHashMap errors = Maps.newLinkedHashMap();
//            Iterator iterator = validateResult.iterator();
//            while(iterator.hasNext()){
//
//            }
//        }
//    }
//}
