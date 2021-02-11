package com.some_catch_vc.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.some_catch_vc.game.SomeCatchVC;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Some Catch";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new SomeCatchVC(), config);
	}
}
