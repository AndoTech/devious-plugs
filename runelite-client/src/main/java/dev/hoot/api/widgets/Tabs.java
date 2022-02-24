package dev.hoot.api.widgets;

import dev.hoot.api.game.Game;
import dev.hoot.api.game.GameThread;
import dev.hoot.api.game.Vars;
import net.runelite.api.GameState;
import net.runelite.api.VarClientInt;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;

import java.util.Arrays;

public class Tabs
{
	public static void open(Tab tab)
	{
		Widget widget = Widgets.get(WidgetID.FIXED_VIEWPORT_GROUP_ID, tab.getChildId());
		if (widget != null)
		{
			widget.interact(0);
		}
	}

	public static void openInterface(Tab tab)
	{
		if (Game.getClient() == null || Game.getState() != GameState.LOGGED_IN)
		{
			return;
		}

		GameThread.invoke(() -> Game.getClient().runScript(915, tab.getIndex()));
	}

	public static boolean isOpen(Tab tab)
	{
		return Vars.getVarcInt(VarClientInt.INVENTORY_TAB) == Arrays.asList(Tab.values()).indexOf(tab);
	}
}
