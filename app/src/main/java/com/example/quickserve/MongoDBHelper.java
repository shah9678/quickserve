package com.example.quickserve;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBHelper {
    private static final String CONNECTION_STRING = "mongodb+srv://aditshah9678:<db_password>@quickservedb.j0dvc.mongodb.net/?retryWrites=true&w=majority&appName=QuickServeDB";
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase("QuickServeDB");
        }
        return database;
    }
}
