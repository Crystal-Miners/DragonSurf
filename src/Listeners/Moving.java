package Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import Main.Main;

public class Moving implements Listener {
	Main pl;
	public Moving(Main pl){
		this.pl = pl;
	}
	/*     */ 
	/*     */   @EventHandler
	/*     */   public void Move(PlayerMoveEvent event)
	/*     */   {
	/* 205 */     Player ep = event.getPlayer();
	/* 206 */     if (pl.getConfig().getBoolean("Player." + ep.getName() + ".Playing")) {
	/* 207 */       ep.setFoodLevel(20);
    Material m = event.getPlayer().getLocation().getBlock().getType();
    if (m == Material.STATIONARY_WATER || m == Material.WATER) {

    	/* 210 */         ep.setVelocity(ep.getVelocity().setY(10));
    }
	/*     */     }
	/*     */   }

}
