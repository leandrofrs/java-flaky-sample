import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserDao {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public UserDao(String connectionString) {
        // Connect to the local MongoDB instance; you may customize the connection string as needed.
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("your_database_name");
        userCollection = database.getCollection("users");
    }

    public void createUser(User user) {
        Document userDocument = new Document("username", user.getUsername())
                .append("email", user.getEmail());

        userCollection.insertOne(userDocument);
    }

    // Additional methods for querying, updating, or deleting users if needed

    public void close() {
        mongoClient.close();
    }
}