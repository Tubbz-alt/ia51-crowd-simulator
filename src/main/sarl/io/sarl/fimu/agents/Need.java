package io.sarl.fimu.agents;

public enum Need {
	WATER("to drink"),
	FOOD("to eat"),
	TOILETS("to pee"),
	CONCERT("to attend a concert"),
	TALK("to have a nice conversation with other people");
	
	String sentenceFragment = "";
	
	Need(String sentenceFragment) {
		this.sentenceFragment = sentenceFragment;
	}
	
	public String toString() {
		return sentenceFragment;
	}
}
