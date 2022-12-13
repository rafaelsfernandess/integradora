package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public interface IBibliaBO {
	
	public abstract List<BibliaVO> listarLivro() throws BOException;

	public abstract List<BibliaVO> listarVersiculos(LivroVO livro) throws BOException;
	
	


}
