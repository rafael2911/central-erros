package br.com.codenation.central.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.codenation.central.entity.Log;

public class LogForm {
	
	@NotNull @NotEmpty @Size(min = 3)
	private String environment;
	
	@NotNull @NotEmpty @Size(min = 3)
	private String level;
	
	@NotNull @NotEmpty @Size(min = 3)
	private String title;
	
	@NotNull @NotEmpty @Size(min = 3)
	private String details;
	
	@NotNull @NotEmpty @Size(min = 3)
	private String source;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Log converter() {
		Log log = new Log();
		log.setEnvironment(this.environment);
		log.setLevel(this.level);
		log.setTitle(this.title);
		log.setDetails(this.details);
		log.setSource(this.source);
		
		return log;
	}

}
