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

import com.pixelcheateon.scenes.GameScene;
import com.pixelcheateon.scenes.PixelScene;
import com.pixelcheateon.scenes.TitleScene;
import com.pixelcheateon.scenes.WelcomeScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.PlatformSupport;

public class PixelCheateon extends Game {

	//variable constants for specific older versions of shattered, used for data conversion
	//versions older than v1.2.3 are no longer supported, and data from them is ignored
	public static final int v1_2_3  = 628;
	public static final int v1_3_2  = 648;
	public static final int v1_4_3  = 668;

	public static final int v2_0_0  = 684;
	
	public PixelCheateon(PlatformSupport platform ) {
		super( sceneClass == null ? WelcomeScene.class : sceneClass, platform );

		//pre-v1.3.0
		com.watabou.utils.Bundle.addAlias(
				com.pixelcheateon.actors.buffs.Bleeding.class,
				"com.pixelcheateon.levels.features.Chasm$FallBleed" );
		com.watabou.utils.Bundle.addAlias(
				com.pixelcheateon.plants.Mageroyal.class,
				"com.pixelcheateon.plants.Dreamfoil" );
		com.watabou.utils.Bundle.addAlias(
				com.pixelcheateon.plants.Mageroyal.Seed.class,
				"com.pixelcheateon.plants.Dreamfoil$Seed" );

		com.watabou.utils.Bundle.addAlias(
				com.pixelcheateon.items.weapon.curses.Dazzling.class,
				"com.pixelcheateon.items.weapon.curses.Exhausting" );
		com.watabou.utils.Bundle.addAlias(
				com.pixelcheateon.items.weapon.curses.Explosive.class,
				"com.pixelcheateon.items.weapon.curses.Fragile" );
	}
	
	@Override
	public void create() {
		super.create();

		updateSystemUI();
		PCAction.loadBindings();
		
		Music.INSTANCE.enable( PCSettings.music() );
		Music.INSTANCE.volume( PCSettings.musicVol()* PCSettings.musicVol()/100f );
		Sample.INSTANCE.enable( PCSettings.soundFx() );
		Sample.INSTANCE.volume( PCSettings.SFXVol()* PCSettings.SFXVol()/100f );

		Sample.INSTANCE.load( Assets.Sounds.all );
		
	}

	@Override
	public void finish() {
		if (!DeviceCompat.isiOS()) {
			super.finish();
		} else {
			//can't exit on iOS (Apple guidelines), so just go to title screen
			switchScene(TitleScene.class);
		}
	}

	public static void switchNoFade(Class<? extends PixelScene> c){
		switchNoFade(c, null);
	}

	public static void switchNoFade(Class<? extends PixelScene> c, SceneChangeCallback callback) {
		PixelScene.noFade = true;
		switchScene( c, callback );
	}
	
	public static void seamlessResetScene(SceneChangeCallback callback) {
		if (scene() instanceof PixelScene){
			((PixelScene) scene()).saveWindows();
			switchNoFade((Class<? extends PixelScene>) sceneClass, callback );
		} else {
			resetScene();
		}
	}
	
	public static void seamlessResetScene(){
		seamlessResetScene(null);
	}
	
	@Override
	protected void switchScene() {
		super.switchScene();
		if (scene instanceof PixelScene){
			((PixelScene) scene).restoreWindows();
		}
	}
	
	@Override
	public void resize( int width, int height ) {
		if (width == 0 || height == 0){
			return;
		}

		if (scene instanceof PixelScene &&
				(height != Game.height || width != Game.width)) {
			PixelScene.noFade = true;
			((PixelScene) scene).saveWindows();
		}

		super.resize( width, height );

		updateDisplaySize();

	}
	
	@Override
	public void destroy(){
		super.destroy();
		GameScene.endActorThread();
	}
	
	public void updateDisplaySize(){
		platform.updateDisplaySize();
	}

	public static void updateSystemUI() {
		platform.updateSystemUI();
	}
}