package es.cc.liceu.db.examen;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import sun.java2d.pipe.SolidTextRenderer;

import java.util.ArrayList;
import java.util.List;

public class TestMongo {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase bd = client.getDatabase("examen_jdbc_model");
        MongoCollection<Document> collection = bd.getCollection("productes");

        List<Document> llista = collection.find().into(new ArrayList<>());
        for (Document document : llista) {
            System.out.println(document.toJson());
        }
        System.out.println("**** llista filtrada **** ");
        llista =  collection.find(new Document("name", "Nom producte")).into(new ArrayList<>());
        for (Document document : llista) {
            System.out.println(document.toJson());
        }


    }
}
