package daxelarne.knightworld.MiningIUT;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class limitEntity implements Listener {
	
	@EventHandler
	public void onPlaceEntity(EntityPlaceEvent event) {
		if(event.getEntity().getLocation().getChunk().getEntities().length>=Main.maxEntity) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(Lang.ERROR_PREFIX.toString() + Lang.MAX_ENTITY.toString());
			
		}
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {
		if(event.getLocation().getChunk().getEntities().length>=Main.maxEntity && event.getEntity().getType()!=EntityType.PLAYER) {
			event.setCancelled(true);
			Bukkit.getLogger().log(Level.WARNING, "Entite non spawn au "+event.getLocation().getX()+","+event.getLocation().getY()+","+event.getLocation().getZ()+" de type "+event.getEntity().getType().toString());
		}
	}

}
