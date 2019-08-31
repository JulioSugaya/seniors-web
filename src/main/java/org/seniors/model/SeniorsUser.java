package org.seniors.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class SeniorsUser implements Serializable, UserDetails {

	private static final long serialVersionUID = -5650549052650207619L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "login")
	private String name;

	private String nomeCompleto;

	private String parentesco;

	private String email;

	private String endereco;

	private String tipo_sanguineo;

	private String doencas;
	
	@Column(name = "raio_seg", nullable = true)
	private Integer raio_seg;

	private String celular;
	
	@XmlTransient
	private String password;
	
	private String chave;

	private String role;

	private String registrationId;

	@Column(name = "altura", nullable = true)
	private Integer altura;
	
	@Column(name = "peso", nullable = true)
	private Integer peso;
	
	@Column(name = "idade", nullable = true)
	private Integer idade;
	
	@Column(name = "id_cuidador", nullable = true)
	private Long id_cuidador;
	
	public Long getId_cuidador() {
		return id_cuidador;
	}

	public void setId_cuidador(Long id_cuidador) {
		this.id_cuidador = id_cuidador;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public void setRoles(List<String> roles) {
//		this.roles = roles;
//	}
	
//	public void addRole(Role role){
//		this.roles.add(role.getRole());
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<String> roles = this.getRoles();
		String role = this.getRole();

		if (role == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//		for (String role : roles) {
		authorities.add(new SimpleGrantedAuthority(role.toLowerCase()));
//		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDoencas() {
		return doencas;
	}

	public void setDoencas(String doencas) {
		this.doencas = doencas;
	}

	public Integer getRaio_seg() {
		return raio_seg;
	}

	public void setRaio_seg(Integer raio_seg) {
		this.raio_seg = raio_seg;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String key) {
		this.chave = key;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

}