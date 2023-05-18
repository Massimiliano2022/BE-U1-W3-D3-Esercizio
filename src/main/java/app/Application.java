package app;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import entities.Sesso;
import entities.Stato;
import entities.TipoEvento;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = util.JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();

		EventoDAO ed = new EventoDAO(em);
		PersonaDAO pd = new PersonaDAO(em);
		LocationDAO ld = new LocationDAO(em);
		PartecipazioneDAO partecipazioneD = new PartecipazioneDAO(em);

		Persona p1 = new Persona("Mario", "Rossi", "mariorossi@example.com", LocalDate.now().minusYears(20),
				Sesso.MASCHIO);
		pd.save(p1);

		Persona p2 = new Persona("Giuseppe", "Verdi", "giuseppeverdi@example.com", LocalDate.now().minusYears(25),
				Sesso.MASCHIO);
		pd.save(p2);

		Location l1 = new Location("Teatro", "Torino");
		ld.save(l1);

		Location l2 = new Location("Sala congressi", "Milano");
		ld.save(l2);

		Evento e1 = new Evento("Concerto Pink Floyd", LocalDate.now(), "STUPENDO!", TipoEvento.PRIVATO, 6000, l1);
		ed.save(e1);

		Evento e2 = new Evento("Developer Conference", LocalDate.now().plusMonths(1), "BRC-20", TipoEvento.PUBBLICO,
				3000, l2);
		ed.save(e2);

		Partecipazione partecipazione1 = new Partecipazione(p1, e1, Stato.CONFERMATA);
		Partecipazione partecipazione2 = new Partecipazione(p2, e1, Stato.DA_CONFERMARE);

		Partecipazione partecipazione3 = new Partecipazione(p1, e2, Stato.CONFERMATA);
		Partecipazione partecipazione4 = new Partecipazione(p2, e2, Stato.CONFERMATA);

		Set<Partecipazione> listaPersoneE1 = new HashSet<Partecipazione>();
		listaPersoneE1.add(partecipazione1);
		listaPersoneE1.add(partecipazione2);

		e1.setListaPartecipazioni(listaPersoneE1);

		Set<Partecipazione> listaPersoneE2 = new HashSet<Partecipazione>();

		listaPersoneE2.add(partecipazione3);
		listaPersoneE2.add(partecipazione4);

		e2.setListaPartecipazioni(listaPersoneE2);

		Set<Evento> listaEventil1 = new HashSet<Evento>();
		listaEventil1.add(e1);

		l1.setListaEventi(listaEventil1);

		Set<Evento> listaEventil2 = new HashSet<Evento>();
		listaEventil2.add(e2);

		l2.setListaEventi(listaEventil2);

		partecipazioneD.save(partecipazione1);
		partecipazioneD.save(partecipazione2);

		partecipazioneD.save(partecipazione3);
		partecipazioneD.save(partecipazione4);

		logger.info("LE PERSONE PARTECIPANTI ALL'EVENTO " + e1.getTitolo() + " SONO: ");
		ed.getPersonePartecipanti(e1);

		logger.info("LE PERSONE PARTECIPANTI ALL'EVENTO " + e2.getTitolo() + " SONO: ");
		ed.getPersonePartecipanti(e2);

		logger.info("I PROSSIMI EVNETI ORGANIZZATI ALLA LOCATION " + l1.getNome() + " SONO: ");
		ld.getEventiProssimi(l1);

		logger.info("I PROSSIMI EVNETI ORGANIZZATI ALLA LOCATION " + l2.getNome() + " SONO: ");
		ld.getEventiProssimi(l2);

		em.close();
		emf.close();
	}

}
