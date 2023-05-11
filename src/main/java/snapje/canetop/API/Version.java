package snapje.canetop.API;

import org.bukkit.Bukkit;

public class Version {

	public static double getVersion() {
		String string = Bukkit.getServer().getVersion();
		if(string != null) {
		if(string.contains("1.7") || string.contains("1_7")) {
			return 1.07;
		}  else if(string.contains("1.8") || string.contains("1_8")) {
			return 1.08;
		} else if(string.contains("1.9") || string.contains("1_9")) {
			return 1.09;
		} else if(string.contains("1.10") || string.contains("1_10")) {
			return 1.10;
		} else if(string.contains("1.11") || string.contains("1_11")) {
			return 1.11;
		} else if(string.contains("1.12") || string.contains("1_12")) {
			return 1.12;
		} else if(string.contains("1.13") || string.contains("1_13")) {
			return 1.13;
		} else if(string.contains("1.14") || string.contains("1_14")) {
			return 1.14;
		} else if(string.contains("1.15") || string.contains("1_15")) {
			return 1.15;
		} else if(string.contains("1.16") || string.contains("1_16")) {
			return 1.16;
		} else if(string.contains("1.17") || string.contains("1_17")) {
			return 1.17;
		} else if(string.contains("1.18") || string.contains("1_18")) {
			return 1.18;
		} else if(string.contains("1.19") || string.contains("1_19")) {
			return 1.19;
		}
		}
		return 0;
	}
	
}
