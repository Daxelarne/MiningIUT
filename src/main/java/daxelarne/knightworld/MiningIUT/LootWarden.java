package daxelarne.knightworld.MiningIUT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LootWarden implements Listener{
	private static Random r = new Random();
	private static int maxEntity = EntitySpawnable.values().length;


	@EventHandler
	public void onWardenDeath(EntityDamageByEntityEvent event) {

			if(event.getEntityType().equals(EntityType.WARDEN)
					&& event.getDamager().getType().equals(EntityType.PLAYER)
					&& (event.getEntity().isDead()  ||
							((LivingEntity)event.getEntity()).getHealth()<=0
							|| ((LivingEntity)event.getEntity()).getHealth()-event.getFinalDamage()<=0
							
							)
					) {
				
				if(r.nextInt(maxEntity)%2==0) {
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give "+event.getDamager().getName()+" 2500");
					
					Bukkit.getPlayer(event.getDamager().getName()).sendMessage(Lang.WARDEN_DROP.toString().replace("{1}","2 500 coins"));
				} else {
					ItemStack item = new ItemStack(Material.SPAWNER, 1);
					List<String> lore = new ArrayList<String>();
					
					EntitySpawnable e = EntitySpawnable.values()[r.nextInt(maxEntity)];
					
					@SuppressWarnings("deprecation")
					String loreString =  EntityType.fromName(e.getName()).toString();
					loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
					loreString = loreString+" Spawner";
					lore.add(loreString);
					
					ItemMeta meta = item.getItemMeta();
					meta.setLore(lore);
					item.setItemMeta(meta);
					
					event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), item);
					
					Bukkit.getPlayer(event.getDamager().getName()).sendMessage(Lang.WARDEN_DROP.toString().replace("{1}","Un spawner "+EntityType.fromName(e.getName()).toString()));
				}
				
				
				
				
		}
	}
	
	
	@EventHandler
	public void onCatalystActivate( EntitySpawnEvent event ) {
		if(event.getEntityType().equals(EntityType.WARDEN)) {
			int radius = 10;
			Location loc = event.getLocation();
			World world = loc.getWorld();
			
			for (int x = -radius; x < radius; x++) {
			    for (int y = -radius; y < radius; y++) {
			        for (int z = -radius; z < radius; z++) {
			            Block block = world.getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z);
			            if (block.getType() == Material.SCULK_SHRIEKER) {
			                block.setType(Material.SCULK_SENSOR);
			                return;
			            }
			        }
			    }
			}
		}
	}
	
}
