package aplini.noportals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoPortals extends JavaPlugin implements Listener {
    private static NoPortals plugin;
    private static List<String> worlds;

    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        plugin.getConfig();
        worlds = (List<String>) plugin.getConfig().get("worlds");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {

    }

    // 地狱传送门
    @EventHandler(priority = EventPriority.HIGH)
    public void onPortalCreateEvent(PortalCreateEvent e) {
        if (e.getReason() == PortalCreateEvent.CreateReason.FIRE && // 由火触发
                worlds.contains(e.getWorld().getName())){           // 世界在配置中
            e.setCancelled(true);
        }
    }

    // 末地传送门
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK &&    // 右键单击
                Objects.requireNonNull(e.getClickedBlock()).getType() == Material.END_PORTAL_FRAME &&   // 点击传送门框架
                Objects.requireNonNull(e.getPlayer().getInventory().getItemInMainHand().getType()) == Material.ENDER_EYE && // 手持末影之眼
                worlds.contains(e.getPlayer().getWorld().getName())){   // 世界在配置中
            e.setCancelled(true);
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("noportals")){
            if (args.length == 0) {
                sender.sendMessage("IpacEL > NoPortals: 禁用传送门 v0.0.0");
                sender.sendMessage("  指令: ");
                sender.sendMessage("    - /noportals reload - 重载配置");
                return true;
            }

            else if(args[0].equals("reload")){
                plugin.reloadConfig();
                worlds = (List<String>) plugin.getConfig().get("worlds");
                sender.sendMessage("NoPortals 已完成重载");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sendermm, Command command, String label, String[] args) {
        if(args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("reload"); // 重载配置
            return list;
        }
        return null;
    }
}
