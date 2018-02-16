package games.phanatic.kitpvp;

import code.matthew.psc.utils.core.CommandManager;
import games.phanatic.kitpvp.cmds.Dev;
import games.phanatic.kitpvp.cmds.Setspawn;
import games.phanatic.kitpvp.hardcoded.TNT;
import games.phanatic.kitpvp.listeners.*;
import games.phanatic.kitpvp.manager.*;
import games.phanatic.kitpvp.util.FileUtil;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PKPvP extends JavaPlugin {

    @Getter
    private FileUtil fileUtil;

    @Getter
    private ItemManager isManager;

    @Getter
    private LocationManager locManager;

    @Getter
    private InventoryManager invManager;

    @Getter
    private KitManager kitManager;

    @Getter
    private TempDataManager tmpDatManager;

    @Getter
    private HardCodedAblitys ablitys;

    @Override
    public void onEnable() {
        fileUtil = new FileUtil(this);
        ablitys = new HardCodedAblitys(this);
        ablitys.registerHardCodedAbility(new TNT());
        isManager = new ItemManager(this);
        locManager = new LocationManager(this);
        invManager = new InventoryManager(this);
        kitManager = new KitManager(this);
        tmpDatManager = new TempDataManager();
        regListeners();
        regCommands();
    }

    @Override
    public void onDisable() {

    }

    private void regListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new EntityDeath(), this);
        manager.registerEvents(new PlayerDeath(this), this);
        manager.registerEvents(new PlayerJoin(this), this);
        manager.registerEvents(new PlayerLeave(), this);
        manager.registerEvents(new Interact(this), this);
        manager.registerEvents(new PlayerHunger(), this);
        manager.registerEvents(new InventoryClick(this), this);
    }

    private void regCommands() {
        CommandManager.regCommand(new Setspawn(this));
        CommandManager.regCommand(new Dev(this));
    }
}
