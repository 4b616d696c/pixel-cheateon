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

package com.pixelcheateon.items;

import com.pixelcheateon.Assets;
import com.pixelcheateon.actors.Actor;
import com.pixelcheateon.actors.hero.Hero;
import com.pixelcheateon.actors.hero.Talent;
import com.pixelcheateon.actors.hero.abilities.ArmorAbility;
import com.pixelcheateon.actors.hero.abilities.Ratmogrify;
import com.pixelcheateon.effects.Speck;
import com.pixelcheateon.items.armor.Armor;
import com.pixelcheateon.items.armor.ClassArmor;
import com.pixelcheateon.messages.Messages;
import com.pixelcheateon.scenes.GameScene;
import com.pixelcheateon.sprites.HeroSprite;
import com.pixelcheateon.sprites.ItemSpriteSheet;
import com.pixelcheateon.utils.GLog;
import com.pixelcheateon.windows.WndChooseAbility;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class KingsCrown extends Item {
	
	private static final String AC_WEAR = "WEAR";
	
	{
		image = ItemSpriteSheet.CROWN;

		defaultAction = AC_WEAR;

		unique = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_WEAR );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_WEAR)) {

			curUser = hero;
			if (hero.belongings.armor() != null){
				GameScene.show( new WndChooseAbility(this, hero.belongings.armor(), hero));
			} else {
				GLog.w( Messages.get(this, "naked"));
			}
			
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	public void upgradeArmor(Hero hero, Armor armor, ArmorAbility ability) {

		detach(hero.belongings.backpack);

		hero.sprite.emitter().burst( Speck.factory( Speck.CROWN), 12 );
		hero.spend(Actor.TICK);
		hero.busy();

		if (armor != null){

			if (ability instanceof Ratmogrify){
				GLog.p(Messages.get(this, "ratgraded"));
			} else {
				GLog.p(Messages.get(this, "upgraded"));
			}

			ClassArmor classArmor = ClassArmor.upgrade(hero, armor);
			if (hero.belongings.armor == armor) {

				hero.belongings.armor = classArmor;
				((HeroSprite) hero.sprite).updateArmor();
				classArmor.activate(hero);

			} else {

				armor.detach(hero.belongings.backpack);
				classArmor.collect(hero.belongings.backpack);

			}
		}

		hero.armorAbility = ability;
		Talent.initArmorTalents(hero);

		hero.sprite.operate( hero.pos );
		Sample.INSTANCE.play( Assets.Sounds.MASTERY );
	}

}
