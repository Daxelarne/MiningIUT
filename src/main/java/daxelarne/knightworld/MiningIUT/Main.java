package daxelarne.knightworld.MiningIUT;

import static org.bukkit.Bukkit.getPlayer;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private FileConfiguration config;
	protected static int maxEntity=256;
	

	@Override
    public void onDisable() {
        super.onDisable();
        

	 }

    @Override
    public void onEnable() {
    	super.onEnable();
    	this.getLogger().log(Level.INFO, "Chargement...");
    	
    	/**
    	 * CONFIG
    	 */
    	saveDefaultConfig();
        config = getConfig();
        getConfigValues();

        
        /**
         * LANG
         */
        loadLang();

        
        /**
         * EVENTS
         */
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new xpbottle(), this);
        getServer().getPluginManager().registerEvents(new limitEntity(), this);
    	
        
        /**
         * NEW CRAFT
         */
        crafts();
        
        /**
         * INIT
         */

        
    	this.getLogger().log(Level.INFO, "Chargé !");
    }
    
    private void crafts() {
    	//SELLE
    	ItemStack saddle = new ItemStack(Material.SADDLE);
    	NamespacedKey nk = new NamespacedKey(this, "saddle");
    	
		ShapedRecipe craftSaddle = new ShapedRecipe(nk, saddle);
    	
    	craftSaddle.shape("LLL","SAS","TAT");
    	
    	craftSaddle.setIngredient('L', Material.LEATHER);
    	craftSaddle.setIngredient('S', Material.STRING);
    	craftSaddle.setIngredient('A', Material.AIR);
    	craftSaddle.setIngredient('T', Material.TRIPWIRE_HOOK);
    	
    	getServer().addRecipe(craftSaddle);
    	
    	
    	//ARMURE CHEVAUX DIAMANT
    	ItemStack diamondArmor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
    	
    	nk = new NamespacedKey(this, "diamondarmor");
		ShapedRecipe crafDiamondArmor = new ShapedRecipe(nk, diamondArmor);
    	
    	crafDiamondArmor.shape("SCS","PLP","BAB");
    	
    	crafDiamondArmor.setIngredient('S', Material.STRING);
    	crafDiamondArmor.setIngredient('C', Material.SADDLE);
    	crafDiamondArmor.setIngredient('P', Material.DIAMOND_LEGGINGS);
    	crafDiamondArmor.setIngredient('L', Material.LEATHER);
    	crafDiamondArmor.setIngredient('B', Material.DIAMOND_BOOTS);
    	crafDiamondArmor.setIngredient('A', Material.AIR);
    	
    	
    	getServer().addRecipe(crafDiamondArmor);
    	
    	//XB BOTTLE
    	ItemStack xpBottle = new ItemStack(Material.GLASS_BOTTLE);
    	xpBottle.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	
    	
    	ItemMeta itemmeta = xpBottle.getItemMeta();
		itemmeta.setDisplayName("§6Bouteille d'XP §e(vide)");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§ePeux contenir 10 niveaux");
		itemmeta.setLore(lore);
		
		itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		xpBottle.setItemMeta(itemmeta);
    	
    	nk = new NamespacedKey(this, "xpbottle");
		ShapedRecipe crafXpBottle = new ShapedRecipe(nk, xpBottle);
    	
		crafXpBottle.shape("ASA","SBS","ASA");
    	
		crafXpBottle.setIngredient('S', Material.AMETHYST_SHARD);
		crafXpBottle.setIngredient('B', Material.GLASS_BOTTLE);
		crafXpBottle.setIngredient('A', Material.AIR);
    	
    	
    	getServer().addRecipe(crafXpBottle);
    	
    }
    
    
    private void loadLang() {
		File lang = new File(getDataFolder(), "lang.yml");
		YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(lang);
		//Si le fichier n'éxiste pas, on le créer
		if(!lang.exists()) {
			try {
				langConfig.save(lang);
			} catch(IOException e) {
				disablePl(e);
			}
		}
		
		//Si la config ne contient pas les valeurs, on les initialise
		for(Lang item : Lang.values()) {
			if(langConfig.getString(item.getPath()) == null) {
				langConfig.set(item.getPath(), item.getDefault());
			}
		}
		
		Lang.setFile(langConfig);
		
		try {
			langConfig.save(lang);
		} catch(IOException e) {
			disablePl(e);
		}
		
	}
    
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	/**
		 * Affichage valeur
		 */
    	if(command.getName().toLowerCase().equals("xpbottle")) {
    		
    		//S'il n'à pas la permission
			if(hasPermission(sender, "xpbottle.xpbottle")) {
				
				new xpbottle(sender, command, label, args);
				
			}
    	
    	} 
    	
    	
    	return true;
    }
    
    
    //Check si un joueur possède la permission
  	private boolean hasPermission(CommandSender sender, String perm) {
  		Player p = getPlayer(sender.getName());
  		
  		if(p!=null && !p.hasPermission(perm)) {
  			sendNoPerm(sender);
  			return false;
  		}
  		
  		return true;
  	}
  	
    
    //Initialisation des variable en fonction de la config
  	private void getConfigValues() {
  		try {
  			//wordResetTitle=config.getString("wordResetTitle");
  			maxEntity=config.getInt("MaxEntityByChunk");
          } catch(Exception e) {
          	disablePl(e);
          }
  	}
  	
	//Désactive le plugin et affiche le message de l'erreur
  	private void disablePl(Exception e) {
  		this.getLogger().log(Level.SEVERE, e.getClass()+" : "+e.getMessage());
  		getServer().getPluginManager().disablePlugin(this);
  	}

	
	
	/**
	 * MESSAGES
	 */

	
	private void sendNoPerm(CommandSender sender) {
		sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NO_PERM.toString());
	}
  	
}
