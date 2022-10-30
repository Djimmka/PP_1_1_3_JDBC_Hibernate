public class DBConnectionTest {

    public static void main(String[] args) {
        UserServiceTest usTest = new UserServiceTest();
        usTest.createUsersTable();
        usTest.saveUser();
        usTest.getAllUsers();
        usTest.removeUserById();
        usTest.cleanUsersTable();
        usTest.dropUsersTable();
    }
}
