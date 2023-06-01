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

package com.pixelcheateon.plants;

import com.pixelcheateon.Dungeon;
import com.pixelcheateon.actors.Char;
import com.pixelcheateon.actors.blobs.Blob;
import com.pixelcheateon.actors.blobs.Fire;
import com.pixelcheateon.actors.buffs.Buff;
import com.pixelcheateon.actors.buffs.FireImbue;
import com.pixelcheateon.actors.hero.Hero;
import com.pixelcheateon.actors.hero.HeroSubClass;
import com.pixelcheateon.effects.CellEmitter;
import com.pixelcheateon.effects.particles.FlameParticle;
import com.pixelcheateon.scenes.GameScene;
import com.pixelcheateon.sprites.ItemSpriteSheet;

public class Firebloom extends Plant {
	
	{
		image = 1;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
			Buff.affect(ch, FireImbue.class).set( FireImbue.DURATION*0.3f );
		}
		
		GameScene.add( Blob.seed( pos, 2, Fire.class ) );
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).burst( FlameParticle.FACTORY, 5 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FIREBLOOM;

			plantClass = Firebloom.class;
		}
	}
}
