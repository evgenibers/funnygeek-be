package hackathon.hardcode.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import hackathon.hardcode.integration.ErrorCodes;
import hackathon.hardcode.model.error.ErrorInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(RestException.class)
    public @ResponseBody
    ErrorInfo handleRestException(HttpServletResponse httpServletResponse, RestException e) {
        LOGGER.error("RestException: " + e.getClass() + " " + e.getMessage() + " " + e.getErrorCode() + " " 
        	+ e.getErrorKey());
        httpServletResponse.setStatus(e.getHttpStatus());
        return new ErrorInfo(e.getMessage(), e.getErrorCode(), e.getErrorKey());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public @ResponseBody
    ErrorInfo handleHttpException(HttpServletResponse httpServletResponse, Exception e) {
        return getErrorInfo(e, httpServletResponse, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getClass().getName(),
        	"000002", "error.http_error");
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody
    ErrorInfo handleMissingServletRequestParameterException(HttpServletResponse httpServletResponse,
    MissingServletRequestParameterException e) {
        return getErrorInfo(e, httpServletResponse, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
        	e.getClass().getName()+":"+e.getParameterName(), "000002", "error.http_error");
    }
    private ErrorInfo getErrorInfo(Exception e, HttpServletResponse httpServletResponse, int scInternalServerError,
    String name, String errorCode, String errorKey) {
        LOGGER.error("HttpException: " + e.getLocalizedMessage());
        httpServletResponse.setStatus(scInternalServerError);
        return new ErrorInfo(name, errorCode, errorKey);
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ErrorInfo handleException(HttpServletResponse httpServletResponse, Exception e) {
        LOGGER.error("Exception: " + e.getClass() + " " + e.getLocalizedMessage() + " " 
        	+ Arrays.toString(e.getStackTrace()));
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ErrorInfo(ErrorCodes.EXCEPTION.getCode(), ErrorCodes.EXCEPTION.getMessageKey());
    }
    
}
