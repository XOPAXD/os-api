package com.jhone.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhone.os.domain.Pessoa;
import com.jhone.os.domain.Cliente;
import com.jhone.os.dtos.ClienteDTO;
import com.jhone.os.repositories.PessoaRepository;
import com.jhone.os.repositories.ClienteRepository;
import com.jhone.os.services.exceptions.DataIntegratyViolationException;
import com.jhone.os.services.exceptions.ObjectNotFoundExceptions;

@Service
public class ClienteService {
	@Autowired
	private PessoaRepository repositoryp;
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! "+id+" Tipo:"+Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO ObjDTO) {
		if(FindByCPF(ObjDTO) != null) {
			throw new DataIntegratyViolationException("CPF Já Cadastrado na Base de Dados!");
		}
		return repository.save(new Cliente(null, ObjDTO.getNome(), ObjDTO.getCpf(), ObjDTO.getTelefone())); 
	}
	
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);
		
		if(FindByCPF(objDTO) != null && FindByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF Já Cadastrado na Base de Dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente Obj = findById(id);
		
		if(Obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("O Cliente escolhido possui ordens de serviço e não pode ser deletado!");
		}
		
		
		repository.deleteById(id);
		
	}
	
	private Pessoa FindByCPF(ClienteDTO ObjDTO) {
		Pessoa obj = repositoryp.findbyCpf(ObjDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}
}
