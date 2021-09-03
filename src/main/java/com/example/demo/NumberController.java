package com.example.demo;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*Controller class*/
@RestController
public class NumberController {

	@RequestMapping(value = "/calculate", method = { RequestMethod.POST,
			RequestMethod.GET }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String calculateSqrtOfSumOfSquareOf3HighestNums(@RequestBody(required = false) NumberModel number,
			HttpServletResponse response) {

		NumberView numberView = new NumberView();
		numberView.calulateResult(number);
		return "{\"output\":"+number.getOutput()+"}";
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {
		return new ResponseEntity<String>("Invalid Input.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<String> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		return new ResponseEntity<String>("Media Type not supported.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<String>("Bad Request! Content type must be 'application/json'.",
				HttpStatus.BAD_REQUEST);
	}

}
