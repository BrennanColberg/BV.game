package bv.sportsGame.game.entities.classes;

import java.awt.Color;

public enum Team {
	
	RIGHT(Color.red), LEFT(Color.blue);
	
	public Color color;
	private Team (Color color) {
		this.color = color;
	}
	
	public Team getTeam(int index) {
		return Team.values()[index];
	}
}
