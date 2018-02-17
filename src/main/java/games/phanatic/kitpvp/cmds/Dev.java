package games.phanatic.kitpvp.cmds;

import code.matthew.psc.api.command.ICommand;
import games.phanatic.kitpvp.PKPvP;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Dev extends ICommand {

    private PKPvP pvp;

    public Dev(PKPvP pvp) {
        super("dev", "dev", "Dev", true);
        this.pvp = pvp;
    }

    @Override
    public boolean finalExe(CommandSender sender, String[] args) {
        Player p = (Player) sender;

        if (p.isOp()) {
            for (int i = 0; i < 20; i++) {
                pvp.getTmpDatManager().addKS(p);
            }
            sender.sendMessage("Added 20 to ks");
        }
        return true;
    }
}
