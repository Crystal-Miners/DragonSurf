package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import Main.Main;

public class Death implements Listener {

	Main pl;
	public Death(Main pl){
		this.pl = pl;
	}
	/*     */ 
	@SuppressWarnings("deprecation")
	/*     */   @EventHandler
	/*     */   public void onPlayerDeath(PlayerDeathEvent event) {
	/* 173 */     CraftPlayer ep = (CraftPlayer) event.getEntity();
	CraftPlayer ek = (CraftPlayer) ep.getKiller();
	/* 174 */     if (pl.getConfig().getBoolean("Player." + ep.getName() + ".Playing")) {
	/* 175 */       event.setDroppedExp(0);
	/* 176 */       ep.setLevel(0);
	/* 177 */       event.setDeathMessage("§7[§bDragonSurf§7] §c" + ek.getName() + " §ahat §c" + ep.getName() + " §agekillt");
	/* 178 */       event.getDrops().clear();
	/* 179 */       int l = ep.getKiller().getLevel() + 1;
	/* 180 */       ep.getKiller().setLevel(l);
	/* 183 */       ep.getKiller().setHealth(ek.getHealth() + 10);
					ep.setHealth(20.0D);
	/* 248 */       int Arena = pl.getConfig().getInt("ActiveArena");
	/* 249 */       Location Spawn = new Location(Bukkit.getWorld(pl.getConfig().getString("Arena." + Arena + ".World")), pl.getConfig().getInt("Arena." + Arena + ".X"), pl.getConfig().getInt("Arena." + Arena + ".Y"), pl.getConfig().getInt("Arena." + Arena + ".Z"));
	/*     */ 
	/* 251 */       ep.getInventory().addItem(new ItemStack[] { new ItemStack(276) });
	/* 252 */       ep.getInventory().setHelmet(new ItemStack(302));
	/* 253 */       ep.getInventory().setChestplate(new ItemStack(303));
	/* 254 */       ep.getInventory().setLeggings(new ItemStack(304));
	/* 255 */       ep.getInventory().setBoots(new ItemStack(305));
	/* 256 */       ep.getInventory().addItem(new ItemStack[] { new ItemStack(261) });
	/* 257 */       ep.getInventory().addItem(new ItemStack[] { new ItemStack(262, 32) });
	ep.teleport(Spawn);
	/*     */     }
	/*     */   }

}
