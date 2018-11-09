package fr.ub.m2gl;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRessource {
	public User getUser(ObjectMapper mapper, String jsonInString) {
		try {
			User user = mapper.readValue(jsonInString, User.class);
			return user;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Fichier json non trouve !");
			return null;
		}
	}
}
