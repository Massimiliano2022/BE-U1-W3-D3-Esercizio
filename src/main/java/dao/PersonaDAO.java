package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Application;
import entities.Persona;

public class PersonaDAO {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private final EntityManager em;

	public PersonaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Persona p) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(p);
		transaction.commit();
		logger.info("Persona salvata " + p.toString());
	}

	public Persona getById(UUID id) {
		Persona found = em.find(Persona.class, id);
		return found;
	}

	public void findByIdAndDelete(UUID id) {
		Persona found = em.find(Persona.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			logger.info("Persona con id " + id + " eliminata!");
		}
	}

	public void refresh(UUID id) {
		Persona found = em.find(Persona.class, id);
		found.setNome("Aldo");
		logger.info("PRE REFRESH");
		logger.info("" + found);
		em.refresh(found);
		logger.info("POST REFRESH");
		logger.info("" + found);
	}
}
