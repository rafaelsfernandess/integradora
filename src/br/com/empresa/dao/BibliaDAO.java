package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;




public class BibliaDAO implements IBibliaDAO{

	@Override
	public List<LivroVO> listarLivro() throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LivroVO> criteria = cb.createQuery(LivroVO.class);

		// Cláusula From
		Root<LivroVO> bibliaFrom = criteria.from(LivroVO.class);

		// Atribuindo as cláusulas à consulta
		criteria.select(bibliaFrom);


		TypedQuery<LivroVO> query = em.createQuery(criteria);

		List<LivroVO> listaLivros = query.getResultList();

		List<LivroVO> retorno = new ArrayList<>();

		em.close();
		return listaLivros;
		
	}

	@Override
	public List<BibliaVO> listarVersiculo() throws BOException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
