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

package com.pixelcheateon.items.scrolls;

import com.pixelcheateon.Assets;
import com.pixelcheateon.Dungeon;
import com.pixelcheateon.actors.buffs.Buff;
import com.pixelcheateon.actors.buffs.Drowsy;
import com.pixelcheateon.actors.mobs.Mob;
import com.pixelcheateon.effects.Speck;
import com.pixelcheateon.messages.Messages;
import com.pixelcheateon.sprites.ItemSpriteSheet;
import com.pixelcheateon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfLullaby extends Scroll {

	{
		icon = ItemSpriteSheet.Icons.SCROLL_LULLABY;
	}

	@Override
	public void doRead() {
		
		curUser.sprite.centerEmitter().start( Speck.factory( Speck.NOTE ), 0.3f, 5 );
		Sample.INSTANCE.play( Assets.Sounds.LULLABY );

		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				Buff.affect( mob, Drowsy.class );
				mob.sprite.centerEmitter().start( Speck.factory( Speck.NOTE ), 0.3f, 5 );
			}
		}

		Buff.affect( curUser, Drowsy.class );

		GLog.i( Messages.get(this, "sooth") );

		identify();
		readAnimation();
	}
	
	@Override
	public int value() {
		return isKnown() ? 40 * quantity : super.value();
	}
}
