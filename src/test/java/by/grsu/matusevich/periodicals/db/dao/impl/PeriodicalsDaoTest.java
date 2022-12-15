package by.grsu.matusevich.periodicals.db.dao.impl;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.PublishingHouse;
import by.grsu.matusevich.periodicals.db.model.Periodicals;

public class PeriodicalsDaoTest extends AbstractTest {
	private static final IDao<Integer,PublishingHouse> brandDao = PublishingHouseDaoImpl.INSTANCE;
	private static final IDao<Integer, Periodicals> modelDao = PeriodicalsDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Periodicals entity = new Periodicals();		
		entity.setName("Passat");
		entity.setSynopsis("fghjklgf");
        entity.setPrice(3.0);
        entity.setPublishing_house_id(savePublishingHouse("VW").getId());
		modelDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Periodicals entity = new Periodicals();
        entity.setName("Passat");
		entity.setSynopsis("fghjklgf");
        entity.setPrice(3.0);
		entity.setPublishing_house_id(savePublishingHouse("VW").getId());
        modelDao.insert(entity);
		
		String newName = "Golf";
		entity.setName(newName);
		entity.setSynopsis("fghjklgf");
        entity.setPrice(2.0);
		modelDao.update(entity);

		Periodicals updatedEntity = modelDao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName(),updatedEntity.getSynopsis());		
		Assertions.assertNotEquals(updatedEntity.getPrice(), updatedEntity.getPublishing_house_id());
	}

	@Test
	public void testDelete() {
		Periodicals entity = new Periodicals();
		entity.setPublishing_house_id(savePublishingHouse("VW").getId());
		entity.setName("Passat");
		entity.setSynopsis("fghjklgf");
        entity.setPrice(3.0);
		modelDao.insert(entity);

		modelDao.delete(entity.getId());

		Assertions.assertNull(modelDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Periodicals entity = new Periodicals();
		entity.setPublishing_house_id(savePublishingHouse("VW").getId());
		entity.setName("Passat");		
		entity.setSynopsis("fghjklgf");
        entity.setPrice(3.0);
		modelDao.insert(entity);

		Periodicals selectedEntity = modelDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getPublishing_house_id(), selectedEntity.getPublishing_house_id());
		Assertions.assertEquals(entity.getSynopsis(), selectedEntity.getSynopsis());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Periodicals entity = new Periodicals();
			entity.setPublishing_house_id(savePublishingHouse("VW" + i).getId());
			entity.setName("Passat" + i);
			entity.setSynopsis("fghjklgf"+i);
        entity.setPrice(3.0);
			modelDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, modelDao.getAll().size());
	}

	private PublishingHouse savePublishingHouse(String name) {
		PublishingHouse entity = new PublishingHouse();
		entity.setEmail(name);
		entity.setAddress("ul.Pushkin's");
		entity.setPhone(1234567);
		brandDao.insert(entity);
		return entity;
	}
}