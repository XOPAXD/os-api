package com.jhone.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhone.os.domain.Cliente;
import com.jhone.os.domain.OS;
import com.jhone.os.domain.Tecnico;
import com.jhone.os.domain.enuns.Prioridade;
import com.jhone.os.domain.enuns.Status;
import com.jhone.os.repositories.ClienteRepository;
import com.jhone.os.repositories.OsRepository;
import com.jhone.os.repositories.TecnicoRepository;

@Service
public class DBServices {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OsRepository osRepository;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null,"jhone silva", "037.276.401-03","(65)9695-8563");
		Tecnico t2 = new Tecnico(null,"jhone silva tnc ", "122.754.530-40","(65)9695-4444");
		Cliente c1 = new Cliente(null,"juci silva", "274.963.331-15","(65)9695-8563");
		
		
		OS os1 = new OS(null, Prioridade.ALTA, Status.ANDAMENTO, "TESTE", t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		
		osRepository.saveAll(Arrays.asList(os1));
	}
}
