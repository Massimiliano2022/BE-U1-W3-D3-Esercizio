package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "persone")
public class Persona {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate birthDate;
	private Sesso sesso;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	@OrderBy("nome DESC")
	private Set<Partecipazione> listaPartecipazioni;

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setListaPartecipazioni(Set<Partecipazione> listaPartecipazioni) {
		this.listaPartecipazioni = listaPartecipazioni;
	}

	public Set<Partecipazione> getListaPartecipazioni() {
		return listaPartecipazioni;
	}

	public Persona() {

	}

	public Persona(String nome, String cognome, String email, LocalDate birthDate, Sesso sesso) {
		setNome(nome);
		setCognome(cognome);
		setEmail(email);
		setBirthDate(birthDate);
		setSesso(sesso);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + "," + "nome=" + nome + "," + "cognome=" + cognome + "," + "email=" + email + ","
				+ "birthDate=" + birthDate + "," + "sesso=" + sesso + "]";
	}

}
