package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Evento {

	@Id
	@GeneratedValue
	private UUID id;
	private String titolo;
	private LocalDate dataEvento;
	private String descrizione;
	private TipoEvento tipoEvento;
	private int numeroMassimoPartecipanti;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	@OrderBy("titolo DESC")
	private Set<Partecipazione> listaPartecipazioni;

	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
	private Location location;

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}

	public int getNumeroMassimoPartecipanti() {
		return numeroMassimoPartecipanti;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setListaPartecipazioni(Set<Partecipazione> listaPartecipazioni) {
		this.listaPartecipazioni = listaPartecipazioni;
	}

	public Set<Partecipazione> getPartecipazioni() {
		return listaPartecipazioni;
	}

	public Evento() {

	}

	public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Location location) {
		setTitolo(titolo);
		setDataEvento(dataEvento);
		setDescrizione(descrizione);
		setTipoEvento(tipoEvento);
		setNumeroMassimoPartecipanti(numeroMassimoPartecipanti);
		setLocation(location);
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", titolo=" + titolo + ", dataEvento=" + dataEvento + ", descrizione=" + descrizione
				+ ", tipoEvento=" + tipoEvento + ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti + "]";
	}

}
