package fr.ub.m2gl;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("/user")
public class Register {
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public User register(User user) {
		MongoClient mongoClient = new MongoClient();
		try {
			
		    MongoDatabase db = mongoClient.getDatabase("myBase");
		    MongoCollection<Document> collection = db.getCollection("myCollection");

		    ObjectMapper mapper = new ObjectMapper();
		    
		    String jsonString = mapper.writeValueAsString(user);
		    Document doc = Document.parse(jsonString);
		    collection.insertOne(doc);
		    
		    return user;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		} finally{
		    mongoClient.close();
		}

	}
}
