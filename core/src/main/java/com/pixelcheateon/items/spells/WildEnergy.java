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

package com.pixelcheateon.items.spells;

import com.pixelcheateon.Assets;
import com.pixelcheateon.actors.buffs.ArtifactRecharge;
import com.pixelcheateon.actors.buffs.Buff;
import com.pixelcheateon.actors.buffs.Recharging;
import com.pixelcheateon.actors.hero.Hero;
import com.pixelcheateon.effects.SpellSprite;
import com.pixelcheateon.items.artifacts.Artifact;
import com.pixelcheateon.items.quest.MetalShard;
import com.pixelcheateon.items.scrolls.ScrollOfRecharging;
import com.pixelcheateon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.pixelcheateon.items.wands.CursedWand;
import com.pixelcheateon.mechanics.Ballistica;
import com.pixelcheateon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class WildEnergy extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.WILD_ENERGY;
		usesTargeting = true;
	}
	
	//we rely on cursedWand to do fx instead
	@Override
	protected void fx(Ballistica bolt, Callback callback) {
		CursedWand.cursedZap(this, curUser, bolt, callback);
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, final Hero hero) {
		Sample.INSTANCE.play( Assets.Sounds.LIGHTNING );
		Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
		ScrollOfRecharging.charge(hero);
		SpellSprite.show(hero, SpellSprite.CHARGE);

		hero.belongings.charge(1f);
		for (Buff b : hero.buffs()){
			if (b instanceof Artifact.ArtifactBuff){
				if (!((Artifact.ArtifactBuff) b).isCursed()) ((Artifact.ArtifactBuff) b).charge(hero, 4);
			}
		}

		Buff.affect(hero, Recharging.class, 8f);
		Buff.affect(hero, ArtifactRecharge.class).prolong( 8 ).ignoreHornOfPlenty = false;
	}
	
	@Override
	public int value() {
		//prices of ingredients, divided by output quantity, rounds down
		return (int)((50 + 50) * (quantity/5f));
	}
	
	public static class Recipe extends com.pixelcheateon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfMysticalEnergy.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 4;
			
			output = WildEnergy.class;
			outQuantity = 5;
		}
		
	}
}
