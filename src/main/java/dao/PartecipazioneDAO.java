package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Partecipazione;

public class PartecipazioneDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public PartecipazioneDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Partecipazione p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(p);
		transaction.commit();
		logger.info("Partecipazione salvata " + p.toString());
	}

	public Partecipazione getById(UUID id) {
		Partecipazione found = em.find(Partecipazione.class, id);
		return found;
	}

	public void findByIdAndDelete(UUID id) {
		Partecipazione found = em.find(Partecipazione.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Partecipazione con id " + id + " eliminata!");
		}
	}

	public void refresh(UUID id) {
		Partecipazione found = em.find(Partecipazione.class, id);
		found.setId(UUID.fromString("019219102"));
		logger.info("PRE REFRESH");
		logger.info("" + found);
		em.refresh(found);
		logger.info("POST REFRESH");
		logger.info("" + found);
	}
}
