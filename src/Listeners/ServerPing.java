package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing implements Listener{
	

	
	  @EventHandler()
	  public void onServerListPing(final ServerListPingEvent event) {
	    final String message = "&aNature";
	    event.setMotd(message);
	  }
	
}
