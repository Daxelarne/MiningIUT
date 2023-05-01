package daxelarne.knightworld.MiningIUT;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Faction implements Listener{
	//Inventaire (vide) de la poubelle
	private Inventory inv;
	
	public Faction() {
		super();
	}

	public Faction(CommandSender sender) {
		super();
		
		inv = Bukkit.createInventory(null, 27, "§4§lRANK §4"+sender.getName());
		
		initializeItems();
	}
	
	
	public void initializeItems() {
		 
		 for(int y=0;y<27;y++) {
			 inv.setItem(y, createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ""));
		 }

		 
		 inv.setItem(10, createGlowingGuiItem(Material.NETHERITE_SWORD, "§c§lAssassin", "§a§l+ §210% de loot sur les monstres", "§a§l+ §2Resistance 1 24h/24", "§a§l+ §2Atout de disparition", ""
				 , "§c§l- §4Les pommes d'or de lui font rien", "§c§l- §43% de chance que la potion ne s'applique pas"));
		 
		 inv.setItem(12, createGlowingGuiItem(Material.SCAFFOLDING, "§2§lArchitecte", "§a§l+ §210% de consomation de moins des blocks (hors minerais, spawner et shulker)", "§a§l+ §2Night Vision 1 24h/24", "§a§l+ §2Atout de fly", ""
				 , "§c§l- §4Phantomes au bout d'une nuit sans sommeil (au lieu de 3)", "§c§l- §43% de chance qu'un escalier se pose à l'envers"));
		 
		 inv.setItem(14, createGlowingGuiItem(Material.WHEAT, "§6§lFermier", "§a§l+ §210% de drop en plus des plantations", "§a§l+ §2Speed 1 24h/24", "§a§l+ §2Atout de heal et feed", ""
				 , "§c§l- §4Siltouch nécéssaire pour bécher ou l'écorcage", "§c§l- §43% de chance que 2 aliments soit mangé au lieu d'1"));
		 
		 inv.setItem(16, createGlowingGuiItem(Material.NETHERITE_PICKAXE, "§7§lMineur", "§a§l+ §210% de drop en plus des minerais", "§a§l+ §2Haste 24h/24", "§a§l+ §2Atout de 3x3", ""
				 , "§c§l- §4La résistance au feu ne marche pas", "§c§l- §43% de chance que sa pioche s'abime 2 fois"));
		 
	 }
	
	 @EventHandler
	 public void onInventoryClick(final InventoryClickEvent e) {
		if (!e.getInventory().equals(inv)) return;
		
		e.setCancelled(true);
		
		final ItemStack clickedItem = e.getCurrentItem();
		
		// verify current item is not null
		if (clickedItem == null || clickedItem.getType().isAir()) return;
		
		final Player p = (Player) e.getWhoClicked();
		
		if(e.getRawSlot()==10) {
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user "+p.getName()+" parent set assassin" );
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skillset "+p.getName()+" res" );
			p.sendMessage("§6§l[§e§lFaction§6§l] §cBienvenue parmis les §lAssassins§c ! Gouverne le monde et extermine les tous !");
			p.closeInventory();
		} else if(e.getRawSlot()==12) {
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user "+p.getName()+" parent set architecte" );
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skillset "+p.getName()+" nv" );
			p.sendMessage("§6§l[§e§lFaction§6§l] §2Bienvenue parmis les §lArchitectes§2 ! Donne vie à tes idées !");
			p.closeInventory();
		} else if(e.getRawSlot()==14) {
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user "+p.getName()+" parent set fermier" );
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skillset "+p.getName()+" speed" );
			p.sendMessage("§6§l[§e§lFaction§6§l] §6Bienvenue parmis les §lFermiers§6 ! Profite d'une vie riche de nature !");
			p.closeInventory();
		} else if(e.getRawSlot()==16) {
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user "+p.getName()+" parent set mineur" );
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "skillset "+p.getName()+" haste" );
			p.sendMessage("§6§l[§e§lFaction§6§l] §7Bienvenue parmis les §lMineurs§7 ! Que seul le son de ta pioche se fasse entendre !");
			p.closeInventory();
		}
	 }
	
	
	 protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
	        final ItemStack item = new ItemStack(material, 1);
	        final ItemMeta meta = item.getItemMeta();

	        if(meta!=null) {
	            // Set the name of the item
	            meta.setDisplayName(name);

	            // Set the lore of the item
	            meta.setLore(Arrays.asList(lore));

	            item.setItemMeta(meta);
	        }


	        return item;
	    }
		 
		 protected ItemStack createGlowingGuiItem(final Material material, final String name, final String... lore) {
	        final ItemStack item = new ItemStack(material, 1);
	        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
	        final ItemMeta meta = item.getItemMeta();
	        if(meta!=null) {
	            // Set the name of the item
	            meta.setDisplayName(name);

	            // Set the lore of the item
	            meta.setLore(Arrays.asList(lore));

	            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

	            item.setItemMeta(meta);
	        }

	        return item;
	    }
		 
		 public void openInventory(final HumanEntity ent) {
	        ent.openInventory(inv);
		 }
		 
		 
		 @EventHandler
		    public void onInventoryClick(final InventoryDragEvent e) {
		        if (e.getInventory().equals(inv)) {
		            e.setCancelled(true);
		        }
		    }
	

}
