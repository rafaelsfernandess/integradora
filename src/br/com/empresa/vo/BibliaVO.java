package br.com.empresa.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "biblia")
public class BibliaVO implements Serializable {

	private static final long serialVersionUID = -8072398248268673188L;

	@Id
	@Column(name = "sequencia")
	private Integer sequencia;
	
	// Livro
	@JoinColumn(name = "livro", referencedColumnName = "livro")
	@ManyToOne
	private LivroVO livro;

	// Capitulo
	@Column(name = "capitulo")
	private Integer capitulo;

	// Versiculo
	@Column(name = "versiculo")
	private Integer versiculo;

	// Texto
	@Column(name = "texto")
	private String texto;

	
	public LivroVO getLivro() {
		return livro;
	}

	public void setLivro(LivroVO livro) {
		this.livro = livro;
	}

	public Integer getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Integer capitulo) {
		this.capitulo = capitulo;
	}

	public Integer getVersiculo() {
		return versiculo;
	}

	public void setVersiculo(Integer versiculo) {
		this.versiculo = versiculo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sequencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliaVO other = (BibliaVO) obj;
		return Objects.equals(sequencia, other.sequencia);
	}

}
