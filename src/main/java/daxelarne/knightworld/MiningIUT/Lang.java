package daxelarne.knightworld.MiningIUT;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
	PREFIX("prefix",""),
	ERROR_PREFIX("error-prefix","&4[&cErreur&4]&c"),
	NO_PERM("no-permission","Vous n'avez pas la permission de faire cette commande !"),
	NO_VALUE("no-value","Informations manquante, merci d'utiliser la commande correctement !"),
	BAD_PLAYER("no-player","Le joueur n'est pas connecte"),
	NOT_ENOUGH_XP("not-enough-xp","Vous n'avez pas assez d'éxpérience pour faire cela"),
	MAX_ENTITY("max-entity","Le nombre d'entité à était atteinte dans le chunk");
	
	
	private String path;
    private String def;
    private static YamlConfiguration LANG;
    
    
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }
    
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }
    
    @Override
    public String toString() {
        if (this == PREFIX || this == ERROR_PREFIX) return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }
    
    public String getDefault() {
        return this.def;
    }
    
    public String getPath() {
        return this.path;
    }
}
