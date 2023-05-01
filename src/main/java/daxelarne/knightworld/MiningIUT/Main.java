package daxelarne.knightworld.MiningIUT;

import static org.bukkit.Bukkit.getPlayer;

import java.io.File;
import java.io.IOException;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin implements Listener {
	
	private FileConfiguration config;
	protected static int maxEntity=256;
	@SuppressWarnings("unused")
	private static BukkitTask task;
	

	@Override
    public void onDisable() {
        super.onDisable();
        
        task.cancel();
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
        getServer().getPluginManager().registerEvents(new JoinAndDeathEvent(), this);
        getServer().getPluginManager().registerEvents(new xpbottle(), this);
        getServer().getPluginManager().registerEvents(new limitEntity(), this);
        getServer().getPluginManager().registerEvents(new spawnerPick(), this);
        getServer().getPluginManager().registerEvents(new NoEndGateaway(), this);
        getServer().getPluginManager().registerEvents(new LootWarden(), this);
        getServer().getPluginManager().registerEvents(new SpawnerSword(), this);
        getServer().getPluginManager().registerEvents(new Faction(), this);
        getServer().getPluginManager().registerEvents(new TenPercentReduction(), this);
        
        
        
        /**
         * NEW CRAFT
         */
        crafts();
        
        /**
         * INIT
         */
        spammingBot();
        
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
    	
    	
    	//PIOCHE SPAWNER
    	ItemStack spawnerPickaxe = new ItemStack(Material.GOLDEN_PICKAXE);
    	spawnerPickaxe.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
    	
    	
    	ItemMeta itemmetaSp = spawnerPickaxe.getItemMeta();
    	itemmetaSp.setDisplayName("§6Pioche spawner");
		ArrayList<String> lore1 = new ArrayList<String>();
		lore1.add("§eUtilisations restantes : §610");
		itemmetaSp.setLore(lore1);
		
		itemmetaSp.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		spawnerPickaxe.setItemMeta(itemmetaSp);
    	
    	nk = new NamespacedKey(this, "spawnerpick");
    	
		ShapedRecipe crafspawnerPickaxe = new ShapedRecipe(nk, spawnerPickaxe);
    	
		crafspawnerPickaxe.shape("AEA","SPT","ABA");
    	
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		book.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		
		crafspawnerPickaxe.setIngredient('E', Material.ENDER_EYE);
		crafspawnerPickaxe.setIngredient('S', Material.AMETHYST_SHARD);
		crafspawnerPickaxe.setIngredient('P', Material.GOLDEN_PICKAXE);
		crafspawnerPickaxe.setIngredient('T', Material.GHAST_TEAR);
		crafspawnerPickaxe.setIngredient('B', book.getData());
		crafspawnerPickaxe.setIngredient('A', Material.AIR);
    	
    	
    	getServer().addRecipe(crafspawnerPickaxe);
    	
    	
    	//EPEE SPAWNER
    	ItemStack spawnerSword = new ItemStack(Material.GOLDEN_SWORD);
    	spawnerSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    	spawnerSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL , 5);
    	
    	
    	ItemMeta itemmetaSword = spawnerPickaxe.getItemMeta();
    	itemmetaSword.setDisplayName("§6Epée spawner");
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add("§6Drop un spawner pour chaque type de mob ci-dessous");
		lore2.add("§eCreeper : 0/§6200");
		lore2.add("§eVache : 0/§6200");
		lore2.add("§eMouton : 0/§6200");
		itemmetaSword.setLore(lore2);
		
		spawnerSword.setItemMeta(itemmetaSword);
    	
    	nk = new NamespacedKey(this, "spawnersword");
    	
		ShapedRecipe crafspawnerSword = new ShapedRecipe(nk, spawnerSword);
    	
		crafspawnerSword.shape("LAL","ASA","BDB");

		
		crafspawnerSword.setIngredient('L', Material.LAPIS_LAZULI);
		crafspawnerSword.setIngredient('A', Material.AMETHYST_SHARD);
		crafspawnerSword.setIngredient('S', Material.GOLDEN_SWORD);
		crafspawnerSword.setIngredient('B', book.getData());
		crafspawnerSword.setIngredient('D', Material.DRAGON_BREATH);
    	
    	
    	getServer().addRecipe(crafspawnerSword);
    	
    	//EATHERSTONE
    	
    	//HELLSTONE
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
    
    
    protected static void spammingBot() {
		task = Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("MiningIUT"), () -> {
			try {
				for(World w : Bukkit.getWorlds()) {
					for(Chunk c : w.getLoadedChunks()) {
						if(c.getInhabitedTime()==0 && !c.isForceLoaded()) {
							c.unload();
						}
					}
				}
			} catch(ConcurrentModificationException e) {
				//Juste Java qui est stupide
			}
		 }, 20, 20 );
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
    	
    	} else if(command.getName().toLowerCase().equals("rank")) {
    		
    		Player p = Bukkit.getPlayer(sender.getName());
    		
    		if(!p.hasPermission("group.fermier") && !p.hasPermission("group.mineur") && !p.hasPermission("group.assassin") && !p.hasPermission("group.architecte")) {
				
    			Faction f = new Faction(sender);
    			
    			getServer().getPluginManager().registerEvents(f, this);
    			
    			f.openInventory(p);
				
			}
    		
    		
    	} else if(command.getName().toLowerCase().equals("faction")) {
    		
    		Player p = Bukkit.getPlayer(sender.getName());
    		
    		p.sendMessage("\n \n§6§lLes Factions:\n"
    				+ "§eIl existe 4 factions : §c§lAssassin§e, §2§lArchitecte§e, §6§lFermier §eet §7§lMineur\n"
    				+ " \n"
    				+ "§eChaque faction possède ses spécificités, ses bonus, mais aussi ses malus. Un joueur doit choisir sa faction en fonction de son mode de jeu\n"
    				+ "§eVous pouvez choisir la classe que vous voulez, cependant §lvous ne pourrez pas en changer§e. Choississez bien.\n"
    				+ " \n"
    				+ "§c§lAssassin :\n"
    				+ "§cL'Assassin est fait pour tuer des monstres, boss et joueur. C'est une classe violente et rapide, qui conviendras à ceux qui aime explorer et le danger.\n"
    				+ " \n"
    				+ "§2§lArchitecte :\n"
    				+ "§2L'Architecte construit. Il possède donc des talents pour l'accompagner dans le modelage de ses créations et pour donner vie à ses idées.\n"
    				+ " \n"
    				+ "§6§lFermier :\n"
    				+ "§6Le Fermier récolte des ressources pour ses congénères. Il conviendras à ceux qui aime la pêche, la ferme et la récolte.\n"
    				+ " \n"
    				+ "§7§lMineur :\n"
    				+ "§7Le Mineur récupère des minerais dans les entrailles de la terre, et par delà. Il est fait pour ceux qui ignore quand il fait jour.\n"
    				+ " \n"
    				+ "§eVous pouvez choisir votre classe et afficher les bonus et malus de chaque classe en faisant §6/rank§e mais attention : §lpas de retour en arrière possible !\n"
    				+ "§eQue le sort vous soit favorable !");
    		
    		
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
