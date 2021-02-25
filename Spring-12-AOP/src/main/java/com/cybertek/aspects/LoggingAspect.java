package com.cybertek.aspects;

import com.cybertek.controller.ProductController;
import com.cybertek.entity.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Before("anyServiceAnnotatedOperation() || anyControllerOperation()")
    public void beforeControllerAdvice2(JoinPoint joinPoint){
        logger.info("Before->Method:{} - Argument {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    //@annotation
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void anyDeleteProductOperation(){}

    @Before("anyDeleteProductOperation()")
    public void beforeControllerDeleteAdvice(JoinPoint joinPoint){
        logger.info("Before->Method:{} - Argument {} - Target: {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    //@AfterReturning
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetMappingProductOperation(){}

//    @AfterReturning(pointcut = "anyGetMappingProductOperation()", returning = "result")
//    public void afterReturningGetMappingControllerAdvice(JoinPoint joinPoint, Product result){
//        logger.info("After Returning (Mono Result) -> Method: {} - results:{}",
//                joinPoint.getSignature().toShortString(), result);
//    }
    @AfterReturning(pointcut = "anyGetMappingProductOperation()", returning = "result")
    public void afterReturningGetMappingControllerAdvice(JoinPoint joinPoint, ResponseEntity result){
        logger.info("After Returning (Mono Result) -> Method: {} - results:{}",
                joinPoint.getSignature().toShortString(), result);
    }
    @AfterReturning(pointcut = "anyGetMappingProductOperation()", returning = "results")
    public void afterReturningGetMappingControllerAdvice2(JoinPoint joinPoint, List<Product> results){
        logger.info("After Returning (List Result) -> Method: {} - results:{}",
                joinPoint.getSignature().toShortString(), results);
    }

    //@after throwing
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetPutMappingProductOperation(){ }

    @AfterThrowing(pointcut = "anyGetPutMappingProductOperation()", throwing = "exception")
    public void afterThrowingControllerAdvice(JoinPoint joinPoint, RuntimeException exception){
        logger.info("After Throwing(Send Email to L2 Team -> Method:{} - Exception: {}",
                joinPoint.getSignature().toShortString(), exception.getMessage());
    }

    //@after(finally)
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetPutMappingProductOperation2(){}
    @After("anyGetPutMappingProductOperation2()")
    public void afterControllerAdvice(JoinPoint joinPoint){
        logger.info("After finally -> method: {} - results: {}", joinPoint.getSignature().toShortString(), "Execution" +
                " completed");
    }

    //@Around
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void anyPostProductsOperation(){}
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    private void anyPutProductsOperation(){}
    ////advice
    @Around("anyPostProductsOperation()")
    public Object anyPostControllerAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Before(Method: {} - Parameters: {}", proceedingJoinPoint.getSignature().toShortString(),
                proceedingJoinPoint.getArgs());
        List<Product> results = new ArrayList<>();
        results = (List<Product>) proceedingJoinPoint.proceed();
        logger.info("After(Method: {} - Results: {}", proceedingJoinPoint.getSignature().toShortString(),
                results);
        return  results;
    }




}
