package com.jhone.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhone.os.domain.Cliente;
import com.jhone.os.domain.OS;
import com.jhone.os.domain.Tecnico;
import com.jhone.os.domain.enuns.Prioridade;
import com.jhone.os.domain.enuns.Status;
import com.jhone.os.dtos.OSDTO;
import com.jhone.os.repositories.OsRepository;
import com.jhone.os.services.exceptions.ObjectNotFoundExceptions;

@Service
public class OsService {
	@Autowired
	private TecnicoService tecnicoservice;
	
	@Autowired
	private ClienteService clienteservice;
	
	@Autowired
	private OsRepository repository;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! "+id+" Tipo:"+OS.class.getName()));
	}
	
	public List<OS> findAll() {
		
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		
		newObj.setId(obj.getId());
		newObj.setObservacos(obj.getObservacos());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoservice.findById(obj.getTecnico());
		Cliente cli = clienteservice.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		return repository.save(newObj);
	}

}
