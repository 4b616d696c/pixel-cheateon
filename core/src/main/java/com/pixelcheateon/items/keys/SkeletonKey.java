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

package com.pixelcheateon.items.keys;

import com.pixelcheateon.Dungeon;
import com.pixelcheateon.PCSettings;
import com.pixelcheateon.PixelCheateon;
import com.pixelcheateon.actors.hero.Hero;
import com.pixelcheateon.sprites.ItemSpriteSheet;
import com.pixelcheateon.windows.WndSupportPrompt;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

import java.io.IOException;

public class SkeletonKey extends Key {
	
	{
		image = ItemSpriteSheet.SKELETON_KEY;
	}
	
	public SkeletonKey() {
		this( 0 );
	}
	
	public SkeletonKey( int depth ) {
		super();
		this.depth = depth;
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		if(!PCSettings.supportNagged()){
			try {
				Dungeon.saveAll();
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						PixelCheateon.scene().add(new WndSupportPrompt());
					}
				});
			} catch (IOException e) {
				PixelCheateon.reportException(e);
			}
			
		}
		
		return super.doPickUp(hero, pos);
	}

}
