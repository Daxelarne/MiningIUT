package daxelarne.knightworld.MiningIUT;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class LootWarden implements Listener{
	/*private static Random r = new Random();
	private static int maxEntity = EntitySpawnable.values().length;*/
	
	public void onWardenDeath(EntityDamageByEntityEvent event) {

		if(event.getEntityType().equals(EntityType.WARDEN)
				&& event.getDamager().getType().equals(EntityType.PLAYER)
				&& (event.getEntity().isDead()  ||
						((LivingEntity)event.getEntity()).getHealth()<=0
						|| ((LivingEntity)event.getEntity()).getHealth()-event.getFinalDamage()<=0
						
						)
				) {
			
			Location eventLocation = event.getEntity().getLocation();
			
			ExperienceOrb orb = (ExperienceOrb)  eventLocation.getWorld().spawn(eventLocation.add(0.5, 0.5, 0.5), ExperienceOrb.class);
            orb.setExperience(100);
            
            ExperienceOrb orb1 = (ExperienceOrb)  eventLocation.getWorld().spawn(eventLocation.add(0.5, 0.5, 0.5), ExperienceOrb.class);
            orb1.setExperience(100);
            
            ExperienceOrb orb2 = (ExperienceOrb)  eventLocation.getWorld().spawn(eventLocation.add(0.5, 0.5, 0.5), ExperienceOrb.class);
            orb2.setExperience(100);
            
            ExperienceOrb orb3 = (ExperienceOrb)  eventLocation.getWorld().spawn(eventLocation.add(0.5, 0.5, 0.5), ExperienceOrb.class);
            orb3.setExperience(100);
			
		}
	}

	
	/*@SuppressWarnings("deprecation")
	@EventHandler
	public void onWardenDeath(EntityDamageByEntityEvent event) {

			if(event.getEntityType().equals(EntityType.WARDEN)
					&& event.getDamager().getType().equals(EntityType.PLAYER)
					&& (event.getEntity().isDead()  ||
							((LivingEntity)event.getEntity()).getHealth()<=0
							|| ((LivingEntity)event.getEntity()).getHealth()-event.getFinalDamage()<=0
							
							)
					) {
				
				if(r.nextInt(maxEntity)==20){
					ItemStack item = new ItemStack(Material.SPAWNER, 1);
					List<String> lore = new ArrayList<String>();
					
					EntitySpawnable e = EntitySpawnable.values()[r.nextInt(maxEntity)];
					
					String loreString =  EntityType.fromName(e.getName()).toString();
					loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
					loreString = loreString+" Spawner";
					lore.add(loreString);
					
					ItemMeta meta = item.getItemMeta();
					meta.setLore(lore);
					item.setItemMeta(meta);
					
					event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), item);
					
					Bukkit.getPlayer(event.getDamager().getName()).sendMessage(Lang.WARDEN_DROP.toString().replace("{1}","Un spawner "+EntityType.fromName(e.getName()).toString()));
				} else {
					if(r.nextInt(maxEntity)%2==0) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give "+event.getDamager().getName()+" 50");
						
						Bukkit.getPlayer(event.getDamager().getName()).sendMessage(Lang.WARDEN_DROP.toString().replace("{1}","50 coins"));
					} else {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give "+event.getDamager().getName()+" 20");
						
						Bukkit.getPlayer(event.getDamager().getName()).sendMessage(Lang.WARDEN_DROP.toString().replace("{1}","20 coins"));
					}
				}
				
				
				
				
				
				
		}
	}*/
	
	
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
