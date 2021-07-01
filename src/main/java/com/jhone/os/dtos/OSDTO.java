package com.jhone.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhone.os.domain.OS;


public class OSDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	private Integer status;
	
	@NotEmpty(message = "O campo observações é requerido!")
	private String observacos;
	
	//@NotEmpty(message = "O campo Técnico é requerido!")
	private Integer tecnico;
	
	//@NotEmpty(message = "O campo Cliente é requerido!")
	private Integer cliente;
	public OSDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OSDTO(OS obj) {
		super();
		this.id =  obj.getId();
		this.dataAbertura =  obj.getDataAbertura();
		this.dataFechamento =  obj.getDataAbertura();
		this.prioridade = obj.getPrioridade().getCod();
		this.status = obj.getStatus().getCod();
		this.observacos =  obj.getObservacos();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getObservacos() {
		return observacos;
	}
	public void setObservacos(String observacos) {
		this.observacos = observacos;
	}
	public Integer getTecnico() {
		return tecnico;
	}
	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	

}
