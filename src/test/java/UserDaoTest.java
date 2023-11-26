import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserDaoTest {

    private UserDao userDao;
    private MongoCollection<Document> userCollection;

    @Before
    public void setUp() {
        // Mock the MongoDB collection
        userCollection = mock(MongoCollection.class);

        // Mock the UserDao instance with the mocked dependencies
        userDao = new UserDao("mongodb://localhost:27017", userCollection);
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
