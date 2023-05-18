package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Evento;
import entities.Partecipazione;

public class EventoDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		logger.info("Evento salvato " + e.toString());
	}

	public Evento getById(UUID id) {
		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void findByIdAndDelete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Evento con id " + id + " eliminato!");
		}
	}

	public void getPersonePartecipanti(Evento e) {
		for (Partecipazione p : e.getPartecipazioni()) {

			logger.info("" + p.getPersona());

		}
	}

	public void refresh(UUID id) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo("Concerto Rolling Stones");
		logger.info("PRE REFRESH");
		logger.info("" + found);
		em.refresh(found);
		logger.info("POST REFRESH");
		logger.info("" + found);
	}

}
