package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {

  @NotBlank(message = "{NotNull.usuario.Nome}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String nome;
      
  @NotBlank(message = "{NotNull.usuario.telefone}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = true, length = 256)
  private String telefone;

  @NotBlank(message = "{NotNull.usuario.email}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String email;

  @NotBlank(message = "{NotNull.usuario.senha}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String senha;

  @NotBlank(message = "{NotNull.usuario.papel}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String papel;

  public String getNome() {
  return nome;
  }

  public void setNome(String nome) {
  this.nome = nome;
  }

  public String getTelefone() {
  return telefone;
  }

  public void setTelefone(String telefone) {
  this.telefone = telefone;
  }


  public String getEmail() {
  return email;
  }

  public void setEmail(String email) {
  this.email = email;
  }

  public String getSenha() {
  return senha;
  }

  public void setSenha(String senha) {
  this.senha = senha;
  }

  public String getPapel() {
  return papel;
  }

  public void setPapel(String papel) {
  this.papel = papel;
	}
}