package Listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import Main.Main;

public class Death implements Listener {

	Main pl;
	public Death(Main pl){
		this.pl = pl;
	}
	/*     */ 
	/*     */   @EventHandler
	/*     */   public void onPlayerDeath(PlayerDeathEvent event) {
	/* 173 */     CraftPlayer ep = (CraftPlayer) event.getEntity();
	/* 175 */       event.setDroppedExp(0);
	/* 176 */       ep.setLevel(0);
	/* 178 */       event.getDrops().clear();
					ep.setHealth(20.0D);
					if(ep.getKiller() instanceof CraftPlayer){
						CraftPlayer ek = (CraftPlayer) ep.getKiller();
						/* 179 */       int l = ep.getKiller().getLevel() + 1;
						/* 180 */       ek.setLevel(l);
						/* 183 */       ek.setHealth(ek.getHealth() + 10);
					}
	/*     */   }
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		e.setRespawnLocation(pl.cms.getArenaLocation(pl.Active));
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) pl.cms.getArenaData(pl.Active , "Kit");
		for(String m : messages){
			String[] split = m.split(",");
			@SuppressWarnings("deprecation")
			ItemStack i = new ItemStack(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
			if(i.getType() == Material.CHAINMAIL_HELMET || i.getType() == Material.LEATHER_HELMET || i.getType() == Material.IRON_HELMET || i.getType() == Material.GOLD_HELMET || i.getType() == Material.DIAMOND_HELMET){
				e.getPlayer().getInventory().setHelmet(i);
			}
			if(i.getType() == Material.CHAINMAIL_BOOTS || i.getType() == Material.LEATHER_BOOTS || i.getType() == Material.IRON_BOOTS || i.getType() == Material.GOLD_BOOTS || i.getType() == Material.DIAMOND_BOOTS){
				e.getPlayer().getInventory().setBoots(i);
			}
			if(i.getType() == Material.CHAINMAIL_LEGGINGS || i.getType() == Material.LEATHER_LEGGINGS || i.getType() == Material.IRON_LEGGINGS || i.getType() == Material.GOLD_LEGGINGS || i.getType() == Material.DIAMOND_LEGGINGS){
				e.getPlayer().getInventory().setLeggings(i);	
			}
			if(i.getType() == Material.CHAINMAIL_CHESTPLATE || i.getType() == Material.LEATHER_CHESTPLATE || i.getType() == Material.IRON_CHESTPLATE || i.getType() == Material.GOLD_CHESTPLATE || i.getType() == Material.DIAMOND_CHESTPLATE){
				e.getPlayer().getInventory().setChestplate(i);	
				return;
			}
			e.getPlayer().getInventory().addItem(i);
		}
	}

}
