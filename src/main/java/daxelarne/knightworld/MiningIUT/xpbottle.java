package daxelarne.knightworld.MiningIUT;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class xpbottle implements Listener{
	
	public xpbottle() {
		super();
	}
	
	//Créer la bouteille
	private void xpInit(ItemStack bottle) {
		ItemMeta itemmeta = bottle.getItemMeta();
		itemmeta.setDisplayName("§6Bouteille d'XP");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§eContient 10 niveaux");
		itemmeta.setLore(lore);

		bottle.setItemMeta(itemmeta);
	}
	
	//Donne la bouteille sur commande
	public xpbottle(CommandSender sender, Command command, String label, String[] args) {
		ItemStack bottle=null;
		
		if(args.length==0) {
			
			bottle =  new ItemStack(Material.EXPERIENCE_BOTTLE);
			
			xpInit(bottle);
			
			Bukkit.getPlayer(sender.getName()).getInventory().addItem(new ItemStack(bottle));
			
		} else if(args.length>1) {
			
			bottle =  new ItemStack(Material.EXPERIENCE_BOTTLE, Integer.valueOf(args[1]));
			
			xpInit(bottle);
			
			if(Bukkit.getPlayer(args[0])==null) {
				sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.BAD_PLAYER.toString());
			} else {
				Bukkit.getPlayer(args[0]).getInventory().addItem(new ItemStack(bottle));
			}
		} else {
			sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NO_VALUE.toString());
		}
	}

	
	@EventHandler
	public void onStockXp(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		if(		p.getInventory().getItemInMainHand()!=null
				&& p.getInventory().getItemInMainHand().getType().equals(Material.GLASS_BOTTLE)
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6Bouteille d'XP §e(vide)")
				&& event.getClickedBlock().getBlockData().getMaterial().equals(Material.ENCHANTING_TABLE)
				
			) {
			if(p.getLevel() >= 10) {
				event.setCancelled(true);
				p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
				p.setLevel(p.getLevel() - 10);
				
				ItemStack bottle =  new ItemStack(Material.EXPERIENCE_BOTTLE);
				
				xpInit(bottle);
				
				p.getInventory().addItem(bottle);
			} else {
				event.setCancelled(true);
				p.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NOT_ENOUGH_XP.toString());
			}
			
		} else if(p.getInventory().getItemInMainHand()!=null
				&& p.getInventory().getItemInMainHand().getType().equals(Material.EXPERIENCE_BOTTLE)
				&& p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6Bouteille d'XP")) {
			event.setCancelled(true);
			
			p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
			p.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE));
			p.setLevel(p.getLevel() + 10);
			
		}
	}
	
	
	
	
}
