package entities;

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
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String citta;

	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	@OrderBy("titolo DESC")
	private Set<Evento> listaEventi;

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

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCitta() {
		return citta;
	}

	public void setListaEventi(Set<Evento> listaEventi) {
		this.listaEventi = listaEventi;
	}

	public Set<Evento> getListaEventi() {
		return listaEventi;
	}

	public Location() {

	}

	public Location(String nome, String citta) {
		setNome(nome);
		setCitta(citta);
	}

	@Override
	public String toString() {
		return "Location [id=" + id + "," + "nome=" + nome + "," + "citta=" + citta + "]";
	}

}
