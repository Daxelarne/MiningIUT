package daxelarne.knightworld.MiningIUT;

import static org.bukkit.Bukkit.getPlayer;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket.Listener;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private FileConfiguration config;
	

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
    	//saveDefaultConfig();
        //config = getConfig();
        //getConfigValues();

        
        /**
         * LANG
         */
        loadLang();

        
        /**
         * EVENTS
         */
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    	
        
        /**
         * NEW CRAFT
         */
        crafts();
        
    	this.getLogger().log(Level.INFO, "Chargé !");
    }
    
    private void crafts() {
    	//SELLE
    	ItemStack saddle = new ItemStack(Material.SADDLE);
    	@SuppressWarnings("deprecation")
		ShapedRecipe craftSaddle = new ShapedRecipe(saddle);
    	
    	craftSaddle.shape("LLL","SAS","TAT");
    	
    	craftSaddle.setIngredient('L', Material.LEATHER);
    	craftSaddle.setIngredient('S', Material.STRING);
    	craftSaddle.setIngredient('A', Material.AIR);
    	craftSaddle.setIngredient('T', Material.TRIPWIRE_HOOK);
    	
    	getServer().addRecipe(craftSaddle);
    	
    	
    	//ARMURE CHEVAUX DIAMANT
    	ItemStack diamondArmor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
    	@SuppressWarnings("deprecation")
		ShapedRecipe crafDiamondArmor = new ShapedRecipe(diamondArmor);
    	
    	crafDiamondArmor.shape("SCS","PLP","BAB");
    	
    	crafDiamondArmor.setIngredient('S', Material.STRING);
    	crafDiamondArmor.setIngredient('C', Material.SADDLE);
    	crafDiamondArmor.setIngredient('P', Material.DIAMOND_LEGGINGS);
    	crafDiamondArmor.setIngredient('L', Material.LEATHER);
    	crafDiamondArmor.setIngredient('B', Material.DIAMOND_BOOTS);
    	crafDiamondArmor.setIngredient('A', Material.AIR);
    	
    	
    	getServer().addRecipe(crafDiamondArmor);
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
  	
  	private void sendNoValue(CommandSender sender) {
		sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NO_VALUE.toString());
	}
  	
}
