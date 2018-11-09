package fr.ub.m2gl;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

@Path("/users")
public class DatabaseUsers {
	
	@GET
	@Produces("application/json")
	public ArrayList<User> getUsers() {
		MongoClient mongoClient = new MongoClient();
		try {
			
		    MongoDatabase db = mongoClient.getDatabase("myBase");
		    MongoCollection<Document> collection = db.getCollection("myCollection");

		    MyObjectMapperProvider provider = new MyObjectMapperProvider();
		    ObjectMapper mapper = provider.defaultObjectMapper;
		    /* On exclut l'attribut "_id" de la liste des documents pour permettre la désérialisation en User
		     * (User n'a pas d'attribut "_id" */
		    ArrayList<Document> databaseUsers = 
		    		collection.find().projection(Projections.exclude("_id")).into(new ArrayList<Document>());
		    
		    ArrayList<User> list_users = new ArrayList<User>();
		    
		    for(Document i: databaseUsers) {
		    	String userInString = i.toJson();
		    	
		    	UserRessource userRessource = new UserRessource();
		    	User user = userRessource.getUser(mapper, userInString);
		    	list_users.add(user);
		    	
		    }
		    return list_users;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		} finally{
		    mongoClient.close();
		}

	}
}
