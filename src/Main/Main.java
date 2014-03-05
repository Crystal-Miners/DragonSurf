package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.Death;
import Listeners.Interact;
import Listeners.Moving;
import Listeners.ServerPing;

public class Main extends JavaPlugin {
	public int current = 1;
	public CMS cms;
	ChatManager cm = new ChatManager();
	public Main pl;
	ArrayList<Player> players = new ArrayList<Player>();
	public String Active = null;

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (command.getName().equalsIgnoreCase("surf")) {
			if (args.length == 0) {
				cm.sendMessage(p, "§6>>>>>>> §cDragonSurf §6<<<<<<<");
				cm.sendMessage(p, "Developed by §cDragonSephHD");
				cm.sendMessage(p, "§6>>>>>>> §cDragonSurf §6<<<<<<<");
			}

			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create")) {
					cms.addArena(args[1], p.getLocation());
					cm.sendMessage(p, "Arena §c" + args[1] + " §aerfolgreich erstellt!");
				}

			}
				if (args[0].equalsIgnoreCase("leave")) {
					cm.sendMessage(p, "Du hast Surf verlassen");
					p.getInventory().clear();
				}

			if (args[0].equalsIgnoreCase("join")) {
				players.add(p);
				p.teleport(cms.getArenaLocation(Active));
				cm.sendMessage(p, "Du hast Surf betreten");
				@SuppressWarnings("unchecked")
				List<String> messages = (List<String>) cms.getArenaData(Active , "Kit");
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

		}

		return true;
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		cms = new CMS(this);
		Bukkit.getPluginManager().registerEvents(new Moving(this), this);
		Bukkit.getPluginManager().registerEvents(new Death(this), this);
		Bukkit.getPluginManager().registerEvents(new Interact(this), this);
		Bukkit.getPluginManager().registerEvents(new ServerPing(this), this);
		Active = cms.getDefaultArena();
	}

}
