package dbmanager;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

public class DBHandler {
    private final MongoCollection<Document> collection;

    public DBHandler(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    //Create operation
    public void create(Document document) {
        collection.insertOne(document);
        System.out.println("Document inserted: " + document.toJson());
    }

    public void read() {
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public void read(Document filter) {
        try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    public void update(Document filter, Document update) {
        Document updateDocument = new Document("$set", update);
        collection.updateOne(filter, updateDocument);
        System.out.println("Document updated with filter: " + filter.toJson());
    }

    public void deleteOne(Document filter) {
        collection.deleteOne(filter);
        System.out.println("Document deleted with filter: " + filter.toJson());
    }

}
