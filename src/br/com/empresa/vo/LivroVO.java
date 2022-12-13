package br.com.empresa.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class LivroVO implements Serializable {

	private static final long serialVersionUID = 8703201536278648121L;
	
	@Id
	@Column(name = "livro")
	private Integer livro;
	
	@Column(name = "escritor")
	private String escritor;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "qtd_capitulos")
	private Integer qtd_capitulos;

	public Integer getLivro() {
		return livro;
	}

	public void setLivro(Integer livro) {
		this.livro = livro;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getQtd_capitulos() {
		return qtd_capitulos;
	}

	public void setQtd_capitulos(Integer qtd_capitulos) {
		this.qtd_capitulos = qtd_capitulos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return escritor ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(livro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroVO other = (LivroVO) obj;
		return Objects.equals(livro, other.livro);
	}

}
