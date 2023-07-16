package com.basis.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/*
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;*/
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pessoa")
@Getter
@Setter
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoaPK", nullable = false)
	private long pessoaPK;

	@Column(name = "Telefone", length = 25, nullable = true)
	private String telefone;
	@Column(name = "Email", length = 100, nullable = false)
	private String email;
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	List<Endereco> enderecos;

}
/*
 * @Data //@NoArgsConstructor
 * 
 * @Entity
 * 
 * @Getter @Setter public abstract class Pessoa { private static Long uid = 0L;
 * 
 * @GeneratedValue private @Id Long id;
 * 
 * @NotNull private String ddd;
 * 
 * @NotNull private String telefone; private String email; private
 * ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
 * 
 * 
 * 
 * 
 * /* public Pessoa() { Pessoa.uid++; this.id = Pessoa.uid;
 * 
 * }
 */

/*
 * public void addEndereco(Endereco endereco) { this.enderecos.add(endereco); }
 */

/*
 * public Pessoa(String ddd, String telefone, String email, Endereco endereco) {
 * 
 * 
 * Pessoa.uid++; this.id = Pessoa.uid; this.ddd = ddd; this.telefone = telefone;
 * this.email = email; this.enderecos.add(endereco);
 * 
 * }
 */

/*
 * public static void main(String[] args) { System.out.println("Oi"); }
 */
