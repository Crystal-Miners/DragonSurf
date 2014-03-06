package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {
	
	Main pl;
	public PlayerManager(Main plugin){
		pl = plugin;
	}

	public void switchKit(Player p){
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[4]);
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) pl.cms.getArenaData(pl.Active , "Kit");
		for(String m : messages){
			String[] split = m.split(",");
			@SuppressWarnings("deprecation")
			ItemStack i = new ItemStack(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
			if(i.getType() == Material.CHAINMAIL_HELMET || i.getType() == Material.LEATHER_HELMET || i.getType() == Material.IRON_HELMET || i.getType() == Material.GOLD_HELMET || i.getType() == Material.DIAMOND_HELMET){
				p.getInventory().setHelmet(i);
			}else
			if(i.getType() == Material.CHAINMAIL_BOOTS || i.getType() == Material.LEATHER_BOOTS || i.getType() == Material.IRON_BOOTS || i.getType() == Material.GOLD_BOOTS || i.getType() == Material.DIAMOND_BOOTS){
				p.getInventory().setBoots(i);
			}else
			if(i.getType() == Material.CHAINMAIL_LEGGINGS || i.getType() == Material.LEATHER_LEGGINGS || i.getType() == Material.IRON_LEGGINGS || i.getType() == Material.GOLD_LEGGINGS || i.getType() == Material.DIAMOND_LEGGINGS){
				p.getInventory().setLeggings(i);	
			}else
			if(i.getType() == Material.CHAINMAIL_CHESTPLATE || i.getType() == Material.LEATHER_CHESTPLATE || i.getType() == Material.IRON_CHESTPLATE || i.getType() == Material.GOLD_CHESTPLATE || i.getType() == Material.DIAMOND_CHESTPLATE){
				p.getInventory().setChestplate(i);	
			}else{
				p.getInventory().addItem(i);
			}
		}
	}
	
	public void switchArena(){
		Set<String> arenas1 = pl.cms.ArenaList();
		ArrayList<String> arenas = new ArrayList<String>();
		for(String a : arenas1){
			arenas.add(a);
		}
		int max = arenas.size();
		String Arena = null;
		if(max <= pl.current){
			pl.current = 1;
			Arena = arenas.get(pl.current -1);
			pl.Active = Arena;
			for(Player p : Bukkit.getOnlinePlayers()){
			p.teleport(pl.cms.getArenaLocation(pl.Active));
			switchKit(p);
			}
			return;
		}
		pl.current ++;
		Arena = arenas.get(pl.current -1);
		pl.Active = Arena;
		for(Player p : Bukkit.getOnlinePlayers()){
		p.teleport(pl.cms.getArenaLocation(pl.Active));
		switchKit(p);
		}
		
	}
	
}
