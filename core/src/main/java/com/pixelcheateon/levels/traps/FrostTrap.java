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
import com.pixelcheateon.actors.blobs.Freezing;
import com.pixelcheateon.effects.Splash;
import com.pixelcheateon.scenes.GameScene;
import com.pixelcheateon.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class FrostTrap extends Trap {

	{
		color = WHITE;
		shape = STARS;
	}

	@Override
	public void activate() {
		
		if (Dungeon.level.heroFOV[ pos ]){
			Splash.at( pos, 0xFFB2D6FF, 5);
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
		}
		
		PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.solid, null ), 2 );
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				GameScene.add(Blob.seed(i, 20, Freezing.class));
			}
		}
	}
}
