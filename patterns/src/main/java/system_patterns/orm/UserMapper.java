package system_patterns.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection conn;

    private final PreparedStatement selectUser;
    private final PreparedStatement updateUser;
    private final PreparedStatement deleteUser;
    private final PreparedStatement insertUser;

    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection conn) {
        this.conn = conn;
        try {
            this.selectUser = conn.prepareStatement("select id, username, password from users where id=?");
            this.updateUser = conn.prepareStatement("update users set username=?, password=? where id=?");
            this.deleteUser = conn.prepareStatement("delete from users where id=?");
            this.insertUser = conn.prepareStatement("insert into users (id,username,password) values (?,?,?)");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public void update(User user) {
        identityMap.put((long)user.getId(),user);
        try {
            updateUser.setString(1, user.getLogin());
            updateUser.setString(2, user.getPassword());
            updateUser.setLong(3, user.getId());
            updateUser.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insert(User user) {
        identityMap.put((long)user.getId(),user);
        try {
            insertUser.setLong(1, user.getId());
            insertUser.setString(2, user.getLogin());
            insertUser.setString(3, user.getPassword());
            insertUser.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(User user) {
        identityMap.remove(user.getId());
        try {
            deleteUser.setLong(1, user.getId());
            deleteUser.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
