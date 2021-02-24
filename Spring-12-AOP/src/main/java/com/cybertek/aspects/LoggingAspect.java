package com.cybertek.aspects;

import com.cybertek.controller.ProductController;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
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
//    @Before("pointCut()")
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
    @Pointcut("execution(* com.cybertek.repository.ProductRepository.findById(Long))")
    private void anyProductRepositoryFindById(){

    }
    @Before("anyProductRepositoryFindById()")
    public void beforeProductRepoAdvice(JoinPoint joinPoint){
        logger.info("Before->method {} - argument: {} - target: {}", joinPoint, joinPoint.getArgs(),
                joinPoint.getTarget());
//        logger.info("---------->>> beforeProductRepoAdvice <<<------------");
    }
    @Before("anyUpdateOperation()")
    public void beforeControllerAdvice(JoinPoint jointPoint){
        logger.info("Before->method {} - argument: {} - target: {}", jointPoint, jointPoint.getArgs(),
                jointPoint.getTarget());

//        logger.info("---------->>> beforeControllerAdvice <<<-------------");
    }

    //Within example
    @Pointcut("within(com.cybertek.controller..*)")
    private void anyControllerOperation(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void anyServiceAnnotatedOperation(){}

    @Before("anyServiceAnnotatedOperation() || anyControllerOperation")
    public void beforeControllerAdvice2(JoinPoint joinPoint){
        logger.info("Before->Method:{} - Argument {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }







}
