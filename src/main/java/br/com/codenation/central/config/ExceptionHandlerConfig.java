package br.com.codenation.central.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.codenation.central.entity.Erro;
import br.com.codenation.central.entity.ErroDeFormulario;
import br.com.codenation.central.service.exception.LogNotFoundException;

@ControllerAdvice
public class ExceptionHandlerConfig {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(LogNotFoundException.class)
	public ResponseEntity<Erro> telefoneNaoEncontrado(LogNotFoundException ex) {
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro(ex.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Erro> telefoneDuplicado(Exception ex){
		
		return ResponseEntity.badRequest().body(new Erro(ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<ErroDeFormulario> erroFormulario(MethodArgumentNotValidException ex) {
		
		List<ErroDeFormulario> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e ->{
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormulario erro = new ErroDeFormulario(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
		
	}
	
}
