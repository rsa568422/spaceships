package com.rsa.spaceships.infrastructure.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class SpaceshipAspect {

    @Before("execution(* com.rsa.spaceships.infrastructure.controllers.*Controller.*ById(*)) && args(id)")
    public void validateId(Integer id) {
        if (Objects.nonNull(id) && id < 0) {
            log.warn("request with id {}", id);
        }
    }
}
