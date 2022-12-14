package br.com.empresa.dao;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public interface IBibliaDAO {

	public abstract List<LivroVO> listarLivro() throws BOException;
	
	public abstract List<BibliaVO> listarVersiculo(LivroVO livro, Integer capitulo, Integer versiculoDe, Integer versiculoAte,
			String texto) throws BOException;

	
	
}
