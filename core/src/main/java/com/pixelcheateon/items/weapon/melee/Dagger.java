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

package com.pixelcheateon.items.weapon.melee;

import com.pixelcheateon.Assets;
import com.pixelcheateon.Dungeon;
import com.pixelcheateon.actors.Actor;
import com.pixelcheateon.actors.Char;
import com.pixelcheateon.actors.buffs.Buff;
import com.pixelcheateon.actors.buffs.Invisibility;
import com.pixelcheateon.actors.hero.Hero;
import com.pixelcheateon.actors.mobs.Mob;
import com.pixelcheateon.effects.CellEmitter;
import com.pixelcheateon.effects.Speck;
import com.pixelcheateon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Dagger extends MeleeWeapon {
	
	{
		image = ItemSpriteSheet.DAGGER;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.1f;

		tier = 1;
		
		bones = false;
	}

	@Override
	public int max(int lvl) {
		return  4*(tier+1) +    //8 base, down from 10
				lvl*(tier+1);   //scaling unchanged
	}
	
	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero hero = (Hero)owner;
			Char enemy = hero.enemy();
			if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)) {
				//deals 75% toward max to max on surprise, instead of min to max.
				int diff = max() - min();
				int damage = augment.damageFactor(Random.NormalIntRange(
						min() + Math.round(diff*0.75f),
						max()));
				int exStr = hero.STR() - STRReq();
				if (exStr > 0) {
					damage += Random.IntRange(0, exStr);
				}
				return damage;
			}
		}
		return super.damageRoll(owner);
	}

	@Override
	public float abilityChargeUse( Hero hero ) {
		return 2*super.abilityChargeUse(hero);
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		sneakAbility(hero, 8, this);
	}

	public static void sneakAbility(Hero hero, int invisTurns, MeleeWeapon wep){
		wep.beforeAbilityUsed(hero);
		Buff.affect(hero, Invisibility.class, invisTurns);
		hero.spendAndNext(Actor.TICK);
		CellEmitter.get( Dungeon.hero.pos ).burst( Speck.factory( Speck.WOOL ), 6 );
		Sample.INSTANCE.play( Assets.Sounds.PUFF );
		wep.afterAbilityUsed(hero);
	}

}
