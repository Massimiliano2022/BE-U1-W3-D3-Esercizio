package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partecipazioni")
public class Partecipazione {

	@Id
	@GeneratedValue
	private UUID id;
	private Stato stato;

	@ManyToOne
	@JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)
	private Evento evento;

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Stato getStato() {
		return stato;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

	public Partecipazione() {

	}

	public Partecipazione(Persona persona, Evento evento, Stato stato) {
		setPersona(persona);
		setEvento(evento);
		setStato(stato);
	}

	@Override
	public String toString() {
		return "Partecipazione [id=" + id + "," + "stato=" + stato + "]";
	}

}
