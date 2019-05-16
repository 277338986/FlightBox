package me.kkdevs;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class fg extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent var1) {
        try {
            Player var2 = var1.getPlayer();
            if (var1.getWindow() instanceof FormWindowSimple) {
                FormWindowSimple var3 = (FormWindowSimple) var1.getWindow();
                String var4 = var3.getResponse().getClickedButton().getText();
                if (var2.hasPermission("fly.use")) {
                    if (var4.equals("Fly" + TextFormat.GREEN + " on")) {
                        var2.setAllowFlight(true);
                    }

                    if (var4.equals("Fly" + TextFormat.RED + " off")) {
                        var2.setAllowFlight(false);
                    }
                }

                if (var2.hasPermission("gm.use")) {
                    if (var4.equals(TextFormat.GREEN + "Survival")) {
                        var2.setGamemode(0);
                    }

                    if (var4.equals(TextFormat.GOLD + "Creative")) {
                        var2.setGamemode(1);
                    }

                    if (var4.equals(TextFormat.GRAY + "Spectator")) {
                        var2.setGamemode(2);
                    }
                }

            }
        } catch(Exception e) {}
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            switch (cmd.getName()) {
                case "gm":
                    if (sender.hasPermission("gm.use")) {
                        FormWindowSimple var = new FormWindowSimple("Gamemode", "");
                        var.addButton(new ElementButton(TextFormat.GREEN + "Survival"));
                        var.addButton(new ElementButton(TextFormat.GOLD + "Creative"));
                        var.addButton(new ElementButton(TextFormat.GRAY + "Spectator"));
                        ((Player) sender).showFormWindow(var);
                        return true;
                    }
                    break;
                case "fly":
                    if (sender.hasPermission("fly.use")) {
                        FormWindowSimple var = new FormWindowSimple("Fly", "");
                        var.addButton(new ElementButton("Fly" + TextFormat.GREEN + " on"));
                        var.addButton(new ElementButton("Fly" + TextFormat.RED + " off"));
                        ((Player) sender).showFormWindow(var);
                        return true;
                    }
                    break;
            }
        } else {
            this.getServer().getLogger().error("Вы должны быть в игре!");
        }
        return true;
    }
}
