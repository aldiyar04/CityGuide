package kz.edu.iitu.CityGuide.feature.aop;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Log4j2
@AllArgsConstructor
@ControllerAdvice
public class AOPLogger {
    private static final String POINTCUT = "within(kz.edu.iitu.CityGuide.controller.*)";

    private HttpServletRequest request;

    @Before(POINTCUT)
    public void logIncomingInfo(JoinPoint joinPoint) {
        System.out.println("\nINCOMING INFO");
        log.info("Request URL: " + request.getRequestURL());
        log.info("HTTP method: " + request.getMethod());
        log.info("Method invoked: " + getClassAndMethod(joinPoint));
        if (HttpMethod.POST.matches(request.getMethod()) || HttpMethod.PUT.matches(request.getMethod())) {
            log.info("Arguments: [" + getMethodArguments(joinPoint) + "]");
        }
        log.info("Client IP: " + request.getRemoteAddr());
    }

    @AfterThrowing(pointcut = POINTCUT, throwing = "ex")
    public void logIncomingInfoAndException(JoinPoint joinPoint, Throwable ex) {
        log.info(ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }

    private String getClassAndMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return className + "." + methodName + "()";
    }

    private String getMethodArguments(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            builder.append(parameterName);
            builder.append(": ");
            builder.append(joinPoint.getArgs()[i].toString());
            if (i != joinPoint.getArgs().length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
