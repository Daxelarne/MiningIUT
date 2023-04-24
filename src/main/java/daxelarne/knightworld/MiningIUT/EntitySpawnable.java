package daxelarne.knightworld.MiningIUT;

public enum EntitySpawnable {
	CREEPER("creeper"),
    SKELETON("skeleton"),
    SPIDER("spider"),
    ZOMBIE("zombie"),
    SLIME("slime"),
    ZOMBIFIED_PIGLIN("zombified_piglin"),
    ENDERMAN("enderman"),
    CAVE_SPIDER("cave_spider"),
    BLAZE("blaze"),
    MAGMA_CUBE("magma_cube"),
    WITCH("witch"),
    PIG("pig"),
    SHEEP("sheep"),
    GLOW_SQUID("glow_squid"),
    COW("cow"),
    CHICKEN("chicken"),
    SQUID("squid"),
    IRON_GOLEM("iron_golem"),
    RABBIT("rabbit"),
    EVOKER("evoker"),
    CAT("cat");
    
    private final String name;
	
	EntitySpawnable(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
    
}
