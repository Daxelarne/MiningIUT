package daxelarne.knightworld.MiningIUT;

import java.util.ConcurrentModificationException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

public class FactionDebuf implements Listener {
	/**		 
		 inv.setItem(12, createGlowingGuiItem(Material.SCAFFOLDING, "§2§lArchitecte", "§a§l+ §210% de consomation de moins des blocks (hors minerais, spawner et shulker)", "§a§l+ §2Night Vision 1 24h/24", "§a§l+ §2Atout de fly", ""
				 , "§c§l- §4Phantomes au bout d'une nuit sans sommeil (au lieu de 3)", "§c§l- §43% de chance qu'un escalier se pose à l'envers"));
		 
	 */
	
	/**ASSASSIN
	 * No gapple
	 * @param event
	 */
	@EventHandler
	public void noGapple(PlayerItemConsumeEvent event) {
		
		if(event.getPlayer().hasPermission("group.assassin") && !event.getPlayer().hasPermission("faction.bypass") && (event.getItem().getType().equals(Material.GOLDEN_APPLE)
				|| event.getItem().getType().equals(Material.ENCHANTED_GOLDEN_APPLE))
				) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("§cCette pomme vous semble bizarre, votre rythme de vie d'assassin vous invite à ne pas la consommer");
			
		}
		
	}
	
	/**Fermier
	 * No stripping & beching
	 * @param event
	 */
	@EventHandler
	public void noStripping(PlayerInteractEvent event) {
		
		if(event.getPlayer().hasPermission("group.fermier") && !event.getPlayer().hasPermission("faction.bypass") 
				&& (event.getClickedBlock().getType().equals(Material.BIRCH_LOG)
					|| event.getClickedBlock().getType().equals(Material.OAK_LOG)
					|| event.getClickedBlock().getType().equals(Material.JUNGLE_LOG)
					|| event.getClickedBlock().getType().equals(Material.ACACIA_LOG)
					|| event.getClickedBlock().getType().equals(Material.DARK_OAK_LOG)
					|| event.getClickedBlock().getType().equals(Material.SPRUCE_LOG)
					|| event.getClickedBlock().getType().equals(Material.CRIMSON_STEM)
					|| event.getClickedBlock().getType().equals(Material.WARPED_STEM)
						)
				&& !event.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)
				) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("§cMince, votre faction de fermier vous impose d'utiliser Silk Touch pour garantir la qualité de votre travail");
			
		} else if(event.getPlayer().hasPermission("group.fermier") && !event.getPlayer().hasPermission("faction.bypass") 
				&& (event.getClickedBlock().getType().equals(Material.DIRT)
						|| event.getClickedBlock().getType().equals(Material.GRASS_BLOCK)
						|| event.getClickedBlock().getType().equals(Material.COARSE_DIRT))
				&& !event.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)
				) {
				event.setCancelled(true);
				event.getPlayer().sendMessage("§cMince, votre faction de fermier vous impose d'utiliser Silk Touch pour garantir la qualité de votre travail");
				
			}
		
	}
	
	/**MINEUR & ARCHITECTE
	 * No fire_res
	 * Little Phantom
	 */
	public FactionDebuf() {
		super();
		
		Main.FactionTask = Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("MiningIUT"), () -> {
			try {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.hasPermission("group.mineur") && !p.hasPermission("faction.bypass")) {
						if(p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
							p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
							p.sendMessage("§cLe résistance au feu ne vous plait pas, vous et les autres mineurs vous en débarassez imédiatement");
						}
						
					}
				}
				
				
				if(Bukkit.getWorld("world").getTime()>14000 && Bukkit.getWorld("world").getTime()<14020) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.hasPermission("group.architecte") && !p.hasPermission("faction.bypass")) {
							p.getLocation().getWorld().spawnEntity(p.getLocation().add(0, 10, 0), EntityType.PHANTOM);
							
						}
					}
					
				}
				
			} catch(ConcurrentModificationException e) {
				//Juste Java qui est stupide
			}
		 }, 20, 20 );
	}
		

}
