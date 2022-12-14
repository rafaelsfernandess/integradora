package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
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
		Root<LivroVO> livroFrom = criteria.from(LivroVO.class);
		
        Order livroOrderBy = cb.asc(livroFrom.get("livro"));

		// Atribuindo as cláusulas à consulta
		criteria.select(livroFrom);
        criteria.orderBy(livroOrderBy);


		TypedQuery<LivroVO> query = em.createQuery(criteria);

		List<LivroVO> listaLivros = query.getResultList();

		List<LivroVO> retorno = new ArrayList<>();

		em.close();
		return listaLivros;
		
	}

	@Override
	public List<BibliaVO> listarVersiculo(LivroVO livro, Integer capitulo, Integer versiculoDe, Integer versiculoAte, String texto)
			throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);

       // Cláusula From
        Root<BibliaVO> bibliaFrom = criteria.from(BibliaVO.class);
        Join<BibliaVO, LivroVO> livroFrom = bibliaFrom.join("livro");
      
        criteria.multiselect(livroFrom, bibliaFrom);
        
        Predicate bibliaWhere = cb.isNull(bibliaFrom);
        
        if(versiculoDe != null && versiculoAte != null) {
        	bibliaWhere = cb.and(cb.between(bibliaFrom.get("versiculo"), versiculoDe, versiculoAte));
        } 
        
        if(livro != null) {
        	bibliaWhere = cb.and(bibliaWhere, cb.equal(livroFrom, livro));
        }
        
        if(capitulo != null) {
        	bibliaWhere = cb.and(bibliaWhere, cb.equal(bibliaFrom.get("capitulo"), capitulo));
        }
        
        if(texto != null) {
        	bibliaWhere = cb.and(bibliaWhere, cb.like(bibliaFrom.get("texto"),"%" + texto + "%"));
        }
       
        
        
        Order bibliaOrderBy = cb.asc(bibliaFrom.get("capitulo"));
        
        // Atribuindo as cláusulas à consulta
        
        criteria.where(bibliaWhere);
        criteria.orderBy(bibliaOrderBy);
        
        
        TypedQuery<Tuple> query = em.createQuery(criteria);

        List<Tuple> tuples = query.getResultList();
        
        List<BibliaVO> retorno = new ArrayList<BibliaVO>();
        
        if(tuples != null) {
        	for (Tuple tuple : tuples) {
				
        		LivroVO livrosVO = tuple.get(livroFrom);        		
        		BibliaVO bibliaVO = tuple.get(bibliaFrom);
        		bibliaVO.setLivro(livrosVO);
        		
        		retorno.add(bibliaVO);
			}
        }
        
        

       for (BibliaVO bibliaVO : retorno) {
    	   
            if (bibliaVO.getLivro() != null) {
                continue;
            }

           if (livro != null) {
               if (bibliaVO.getLivro().equals(livro) == false) {
                   continue;
               }
           }
           
           if (capitulo != null) {
               if (bibliaVO.getCapitulo().equals(capitulo) == false) {
                   continue;
               }
           }
           
           if (versiculoDe != null) {
               if (bibliaVO.getVersiculo().equals(versiculoDe) == false) {
                   continue;
               }
           }
           
           if (versiculoAte != null) {
               if (bibliaVO.getVersiculo().equals(versiculoAte) == false) {
                   continue;
               }
           }
           
           if (texto != null && texto.trim().length() > 0) {
               if (bibliaVO.getTexto().contains(texto) == false) {
                   continue;
               }
           }
           
           retorno.add(bibliaVO);
        }
       
        em.close();
        
        return retorno;
	}

	
}
