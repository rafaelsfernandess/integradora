package br.com.empresa.bo;

import java.util.List;


import br.com.empresa.exception.BOException;
import br.com.empresa.vo.LivroVO;

public interface ILivroBO {
	
	public abstract List<LivroVO> listarLivro() throws BOException;
	
}
