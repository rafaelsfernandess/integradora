package br.com.empresa.service;

import java.util.List;

import br.com.empresa.bo.ILivroBO;
import br.com.empresa.bo.LivroBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public class ServicoBeanLocal implements IServicoBeanLocal{
	
	
	@Override
	public List<BibliaVO> listarVersiculos() throws BOException {
		
		return null;
	}


	@Override
	public List<LivroVO> listarLivros() throws BOException {
		ILivroBO livroBO = new LivroBO();
		return livroBO.listarLivro();
	}

	
	
}