package com.md_5.goinround;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Scanner implements Runnable {

    private int interval;
    private int currentPosition;
    private ArrayList<Player> playerList;
    private Player player;
    private Location playerLoc;
    private Player currentPlayer;
    private int taskId;
    private GoinRound plugin;

    public Scanner(Player playerArg, int intervalArg, ArrayList<Player> playerListArg, GoinRound plugin) {
    	this.plugin = plugin;
        player = playerArg;
        playerLoc = player.getLocation();
        interval = (intervalArg * 20);
        playerList = playerListArg;
        currentPosition = 0;
        taskId = 0;
    }

    @Override
    public void run() {
    	if(!player.isOnline())
    	{
    		plugin.removeAndDisableScanner(this,player);
    	}
       if(currentPosition < playerList.size())
       {
    	   currentPlayer = playerList.get(currentPosition); 
            if (currentPlayer.isOnline()) {
            	player.teleport(currentPlayer);
                player.sendMessage(ChatColor.GREEN + "You are now at " + currentPlayer.getName());
            }
            currentPosition = currentPosition + 1;
            return;
        }
       else
       {
    	   player.teleport(playerLoc);
    	   player.sendMessage(ChatColor.GOLD + "Your journey has ended and you have been returned to your original location");
    	   plugin.removeAndDisableScanner(this,player);
       }
    }
    public String getCurrentPlayerName()
    {
    	return currentPlayer.getName();
    }

	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId)
	{
		this.taskId = taskId;
	}

	public long getInterval() {
		return interval;
	}
	
}
