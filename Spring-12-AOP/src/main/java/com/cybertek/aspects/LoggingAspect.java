package com.cybertek.aspects;

import com.cybertek.controller.ProductController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect //AOP
@Configuration
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

//    //pointcut
//    @Pointcut("execution (* com.cybertek.controller.ProductController.*(..))")
//    public void pointCut(){
//
//    }
//
//    //advice
//    @Before("pointcut()")
//    public void log(){
//        //before all methods in Product controller
//        logger.info("------------");
//    }
//    @Before ("execution (* com.cybertek.controller.ProductController.*(..))")
//    public void beforeAdvice(){
//        logger.info("---- before advice -----");
//    }

    //execution example
    @Pointcut("execution(* com.cybertek.controller.ProductController.up*(..))")
    private void anyUpdateOperation(){

    }
    @Pointcut("execution(* com.cybertek.repository.ProductRepository.findById(long))")
    private void anyProductRepositoryFindById(){

    }
    @Before("anyProductRepositoryFindById()")
    public void beforeProductRepoAdvice(){
        logger.info("------- beforeProductRepoAdvice ---------");
    }
    @Before("anyUpdateOperation()")
    public void beforeControllerAdvice(){
        logger.info("---------- beforeControllerAdvice-------------");
    }
}
