package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int minLimit = 2, maxLimit = 10;
		System.out.println("Enter the number of players");
		int players = sc.nextInt();
		
		if(players>=minLimit && players<=maxLimit)
		{
			List<String> playersNameList = new ArrayList<>();
			for(int i=0;i<players;i++)
			{
				System.out.println("Enter name of player "+(i+1));
				String name = sc.next();
				playersNameList.add(name);
			}
			Game game = new Game();
			game.createPlayers(playersNameList);
			game.startGame();
			
		}
		else System.out.println("Players can played between "+minLimit+" to "+maxLimit);
		
	}

}
