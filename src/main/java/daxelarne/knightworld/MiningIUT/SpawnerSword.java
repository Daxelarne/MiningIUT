package daxelarne.knightworld.MiningIUT;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerSword implements Listener{

	@EventHandler
	public void onEntityDeath(EntityDamageByEntityEvent event) {
		if(event.getDamager().getType().equals(EntityType.PLAYER)) {
			Player p = Bukkit.getPlayer(event.getDamager().getName());
			
			if(p.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_SWORD)
					&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6Epée spawner")
					&& (event.getEntity().isDead()  ||
							((LivingEntity)event.getEntity()).getHealth()<=0
							|| ((LivingEntity)event.getEntity()).getHealth()-event.getFinalDamage()<=0
							
							)
					
					) {
				
				if(event.getEntityType().equals(EntityType.CREEPER)) {
					String l = (String) p.getInventory().getItemInMainHand().getItemMeta().getLore().toArray()[1];
					l=l.replace("§eCreeper : ", "").replace("/§6200", "");
					int i = Integer.valueOf(l);
					
					if(i!=200) {
						i++;
						
						if(i==200) {
							
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add("§eCreeper : "+i+"/§6200");
							lore1.add(originalLore.get(2));
							lore1.add(originalLore.get(3));
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);
							
							
							ItemStack item = new ItemStack(Material.SPAWNER, 1);
							List<String> lore = new ArrayList<String>();
							
							String loreString =  EntityType.CREEPER.toString();
							loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
							loreString = loreString+" Spawner";
							lore.add(loreString);
							
							ItemMeta meta = item.getItemMeta();
							meta.setLore(lore);
							item.setItemMeta(meta);
							
							event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), item);
							
							
							
						} else {
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add("§eCreeper : "+i+"/§6200");
							lore1.add(originalLore.get(2));
							lore1.add(originalLore.get(3));
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);
						}
					}
					
					
					
				} else if(event.getEntityType().equals(EntityType.COW)) {
					
					String l = (String) p.getInventory().getItemInMainHand().getItemMeta().getLore().toArray()[2];
					l=l.replace("§eVache : ", "").replace("/§6200", "");
					int i = Integer.valueOf(l);

					
					if(i!=200) {
						i++;
						
						if(i==200) {
							
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add(originalLore.get(1));
							lore1.add("§eVache : "+i+"/§6200");
							lore1.add(originalLore.get(3));
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);

							
							ItemStack item = new ItemStack(Material.SPAWNER, 1);
							List<String> lore = new ArrayList<String>();
							
							String loreString =  EntityType.COW.toString();
							loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
							loreString = loreString+" Spawner";
							lore.add(loreString);
							
							ItemMeta meta = item.getItemMeta();
							meta.setLore(lore);
							item.setItemMeta(meta);
							
							event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), item);
							
							
							
						} else {
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add(originalLore.get(1));
							lore1.add("§eVache : "+i+"/§6200");
							lore1.add(originalLore.get(3));
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);
						}
					}
					
					
				} else if(event.getEntityType().equals(EntityType.SHEEP)) {
					
					String l = (String) p.getInventory().getItemInMainHand().getItemMeta().getLore().toArray()[3];
					l=l.replace("§eMouton : ", "").replace("/§6200", "");
					int i = Integer.valueOf(l);
					if(i!=200) {
						i++;
						
						if(i==200) {
							
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add(originalLore.get(1));
							lore1.add(originalLore.get(2));
							lore1.add("§eMouton : 200/§6200");
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);

							
							ItemStack item = new ItemStack(Material.SPAWNER, 1);
							List<String> lore = new ArrayList<String>();
							
							String loreString =  EntityType.SHEEP.toString();
							loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
							loreString = loreString+" Spawner";
							lore.add(loreString);
							
							ItemMeta meta = item.getItemMeta();
							meta.setLore(lore);
							item.setItemMeta(meta);
							
							event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), item);
							
							
							
						} else {
							ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
							spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
					    	
					    	
					    	ItemMeta itemmetaSp = spawnerSword.getItemMeta();
					    	itemmetaSp.setDisplayName("§6Epée spawner");
					    	
					    	ArrayList<String> originalLore = (ArrayList<String>) p.getInventory().getItemInMainHand().getItemMeta().getLore();
							ArrayList<String> lore1 = new ArrayList<String>();
							
							lore1.add("§6Drop un spawner pour chaque type de mob ci-dessous");
							lore1.add(originalLore.get(1));
							lore1.add(originalLore.get(2));
							lore1.add("§eMouton : "+i+"/§6200");
							
							itemmetaSp.setLore(lore1);
							
							spawnerSword.setItemMeta(itemmetaSp);
							
							p.getInventory().setItemInMainHand(spawnerSword);
						}
					}
					
					
					
					
					
					
				}
				
				
			}
			
			
		}
		
		
	}
	
}
