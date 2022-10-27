public class DBConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc_hibernate-master";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
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
