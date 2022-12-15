package by.grsu.matusevich.periodicals.db.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.matusevich.periodicals.db.dao.AbstractDao;
import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.Subscription;

public class SubscriptionDaoImpl extends AbstractDao implements IDao<Integer, Subscription> {
	public static final SubscriptionDaoImpl INSTANCE = new SubscriptionDaoImpl();

	private SubscriptionDaoImpl() {
		super();
	}

	@Override
	public void insert(Subscription entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into subscription(client_id, periodicals_id, status, created, created) values(?,?,?,?,?)");
			pstmt.setInt(1, entity.getClient_id());
			pstmt.setInt(2, entity.getPeriodicals_id());
			pstmt.setBoolean(3, entity.getStatus());
			pstmt.setTimestamp(4, entity.getCreated());
			pstmt.setDouble(5, entity.getPrice());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "subscription"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert subscription entity", e);
		}

	}

	@Override
	public void update(Subscription entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update subscription set client_id=?, periodicals_id=?,  status=?, price=?, where id=?");
            pstmt.setInt(1, entity.getClient_id());
            pstmt.setInt(2, entity.getPeriodicals_id());
            pstmt.setBoolean(3, entity.getStatus());
            pstmt.setTimestamp(4, entity.getCreated());
            pstmt.setDouble(5, entity.getPrice());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update subscription entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from subscription where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete subscription entity", e);
		}
	}

	@Override
	public Subscription getById(Integer id) {
		Subscription entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from subscription where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get subscription entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Subscription> getAll() {
		List<Subscription> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from subscription");
			while (rs.next()) {
				Subscription entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select subscription entities", e);
		}

		return entitiesList;
	}

	private Subscription rowToEntity(ResultSet rs) throws SQLException {
		Subscription entity = new Subscription();
		entity.setId(rs.getInt("id"));
		entity.setClient_id(rs.getInt("client_id"));
		entity.setPeriodicals_id(rs.getInt("periodicals_id"));
        entity.setStatus(rs.getBoolean("status"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setPrice(rs.getDouble("updated"));
		return entity;
	}
}