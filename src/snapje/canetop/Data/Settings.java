package snapje.canetop.Data;

import org.bukkit.Bukkit;
import snapje.canetop.Core.Main;
import snapje.canetop.Utils.Check;

import java.util.ArrayList;
import java.util.List;

public class Settings {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

    private static Settings instance;
    public static Settings getInstance() {return instance;}

    private boolean CaneTopGUIEnabled;
    public boolean caneTopGuiEnabled() {return CaneTopGUIEnabled;}
    public void setGUIEnabled(boolean guiEnabled) {
        CaneTopGUIEnabled = guiEnabled;
        Main.getInstance().getConfig().set("CaneTop.GUI.enabled", guiEnabled);
        Main.getInstance().saveConfig();
    }

    private String title;
    public String getCaneTopGUITitle() {return title;}
    public void setGUITitle(String newTitle) {
        title = newTitle;
        Main.getInstance().getConfig().set("CaneTop.GUI.title", newTitle);
        Main.getInstance().saveConfig();
    }

    private String SkulldisplayName;
    public String getSkullDisplayName() {return SkulldisplayName;}
    public void setSkulldisplayName(String newDisplayname) {
        SkulldisplayName = newDisplayname;
        Main.getInstance().getConfig().set("CaneTop.GUI.displayName", newDisplayname);
        Main.getInstance().saveConfig();
    }

    private List<String> lore = new ArrayList<>();
    public List<String> getSkullLore() {return lore;}
    public void setSkullLore(List<String> newLore) {
        lore = newLore;
        Main.getInstance().getConfig().set("CaneTop.GUI.lore", newLore);
        Main.getInstance().saveConfig();
    }

    private int topSize;
    public int getTopSize() {return topSize;}
    public void setTopSize(int newSize) {
        this.topSize = newSize;
        Main.getInstance().getConfig().set("CaneTop.GUI.size", newSize);
        Main.getInstance().saveConfig();
    }

    public Settings() {
        instance = this;

        CaneTopGUIEnabled = CaneTopGUIIsEnabled();
        SkulldisplayName = getDisplayName();
        lore = getLore();
        title = getTitle();
        topSize = getTop();
    }

    private boolean CaneTopGUIIsEnabled() {
            String path = "CaneTop.GUI.enabled";
            boolean is = true;

            if (DataHandler.getInstance().getConfig().get(path) == null) return is;
            if (DataHandler.getInstance().getConfig().isBoolean(path) == false) return is;
            is = DataHandler.getInstance().getConfig().getBoolean(path);

            return is;
    }

    private String getTitle() {
        String path = "CaneTop.GUI.title";
        String s = "&b&lCaneTop";

        if (DataHandler.getInstance().getConfig().get(path) == null) return s;
        if (DataHandler.getInstance().getConfig().getString(path) == null) return s;
        s = DataHandler.getInstance().getConfig().getString(path);

        return s;
    }


    private String getDisplayName() {
            String path = "CaneTop.GUI.displayName";
            String s = "&3{PLACE} &b{PLAYER}";

            if (DataHandler.getInstance().getConfig().get(path) == null) return s;
            if (DataHandler.getInstance().getConfig().getString(path) == null) return s;
            s = DataHandler.getInstance().getConfig().getString(path);

            return s;
    }

    private List<String> getLore() {
        String path = "CaneTop.GUI.lore";
        List<String> lore = new ArrayList<>();
        lore.add("&ffarmed {SCORE} canes.");

        if (DataHandler.getInstance().getConfig().get(path) == null) return lore;
        if (DataHandler.getInstance().getConfig().getStringList(path) == null) return lore;
        lore.clear();
        lore = DataHandler.getInstance().getConfig().getStringList(path);

        return lore;
    }

    private int getTop() {
        String path = "CaneTop.GUI.size";

        int returned = 16;
        String number = "16";

        if (DataHandler.getInstance().getConfig().get(path) == null) return 16;
        if (DataHandler.getInstance().getConfig().getString(path) == null) return 16;
        number = DataHandler.getInstance().getConfig().getString(path);
        if(Check.isInteger(number) == false) return 16;
        returned = Integer.parseInt(number);

        return returned;
    }


}
