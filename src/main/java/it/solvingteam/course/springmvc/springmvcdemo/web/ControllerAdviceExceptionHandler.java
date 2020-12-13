package it.solvingteam.course.springmvc.springmvcdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ControllerAdviceExceptionHandler.class);

//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(Exception e) {
//        logger.error("An error has occurred: ", e);
//        return "error";
//    }

}
