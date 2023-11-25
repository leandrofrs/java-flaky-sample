import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.UserDao;


public class UserDaoTest {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        // Update the connection string with your remote MongoDB URI
        String connectionString = "mongodb+srv://<username>:<password>@<cluster-url>/<database-name>?retryWrites=true&w=majority";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("your_test_database_name");
        userCollection = database.getCollection("users");
        userDao = new UserDao(connectionString);
    }

    @AfterEach
    public void tearDown() {
        mongoClient.close();
        userDao.close();
    }

    @Test
    public void testCreateUser() {
        User user = new User("john_doe", "john.doe@example.com");
        userDao.createUser(user);

        // Assuming you have a method to query the user by username or email
        User retrievedUser = getUserByUsername(user.getUsername());

        // Check if the user was successfully created
        assertEquals(user.getUsername(), retrievedUser.getUsername());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    private User getUserByUsername(String username) {
        // Implement the logic to query the user from the database by username
        // Return a User object or null if the user is not found
        // This method is for demonstration purposes; you may replace it with your actual database query logic
        return new User(username, "dummy.email@example.com");
    }
}
