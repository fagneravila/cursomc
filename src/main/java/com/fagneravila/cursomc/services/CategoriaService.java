package com.fagneravila.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fagneravila.cursomc.domain.Categoria;
import com.fagneravila.cursomc.domain.Cliente;
import com.fagneravila.cursomc.dto.CategoriaDTO;
import com.fagneravila.cursomc.repositories.CategoriaRepository;
import com.fagneravila.cursomc.services.exception.DataIntegrityException;
import com.fagneravila.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	

	
	 public Categoria find(Integer id) { 
		 Optional<Categoria> obj = repo.findById(id); 
		 return obj.orElseThrow(() -> 
		 new ObjectNotFoundException(
	    "Objeto não Encontrado! Id " + id + ", Tipo: " + Categoria.class.getName()));
	 }
	 
	 public Categoria insert(Categoria obj) { 
		 // verifica que é uma insercao
		 obj.setId(null);;
		 return repo.save(obj);
		 
	 }
	 

	 
	 public Categoria update(Categoria obj) { 
		 Categoria newObj = find(obj.getId());
	     updateDate(newObj,obj);
		 return repo.save(newObj);
		 
	 }
	 
	 private void updateDate(Categoria newObj, Categoria obj) {
			newObj.setNome(obj.getNome());
					
		}
	 
	 
	 
	 public void delete(Integer id) { 
	     find(id);
	     try {
		 repo.deleteById(id);
		 
		    }catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos" );
		}
	 }
	 
	 
	 public List<Categoria> findAll() { 
	     return	 repo.findAll();
		   
	 }
	 
	 //paginação
	 public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		 PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy );
		 return repo.findAll(pageRequest);
	
	 }
	 
	 //converte em categoria dto	 
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
}

