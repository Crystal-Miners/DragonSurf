package Main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.Death;
import Listeners.Interact;
import Listeners.Moving;
import Listeners.ServerPing;

public class Main extends JavaPlugin{
	public Main pl;
	/*     */ 
	/*     */   public int getActiveArena() {
	/* 276 */     int Active = getConfig().getInt("ActiveArena");
	/* 277 */     return Active;
	/*     */   }

	/*     */ 
	/*     */   @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	/*     */   {
	/*  85 */     Player p = (Player)sender;
	/*  86 */     if (command.getName().equalsIgnoreCase("surf")) {
	/*  87 */       if (args.length == 0) {
	/*  88 */         p.sendMessage("§6Surf Hilfe: -------- \n/surf setarenaspawn <arena> - Setzt den Arenaspawn\n/surf join <arena> - Trete einer Arena bei!\n/surf leave - Verlasse deine Arena!");
	return true;
	/*     */       }
	/*     */ 
	/*  93 */       if (args.length == 1) {
	/*  94 */         if (args[0].equalsIgnoreCase("setarenaspawn")) {
	/*  95 */           int Count = getConfig().getInt("ArenaCounter") + 1;
	/*  96 */           getConfig().set("Arena." + Count + ".X", Integer.valueOf(p.getLocation().getBlockX()));
	/*  97 */           getConfig().set("Arena." + Count + ".Y", Integer.valueOf(p.getLocation().getBlockY()));
	/*  98 */           getConfig().set("Arena." + Count + ".Z", Integer.valueOf(p.getLocation().getBlockZ()));
	/*  99 */           getConfig().set("Arena." + Count + ".World", p.getWorld().getName());
	/* 100 */           getConfig().set("ArenaCounter", Integer.valueOf(getConfig().getInt("ArenaCounter") + 1));
	/* 101 */           saveConfig();
	/* 102 */           String msg = getConfig().getString("Messages.Arena.Setspawn").replace("{Arena}", String.valueOf(Count)).replace("&", "§");
	/* 103 */           p.sendMessage(msg);
	/*     */         }
	/*     */ 
	/* 106 */         if ((args[0].equalsIgnoreCase("leave")) && 
	/* 107 */           (getConfig().getBoolean("Player." + p.getName() + ".Playing"))) {
	/* 108 */           p.performCommand("spawn");
	/* 109 */           int XP = getConfig().getInt("Player." + p.getName() + "XP");
	/* 110 */           p.setLevel(XP);
	/* 111 */           getConfig().set("Player." + p.getName() + ".Playing", Boolean.valueOf(false));
	/* 112 */           p.sendMessage("§6Du hast die Arena verlassen!");
	/* 113 */           p.getInventory().clear();
	/*     */         }
	/*     */ 
	/*     */       }
	/*     */ 
	/* 120 */       if (args[0].equalsIgnoreCase("join"))
	/*     */       {
	/* 122 */         if (getConfig().getBoolean("Player." + p.getName() + ".Playing"))
	/*     */         {
	/* 124 */           String msg = getConfig().getString("Messages.Arena.already_playing").replace("&", "§").replace("{Player}", p.getName());
	/* 125 */           p.sendMessage(msg);
	/*     */         }
	/* 128 */         else if (!getConfig().getBoolean("Player." + p.getName() + ".Playing")) {
	/* 129 */           getConfig().set("Player." + p.getName() + "XP", Integer.valueOf(p.getLevel()));
	/*     */           try
	/*     */           {
	/* 133 */             p.setLevel(0);
	/* 134 */             int Arena = getConfig().getInt("ActiveArena");
	/*     */ 
	/* 136 */             Location Spawn = new Location(Bukkit.getWorld(getConfig().getString("Arena." + Arena + ".World")), getConfig().getInt("Arena." + Arena + ".X"), getConfig().getInt("Arena." + Arena + ".Y"), getConfig().getInt("Arena." + Arena + ".Z"));
	/* 137 */             p.teleport(Spawn);
	/* 138 */             p.setGameMode(GameMode.SURVIVAL);
	/* 139 */             p.getInventory().clear();
	/* 140 */             p.getInventory().addItem(new ItemStack[] { new ItemStack(276) });
	/* 141 */             p.getInventory().setHelmet(new ItemStack(302));
	/* 142 */             p.getInventory().setChestplate(new ItemStack(303));
	/* 143 */             p.getInventory().setLeggings(new ItemStack(304));
	/* 144 */             p.getInventory().setBoots(new ItemStack(305));
	/* 145 */             p.getInventory().addItem(new ItemStack[] { new ItemStack(261) });
	/* 146 */             p.getInventory().addItem(new ItemStack[] { new ItemStack(262, 32) });
	/*     */ 
	/* 148 */             String msg = getConfig().getString("Messages.Arena.Join.Player").replace("{Arena}", String.valueOf(Arena)).replace("&", "§");
	/* 149 */             p.sendMessage(msg);
	/* 150 */             getConfig().set("Player." + p.getName() + ".Playing", Boolean.valueOf(true));
	/* 151 */             getConfig().set("Player." + p.getName() + ".Arena", "Arena");
	/* 152 */             for (Player pp : Bukkit.getOnlinePlayers())
	/* 153 */               if (getConfig().getString("Player." + pp.getName() + ".Arena").equalsIgnoreCase("Arena")) {
	/* 154 */                 String msg2 = getConfig().getString("Messages.Arena.Join.Broadcast").replace("&", "§").replace("{Player}", p.getName());
	/* 155 */                 pp.sendMessage(msg2);
	/*     */               }
	saveConfig();
	/*     */           }
	/*     */           catch (Exception e) {
	/* 159 */             p.sendMessage("§cError.");
	/*     */           }
	/*     */ 
	/*     */         }
	/*     */ 
	/*     */       }
	/*     */ 
	/*     */     }
	/*     */ 
	/* 168 */     return true;
	/*     */   }

