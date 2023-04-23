package daxelarne.knightworld.MiningIUT;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NoEndGateaway implements Listener{

	 @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_GATEWAY)){
            event.setCancelled(true);
        }
    }

}
