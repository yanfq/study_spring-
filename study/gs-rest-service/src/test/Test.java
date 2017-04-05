import com.mongodb.*;

import java.net.UnknownHostException;
/**
 * Created by yanfq on 2017/3/25.
 */
public class Test {
    public static void main(String[] args) throws UnknownHostException {
        //MongoCredential credential = MongoCredential.createCredential("username", "database", "password".toCharArray());
        ServerAddress serverAddress = new ServerAddress("192.168.81.132", 27017);
        //MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
        MongoClient mongoClient = new MongoClient(serverAddress);
        DB db = mongoClient.getDB("yanfqDb");
        DBCollection collection = db.getCollection("user");

        BasicDBObject doc = new BasicDBObject("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new BasicDBObject("x", 203).append("y", 102));
        collection.insert(doc);

        System.out.println(collection.findOne());
    }
}
