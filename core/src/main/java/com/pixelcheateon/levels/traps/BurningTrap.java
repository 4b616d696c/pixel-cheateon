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

package com.pixelcheateon.levels.traps;

import com.pixelcheateon.Assets;
import com.pixelcheateon.Dungeon;
import com.pixelcheateon.actors.blobs.Blob;
import com.pixelcheateon.actors.blobs.Fire;
import com.pixelcheateon.effects.CellEmitter;
import com.pixelcheateon.effects.particles.FlameParticle;
import com.pixelcheateon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class BurningTrap extends Trap {

	{
		color = ORANGE;
		shape = DOTS;
	}

	@Override
	public void activate() {
		
		for( int i : PathFinder.NEIGHBOURS9) {
			if (!Dungeon.level.solid[pos + i]) {
				GameScene.add( Blob.seed( pos+i, 2, Fire.class ) );
				CellEmitter.get( pos+i ).burst( FlameParticle.FACTORY, 5 );
			}
		}
		Sample.INSTANCE.play(Assets.Sounds.BURNING);
	}
}
