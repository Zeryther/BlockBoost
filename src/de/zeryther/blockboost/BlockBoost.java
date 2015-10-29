package de.zeryther.blockboost;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBoost extends JavaPlugin implements CommandExecutor, Listener {

	private int BLOCK_ID;
	private boolean NOT_JUMP_WHEN_SNEAKING;
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("blockboost").setExecutor(this);
		
		BLOCK_ID = getConfig().getInt("block-id");
		NOT_JUMP_WHEN_SNEAKING = getConfig().getBoolean("notJumpWhenSneaking");
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		
		if((NOT_JUMP_WHEN_SNEAKING == true && p.isSneaking() == false) || (NOT_JUMP_WHEN_SNEAKING == false)) {
			Location blockBelow = p.getLocation().add(0.0, -1.0, 0.0);
			Location blockBelowBelow = p.getLocation().add(0.0, -2.0, 0.0);
			if(blockBelow.getBlock().getType().getId() == BLOCK_ID) {
				p.setVelocity(p.getVelocity().setY(17 / 9.0D));
			} else if(blockBelowBelow.getBlock().getType().getId() == BLOCK_ID) {
				p.setVelocity(p.getVelocity().setY(17 / 9.0D));
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("blockboost")){
			sender.sendMessage("§cThis server is using §eBlockBoost §cversion §e" + 1.0);
			sender.sendMessage("§eBlockBoost §cwas made by §eZeryther§c.");
			sender.sendMessage("§cMore information at §ehttp://www.zeryther.de");
		}
		
		return true;
	}
	
}
