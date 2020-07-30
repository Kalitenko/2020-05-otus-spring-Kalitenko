package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* ru.otus.spring.service.ConsoleServiceImpl.*(..))")
    public void errorsInConsoleServiceImpl() {
    }

    @Pointcut("execution(* ru.otus.spring.service.TestingServiceImpl.*(..))")
    public void errorsInTestingServiceImpl() {
    }

    @AfterThrowing(pointcut = "errorsInConsoleServiceImpl() || errorsInTestingServiceImpl()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println(
                "Логгирование выброса исключения методом: " + joinPoint.getTarget().getClass().getSimpleName()
        );
        System.out.println(
                "Логгирование выброса исключения методом: " + joinPoint.getSignature().getName()
        );
        System.out.println("Возникшее исключение: " + error);
    }
}
