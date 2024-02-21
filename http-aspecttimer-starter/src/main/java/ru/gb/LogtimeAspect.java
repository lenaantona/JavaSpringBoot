package ru.gb;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogtimeAspect {

    @Pointcut("within(@ru.gb.Logtime *)")
    public void beansAnnotatedWith() {

    }

    @Pointcut("@annotation(ru.gb.Logtime)")
    public void methodsAnnotatedWith() {
    }
    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object logtimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Level level = extractLevel(joinPoint);
        long start = System.nanoTime();
        long time = 0;
        Object output = null;
        log.atLevel(level).log("target = {}", joinPoint.getTarget().getClass());
        log.atLevel(level).log("method = {}", joinPoint.getSignature().getName());
        try {
            output = joinPoint.proceed();
            long end = System.nanoTime();
            time = end - start;
            log.atLevel(level).log("time(nanosec) = # " + time);
            return output;
        } catch (Throwable e) {
            log.atLevel(level).log("exception = [{}, {}]", e.getClass(), e.getMessage());
            throw e;
        }
    }


    private Level extractLevel(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Logtime annotation = signature.getMethod().getAnnotation(Logtime.class);
        if (annotation != null) {
            return annotation.level();
        }
        return joinPoint.getTarget().getClass().getAnnotation(Logtime.class).level();
    }

}
