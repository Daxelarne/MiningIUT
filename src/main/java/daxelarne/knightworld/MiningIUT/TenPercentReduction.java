package daxelarne.knightworld.MiningIUT;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class TenPercentReduction implements Listener{
	private static Random r = new Random();
	
	
	/**ASSASSIN
	 * 10% de loot sur les monstres
	 * @param event
	 */
	@EventHandler
	public void onKillMob(EntityDeathEvent event) {
		if(!event.getEntityType().equals(EntityType.PLAYER)
				&& ((LivingEntity) event.getEntity()).getKiller().getType().equals(EntityType.PLAYER)
				&& Bukkit.getPlayer(((LivingEntity) event.getEntity()).getKiller().getName()).hasPermission("group.assassin")
				) {
			
			
			if(r.nextInt(10)==0) {
				Location eventLocation = event.getEntity().getLocation();
				
				for(ItemStack is : event.getDrops()) {
					eventLocation.getWorld().dropItem(eventLocation.add(0, 0.5, 0), is);
				}	
			}
		}
	}

	/**ARCHITECTE
	 * 10% de consomation de moins des blocks (hors minerais, spawner et shulker)
	 * @param event
	 */
	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent event) {
			Player p = event.getPlayer();
			
			if(p.hasPermission("group.architecte") ) {
				Material m = event.getBlock().getType();
				
				//Shulk
				if( !m.equals(Material.SHULKER_BOX)
						&& !m.equals(Material.BLACK_SHULKER_BOX)
						&& !m.equals(Material.GRAY_SHULKER_BOX)
						&& !m.equals(Material.LIGHT_GRAY_SHULKER_BOX)
						&& !m.equals(Material.WHITE_SHULKER_BOX)
						&& !m.equals(Material.BLUE_SHULKER_BOX)
						&& !m.equals(Material.LIGHT_BLUE_SHULKER_BOX)
						&& !m.equals(Material.CYAN_SHULKER_BOX)
						&& !m.equals(Material.PURPLE_SHULKER_BOX)
						&& !m.equals(Material.MAGENTA_SHULKER_BOX)
						&& !m.equals(Material.PINK_SHULKER_BOX)
						&& !m.equals(Material.RED_SHULKER_BOX)
						&& !m.equals(Material.ORANGE_SHULKER_BOX)
						&& !m.equals(Material.YELLOW_SHULKER_BOX)
						&& !m.equals(Material.BROWN_SHULKER_BOX)
						&& !m.equals(Material.GREEN_SHULKER_BOX)
						&& !m.equals(Material.LIME_SHULKER_BOX)
						
						//Spawner
						&& !m.equals(Material.SPAWNER)
						
						//Minerais
						&& !m.equals(Material.COAL_ORE)
						&& !m.equals(Material.COPPER_ORE)
						&& !m.equals(Material.DIAMOND_ORE)
						&& !m.equals(Material.EMERALD_ORE)
						&& !m.equals(Material.GOLD_ORE)
						&& !m.equals(Material.LAPIS_ORE)
						&& !m.equals(Material.IRON_ORE)
						&& !m.equals(Material.REDSTONE_ORE)
						
						&& !m.equals(Material.DEEPSLATE_COAL_ORE)
						&& !m.equals(Material.DEEPSLATE_COPPER_ORE)
						&& !m.equals(Material.DEEPSLATE_DIAMOND_ORE)
						&& !m.equals(Material.DEEPSLATE_EMERALD_ORE)
						&& !m.equals(Material.DEEPSLATE_GOLD_ORE)
						&& !m.equals(Material.DEEPSLATE_LAPIS_ORE)
						&& !m.equals(Material.DEEPSLATE_IRON_ORE)
						&& !m.equals(Material.DEEPSLATE_REDSTONE_ORE)
						
						&& !m.equals(Material.NETHER_QUARTZ_ORE)
						&& !m.equals(Material.NETHER_GOLD_ORE)
						&& !m.equals(Material.ANCIENT_DEBRIS)
						
						&& !m.equals(Material.NETHERITE_BLOCK)
						&& !m.equals(Material.DIAMOND_BLOCK)
						&& !m.equals(Material.COAL_BLOCK)
						&& !m.equals(Material.COPPER_BLOCK)
						&& !m.equals(Material.WAXED_COPPER_BLOCK)
						&& !m.equals(Material.EMERALD_BLOCK)
						&& !m.equals(Material.GOLD_BLOCK)
						&& !m.equals(Material.LAPIS_BLOCK)
						&& !m.equals(Material.IRON_BLOCK)
						&& !m.equals(Material.REDSTONE_BLOCK)

						&& !m.equals(Material.RAW_COPPER_BLOCK)
						&& !m.equals(Material.RAW_GOLD_BLOCK)
						&& !m.equals(Material.RAW_IRON_BLOCK)
						
						
						//Random
						&& r.nextInt(10)==0) {
					p.getInventory().addItem(new ItemStack(event.getBlock().getType()));	
				}
			}
	}
	
	/**Fermier
	 * 10% de drop en plus des plantations
	 * @param event
	 */
	@EventHandler
	public void onCropsBroken(BlockDropItemEvent event) {
			Player p = event.getPlayer();
			Material m = event.getBlock().getType();
			
			if(p.hasPermission("group.fermier")
					&& (
							m.equals(Material.WHEAT)
							|| m.equals(Material.BEETROOT)
							|| m.equals(Material.CARROT)
							|| m.equals(Material.POTATOES)
							|| m.equals(Material.BAMBOO)
							|| m.equals(Material.CACTUS)
							|| m.equals(Material.PUMPKIN)
							|| m.equals(Material.CHORUS_FLOWER)
							|| m.equals(Material.CHORUS_FRUIT)
							|| m.equals(Material.CHORUS_PLANT)
							|| m.equals(Material.MELON)
							|| m.equals(Material.KELP)
							|| m.equals(Material.SUGAR_CANE)
							|| m.equals(Material.NETHER_WART)
							|| m.equals(Material.BROWN_MUSHROOM)
							|| m.equals(Material.RED_MUSHROOM)
						)
					&& r.nextInt(10)==0
					) {
				
				for(Item is : event.getItems()) {					
					event.getBlock().getLocation().getWorld().dropItem(event.getBlock().getLocation().add(0, 0.5, 0), is.getItemStack());
				}
				
				
				
			}
	}
	
	/**Mineur
	 * 10% de drop en plus des minerais
	 * @param event
	 */
	@EventHandler
	public void onMineraisBroken(BlockDropItemEvent event) {
			Player p = event.getPlayer();
			Material m = event.getBlock().getType();
			
			if(p.hasPermission("group.mineur")
					&& (
							m.equals(Material.COAL_ORE)
							||m.equals(Material.COPPER_ORE)
							||m.equals(Material.DIAMOND_ORE)
							||m.equals(Material.EMERALD_ORE)
							||m.equals(Material.GOLD_ORE)
							||m.equals(Material.LAPIS_ORE)
							||m.equals(Material.IRON_ORE)
							||m.equals(Material.REDSTONE_ORE)
							||m.equals(Material.NETHER_QUARTZ_ORE)
							||m.equals(Material.NETHER_GOLD_ORE)
							
							
							||m.equals(Material.DEEPSLATE_COAL_ORE)
							||m.equals(Material.DEEPSLATE_COPPER_ORE)
							||m.equals(Material.DEEPSLATE_DIAMOND_ORE)
							||m.equals(Material.DEEPSLATE_EMERALD_ORE)
							||m.equals(Material.DEEPSLATE_GOLD_ORE)
							||m.equals(Material.DEEPSLATE_LAPIS_ORE)
							||m.equals(Material.DEEPSLATE_IRON_ORE)
							||m.equals(Material.DEEPSLATE_REDSTONE_ORE)
							
							||m.equals(Material.ANCIENT_DEBRIS)
						)
					&& r.nextInt(10)==0
					) {
				
				for(Item is : event.getItems()) {					
					event.getBlock().getLocation().getWorld().dropItem(event.getBlock().getLocation().add(0, 0.5, 0), is.getItemStack());
				}
				
				
				
			}
	}

}
