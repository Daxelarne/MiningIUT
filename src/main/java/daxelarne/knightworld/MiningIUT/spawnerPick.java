package daxelarne.knightworld.MiningIUT;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class spawnerPick implements Listener{
	
	@EventHandler
	public void mineSpawner(BlockBreakEvent event) {
		
		
			ItemStack playerMining = event.getPlayer().getInventory().getItemInMainHand();
			if(playerMining.getType().equals(Material.GOLDEN_PICKAXE)
					&& playerMining.getItemMeta().hasDisplayName()
					&& playerMining.getItemMeta().getDisplayName().equals("§6Pioche spawner")
					) {

				
				if(event.getBlock().getType().equals(Material.SPAWNER)) {
				
				event.setCancelled(true);
				
				CreatureSpawner s = (CreatureSpawner)event.getBlock().getState();
				
				ItemStack item = new ItemStack(Material.SPAWNER, 1);
				List<String> lore = new ArrayList<String>();
				
				String loreString =  s.getSpawnedType().toString();
				loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
				loreString = loreString+" Spawner";
				lore.add(loreString);
				
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lore);
				item.setItemMeta(meta);
				
				s.getWorld().dropItem(s.getLocation(), item);
				event.getBlock().setType(Material.AIR);
				
				
				String l = (String) playerMining.getItemMeta().getLore().toArray()[0];
				l=l.replace("§eUtilisations restantes : §6", "");
				int i = Integer.valueOf(l);
				
				if(i-1==0) {
					event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				} else {
					ItemStack spawnerPickaxe = new ItemStack(Material.GOLDEN_PICKAXE);
			    	spawnerPickaxe.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			    	
			    	
			    	ItemMeta itemmetaSp = spawnerPickaxe.getItemMeta();
			    	itemmetaSp.setDisplayName("§6Pioche spawner");
					ArrayList<String> lore1 = new ArrayList<String>();
					lore1.add("§eUtilisations restantes : §6"+(i-1));
					itemmetaSp.setLore(lore1);
					
					itemmetaSp.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					
					spawnerPickaxe.setItemMeta(itemmetaSp);
					
					event.getPlayer().getInventory().setItemInMainHand(spawnerPickaxe);
				}
				
				
				//event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), (ItemStack) spawner);
				
			} else {
				event.setCancelled(true);
			}
			
		} 
	}
	
	@EventHandler
	public void onSpawnerPlaced(BlockPlaceEvent evt) {
		if(evt.isCancelled())
			return;
		
		ItemStack item = evt.getItemInHand();
		
		if(item == null)
			return;
		
		if(item.getType().equals(Material.SPAWNER)) {			
			String sType;

			
			if(item.getItemMeta().getLore() == null) {
				sType = "Pig Spawner";
			} else {
				sType = item.getItemMeta().getLore().get(0);
			}
			
			Block setBlock = evt.getBlock();
			CreatureSpawner s = (CreatureSpawner)setBlock.getState();
			s.setSpawnedType(EntityType.valueOf(sType.split(" ")[0].toUpperCase()));
			s.update();
		}
	}

}
