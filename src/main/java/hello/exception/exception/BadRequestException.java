package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")    // MessageSource에서 찾는 기능도 제공
public class BadRequestException extends RuntimeException {



}