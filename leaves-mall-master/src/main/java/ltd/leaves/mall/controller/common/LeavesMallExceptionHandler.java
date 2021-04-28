package ltd.leaves.mall.controller.common;

import ltd.leaves.mall.common.LeavesMallException;
import ltd.leaves.mall.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * NewBee-Mall global exception handling
 */
@RestControllerAdvice
public class LeavesMallExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        Result result = new Result();
        result.setResultCode(500);
        //Distinguishes whether it is a custom exception
        if (e instanceof LeavesMallException) {
            result.setMessage(e.getMessage());
        } else {
            e.printStackTrace();
            result.setMessage("Unknown abnormal");
        }
        //Check if the request is Ajax, and return the Result JSON string if it is Ajax, and return the Error view if it is not Ajax
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            return result;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("message", e.getMessage());
            modelAndView.addObject("url", req.getRequestURL());
            modelAndView.addObject("stackTrace", e.getStackTrace());
            modelAndView.addObject("author", "LeavesTeam");
            modelAndView.addObject("ltd", "leaves mall");
            modelAndView.setViewName("error/error");
            return modelAndView;
        }
    }
}
