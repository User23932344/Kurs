package by.grsu.matusevich.periodicals.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.matusevich.periodicals.db.dao.AbstractDao;
import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.PublishingHouse;
public class PublishingHouseDaoImpl extends AbstractDao implements IDao<Integer, PublishingHouse> {
	public static final PublishingHouseDaoImpl INSTANCE = new PublishingHouseDaoImpl();

	private PublishingHouseDaoImpl() {
		super();
	}
    @Override
	public void insert(PublishingHouse entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into publiching_house(address, email, phone) values(?,?,?)");
			pstmt.setString(1, entity.getAddress());
			pstmt.setString(2, entity.getEmail());
			pstmt.setInt(3, entity.getPhone());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "publiching_house"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert publiching_house entity", e);
		}

	}
	@Override
	public void update(PublishingHouse entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update publiching_house set address=?, email=?, phone=? where id=?");
			pstmt.setString(1, entity.getAddress());
			pstmt.setString(2, entity.getEmail());
			pstmt.setInt(3, entity.getPhone());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update publiching_house entity", e);
		}

	}
	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from publiching_house where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete publiching_house entity", e);
		}
	}
	@Override
	public PublishingHouse getById(Integer id) {
		PublishingHouse entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from publiching_house where id=?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get publiching_house entity by id", e);
		}

		return entity;
	}

	@Override
	public List<PublishingHouse> getAll() {
		List<PublishingHouse> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from publiching_house");
			while (rs.next()) {
				PublishingHouse entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select publiching_house entities", e);
		}

		return entitiesList;
	}

	private PublishingHouse rowToEntity(ResultSet rs) throws SQLException {
		PublishingHouse entity = new PublishingHouse();
		entity.setId(rs.getInt("id"));
		entity.setAddress(rs.getString("address"));
		entity.setEmail(rs.getString("email"));
		entity.setPhone(rs.getInt(123456));
		return entity;
	}
}
