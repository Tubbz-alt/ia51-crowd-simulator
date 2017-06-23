package io.sarl.fimu.agents;

import java.util.HashMap;
import java.lang.Math;

/** 
 * @author deqyra
 * 
 * Class to represent the music taste of an agent, or the "musical orientation" of a scene.
 * Genres are associated to a 'like' value (i.e. 10 = love, -10 = hate).
 * 
 * A MusicTaste is an association of all genres which results in a vector, or a point.
 * The Euclidean distance between points can then be calculated to approximate a "musical compatibility".
 * Small distance = very compatible, great distance = not compatible at all.
 * 
 * Used to determine which scene an agent might want to go to.
 */

class MusicTaste {
	private HashMap<Genre, Integer> musicVectors;
	
	/**
	 * Constructors
	 * Set all vectors to a value (default: 0)
	 */
	MusicTaste() {
		for (Genre g : Genre.values())
		{
			musicVectors.put(g, 0);
		}
	}

	MusicTaste(int n) {
		for (Genre g : Genre.values())
		{
			musicVectors.put(g, n);
		}
	}
	
	/**
	 * Get vector length for Genre g
	 */
	public int get(Genre g) {
		return musicVectors.get(g);
	}
	
	/**
	 * Set vector length at i for Genre g
	 */
	public void set(Genre g, int i) {
		musicVectors.put(g, i);
	}
	
	/**
	 * Compute Euclidean distance between this and another music taste
	 * Music affinity is inversely proportional to distance.
	 */
	public double distance(MusicTaste other) {
		double res = 0;
		for (Genre g : Genre.values())
		{
			double dist = this.get(g) - other.get(g);
			res += Math.pow(dist, 2);
		}
		
		res = Math.sqrt(res);
		return res;
	}
}
