import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

public class UserDaoTest {

    private UserDao userDao;
    private MongoCollection<Document> userCollection;

    @BeforeEach
    public void setUp() {
        // Connect to a real MongoDB instance
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Get the database and collection
        MongoDatabase database = mongoClient.getDatabase("your_database_name");
        userCollection = database.getCollection("users");

        // Create a UserDao instance with the real dependencies
        userDao = new UserDao("mongodb://localhost:27017");
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User("john_doe", "john.doe@example.com");
        Document expectedUserDocument = new Document("username", user.getUsername())
                .append("email", user.getEmail());

        // Act
        userDao.createUser(user);

        // Assert
        verify(userCollection, times(1)).insertOne(expectedUserDocument);
    }

}