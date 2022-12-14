package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.BibliaDAO;
import br.com.empresa.dao.IBibliaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public class BibliaBO implements IBibliaBO{

	private IBibliaDAO bibliaDAO;
	
	public BibliaBO() {
	bibliaDAO = new BibliaDAO();
	}

	@Override
	public List<BibliaVO> listarVersiculo(LivroVO livro, Integer capitulo, Integer versiculoDe, Integer versiculoAte,
			String texto) throws BOException, BOValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
