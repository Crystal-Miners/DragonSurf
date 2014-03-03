package Main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import Main.Main;

public class CMS {
	FileConfiguration cfg;
	File file;
	Main plugin;
	
	public CMS(Main plugin){
		this.plugin = plugin;
		file = new File(plugin.getDataFolder(), "Arenas.yml");
		load();
		saveConf();
		
	}
	
	public void load() { 
		if (!file.exists()) {
			try {
					file.createNewFile();
		} catch (IOException ex) {
		}
		}
		this.file = new File(plugin.getDataFolder(), "Arenas.yml");
		this.cfg = YamlConfiguration.loadConfiguration(file);
		}
		
	
	
 public void saveConf() {
	 try {
		 this.cfg.save(file);
		   	} catch (IOException ex) {
		   	}
		 }
		  
		  
		  
		 
 public void addChannel(String name, Location loc) {
	 String c = "Arenas.";
	 cfg.set(c + name + ".World", loc.getWorld());
	 cfg.set(c + name + ".X", loc.getX());
	 cfg.set(c + name + ".Y", loc.getY());
	 cfg.set(c + name + ".Z", loc.getZ());
	saveConf();
} 
 
public String getArena(String name){
	load();
	 String c = "Arenas.";
	
	return (String) cfg.get(c + name + ".Name");
}

public Object getChannelData(String Channel, Object ChannelData){
	load();
	 String c = "Arenas.";
	
	return cfg.get(c + Channel + "." + ChannelData );
}
public Integer getArenaInt(String Channel, Object ChannelData){
	load();
	 String c = "Arenas.";
	
	return (Integer) cfg.get(c + Channel + "." + ChannelData );
}

public boolean getArenaBoolean(String Channel, Object ChannelData){
	load();
	 String c = "Arenas.";
	
	return cfg.getBoolean(c + Channel + "." + ChannelData );
}

public void DeleteArena(String name){
	load();
cfg.set("Arenas." + name, null);
saveConf();
}

public void SetChannelData(String Channel, Object ChannelData, String Value){
	load();
	String c = "Arenas.";
	if(cfg.get(c + Channel + "." + ChannelData) instanceof Boolean){
		cfg.set(c + Channel + "." + ChannelData, Boolean.valueOf(Value));
		saveConf();
		return;
	}
	if(cfg.get(c + Channel + "." + ChannelData) instanceof Integer){
		cfg.set(c + Channel + "." + ChannelData, Integer.valueOf(Value));
		saveConf();
		return;
	}
	cfg.set(c + Channel + "." + ChannelData, Value);
	saveConf();
}

public Set<String> channelList(){
	Set<String> list = cfg.getConfigurationSection("Arenas").getKeys(false);
	return list;
}
}
