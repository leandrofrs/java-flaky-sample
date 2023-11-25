import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBCollection {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public MongoDBCollection() {
        // Connect to the local MongoDB instance; you may customize the connection string as needed.
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("your_database_name");
        userCollection = database.getCollection("users");
    }

    public void createUser(String user) {
        Document userDocument = new Document("username", user)
                .append("email", "email");

        userCollection.insertOne(userDocument);
    }

    // Additional methods for querying, updating, or deleting users if needed

    public void close() {
        mongoClient.close();
    }
}
