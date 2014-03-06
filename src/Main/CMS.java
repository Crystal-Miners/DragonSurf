package Main;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
		InitalizeConfig();
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
		  
 public String getDefaultArena(){
	 return cfg.getString("DefaultArena");
 }
 public void InitalizeConfig(){
	 if(cfg.getString("Prefix") == null){
		 cfg.set("Prefix", "");
		 cfg.set("DefaultArena", "None");
	 }
 }
		 
 public void addArena(String name, Location loc) {
	 String c = "Arenas.";
	 cfg.set(c + name + ".Name", name);
	 cfg.set(c + name + ".World", loc.getWorld().getName());
	 cfg.set(c + name + ".X", loc.getX());
	 cfg.set(c + name + ".Y", loc.getY());
	 cfg.set(c + name + ".Z", loc.getZ());
	 cfg.set(c + name + ".Kit", new String[] {
			 "302,1",
			 "303,1",
			 "304,1",
			 "305,1",
			 "267,1",
			 "261,1",
			 "262,64",
	 });
	saveConf();
} 
 
public String getArena(String name){
	load();
	 String c = "Arenas.";
	
	return (String) cfg.get(c + name + ".Name");
}

public Object getArenaData(String Arena, Object ArenaData){
	load();
	 String c = "Arenas.";
	
	return cfg.get(c + Arena + "." + ArenaData );
}
public Integer getArenaInt(String Arena, Object ArenaData){
	load();
	 String c = "Arenas.";
	
	return (Integer) cfg.get(c + Arena + "." + ArenaData );
}

public boolean getArenaBoolean(String Arena, Object ArenaData){
	load();
	 String c = "Arenas.";
	
	return cfg.getBoolean(c + Arena + "." + ArenaData );
}

public void DeleteArena(String name){
	load();
cfg.set("Arenas." + name, null);
saveConf();
}

public void SetArenaData(String Arena, Object ArenaData, String Value){
	load();
	String c = "Arenas.";
	if(cfg.get(c + Arena + "." + ArenaData) instanceof Boolean){
		cfg.set(c + Arena + "." + ArenaData, Boolean.valueOf(Value));
		saveConf();
		return;
	}
	if(cfg.get(c + Arena + "." + ArenaData) instanceof Integer){
		cfg.set(c + Arena + "." + ArenaData, Integer.valueOf(Value));
		saveConf();
		return;
	}
	cfg.set(c + Arena + "." + ArenaData, Value);
	saveConf();
}

public Set<String> ArenaList(){
	Set<String> list = cfg.getConfigurationSection("Arenas").getKeys(false);
	return list;
}

public Location getArenaLocation(String Arena){	 
String c = "Arenas.";
	World w = Bukkit.getWorld(cfg.getString(c + Arena + ".World"));
	double x = cfg.getDouble(c + Arena + ".X");
	double y = cfg.getDouble(c + Arena + ".Y");
	double z = cfg.getDouble(c + Arena + ".Z");
	Location loc = new Location(w, x, y, z);
	return loc;
	}
}
