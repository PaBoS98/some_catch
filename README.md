# some_catch

How to set up a project:
1. Packages `(android, core, desktop)` must be declared as gradle modules.
2. Create a configuration to start the computer - `Edit Configuration -> Add New Configuration -> Application -> 
field(Main Class): com.some_catch_vc.game.desktop.DesktopLauncher, 
field(Working diroctory): #way#\some_catch\android\assets,
field(Use classpath of module): some_catch.desktop -> apply -> ok`.
3. Launch.

The compiled files are located:
1. Desktop -> `desktop -> build -> libs - > desktop-1.0.jar`.
2. Android ->  `android -> build -> outputs -> apk -> android-debug.apk`.

© Bohomaz Pavel | https://www.linkedin.com/in/pabos98/ |