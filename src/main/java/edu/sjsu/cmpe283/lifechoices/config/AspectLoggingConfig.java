//package edu.sjsu.cmpe283.lifechoices.config;
//
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
///**
// * User: maksim
// * Date: 3/15/14 - 8:11 PM
// */
//@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//public class AspectLoggingConfig {
//
//    @Bean
//    public CustomizableTraceInterceptor customizableTraceInterceptor() {
//        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
//        customizableTraceInterceptor.setUseDynamicLogger(true);
//        customizableTraceInterceptor.setEnterMessage("Entering $[methodName]($[arguments])");
//        customizableTraceInterceptor.setExitMessage("Leaving  $[methodName](), returned $[returnValue]");
//        return customizableTraceInterceptor;
//    }
//
//    @Bean
//    public Advisor jpaRepositoryAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(public * edu.sjsu.cmpe283.lifechoices.repositories.*(..))");
//        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
//    }
//
//}
