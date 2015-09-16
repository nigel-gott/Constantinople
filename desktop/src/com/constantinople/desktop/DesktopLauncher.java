package com.constantinople.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.constantinople.Constantinople;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Constantinople";
        config.width = 1440;
        config.height = 900;
		new LwjglApplication(new Constantinople(), config);
	}
}
