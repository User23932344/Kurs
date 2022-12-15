package by.grsu.matusevich.periodicals.db.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.matusevich.periodicals.db.dao.IDao;
//import by.grsu.matusevich.periodicals.db.dao.impl.PublishingHouseDaoImpl;
import by.grsu.matusevich.periodicals.db.model.PublishingHouse;

public class PublishinghouseDaoTest extends AbstractTest {
	private static final IDao<Integer, PublishingHouse > dao = PublishingHouseDaoImpl.INSTANCE;

    @Test
	public void testInsert() {
		PublishingHouse entity = new PublishingHouse();
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}
    
    @Test
	public void testUpdate() {
		PublishingHouse entity = new PublishingHouse();		
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
		dao.insert(entity);
		
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail4.com");
        entity.setPhone(12345);
		dao.update(entity);

		PublishingHouse updatedEntity = dao.getById(entity.getId());
		Assertions.assertNotEquals(updatedEntity.getAddress(), updatedEntity.getEmail()); 
		Assertions.assertEquals(updatedEntity.getPhone(), updatedEntity.getPhone());    
	}
    @Test
	public void testDelete() {
		PublishingHouse entity = new PublishingHouse();
		
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);
        
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		PublishingHouse entity = new PublishingHouse();
		
        entity.setAddress("ul.Pushkin's");
        entity.setEmail("vw@mail.com");
        entity.setPhone(1234567);       
		dao.insert(entity);

		PublishingHouse selectedEntity = dao.getById(entity.getId());

		
		Assertions.assertEquals(entity.getAddress(), selectedEntity.getAddress());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
        Assertions.assertEquals(entity.getPhone(), selectedEntity.getPhone());     
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			PublishingHouse entity = new PublishingHouse();
			
			entity.setAddress("ul.Pushkin's"+i);
			entity.setEmail("vw@mail.com"+i);
            entity.setPhone(1234567+i);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}
