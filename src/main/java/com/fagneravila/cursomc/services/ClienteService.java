package com.fagneravila.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fagneravila.cursomc.domain.Cidade;
import com.fagneravila.cursomc.domain.Cliente;
import com.fagneravila.cursomc.domain.Endereco;
import com.fagneravila.cursomc.domain.enums.TipoCliente;
import com.fagneravila.cursomc.dto.ClienteDTO;
import com.fagneravila.cursomc.dto.ClienteNewDTO;
import com.fagneravila.cursomc.repositories.ClienteRepository;
import com.fagneravila.cursomc.repositories.EnderecoRepository;
import com.fagneravila.cursomc.services.exception.DataIntegrityException;
import com.fagneravila.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository endrepo;
    
	@Autowired
	private BCryptPasswordEncoder bp;
	
	// buscara cliente por id
	 public Cliente find(Integer id) { 
		 Optional<Cliente> obj = repo.findById(id); 
		 return obj.orElseThrow(() -> 
		 new ObjectNotFoundException(
	    "Objeto não Encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
	 }
	 
	 @Transactional
	 public Cliente insert(Cliente obj) { 
		 // verifica que é uma insercao
		 obj.setId(null);
		 obj = repo.save(obj);
		 endrepo.saveAll(obj.getEnderecos());
		 return obj;
		 
	 }
	 
	 public Cliente update(Cliente obj) { 
	     Cliente newObj = find(obj.getId());
	     updateDate(newObj,obj);
		 return repo.save(newObj);
		 
	 }
	 
	 private void updateDate(Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
			
		}
	 
	 public void delete(Integer id) { 
	     find(id);
	     try {
		 repo.deleteById(id);
		 
		    }catch (DataIntegrityViolationException e) {
			    throw new DataIntegrityException("Nao é possivel excluir um cliente que possui produdo");
		}
	 }
		 
	public List<Cliente> findAll() { 
	     return	 repo.findAll();
		   
	}
	 	 
	public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy );
		 return repo.findAll(pageRequest);
	}
	 
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
		
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		
		Cliente cli = new  Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), bp.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(),  objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	
}

