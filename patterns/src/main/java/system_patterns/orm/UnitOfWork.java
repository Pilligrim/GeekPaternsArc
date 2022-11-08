package system_patterns.orm;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private final UserMapper userMapper;

    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUsers = new ArrayList<>();
    private final List<User> deleteUsers = new ArrayList<>();

    public UnitOfWork(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUsers.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUsers.add(user);
    }

    public void commit() {
        insert();
        update();
        delete();
        clear();
    }

    private void update() {
        this.updateUsers.forEach(userMapper::update);
    }

    private void insert() {
        this.newUsers.forEach(userMapper::insert);
    }

    private void delete() {
        this.deleteUsers.forEach(userMapper::delete);
    }

    private void clear() {
        this.newUsers.clear();
        this.updateUsers.clear();
        this.deleteUsers.clear();
    }
}
