package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @AfterThrowing(pointcut = "execution(* ru.otus.spring.service.ConsoleServiceImpl.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println(
                "Логгирование выброса исключения методом: " +
                        joinPoint.getSignature().getName()
        );
        System.out.println("Возникшее исключение: " + error);
    }
}
