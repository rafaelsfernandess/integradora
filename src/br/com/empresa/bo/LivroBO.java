package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.BibliaDAO;
import br.com.empresa.dao.IBibliaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public class LivroBO implements ILivroBO{
	
	private IBibliaDAO bibliaDAO;

	public LivroBO() {
		bibliaDAO = new BibliaDAO();
	}
	@Override
	public List<LivroVO> listarLivro() throws BOException {
		
		return bibliaDAO.listarLivro();
	}
	
	

	
	
}
