package dbmanager;

import com.mongodb.client.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBUtils {
    static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    public static MongoCollection<Document> getCollection(String dbName, String collectionName) {

        MongoDatabase database = mongoClient.getDatabase(dbName);
        return database.getCollection(collectionName);
    }

    public static void closeClient() {
        mongoClient.close();
    }
}
