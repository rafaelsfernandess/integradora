package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.BibliaDAO;
import br.com.empresa.dao.IBibliaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public class BibliaBO implements IBibliaBO{

	private IBibliaDAO bibliaDAO;
	
	public BibliaBO() {
	bibliaDAO = new BibliaDAO();
	}

	@Override
	public List<BibliaVO> listarLivro()
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BibliaVO> listarVersiculos(LivroVO livro) throws BOException {
		if (livro == null || livro.getEscritor() == null) {
			throw new BOException();
	}
		return bibliaDAO.listarVersiculo();
	}
	

}
