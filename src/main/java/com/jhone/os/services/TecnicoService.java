package com.jhone.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhone.os.domain.Pessoa;
import com.jhone.os.domain.Tecnico;
import com.jhone.os.dtos.TecnicoDTO;
import com.jhone.os.repositories.PessoaRepository;
import com.jhone.os.repositories.TecnicoRepository;
import com.jhone.os.services.exceptions.DataIntegratyViolationException;
import com.jhone.os.services.exceptions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {
	
	@Autowired
	private PessoaRepository repositoryp;
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! "+id+" Tipo:"+Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO ObjDTO) {
		if(FindByCPF(ObjDTO) != null) {
			throw new DataIntegratyViolationException("CPF Já Cadastrado na Base de Dados!");
		}
		return repository.save(new Tecnico(null, ObjDTO.getNome(), ObjDTO.getCpf(), ObjDTO.getTelefone())); 
	}
	
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if(FindByCPF(objDTO) != null && FindByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF Já Cadastrado na Base de Dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Tecnico Obj = findById(id);
		
		if(Obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("O Técnico escolhido possui ordens de serviço e não pode ser deletado!");
		}
		
		
		repository.deleteById(id);
		
	}
	
	private Pessoa FindByCPF(TecnicoDTO ObjDTO) {
		Pessoa obj = repositoryp.findbyCpf(ObjDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

}
