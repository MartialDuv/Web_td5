package fr.ub.m2gl;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

@Path("/user")
public class Delete {
	
	@DELETE
	@Produces("application/json")
	@Consumes("application/json")
	public User delete(User user) {
		MongoClient mongoClient = new MongoClient();
		try {
			
		    MongoDatabase db = mongoClient.getDatabase("myBase");
		    MongoCollection<Document> collection = db.getCollection("myCollection");

		    ObjectMapper mapper = new ObjectMapper();
		    
		    
		    ArrayList<Document> databaseUsers = 
		    		collection.find().projection(Projections.exclude("_id")).into(new ArrayList<Document>());
		    ArrayList<User> list_users = new ArrayList<User>();
		    
		    for(Document i: databaseUsers) {
		    	String userInString = i.toJson();
		    	UserRessource userRessource = new UserRessource();
		    	list_users.add(userRessource.getUser(mapper, userInString));
		    }
		    int cpt=0;
		    for(User j: list_users) {
		    	if(j.getFirstname().contains(user.getFirstname()) && j.getLastname().contains(user.getLastname())) {
		    		cpt++;
		    	}
		    }
		    
		    if(cpt == 0) {
		    	throw new Exception("User not found");
		    }
		    
		    String jsonString = mapper.writeValueAsString(user);
		    Document doc = Document.parse(jsonString);
		    collection.deleteOne(doc);
		    
		    return user;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		} finally{
		    mongoClient.close();
		}

	}
}
