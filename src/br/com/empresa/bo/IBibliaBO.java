package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public interface IBibliaBO {
	
	public List<BibliaVO> listarVersiculo(LivroVO livro, Integer capitulo, Integer versiculoDe, Integer versiculoAte, String texto)
			throws BOException, BOValidationException;	
	


}
