/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.pixelcheateon;

import com.badlogic.gdx.Input;
import com.watabou.input.ControllerHandler;
import com.watabou.input.GameAction;
import com.watabou.input.KeyBindings;
import com.watabou.utils.Bundle;
import com.watabou.utils.FileUtils;

import java.io.IOException;
import java.util.LinkedHashMap;

public class PCAction extends GameAction {

	protected PCAction(String name ){
		super( name );
	}

	//--New references to existing actions from GameAction
	public static final GameAction NONE  = GameAction.NONE;
	public static final GameAction BACK  = GameAction.BACK;

	public static final GameAction LEFT_CLICK   = GameAction.LEFT_CLICK;
	public static final GameAction RIGHT_CLICK  = GameAction.RIGHT_CLICK;
	public static final GameAction MIDDLE_CLICK = GameAction.MIDDLE_CLICK;
	//--

	public static final GameAction N            = new PCAction("n");
	public static final GameAction W            = new PCAction("w");
	public static final GameAction S            = new PCAction("s");
	public static final GameAction E            = new PCAction("e");
	public static final GameAction NW           = new PCAction("nw");
	public static final GameAction NE           = new PCAction("ne");
	public static final GameAction SW           = new PCAction("sw");
	public static final GameAction SE           = new PCAction("se");
	public static final GameAction WAIT_OR_PICKUP   = new PCAction("wait_or_pickup");

	public static final GameAction INVENTORY    = new PCAction("inventory");
	public static final GameAction INVENTORY_SELECTOR   = new PCAction("inventory_selector");
	public static final GameAction QUICKSLOT_SELECTOR   = new PCAction("quickslot_selector");
	public static final GameAction QUICKSLOT_1  = new PCAction("quickslot_1");
	public static final GameAction QUICKSLOT_2  = new PCAction("quickslot_2");
	public static final GameAction QUICKSLOT_3  = new PCAction("quickslot_3");
	public static final GameAction QUICKSLOT_4  = new PCAction("quickslot_4");
	public static final GameAction QUICKSLOT_5  = new PCAction("quickslot_5");
	public static final GameAction QUICKSLOT_6  = new PCAction("quickslot_6");

	public static final GameAction BAG_1        = new PCAction("bag_1");
	public static final GameAction BAG_2        = new PCAction("bag_2");
	public static final GameAction BAG_3        = new PCAction("bag_3");
	public static final GameAction BAG_4        = new PCAction("bag_4");
	public static final GameAction BAG_5        = new PCAction("bag_5");

	public static final GameAction EXAMINE      = new PCAction("examine");
	public static final GameAction WAIT         = new PCAction("wait");
	public static final GameAction REST         = new PCAction("rest");

	public static final GameAction TAG_ATTACK   = new PCAction("tag_attack");
	public static final GameAction TAG_ACTION   = new PCAction("tag_action");
	public static final GameAction TAG_LOOT     = new PCAction("tag_loot");
	public static final GameAction TAG_RESUME   = new PCAction("tag_resume");

	public static final GameAction CYCLE        = new PCAction("cycle");

	public static final GameAction HERO_INFO    = new PCAction("hero_info");
	public static final GameAction JOURNAL      = new PCAction("journal");

	public static final GameAction ZOOM_IN      = new PCAction("zoom_in");
	public static final GameAction ZOOM_OUT     = new PCAction("zoom_out");

