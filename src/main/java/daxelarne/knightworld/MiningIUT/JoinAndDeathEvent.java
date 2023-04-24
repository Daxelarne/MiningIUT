package daxelarne.knightworld.MiningIUT;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinAndDeathEvent implements Listener{

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore()) {
			player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Bukkit.getServer().getLogger().log(Level.WARNING, "[MORT] "+event.getEntity().getName()
				+" est mort dans \"" +event.getEntity().getLocation().getWorld().getName()
				+"\" au " + event.getEntity().getLocation().getBlockX()
				+ ", " + event.getEntity().getLocation().getBlockY()
				+ ", " + event.getEntity().getLocation().getBlockZ()
				+ " avec " + Bukkit.getPlayer(event.getEntity().getName()).getLevel() +" level"
				);
		
	}
}
