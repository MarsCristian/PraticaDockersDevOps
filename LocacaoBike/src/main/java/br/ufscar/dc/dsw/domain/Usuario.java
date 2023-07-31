package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.MappedSuperclass;

import br.ufscar.dc.dsw.validation.UniquedTelefone;
import br.ufscar.dc.dsw.validation.UniquedEmail;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {

  @NotBlank(message = "{NotNull.usuario.nome}")
  //@Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String nome;
      
  @UniquedTelefone (message = "{Unique.usuario.telefone}")
  @NotBlank(message = "{NotNull.usuario.telefone}")
  //@Size(min = 3, max = 256)
  @Column(nullable = false, unique = true, length = 256)
  private String telefone;

  @UniquedEmail (message = "{Unique.usuario.email}")
  @NotBlank(message = "{NotNull.usuario.email}")
  @Size(min = 3, max = 256)
  @Column(nullable = false, unique = false, length = 256)
  private String email;

  @NotBlank(message = "{NotNull.usuario.senha}")
  //@Size(min = 3, max = 256)
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
		System.out.println("set tel");
    this.telefone = telefone;
  }


  public String getEmail() {
  return email;
  }

  public void setEmail(String email) {
		System.out.println("set email");
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
