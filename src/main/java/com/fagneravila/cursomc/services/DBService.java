package com.fagneravila.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagneravila.cursomc.domain.Categoria;
import com.fagneravila.cursomc.domain.Cidade;
import com.fagneravila.cursomc.domain.Cliente;
import com.fagneravila.cursomc.domain.Endereco;
import com.fagneravila.cursomc.domain.Estado;
import com.fagneravila.cursomc.domain.ItemPedido;
import com.fagneravila.cursomc.domain.Pagamento;
import com.fagneravila.cursomc.domain.PagamentoComBoleto;
import com.fagneravila.cursomc.domain.PagamentoComCartao;
import com.fagneravila.cursomc.domain.Pedido;
import com.fagneravila.cursomc.domain.Produto;
import com.fagneravila.cursomc.domain.enums.EstadoPagamento;
import com.fagneravila.cursomc.domain.enums.TipoCliente;
import com.fagneravila.cursomc.repositories.CategoriaRepository;
import com.fagneravila.cursomc.repositories.CidadeRepository;
import com.fagneravila.cursomc.repositories.ClienteRepository;
import com.fagneravila.cursomc.repositories.EnderecoRepository;
import com.fagneravila.cursomc.repositories.EstadoRepository;
import com.fagneravila.cursomc.repositories.ItemPedidoRepository;
import com.fagneravila.cursomc.repositories.PagamentoRepository;
import com.fagneravila.cursomc.repositories.PedidoRepository;
import com.fagneravila.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiaretesteDataBase() throws ParseException                  {
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		Categoria cat3 = new Categoria(null,"mesa");
		Categoria cat4 = new Categoria(null, "armarios");
		Categoria cat5 = new Categoria(null,"casa");
		Categoria cat6 = new Categoria(null, "mesa");
		Categoria cat7 = new Categoria(null,"banho");
	
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora",  800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		Produto p4 = new Produto(null,"mesa de escritorio", 300.00);
		Produto p5 = new Produto(null,"toalha",  50.00);
		Produto p6 = new Produto(null,"colcha", 200.00);
		Produto p7 = new Produto(null,"tv", 1200.00);
		Produto p8 = new Produto(null,"rocadeira",  800.00);
		Produto p9 = new Produto(null,"abajour", 100.00);
		Produto p10 = new Produto(null,"pendente", 180.00);
		Produto p11 = new Produto(null,"shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		
		p1.getCategorias().addAll(Arrays.asList(cat2, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7)).toString();
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)).toString();
		
		Estado est1= new Estado(null, "Minas Gerais");
		Estado est2= new Estado(null, "São Paullo");
		
		Cidade c1 = new Cidade(null, "Uberlêndia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
			Cliente cli1 = new Cliente(null, "Maria Silba", "fagner.avila@gmail.com","3637892377",TipoCliente.PESSOAFISICA );
			cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
			Endereco e1 = new Endereco(null, "Rua Flores","300", "apto 303", "jardim", "38220834", cli1, c1 );
			Endereco e2 = new Endereco(null, "Avenida Matos","105", "sala 800", "Centro", "38735648", cli1, c2 );
			
			cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
			
			clienteRepository.saveAll(Arrays.asList(cli1));
			enderecoRepository.saveAll(Arrays.asList(e1,e2));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			Pedido ped1 = new Pedido(null,sdf.parse("30/10/2020 10:32"),cli1, e1);
			Pedido ped2 = new Pedido(null,sdf.parse("10/10/2020 10:32"),cli1, e2);
			
			Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
			ped1.setPagamento(pgto1);
			
			Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/10/2020 10:32"), null);
			ped2.setPagamento(pgto2);
			
			
			cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
			
			pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
			pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
			
			ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
			ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
			ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
			
			ped1.getItens().addAll(Arrays.asList(ip1,ip2));
			ped2.getItens().addAll(Arrays.asList(ip3));
		
			p1.getItens().addAll(Arrays.asList(ip1));
			p2.getItens().addAll(Arrays.asList(ip3));
			p3.getItens().addAll(Arrays.asList(ip2));
			
			itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}