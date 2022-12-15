package by.grsu.matusevich.periodicals.db.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.Client;

public class ClientDaoTest extends AbstractTest {
	private static final IDao<Integer, Client> dao = ClientDaoImpl.INSTANCE;

    @Test
	public void testInsert() {
		Client entity = new Client();
		entity.setName("VW");
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        entity.setBalance(2.0);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
    
    @Test
	public void testUpdate() {
		Client entity = new Client();
		entity.setName("VW");
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        entity.setBalance(2.0);
		dao.insert(entity);

		String newName = "VW_NEW";
		entity.setName(newName);
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        entity.setBalance(2.0);
		dao.update(entity);

		Client EmailEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newName, EmailEntity.getName());
		Assertions.assertNotEquals(EmailEntity.getAddress(), EmailEntity.getEmail(), EmailEntity.getAddress());
        
	}
    @Test
	public void testDelete() {
		Client entity = new Client();
		entity.setName("VW");
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        entity.setBalance(2.0);
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Client entity = new Client();
		entity.setName("VW");
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        entity.setBalance(2.0);
		dao.insert(entity);

		Client selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getAddress(), selectedEntity.getAddress());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
        Assertions.assertEquals(entity.getPhone(), selectedEntity.getPhone());
        Assertions.assertEquals(entity.getBalance(), selectedEntity.getBalance());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Client entity = new Client();
			entity.setName("VW" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setAddress("ul.Pushkin's"+i);
			entity.setEmail("vw@mail.com"+i);
            entity.setPhone(1234567);
            entity.setBalance(2.0);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
