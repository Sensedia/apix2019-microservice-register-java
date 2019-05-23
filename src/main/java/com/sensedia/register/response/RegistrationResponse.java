package com.sensedia.register.response;

public class RegistrationResponse {
	
	private int score;
	
	public RegistrationResponse() {
		// empty constructor
	}
	
	public RegistrationResponse(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

}