	/*     */ 
	/*     */   public void onDisable()
	/*     */   {
	/*  27 */     for (Player pp : Bukkit.getOnlinePlayers())
	/*  28 */       pp.performCommand("surf leave");
	/*     */   }

	/*     */ 
	/*     */   public void onEnable()
	/*     */   {
		Bukkit.getPluginManager().registerEvents(new Moving(this), this);
		Bukkit.getPluginManager().registerEvents(new Death(this), this);
		Bukkit.getPluginManager().registerEvents(new Interact(this), this);
		Bukkit.getPluginManager().registerEvents(new ServerPing(), this);
	/*  36 */     getConfig().addDefault("Prefix", "&7[&cSurf&7] ");
	/*  37 */     getConfig().addDefault("Max_Players_per_Arena", Integer.valueOf(20));
	/*  38 */     getConfig().addDefault("Messages.Arena.Join.Player", "&6Betrete Arena {Arena}");
	/*  39 */     getConfig().addDefault("Messages.Arena.Join.Broadcast", "&6{Player} hat die Arena betreten!");
	/*  40 */     getConfig().addDefault("Messages.Arena.Create", "&5Arena {Arena} wurde erstellt!");
	/*  41 */     getConfig().addDefault("Messages.Arena.delete", "&5Arena {Arena} wurde entfernt!");
	/*  42 */     getConfig().addDefault("Messages.Arena.leave", "&6Arena {Arena} verlassen!");
	/*  43 */     getConfig().addDefault("Messages.Arena.Setspawn", "&6Spawn für Arena {Arena} gesetzt!");
	/*  44 */     getConfig().addDefault("Messages.Arena.Lobbyspawn", "&6Lobbyspawn für Arena {Arena} gesetzt!");
	/*  45 */     getConfig().addDefault("Messages.Arena.joinsign", "&6Joinsign für Arena {Arena} gesetzt!");
	/*  46 */     getConfig().addDefault("Messages.Arena.already_playing", "&6Du bist schon in einer Arena! /surf leave");
	/*  47 */     getConfig().addDefault("Messages.Arena.Level_Up", "&6{Player} ist nun Level &c{Level}&6!");
	/*  48 */     getConfig().addDefault("Items.Level.5", Integer.valueOf(311));
	/*  49 */     getConfig().addDefault("Starterkit", "-");
	/*  50 */     getConfig().getIntegerList("Starterkit").add(Integer.valueOf(276));
	/*     */ 
	/*  52 */     getConfig().options().copyDefaults(true);
	/*  53 */     saveConfig();
	/*  54 */     getConfig().set("ActiveArena", Integer.valueOf(1));
	/*     */ 
	/*  56 */     Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
	/*     */     {
	/*     */       public void run()
	/*     */       {
		try{
	/*  61 */         if (pl.getConfig().getInt("ActiveArena") <= pl.getConfig().getInt("ArenaCounter") - 1) {
	/*  62 */           pl.getConfig().set("ActiveArena", Integer.valueOf(pl.getConfig().getInt("ActiveArena") + 1));
	/*  63 */           for (Player pp : Bukkit.getOnlinePlayers()) {
	/*  64 */             if (pl.getConfig().getBoolean("Player." + pp.getName() + ".Playing")) {
	/*  65 */               int Arena = pl.getConfig().getInt("ActiveArena");
	/*     */ 
	/*  67 */               Location Spawn = new Location(Bukkit.getWorld(pl.getConfig().getString("Arena." + Arena + ".World")), pl.getConfig().getInt("Arena." + Arena + ".X"), pl.getConfig().getInt("Arena." + Arena + ".Y"), pl.getConfig().getInt("Arena." + Arena + ".Z"));
	/*  68 */               pp.teleport(Spawn);
	/*     */             }
	/*     */ 
	/*     */           }
	/*     */ 
	/*     */         }
	/*  74 */         else if (pl.getConfig().getInt("ActiveArena") + 1 == pl.getConfig().getInt("ArenaCounter")) {
	/*  75 */           pl.getConfig().set("ActiveArena", Integer.valueOf(1));
	/*     */         }
		}catch (Exception e){
			
		}
	/*     */       }
	/*     */     }
	/*     */     , 20L, 200L);
	/*     */   }

	/*     */ 
	/*     */   public void setActiveArena(int Arena)
	/*     */   {
	/* 272 */     getConfig().set("ActiveArena", Integer.valueOf(Arena));
	/*     */   }

}
