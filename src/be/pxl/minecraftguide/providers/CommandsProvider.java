package be.pxl.minecraftguide.providers;

import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import be.pxl.minecraftguide.model.Command;

public class CommandsProvider extends ContentProvider {
	public static final String COL_COMID = "_id";
	public static final String COL_COMCAT = "commandCategory";
	public static final String COL_COMTITLE = "commandTitle";
	public static final String COL_COMDESC = "commandDescription";
	
	private static final String[] columnNames = {COL_COMID, COL_COMCAT, COL_COMTITLE, COL_COMDESC };
	
	public static final String AUTHORITY = "be.pxl.minecraftguide.providers.commandsprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/*");
	
	private List<Command> commands;
	
	@Override
	public boolean onCreate() {
		commands.add(new Command(1, "Chat commands", "melp, ? [page, commandname]",
				"Shows a list of available commands per page or further information to the commandname." +
				"\r\nNote: the multiplayer-only commands are not listed in single-player games, even when open to LAN players."));
		commands.add(new Command(2, "Chat commands", "me <actiontext>",
				"Similar to the /emote or /action, and the same as the /me commands in IRC clients, the /me command sends a narrative message to the other players in the form of * Yourname actiontext (e.g., * Notch sneezes. or * Notch exploded. )."));
		commands.add(new Command(3, "Chat commands", "msg, tell, w <playername>", 
				"Used to send a private message to a player on the server. Very useful on servers if you need to ask/tell something from/to another player without other players seeing."));
		commands.add(new Command(4, "Player commands", "achievement give <achievement> [playername]",
				"Gives a player an achievement, and all prerequisite achievements."));
		commands.add(new Command(5, "Player commands", "effect <playername> <effect> [seconds] [amplifier]",
				"Gives the targeted player the specified effect for the specified time (default is 30 seconds)." +
				"\r\nEffects have a limit of 1,000,000 seconds, and the amplifier field has a limit of 255."));
		commands.add(new Command(6, "Player commands", "effect <playername> clear",
				"Clears all effects on playername."));
		commands.add(new Command(7, "Player commands", "enchant <playername> <enchantment ID> [enchantment level]",
				"Enchants the item that the player is currently holding, according to enchantment ID."));
		commands.add(new Command(8, "Player commands", "give <playername> <item> [amount] [metadata] [dataTag]",
				"Spawns amount (defaults to 1) of the item defined by item with the specified metadata (defaults to 0) at playername's location."));
		commands.add(new Command(9, "Player commands", "kill <playername>",
				"Inflicts 1000 (Heart.svg × 500) damage to the playername, killing them. (Even an extreme Resistance /effect doesn't protect)."));
		commands.add(new Command(10, "Server commands", "debug true|false",
				"Starts a new debug profiling session or stops the session currently running." +
				"\r\nIt notifies about potential performance bottlenecks in the console when active and creates a profiler results file in the folder debug when stopped."));
		commands.add(new Command(11, "Server commands", "defaultgamemode survival | creative | adventure",
				"Sets the default game mode that is shown on the world selection menu. New players that join the world will be put into the default game mode."));
		commands.add(new Command(12, "Server commands", "difficulty peaceful | easy | normal | hard",
				"Changes the difficulty. The difficulties can be abbreviated to p/e/n/h or 0/1/2/3 respectively."));
		commands.add(new Command(13, "Server commands", "gamerule <rulename> [true | false]",
				"Activates or deactivates the rulename. If true/false is not given, displays the current status of rulename. Available rules are:" +
				"\r\n- commandBlockOutput - Whether command blocks should notify admins when they perform commands." +
				"\r\ndoFireTick - Whether fire should spread." +
				"\r\ndoMobLoot - Whether mobs should drop items." +
				"\r\ndoMobSpawning - Whether mobs should naturally spawn." +
				"\r\ndoTileDrops - Whether blocks should have drops." +
				"\r\nkeepInventory - Whether the player should keep items in their inventory if they die." +
				"\r\nmobGriefing - Whether creepers, endermen, ghasts, and withers should be able to change blocks, or zombies, skeletons, and zombie pigmen can pick up items." +
				"\r\nnaturalRegeneration - Whether the player can regenerate health naturally if their hunger is at a regenerable state." +
				"\r\ndoDaylightCycle - Whether the day/night cycle is in effect or not."));
		commands.add(new Command(14, "Environment commands", "time set <number | day | night>",
				"Sets the world time. Number is an integer between 0 and 24000, inclusive, where 0 is dawn, 6000 midday, 12000 dusk and 18000 midnight."));
		commands.add(new Command(15, "Environment commands", "time add <number>",
				"Increments the world time. Number is an integer between 0 and 24000."));
		commands.add(new Command(16, "Environment Commands", "toggledownfall",
				"Toggles rain and snow."));
		commands.add(new Command(17, "Environment commands", "weather (clear | rain | thunder) [seconds]",
				"Changes the weather for the specified duration."));
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String category, String[] selectionArgs, String sortOrder) {
		MatrixCursor mxCur = new MatrixCursor(columnNames);
		RowBuilder rb;
		
		for (Command com : commands) {
			if (com.getCommandCategory() == category) {
				rb = mxCur.newRow();
				rb.add(com.get_id());
				rb.add(com.getCommandTitle());
				rb.add(com.getCommandDescription());
			}
		}
		return mxCur;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
