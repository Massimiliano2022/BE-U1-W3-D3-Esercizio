package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Evento;
import entities.Location;

public class LocationDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public LocationDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Location l) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(l);
		transaction.commit();
		logger.info("Location salvata " + l.toString());
	}

	public Location getById(UUID id) {
		Location found = em.find(Location.class, id);
		return found;
	}

	public void findByIdAndDelete(UUID id) {
		Location found = em.find(Location.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Location con id " + id + " eliminata!");
		}
	}

	public void getEventiProssimi(Location l) {
		for (Evento e : l.getListaEventi()) {
			logger.info("" + e.getTitolo());
		}
	}

	public void refresh(UUID id) {
		Location found = em.find(Location.class, id);
		found.setNome("Nuovo nome location");
		logger.info("PRE REFRESH");
		logger.info("" + found);
		em.refresh(found);
		logger.info("POST REFRESH");
		logger.info("" + found);
	}

}
