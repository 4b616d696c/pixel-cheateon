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

package com.pixelcheateon.items.armor.glyphs;

import com.pixelcheateon.actors.Char;
import com.pixelcheateon.actors.buffs.Charm;
import com.pixelcheateon.actors.buffs.Degrade;
import com.pixelcheateon.actors.buffs.Hex;
import com.pixelcheateon.actors.buffs.MagicalSleep;
import com.pixelcheateon.actors.buffs.Vulnerable;
import com.pixelcheateon.actors.buffs.Weakness;
import com.pixelcheateon.actors.hero.abilities.duelist.ElementalStrike;
import com.pixelcheateon.actors.hero.abilities.mage.ElementalBlast;
import com.pixelcheateon.actors.hero.abilities.mage.WarpBeacon;
import com.pixelcheateon.actors.mobs.DM100;
import com.pixelcheateon.actors.mobs.Eye;
import com.pixelcheateon.actors.mobs.Shaman;
import com.pixelcheateon.actors.mobs.Warlock;
import com.pixelcheateon.actors.mobs.YogFist;
import com.pixelcheateon.items.armor.Armor;
import com.pixelcheateon.items.bombs.Bomb;
import com.pixelcheateon.items.scrolls.ScrollOfRetribution;
import com.pixelcheateon.items.scrolls.ScrollOfTeleportation;
import com.pixelcheateon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.pixelcheateon.items.wands.CursedWand;
import com.pixelcheateon.items.wands.WandOfBlastWave;
import com.pixelcheateon.items.wands.WandOfDisintegration;
import com.pixelcheateon.items.wands.WandOfFireblast;
import com.pixelcheateon.items.wands.WandOfFrost;
import com.pixelcheateon.items.wands.WandOfLightning;
import com.pixelcheateon.items.wands.WandOfLivingEarth;
import com.pixelcheateon.items.wands.WandOfMagicMissile;
import com.pixelcheateon.items.wands.WandOfPrismaticLight;
import com.pixelcheateon.items.wands.WandOfTransfusion;
import com.pixelcheateon.items.wands.WandOfWarding;
import com.pixelcheateon.items.weapon.enchantments.Blazing;
import com.pixelcheateon.items.weapon.enchantments.Grim;
import com.pixelcheateon.items.weapon.enchantments.Shocking;
import com.pixelcheateon.levels.traps.DisintegrationTrap;
import com.pixelcheateon.levels.traps.GrimTrap;
import com.pixelcheateon.sprites.ItemSprite;
import com.watabou.utils.Random;

import java.util.HashSet;

public class AntiMagic extends Armor.Glyph {

	private static ItemSprite.Glowing TEAL = new ItemSprite.Glowing( 0x88EEFF );
	
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( MagicalSleep.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Weakness.class );
		RESISTS.add( Vulnerable.class );
		RESISTS.add( Hex.class );
		RESISTS.add( Degrade.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );

		RESISTS.add( Bomb.MagicalBomb.class );
		RESISTS.add( ScrollOfRetribution.class );
		RESISTS.add( ScrollOfPsionicBlast.class );
		RESISTS.add( ScrollOfTeleportation.class );

		RESISTS.add( ElementalBlast.class );
		RESISTS.add( CursedWand.class );
		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );

		RESISTS.add( ElementalStrike.class );
		RESISTS.add( Blazing.class );
		RESISTS.add( Shocking.class );
		RESISTS.add( Grim.class );

		RESISTS.add( WarpBeacon.class );
		
		RESISTS.add( DM100.LightningBolt.class );
		RESISTS.add( Shaman.EarthenBolt.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( YogFist.BrightFist.LightBeam.class );
		RESISTS.add( YogFist.DarkFist.DarkBolt.class );
	}
	
	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		//no proc effect, see:
		// Hero.damage
		// GhostHero.damage
		// Shadowclone.damage
		// ArmoredStatue.damage
		// PrismaticImage.damage
		return damage;
	}
	
	public static int drRoll( Char ch, int level ){
		return Random.NormalIntRange(
				Math.round(level * genericProcChanceMultiplier(ch)),
				Math.round((3 + (level*1.5f)) * genericProcChanceMultiplier(ch)));
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return TEAL;
	}

}