	private static final LinkedHashMap<Integer, GameAction> defaultBindings = new LinkedHashMap<>();
	static {
		defaultBindings.put( Input.Keys.ESCAPE,         PCAction.BACK );
		defaultBindings.put( Input.Keys.BACKSPACE,      PCAction.BACK );

		defaultBindings.put( Input.Keys.W,              PCAction.N );
		defaultBindings.put( Input.Keys.A,              PCAction.W );
		defaultBindings.put( Input.Keys.S,              PCAction.S );
		defaultBindings.put( Input.Keys.D,              PCAction.E );
		defaultBindings.put( Input.Keys.SPACE,          PCAction.WAIT_OR_PICKUP);

		defaultBindings.put( Input.Keys.UP,             PCAction.N );
		defaultBindings.put( Input.Keys.LEFT,           PCAction.W );
		defaultBindings.put( Input.Keys.DOWN,           PCAction.S );
		defaultBindings.put( Input.Keys.RIGHT,          PCAction.E );

		defaultBindings.put( Input.Keys.NUMPAD_8,       PCAction.N );
		defaultBindings.put( Input.Keys.NUMPAD_4,       PCAction.W );
		defaultBindings.put( Input.Keys.NUMPAD_2,       PCAction.S );
		defaultBindings.put( Input.Keys.NUMPAD_6,       PCAction.E );
		defaultBindings.put( Input.Keys.NUMPAD_7,       PCAction.NW );
		defaultBindings.put( Input.Keys.NUMPAD_9,       PCAction.NE );
		defaultBindings.put( Input.Keys.NUMPAD_1,       PCAction.SW );
		defaultBindings.put( Input.Keys.NUMPAD_3,       PCAction.SE );
		defaultBindings.put( Input.Keys.NUMPAD_5,       PCAction.WAIT_OR_PICKUP );

		defaultBindings.put( Input.Keys.F,              PCAction.INVENTORY );
		defaultBindings.put( Input.Keys.I,              PCAction.INVENTORY );
		defaultBindings.put( Input.Keys.NUM_1,          PCAction.QUICKSLOT_1 );
		defaultBindings.put( Input.Keys.NUM_2,          PCAction.QUICKSLOT_2 );
		defaultBindings.put( Input.Keys.NUM_3,          PCAction.QUICKSLOT_3 );
		defaultBindings.put( Input.Keys.NUM_4,          PCAction.QUICKSLOT_4 );
		defaultBindings.put( Input.Keys.NUM_5,          PCAction.QUICKSLOT_5 );
		defaultBindings.put( Input.Keys.NUM_6,          PCAction.QUICKSLOT_6 );

		defaultBindings.put( Input.Keys.F1,             PCAction.BAG_1 );
		defaultBindings.put( Input.Keys.F2,             PCAction.BAG_2 );
		defaultBindings.put( Input.Keys.F3,             PCAction.BAG_3 );
		defaultBindings.put( Input.Keys.F4,             PCAction.BAG_4 );
		defaultBindings.put( Input.Keys.F5,             PCAction.BAG_5 );

		defaultBindings.put( Input.Keys.E,              PCAction.EXAMINE );
		defaultBindings.put( Input.Keys.Z,              PCAction.REST );

		defaultBindings.put( Input.Keys.Q,              PCAction.TAG_ATTACK );
		defaultBindings.put( Input.Keys.TAB,            PCAction.CYCLE);
		defaultBindings.put( Input.Keys.X,              PCAction.TAG_ACTION );
		defaultBindings.put( Input.Keys.C,              PCAction.TAG_LOOT );
		defaultBindings.put( Input.Keys.ENTER,          PCAction.TAG_LOOT );
		defaultBindings.put( Input.Keys.R,              PCAction.TAG_RESUME );

		defaultBindings.put( Input.Keys.H,              PCAction.HERO_INFO );
		defaultBindings.put( Input.Keys.J,              PCAction.JOURNAL );

		defaultBindings.put( Input.Keys.PLUS,           PCAction.ZOOM_IN );
		defaultBindings.put( Input.Keys.EQUALS,         PCAction.ZOOM_IN );
		defaultBindings.put( Input.Keys.MINUS,          PCAction.ZOOM_OUT );
	}

	public static LinkedHashMap<Integer, GameAction> getDefaults() {
		return new LinkedHashMap<>(defaultBindings);
	}

	private static final LinkedHashMap<Integer, GameAction> defaultControllerBindings = new LinkedHashMap<>();
	static {
		defaultControllerBindings.put( Input.Keys.BUTTON_START,     PCAction.BACK );
		defaultControllerBindings.put( Input.Keys.BUTTON_SELECT,    PCAction.JOURNAL );

		defaultControllerBindings.put( Input.Keys.BUTTON_R2,        PCAction.LEFT_CLICK );
		defaultControllerBindings.put( Input.Keys.BUTTON_THUMBR,    PCAction.LEFT_CLICK );
		defaultControllerBindings.put( Input.Keys.BUTTON_L2,        PCAction.RIGHT_CLICK );

		defaultControllerBindings.put( Input.Keys.DPAD_UP+1000,     PCAction.TAG_ACTION );
		defaultControllerBindings.put( Input.Keys.DPAD_LEFT+1000,   PCAction.TAG_LOOT );
		defaultControllerBindings.put( Input.Keys.DPAD_DOWN+1000,   PCAction.TAG_RESUME );
		defaultControllerBindings.put( Input.Keys.DPAD_RIGHT+1000,  PCAction.CYCLE );

		defaultControllerBindings.put( Input.Keys.BUTTON_THUMBL,    PCAction.WAIT_OR_PICKUP );

		defaultControllerBindings.put( Input.Keys.BUTTON_R1,        PCAction.ZOOM_IN );
		defaultControllerBindings.put( Input.Keys.BUTTON_L1,        PCAction.ZOOM_OUT );

		defaultControllerBindings.put( Input.Keys.BUTTON_A,         PCAction.TAG_ATTACK );
		defaultControllerBindings.put( Input.Keys.BUTTON_B,         PCAction.EXAMINE );
		defaultControllerBindings.put( Input.Keys.BUTTON_X,         PCAction.QUICKSLOT_SELECTOR );
		defaultControllerBindings.put( Input.Keys.BUTTON_Y,         PCAction.INVENTORY_SELECTOR );
	}

