package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import Main.Main;

public class ServerPing implements Listener{
	
	Main pl;
	
	public ServerPing(Main plugin){
		pl = plugin;
	}
	

	
	  @EventHandler()
	  public void onServerListPing(final ServerListPingEvent event) {
	    final String message = "&a" + pl.Active;
	    event.setMotd(message);
	  }
	
}
