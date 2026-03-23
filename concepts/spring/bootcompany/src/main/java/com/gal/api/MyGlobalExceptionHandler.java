package com.gal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@ControllerAdvice
@RestControllerAdvice
public class MyGlobalExceptionHandler {
	private class JsonResponseExceptionBody {
		private String code;
		private String message;
		
		public JsonResponseExceptionBody() {}
		
		public JsonResponseExceptionBody(HttpStatusCode code, Exception exception) {
			this.code = code.toString();
			this.message = exception.getMessage();
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getCode() { return code; }
		public String getMessage() { return message; }
	}

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<JsonResponseExceptionBody> returnEmployeeNotFoundExceptiom(Exception e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new JsonResponseExceptionBody(HttpStatus.NOT_FOUND, e));
    }

	public ResponseEntity<JsonResponseExceptionBody> returnDepartmentNotFoundException(Exception e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new JsonResponseExceptionBody(HttpStatus.NOT_FOUND, e));
	}
}