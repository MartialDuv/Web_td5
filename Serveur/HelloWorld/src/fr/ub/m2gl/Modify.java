package fr.ub.m2gl;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

@Path("/user")
public class Modify {
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public User modify(String users) throws JSONException {
		JSONObject jsonObject = new JSONObject(users);
		User oldUser = new User(jsonObject.getString("Firstname"), jsonObject.getString("Lastname"));
		User newUser = new User(jsonObject.getString("newFirstname"), jsonObject.getString("newLastname"));
		
		MongoClient mongoClient = new MongoClient();
		try {
			
		    MongoDatabase db = mongoClient.getDatabase("myBase");
		    MongoCollection<Document> collection = db.getCollection("myCollection");

		    ObjectMapper mapper = new ObjectMapper();
		    
		    String oldJsonString = mapper.writeValueAsString(oldUser);
		    String newJsonString = mapper.writeValueAsString(newUser);
		    
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
		    	if(j.getFirstname().contains(oldUser.getFirstname()) && j.getLastname().contains(oldUser.getLastname())) {
		    		cpt++;
		    	}
		    }
		    
		    if(cpt == 0) {
		    	throw new Exception("User not found");
		    }
		    
		    Document oldDoc = Document.parse(oldJsonString);
		    Document newDoc = Document.parse(newJsonString);
		    collection.findOneAndReplace(oldDoc, newDoc);
		    
		    return newUser;
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		} finally{
		    mongoClient.close();
		}
	}
}
