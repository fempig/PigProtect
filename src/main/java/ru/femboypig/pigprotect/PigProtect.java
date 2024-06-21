package ru.femboypig.pigprotect;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PigProtect extends JavaPlugin implements Listener {

    private Location spawnLocation;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        spawnLocation = new Location(
                getServer().getWorld(getConfig().getString("spawn.world")),
                getConfig().getDouble("spawn.x"),
                getConfig().getDouble("spawn.y"),
                getConfig().getDouble("spawn.z")
        );

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getLocation().distance(spawnLocation) <= getConfig().getInt("radius")) {
            event.setCancelled(Boolean.parseBoolean(getConfig().getString("protect.blockbreak")));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getLocation().distance(spawnLocation) <= getConfig().getInt("radius")) {
            event.setCancelled(Boolean.parseBoolean(getConfig().getString("protect.blockplace")));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity().getLocation().distance(spawnLocation) <= getConfig().getInt("radius")) {
            event.setCancelled(Boolean.parseBoolean(getConfig().getString("protect.damage")));
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity().getLocation().distance(spawnLocation) <= getConfig().getInt("radius")) {
            event.setCancelled(Boolean.parseBoolean(getConfig().getString("protect.entityspawn")));
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity().getLocation().distance(spawnLocation) <= getConfig().getInt("radius")) {
            event.setCancelled(Boolean.parseBoolean(getConfig().getString("protect.exploding")));
        }
    }
}