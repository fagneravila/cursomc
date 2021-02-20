package com.fagneravila.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fagneravila.cursomc.domain.Categoria;
import com.fagneravila.cursomc.domain.Produto;
import com.fagneravila.cursomc.repositories.CategoriaRepository;
import com.fagneravila.cursomc.repositories.ProdutoRepository;
import com.fagneravila.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	// buscara cliente por id
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
	 	return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não Encontrado! Id " + id + ", Tipo: " + Produto.class.getName()));
		}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPage, String orderBy, String direction){
			 PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy );
			 List<Categoria> categorias = categoriaRepo.findAllById(ids);
			 return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
