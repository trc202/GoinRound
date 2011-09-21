package com.md_5.goinround;

import java.util.Iterator;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GoinRoundPlayerListener extends PlayerListener {

	private GoinRound plugin;
	
	GoinRoundPlayerListener(GoinRound plugin)
	{
		this.plugin = plugin;
	}
	@Override
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Iterator<Scanner> i = plugin.journeys.values().iterator();
		while(i.hasNext())
		{
			Scanner s = i.next();
			if(!(s.isInPlayerList(event.getPlayer())))
			{
				s.addToUnseenPlayers(event.getPlayer());
			}
		}
		
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Iterator<Scanner> i = plugin.journeys.values().iterator();
		while(i.hasNext())
		{
			Scanner s = i.next();
			if(s.hasYetToBeSeen(event.getPlayer()))
			{
				s.removeFromUnseenPlayers(event.getPlayer());
			}
		}
	}
}
