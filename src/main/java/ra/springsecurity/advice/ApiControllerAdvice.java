package ra.springsecurity.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {
@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> doHandler(RuntimeException e){
    Map<String, String> map = new HashMap<>();
    map.put("message", e.getMessage());
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
}

}
