package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Created by arthur on 23.04.17.
 */
public final class MongoConnector {
    private final MongoClient client;

    private MongoConnector() {
        this.client = new MongoClient("localhost", 27017);
    }

    public  MongoCollection<Document> getPlacesCollection() {
        return client.getDatabase("PlacesApi").getCollection("Places");
    }

    private static class MongoConnectorHolder {
        private static final MongoConnector INSTANCE = new MongoConnector();
    }

    public static MongoConnector getInstance() {
        return MongoConnectorHolder.INSTANCE;
    }
}
