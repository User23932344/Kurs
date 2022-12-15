package by.grsu.matusevich.periodicals.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.matusevich.periodicals.db.dao.AbstractDao;
import by.grsu.matusevich.periodicals.db.dao.IDao;
import by.grsu.matusevich.periodicals.db.model.Client;

public class ClientDaoImpl extends AbstractDao implements IDao<Integer,Client>{
    public static final ClientDaoImpl INSTANCE = new ClientDaoImpl();
    private ClientDaoImpl() {
		super();
	}
    @Override
	public void insert(Client entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into client(name, email, address, phone, balance) values(?,?,?,?,?)");
			pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getEmail());
            pstmt.setString(3, entity.getAddress());
            pstmt.setInt(4, entity.getPhone());
            pstmt.setDouble(5, entity.getBalance());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "client"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert client entity", e);
		}
	}

    @Override
	public void update(Client entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update client set name=?, email=?, address=?, phone=?, balance=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getEmail());
            pstmt.setString(3, entity.getAddress());
            pstmt.setInt(4, entity.getPhone());
            pstmt.setDouble(5, entity.getBalance());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update client entity", e);
		}
	}

    @Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from client where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete сlient entity", e);
		}

	}

    @Override
	public Client getById(Integer id) {
		Client entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from client where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get сlient entity by id", e);
		}

		return entity;
	}

    @Override
	public List<Client> getAll() {
		List<Client> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from client");
			while (rs.next()) {
				Client entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select client entities", e);
		}

		return entitiesList;
	}

    private Client rowToEntity(ResultSet rs) throws SQLException {
		Client entity = new Client();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
        entity.setAddress(rs.getString("address"));
        entity.setEmail(rs.getString("email"));
        entity.setPhone(rs.getInt("phone"));
        entity.setBalance(rs.getDouble("balance"));
		return entity;
	}
}