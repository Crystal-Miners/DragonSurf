package Main;

import org.bukkit.entity.Player;

public class ChatManager {
	
	String Prefix = "§7[§6DragonSurf§7] §a";

	public void sendMessage(Player p, String m){
		p.sendMessage(Prefix + m);
	}

	public void sendError(Player p, String m){
		p.sendMessage(Prefix + "§c" + m);
	}

	public void sendBroadcast(Player[] players, String m){
		for(Player p : players){
		p.sendMessage(Prefix + m);
		}
	}

	public void sendDragonCast(Player p, String m){
		p.sendMessage(Prefix + m);
	}

	public void sendDragonBroadcast(Player p, String m){
		p.sendMessage(Prefix + m);
	}
}
