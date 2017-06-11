package framework.agent;

public enum Genre {
	ROCK("rock"),
	JAZZ("jazz"),
	METAL("Mmtal"),
	POP("pop"),
	DUB("dub"),
	ELECTRO("electro"),
	BLUES("blues"),
	TECHNO("techno");
	
	private String name = "";
	
	Genre(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
