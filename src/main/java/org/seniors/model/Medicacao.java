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
@Table(name="MEDICACAO")
@XmlRootElement
public class Medicacao implements Serializable {

	private static final long serialVersionUID = -3407438100726083936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "id_usuario", nullable = true)
	private long id_usuario;

	@Column(name = "id_cuidador", nullable = true)
	private long id_cuidador;

	private String nome;

	private String obs;

	private String dias;
	
	private Date dataInicial;
	
	private String hora;

	@Column(name = "prioridade", nullable = true)
	private Integer prioridade;

	@Column(name = "inicio", nullable = true)
	private Integer inicio;

	@Column(name = "duracao", nullable = true)
	private Integer duracao;

	@Column(name = "frequencia", nullable = true)
	private Integer frequencia;

	@Column(name = "dosagem", nullable = true)
	private Integer dosagem;

	private String tipo;

	private String status;

	public long getId() {
		return id;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}
	
	public String getDias() {
		return dias;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Integer getDosagem() {
		return dosagem;
	}

	public void setDosagem(Integer dosagem) {
		this.dosagem = dosagem;
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
	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
