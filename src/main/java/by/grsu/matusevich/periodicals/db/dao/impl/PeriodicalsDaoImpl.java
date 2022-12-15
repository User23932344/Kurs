package by.grsu.matusevich.periodicals.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.matusevich.periodicals.db.dao.AbstractDao;
import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.Periodicals;

public class PeriodicalsDaoImpl extends AbstractDao implements IDao<Integer, Periodicals> {
	public static final PeriodicalsDaoImpl INSTANCE = new PeriodicalsDaoImpl();

	private PeriodicalsDaoImpl() {
		super();
	}

	@Override
	public void insert(Periodicals entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into periodicals(name, synopsis, price, publishing_house_id) values(?,?,?,?)");
			pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getSynopsis());
			pstmt.setDouble(3, entity.getPrice());
            pstmt.setInt(4, entity.getPublishing_house_id());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "periodicals"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert periodicals entity", e);
		}

	}

	@Override
	public void update(Periodicals entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update periodicals set name=?, synopsis=?, price=?, publishing_house_id=? where id=?");
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getSynopsis());
            pstmt.setDouble(3, entity.getPrice());
            pstmt.setInt(4, entity.getPublishing_house_id());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update periodicals entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from periodicals where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete periodicals entity", e);
		}
	}

	@Override
	public Periodicals getById(Integer id) {
		Periodicals entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from periodicals where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get periodicals entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Periodicals> getAll() {
		List<Periodicals> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from periodicals");
			while (rs.next()) {
				Periodicals entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select periodicals entities", e);
		}

		return entitiesList;
	}

	private Periodicals rowToEntity(ResultSet rs) throws SQLException {
		Periodicals entity = new Periodicals();
		entity.setId(rs.getInt("id"));
		entity.setSynopsis(rs.getString("synopsis"));
		entity.setName(rs.getString("name"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setPrice(rs.getDouble("price"));
		entity.setPublishing_house_id(rs.getInt("publishing_house_id"));
		return entity;
	}
}