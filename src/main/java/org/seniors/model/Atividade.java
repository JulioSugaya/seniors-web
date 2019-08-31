package org.seniors.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="ATIVIDADE")
@XmlRootElement
public class Atividade implements Serializable {

	private static final long serialVersionUID = -3407438100726083936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "id_usuario", nullable = true)
	private long id_usuario;

	@Column(name = "id_cuidador", nullable = true)
	private long id_cuidador;

	private String descricao;

	private String inicio;

	private Date dataInicial;
	
	private Date dataFim;

	private String hora;

	@Column(name = "duracao", nullable = true)
	private Integer duracao;

	private String tipo;

	private String status;
	
	private String diasSemana;
	
	private String medirPeso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}
	
	public String isMedirPeso() {
		return medirPeso;
	}
	
	public String getMedirPeso() {
		return medirPeso;
	}

	public void setMedirPeso(String medirPeso) {
		this.medirPeso = medirPeso;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_cuidador() {
		return id_cuidador;
	}

	public void setId_cuidador(long id_cuidador) {
		this.id_cuidador = id_cuidador;
	}
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