	public static LinkedHashMap<Integer, GameAction> getControllerDefaults() {
		return new LinkedHashMap<>(defaultControllerBindings);
	}

	static {
		//hard bindings for android devices
		KeyBindings.addHardBinding( Input.Keys.BACK, PCAction.BACK );
		KeyBindings.addHardBinding( Input.Keys.MENU, PCAction.INVENTORY );

		//hard bindings for desktop fullscreen toggle
		//not bound to specific game actions, see PixelScene
		//Note that user-entered bindings can override these individually, and that's fine.
		KeyBindings.addHardBinding( Input.Keys.ALT_RIGHT, PCAction.NONE );
		KeyBindings.addHardBinding( Input.Keys.ENTER, PCAction.NONE );
	}

	//we only save/loads keys which differ from the default configuration.
	private static final String BINDINGS_FILE = "keybinds.dat";

	public static void loadBindings(){

		if (!KeyBindings.getAllBindings().isEmpty()){
			return;
		}

		try {
			Bundle b = FileUtils.bundleFromFile(BINDINGS_FILE);

			Bundle firstKeys = b.getBundle("first_keys");
			Bundle secondKeys = b.getBundle("second_keys");
			Bundle thirdKeys = b.getBundle("third_keys");

			LinkedHashMap<Integer, GameAction> defaults = getDefaults();
			LinkedHashMap<Integer, GameAction> merged = new LinkedHashMap<>();

			for (GameAction a : allActions()) {
				if (firstKeys.contains(a.name()) && !ControllerHandler.icControllerKey(firstKeys.getInt(a.name()))) {
					if (firstKeys.getInt(a.name()) == 0){
						continue; //we have no keys assigned to this action, move to the next one
					} else {
						merged.put(firstKeys.getInt(a.name()), a);
						defaults.remove(firstKeys.getInt(a.name())); //prevent duplicates in other actions
					}
				} else {
					//if we have no custom key here, find the first one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

				if (secondKeys.contains(a.name()) && !ControllerHandler.icControllerKey(secondKeys.getInt(a.name()))) {
					if (secondKeys.getInt(a.name()) == 0){
						continue; //we have no more keys assigned to this action, move to the next one
					} else {
						merged.put(secondKeys.getInt(a.name()), a);
						defaults.remove(secondKeys.getInt(a.name()));
					}
				} else {
					//if we have no custom key here, find the next one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

				if (thirdKeys.contains(a.name()) && !ControllerHandler.icControllerKey(thirdKeys.getInt(a.name()))) {
					if (thirdKeys.getInt(a.name()) == 0){
						continue; //we have no more keys assigned to this action, move to the next one
					} else {
						merged.put(thirdKeys.getInt(a.name()), a);
						defaults.remove(thirdKeys.getInt(a.name()));
					}
				} else {
					//if we have no custom key here, find the next one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

			}

			KeyBindings.setAllBindings(merged);

			defaults = getControllerDefaults();
			merged.clear();

			Bundle firstButtons = b.getBundle("first_keys_controller");
			Bundle secondButtons = b.getBundle("second_keys_controller");
			Bundle thirdButtons = b.getBundle("third_keys_controller");

			for (GameAction a : allActions()) {
				if (firstButtons.contains(a.name()) && ControllerHandler.icControllerKey(firstButtons.getInt(a.name()))) {
					if (firstButtons.getInt(a.name()) == 0){
						continue; //we have no keys assigned to this action, move to the next one
					} else {
						merged.put(firstButtons.getInt(a.name()), a);
						defaults.remove(firstButtons.getInt(a.name())); //prevent duplicates in other actions
					}
				} else {
					//if we have no custom key here, find the first one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

				if (secondButtons.contains(a.name()) && ControllerHandler.icControllerKey(secondButtons.getInt(a.name()))) {
					if (secondButtons.getInt(a.name()) == 0){
						continue; //we have no more keys assigned to this action, move to the next one
					} else {
						merged.put(secondButtons.getInt(a.name()), a);
						defaults.remove(secondButtons.getInt(a.name()));
					}
				} else {
					//if we have no custom key here, find the next one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

				if (thirdButtons.contains(a.name()) && ControllerHandler.icControllerKey(thirdButtons.getInt(a.name()))) {
					if (thirdButtons.getInt(a.name()) == 0){
						continue; //we have no more keys assigned to this action, move to the next one
					} else {
						merged.put(thirdButtons.getInt(a.name()), a);
						defaults.remove(thirdButtons.getInt(a.name()));
					}
				} else {
					//if we have no custom key here, find the next one from defaults and merge it
					for (int i : defaults.keySet()){
						if (defaults.get(i) == a){
							merged.put(i, defaults.remove(i));
							break;
						}
					}
				}

			}

			KeyBindings.setAllControllerBindings(merged);

		} catch (Exception e){
			KeyBindings.setAllBindings(getDefaults());
			KeyBindings.setAllControllerBindings(getControllerDefaults());
		}

	}

	public static void saveBindings(){

		Bundle b = new Bundle();

		Bundle firstKeys = new Bundle();
		Bundle secondKeys = new Bundle();
		Bundle thirdKeys = new Bundle();

		for (GameAction a : allActions()){
			int firstCur = 0;
			int secondCur = 0;
			int thirdCur = 0;
			int firstDef = 0;
			int secondDef = 0;
			int thirdDef = 0;

			for (int i : defaultBindings.keySet()){
				if (defaultBindings.get(i) == a){
					if (firstDef == 0) {
						firstDef = i;
					} else if (secondDef == 0) {
						secondDef = i;
					} else {
						thirdDef = i;
					}
				}
			}

			LinkedHashMap<Integer, GameAction> curBindings = KeyBindings.getAllBindings();
			for (int i : curBindings.keySet()){
				if (curBindings.get(i) == a){
					if (firstCur == 0) {
						firstCur = i;
					} else if (secondCur == 0) {
						secondCur = i;
					} else {
						thirdCur = i;
					}
				}
			}

			if (firstCur != firstDef){
				firstKeys.put(a.name(), firstCur);
			}
			if (secondCur != secondDef){
				secondKeys.put(a.name(), secondCur);
			}
			if (thirdCur != thirdDef){
				thirdKeys.put(a.name(), thirdCur);
			}

		}

		b.put("first_keys", firstKeys);
		b.put("second_keys", secondKeys);
		b.put("third_keys", thirdKeys);

		Bundle firstButtons = new Bundle();
		Bundle secondButtons = new Bundle();
		Bundle thirdButtons = new Bundle();

		for (GameAction a : allActions()){
			int firstCur = 0;
			int secondCur = 0;
			int thirdCur = 0;
			int firstDef = 0;
			int secondDef = 0;
			int thirdDef = 0;

			for (int i : defaultControllerBindings.keySet()){
				if (defaultControllerBindings.get(i) == a){
					if (firstDef == 0) {
						firstDef = i;
					} else if (secondDef == 0) {
						secondDef = i;
					} else {
						thirdDef = i;
					}
				}
			}

			LinkedHashMap<Integer, GameAction> curBindings = KeyBindings.getAllControllerBindings();
			for (int i : curBindings.keySet()){
				if (curBindings.get(i) == a){
					if (firstCur == 0) {
						firstCur = i;
					} else if (secondCur == 0) {
						secondCur = i;
					} else {
						thirdCur = i;
					}
				}
			}

			if (firstCur != firstDef){
				firstButtons.put(a.name(), firstCur);
			}
			if (secondCur != secondDef){
				secondButtons.put(a.name(), secondCur);
			}
			if (thirdCur != thirdDef){
				thirdButtons.put(a.name(), thirdCur);
			}

		}

		b.put("first_keys_controller", firstButtons);
		b.put("second_keys_controller", secondButtons);
		b.put("third_keys_controller", thirdButtons);

		try {
			FileUtils.bundleToFile(BINDINGS_FILE, b);
		} catch (IOException e) {
			PixelCheateon.reportException(e);
		}

	}

}
