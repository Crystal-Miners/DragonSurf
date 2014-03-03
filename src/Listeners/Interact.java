package Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Main.Main;

public class Interact implements Listener {
	
	Main plugin;
	public Interact(Main pl){
		this.plugin = pl;
	}

	/*     */ 
	@SuppressWarnings("deprecation")
	/*     */   @EventHandler
	/*     */   public void onInteract(PlayerInteractEvent event)
	/*     */   {
	/* 217 */     final Player ep = event.getPlayer();
	/* 218 */     if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && 
	/* 219 */       (event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) && 
	/* 220 */       (plugin.getConfig().getBoolean("Player." + ep.getName() + ".Playing")) && 
	/* 221 */       (ep.getLevel() > 1)) {
	/* 222 */       ep.closeInventory();
	/* 223 */       ItemStack item = ep.getItemInHand();
	/* 224 */       ItemMeta meta = item.getItemMeta();
	/* 225 */       meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
	/* 226 */       item.setItemMeta(meta);
	/* 227 */       ep.setLevel(ep.getLevel() - 2);
	/* 228 */       ep.closeInventory();
	/* 229 */       Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable()
	/*     */       {
	/*     */         public void run()
	/*     */         {
	/* 233 */           ep.closeInventory();
	/*     */         }
	/*     */       }
	/*     */       , 1L);
	/*     */     }
	/*     */   }

}
