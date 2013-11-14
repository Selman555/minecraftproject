package be.pxl.minecraftguide;

import java.util.ArrayList;
import java.util.List;

import be.pxl.minecraftguide.model.Command;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Commands extends FragmentActivity implements CommandsList.OnListItemSelectedListener {
	//____________BRON: http://developer.android.com/training/basics/fragments/communicating.html
	private List<Command> commands; //Lijst van alle bestaande commandos
	private static String[][] detailValues; //Lijst van alle waarden voor het detailgedeelte
	private static int position;
	public static String[] listValues; //Lijst van alle waarden voor de lijst
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		createCommands();
		setContentView(R.layout.commands);
		if (position != 0) {
			CommandDetails details = (CommandDetails) getSupportFragmentManager().findFragmentById(R.id.commanddetails);
			if (details != null) {
				details.updateArticleView(detailValues[position]);
			}
		}
	}
	
	/***
	 * Maakt de volledige lijst met commando's aan en splitst deze in lijst en detailwaarden
	 */
	public void createCommands() {
		commands = new ArrayList<Command>();
		commands.add(new Command(1, "Chat commands", "Help",
				"help, ? [page, commandname]\r\n\r\nShows a list of available commands per page or further information to the commandname." +
				"\r\nNote: the multiplayer-only commands are not listed in single-player games, even when open to LAN players."));
		commands.add(new Command(2, "Chat commands", "Me",
				"me <actiontext>\r\n\r\nSimilar to the /emote or /action, and the same as the /me commands in IRC clients, the /me command sends a narrative message to the other players in the form of * Yourname actiontext (e.g., * Notch sneezes. or * Notch exploded. )."));
		commands.add(new Command(3, "Chat commands", "Msg, tell, w", 
				"msg, tell, w <playername>\r\n\r\nUsed to send a private message to a player on the server. Very useful on servers if you need to ask/tell something from/to another player without other players seeing."));
		commands.add(new Command(4, "Player commands", "Achievement give",
				"achievement give <achievement> [playername]\r\n\r\nGives a player an achievement, and all prerequisite achievements."));
		commands.add(new Command(5, "Player commands", "Effect",
				"effect <playername> <effect> [seconds] [amplifier]\r\n\r\nGives the targeted player the specified effect for the specified time (default is 30 seconds)." +
				"\r\nEffects have a limit of 1,000,000 seconds, and the amplifier field has a limit of 255."));
		commands.add(new Command(6, "Player commands", "Effect clear",
				"effect <playername> clear\r\n\r\nClears all effects on playername."));
		commands.add(new Command(7, "Player commands", "Enchant",
				"enchant <playername> <enchantment ID> [enchantment level]\r\n\r\nEnchants the item that the player is currently holding, according to enchantment ID."));
		commands.add(new Command(8, "Player commands", "Give",
				"give <playername> <item> [amount] [metadata] [dataTag]\r\n\r\nSpawns amount (defaults to 1) of the item defined by item with the specified metadata (defaults to 0) at playername's location."));
		commands.add(new Command(9, "Player commands", "Kill",
				"kill <playername>\r\n\r\nInflicts 1000 (Heart.svg × 500) damage to the playername, killing them. (Even an extreme Resistance /effect doesn't protect)."));
		commands.add(new Command(10, "Server commands", "Debug",
				"debug true|false\r\n\r\nStarts a new debug profiling session or stops the session currently running." +
				"\r\nIt notifies about potential performance bottlenecks in the console when active and creates a profiler results file in the folder debug when stopped."));
		commands.add(new Command(11, "Server commands", "Default game mode",
				"defaultgamemode survival | creative | adventure\r\n\r\nSets the default game mode that is shown on the world selection menu. New players that join the world will be put into the default game mode."));
		commands.add(new Command(12, "Server commands", "Difficulty",
				"difficulty peaceful | easy | normal | hard\r\n\r\nChanges the difficulty. The difficulties can be abbreviated to p/e/n/h or 0/1/2/3 respectively."));
		commands.add(new Command(13, "Server commands", "Gamerule",
				"gamerule <rulename> [true | false]\r\n\r\nActivates or deactivates the rulename. If true/false is not given, displays the current status of rulename. Available rules are:" +
				"\r\n\r\ncommandBlockOutput - Whether command blocks should notify admins when they perform commands." +
				"\r\n\r\ndoFireTick - Whether fire should spread." +
				"\r\n\r\ndoMobLoot - Whether mobs should drop items." +
				"\r\n\r\ndoMobSpawning - Whether mobs should naturally spawn." +
				"\r\n\r\ndoTileDrops - Whether blocks should have drops." +
				"\r\n\r\nkeepInventory - Whether the player should keep items in their inventory if they die." +
				"\r\n\r\nmobGriefing - Whether creepers, endermen, ghasts, and withers should be able to change blocks, or zombies, skeletons, and zombie pigmen can pick up items." +
				"\r\n\r\nnaturalRegeneration - Whether the player can regenerate health naturally if their hunger is at a regenerable state." +
				"\r\n\r\ndoDaylightCycle - Whether the day/night cycle is in effect or not."));
		commands.add(new Command(14, "Environment commands", "Time set",
				"time set <number | day | night>\r\n\r\nSets the world time. Number is an integer between 0 and 24000, inclusive, where 0 is dawn, 6000 midday, 12000 dusk and 18000 midnight."));
		commands.add(new Command(15, "Environment commands", "Time add",
				"time add <number>\r\n\r\nIncrements the world time. Number is an integer between 0 and 24000."));
		commands.add(new Command(16, "Environment Commands", "toggledownfall",
				"Toggles rain and snow."));
		commands.add(new Command(17, "Environment commands", "Weather",
				"weather (clear | rain | thunder) [seconds]\r\n\r\nChanges the weather for the specified duration."));
		
		listValues = new String[commands.size()];
		detailValues = new String[commands.size()][2];
		
		for (int counter = 0; counter < commands.size(); counter++) {
			Command command = commands.get(counter);
			listValues[counter] = command.getCommandTitle();
			detailValues[counter][0] = command.getCommandTitle();
			detailValues[counter][1] = command.getCommandDescription();
		}
	}

	
	@Override
	public void onListItemSelected(int selectedPosition) {
		position = selectedPosition;
		
		CommandDetails details = (CommandDetails) getSupportFragmentManager().findFragmentById(R.id.commanddetails);
		if (details != null) {
			details.updateArticleView(detailValues[position]);
		} else {
			// Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            details = new CommandDetails();
            Bundle args = new Bundle();
            args.putStringArray("detail", detailValues[position]);
            details.setArguments(args);
        
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.commanddetails, details);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
		}
	}
}
