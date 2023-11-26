import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserDaoTest {

    private UserDao userDao;
    private MongoCollection<Document> userCollection;

    @BeforeEach
    public void setUp() {
        // Create a mock MongoCollection
        userCollection = mock(MongoCollection.class);

        // Create a mock MongoDatabase
        MongoDatabase database = mock(MongoDatabase.class);
        when(database.getCollection("users")).thenReturn(userCollection);

        // Create a UserDao instance with the mock dependencies
        userDao = new UserDao(database);
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