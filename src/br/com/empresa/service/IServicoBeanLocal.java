package br.com.empresa.service;

import java.math.BigInteger;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;



public interface IServicoBeanLocal {
	
	public abstract List<LivroVO> listarLivros() throws BOException;

	public abstract List<BibliaVO> listarVersiculos() throws BOException;
	
	



}
