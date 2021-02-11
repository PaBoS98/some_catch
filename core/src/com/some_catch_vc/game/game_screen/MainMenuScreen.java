package com.some_catch_vc.game.game_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.some_catch_vc.game.MyController;
import com.some_catch_vc.game.SomeCatchVC;

public class MainMenuScreen implements Screen {

    final SomeCatchVC game;

    // for camera and view
    Vector3 temp = new Vector3();
    OrthographicCamera camera;

    // catch
    Texture catch1;
    Texture catch2;
    Texture bonusCatch;
    Texture poisonCatch;
    Texture healCatch;
    Texture catcher;
    Sprite catch1Sprite;
    Sprite catch2Sprite;
    Sprite bonusCatchSprite;
    Sprite poisonCatchSprite;
    Sprite healCatchSprite;
    Sprite catcherSprite;

    Texture basket1;

    Texture basket2;

    Texture basket3;
    Texture basketClose3;

    Texture basket4;
    Texture basketClose4;

    Texture basket5;
    Texture basketClose5;

    Texture basket6;
    Texture basketClose6;

    Texture basket7;
    Texture basketClose7;

    Texture basket8;
    Texture basketClose8;

    // button
    Texture start;
    Texture startHover;
    Sprite startSprite;
    Sprite startHoverSprite;

    Texture back;
    Texture backHover;
    Sprite backSprite;
    Sprite backHoverSprite;

    Texture shop;
    Texture shopHover;
    Sprite shopSprite;
    Sprite shopHoverSprite;
    boolean isShopShow = false;

    Texture settings;
    Texture settingsHover;
    Sprite settingsSprite;
    Sprite settingsHoverSprite;
    boolean isSettingShow = false;

    // choose hero
    Texture choose;
    static boolean isBonusActive = false;
    static boolean isBasketBallHoopActive = false;
    static boolean isShoppingBasket1Active = false;
    static boolean isShoppingBasket2Active = false;
    static boolean isShoppingBasket3Active = false;
    static boolean isShoppingBasket4Active = false;
    public static final int BONUS_BASKET = 150;
    public static final int BASKET_BALL_HOOP = 25;
    public static final int SHOPPING_BASKET1 = 25;
    public static final int SHOPPING_BASKET2 = 25;
    public static final int SHOPPING_BASKET3 = 25;
    public static final int SHOPPING_BASKET4 = 30;
    static int catcherCoin = 999;

    static Preferences save;

    Texture radioOff;
    Texture radioOnn;
    Sprite radioSprite1;
    Sprite radioSprite2;
    static boolean isRadioActive1 = true;
    static boolean isRadioActive2 = true;

    ShopItem shopItem1;
    ShopItem shopItem2;
    ShopItem shopItem3;

    ShopItem shopItem4;
    ShopItem shopItem5;
    ShopItem shopItem6;

    ShopItem shopItem7;
    ShopItem shopItem8;

    public void handleTouch() {

        if (Gdx.input.justTouched()) {

            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;

            if ((touchX >= startSprite.getX()) && touchX <= (startSprite.getX() + startSprite.getWidth()) && (touchY >= startSprite.getY())
                    && touchY <= (startSprite.getY() + startSprite.getHeight()) && !isShopShow) {
                game.setScreen(new GameScreen(game));
                dispose();
            } else if ((touchX >= settingsSprite.getX()) && touchX <= (settingsSprite.getX() + settingsSprite.getWidth())
                    && (touchY >= settingsSprite.getY()) && touchY <= (settingsSprite.getY() + settingsSprite.getHeight()) && !isShopShow) {
                isSettingShow = !isSettingShow;
            } else if (isSettingShow) {
                if ((touchX >= radioSprite1.getX()) && touchX <= (radioSprite1.getX() + radioSprite1.getWidth()) && (touchY >= radioSprite1.getY()) && touchY <= (radioSprite1.getY() + radioSprite1.getHeight())) {
                    isRadioActive1 = !isRadioActive1;
                } else if ((touchX >= radioSprite2.getX()) && touchX <= (radioSprite2.getX() + radioSprite2.getWidth()) && (touchY >= radioSprite2.getY())
                        && touchY <= (radioSprite2.getY() + radioSprite2.getHeight())) {
                    isRadioActive2 = !isRadioActive2;
                }
            } else if ((touchX >= shopSprite.getX()) && touchX <= (shopSprite.getX() + shopSprite.getWidth()) && (touchY >= shopSprite.getY())
                    && touchY <= (shopSprite.getY() + shopSprite.getHeight()) && !isShopShow) {

                isShopShow = true;
            } else if (isShopShow) {
                if ((touchX >= backSprite.getX()) && touchX <= (backSprite.getX() + backSprite.getWidth()) && (touchY >= backSprite.getY())
                        && touchY <= (backSprite.getY() + backSprite.getHeight())) {

                    isShopShow = false;
                } else if ((touchX >= shopItem1.itemSprite.getX()) && touchX <= (shopItem1.itemSprite.getX() + shopItem1.itemSprite.getWidth()) && (touchY >= shopItem1.itemSprite.getY())
                        && touchY <= (shopItem1.itemSprite.getY() + shopItem1.itemSprite.getHeight())) {
                    choose = basket1;
                    GameScreen.chooseHero = 1;
                } else if ((touchX >= shopItem2.itemSprite.getX()) && touchX <= (shopItem2.itemSprite.getX() + shopItem2.itemSprite.getWidth()) && (touchY >= shopItem2.itemSprite.getY())
                        && touchY <= (shopItem2.itemSprite.getY() + shopItem2.itemSprite.getHeight())) {
                    choose = basket2;
                    GameScreen.chooseHero = 2;
                } else if ((touchX >= shopItem3.itemSprite.getX()) && touchX <= (shopItem3.itemSprite.getX() + shopItem3.itemSprite.getWidth()) && (touchY >= shopItem3.itemSprite.getY())
                        && touchY <= (shopItem3.itemSprite.getY() + shopItem3.itemSprite.getHeight())) {
                    choose = basket3;
                    if (isBasketBallHoopActive) {
                        GameScreen.chooseHero = 3;
                    }
                } else if ((touchX >= shopItem4.itemSprite.getX()) && touchX <= (shopItem4.itemSprite.getX() + shopItem4.itemSprite.getWidth()) && (touchY >= shopItem4.itemSprite.getY())
                        && touchY <= (shopItem4.itemSprite.getY() + shopItem4.itemSprite.getHeight())) {
                    choose = basket4;
                    if (isBonusActive) {
                        GameScreen.chooseHero = 4;
                    }
                } else if ((touchX >= shopItem5.itemSprite.getX()) && touchX <= (shopItem5.itemSprite.getX() + shopItem5.itemSprite.getWidth()) && (touchY >= shopItem5.itemSprite.getY())
                        && touchY <= (shopItem5.itemSprite.getY() + shopItem5.itemSprite.getHeight())) {
                    choose = basket5;
                    if (isShoppingBasket1Active) {
                        GameScreen.chooseHero = 5;
                    }
                    if (catcherCoin >= SHOPPING_BASKET1 && !isShoppingBasket1Active) {
                        isShoppingBasket1Active = true;
                        catcherCoin -= SHOPPING_BASKET1;
                        shopItem5.itemSprite = new Sprite(shopItem5.item);
                        shopItem5.itemSprite.setPosition(shopItem5.posX, shopItem5.posY);
                        GameScreen.chooseHero = 5;
                    }
                } else if ((touchX >= shopItem6.itemSprite.getX()) && touchX <= (shopItem6.itemSprite.getX() + shopItem6.itemSprite.getWidth()) && (touchY >= shopItem6.itemSprite.getY())
                        && touchY <= (shopItem6.itemSprite.getY() + shopItem6.itemSprite.getHeight())) {
                    choose = basket6;
                    if (isShoppingBasket2Active) {
                        GameScreen.chooseHero = 6;
                    }
                    if (catcherCoin >= SHOPPING_BASKET2 && !isShoppingBasket2Active) {
                        isShoppingBasket2Active = true;
                        catcherCoin -= SHOPPING_BASKET2;
                        shopItem6.itemSprite = new Sprite(shopItem6.item);
                        shopItem6.itemSprite.setPosition(shopItem6.posX, shopItem6.posY);
                        GameScreen.chooseHero = 6;
                    }
                } else if ((touchX >= shopItem7.itemSprite.getX()) && touchX <= (shopItem7.itemSprite.getX() + shopItem7.itemSprite.getWidth()) && (touchY >= shopItem7.itemSprite.getY())
                        && touchY <= (shopItem7.itemSprite.getY() + shopItem7.itemSprite.getHeight())) {
                    choose = basket7;
                    if (isShoppingBasket3Active) {
                        GameScreen.chooseHero = 7;
                    }
                    if (catcherCoin >= SHOPPING_BASKET3 && !isShoppingBasket3Active) {
                        isShoppingBasket3Active = true;
                        catcherCoin -= SHOPPING_BASKET3;
                        shopItem7.itemSprite = new Sprite(shopItem7.item);
                        shopItem7.itemSprite.setPosition(shopItem7.posX, shopItem7.posY);
                        GameScreen.chooseHero = 7;
                    }
                } else if ((touchX >= shopItem8.itemSprite.getX()) && touchX <= (shopItem8.itemSprite.getX() + shopItem8.itemSprite.getWidth()) && (touchY >= shopItem8.itemSprite.getY())
                        && touchY <= (shopItem8.itemSprite.getY() + shopItem8.itemSprite.getHeight())) {
                    choose = basket8;
                    if (isShoppingBasket4Active) {
                        GameScreen.chooseHero = 8;
                    }
                    if (catcherCoin >= SHOPPING_BASKET4 && !isShoppingBasket4Active) {
                        isShoppingBasket4Active = true;
                        catcherCoin -= SHOPPING_BASKET4;
                        shopItem8.itemSprite = new Sprite(shopItem8.item);
                        shopItem8.itemSprite.setPosition(shopItem8.posX, shopItem8.posY);
                        GameScreen.chooseHero = 8;
                    }
                }
            }
        }
    }

    public void hover() {

        MyController controller = new MyController();
        Gdx.input.setInputProcessor(controller);
        temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);

        if (controller.keyDown(Input.Buttons.LEFT)) {

            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;

            if ((touchX >= startSprite.getX()) && touchX <= (startSprite.getX() + startSprite.getWidth()) && (touchY >= startSprite.getY())
                    && touchY <= (startSprite.getY() + startSprite.getHeight()) && !isShopShow) {
                startHover = new Texture(Gdx.files.internal("start_hover.png")); //
                startHoverSprite = new Sprite(startHover);
                startHoverSprite.setSize(120, 60);
                startHoverSprite.setPosition(startSprite.getX() + 3, startSprite.getY() - 3);
                startHoverSprite.draw(game.batch);
            } else if ((touchX >= settingsSprite.getX()) && touchX <= (settingsSprite.getX() + settingsSprite.getWidth()) && (touchY >= settingsSprite.getY())
                    && touchY <= (settingsSprite.getY() + settingsSprite.getHeight()) && !isShopShow) {

                settingsHover = new Texture(Gdx.files.internal("settings_active.png"));
                settingsHoverSprite = new Sprite(settingsHover);
                settingsHoverSprite.setSize(64, 64);
                settingsHoverSprite.setPosition(720, 400);
                settingsHoverSprite.draw(game.batch);
            } else if ((touchX >= shopSprite.getX()) && touchX <= (shopSprite.getX() + shopSprite.getWidth()) && (touchY >= shopSprite.getY())
                    && touchY <= (shopSprite.getY() + shopSprite.getHeight()) && !isShopShow && !isSettingShow) {

                shopHover = new Texture(Gdx.files.internal("shop_hover.png"));
                shopHoverSprite = new Sprite(shopHover);
                shopHoverSprite.setSize(120, 60);
                shopHoverSprite.setPosition(shopSprite.getX() + 3, shopSprite.getY() - 3);
                shopHoverSprite.draw(game.batch);
            } else if (isShopShow) {
                if ((touchX >= backSprite.getX()) && touchX <= (backSprite.getX() + backSprite.getWidth()) && (touchY >= backSprite.getY())
                        && touchY <= (backSprite.getY() + backSprite.getHeight())) {

                    backHover = new Texture("back_hover.png");
                    backHoverSprite = new Sprite(backHover);
                    backHoverSprite.setSize(backSprite.getWidth(), backSprite.getHeight());
                    backHoverSprite.setPosition(backSprite.getX(), backSprite.getY());
                    backHoverSprite.draw(game.batch);
                }
            }
        }
    }

    public MainMenuScreen(final SomeCatchVC game) {
        this.game = game;

        save = Gdx.app.getPreferences("Save");
        isBonusActive = save.getBoolean("BonusBasket");
        isBasketBallHoopActive = save.getBoolean("BasketBallHoop");
        isShoppingBasket1Active = save.getBoolean("Basket1");
        isShoppingBasket2Active = save.getBoolean("Basket2");
        isShoppingBasket3Active = save.getBoolean("Basket3");
        isShoppingBasket4Active = save.getBoolean("Basket4");
        catcherCoin = save.getInteger("catcherCoin");

        start = new Texture(Gdx.files.internal("start.png"));
        startSprite = new Sprite(start);
        startSprite.setSize(120, 60);
        startSprite.setPosition(45, 170);

        shop = new Texture(Gdx.files.internal("shop.png"));
        shopSprite = new Sprite(shop);
        shopSprite.setSize(120, 60);
        shopSprite.setPosition(startSprite.getX(), startSprite.getY() - 75);

        settings = new Texture(Gdx.files.internal("settings.png"));
        settingsSprite = new Sprite(settings);
        settingsSprite.setSize(64, 64);
        settingsSprite.setPosition(720, 400);

        radioOff = new Texture(Gdx.files.internal("radion_off.png"));
        radioOnn = new Texture(Gdx.files.internal("radion_onn.png"));

        catch1 = new Texture(Gdx.files.internal("catch1.png"));
        catch1Sprite = new Sprite(catch1);
        catch1Sprite.setSize(20, 30);
        catch1Sprite.setPosition(5, 410);

        catch2 = new Texture(Gdx.files.internal("catch2.png"));
        catch2Sprite = new Sprite(catch2);
        catch2Sprite.setSize(20, 20);
        catch2Sprite.setPosition(5, 385);

        bonusCatch = new Texture(Gdx.files.internal("bonus.png"));
        bonusCatchSprite = new Sprite(bonusCatch);
        bonusCatchSprite.setSize(17.5f, 25);
        bonusCatchSprite.setPosition(5, 355);

        poisonCatch = new Texture(Gdx.files.internal("poison.png"));
        poisonCatchSprite = new Sprite(poisonCatch);
        poisonCatchSprite.setSize(17.5f, 20);
        poisonCatchSprite.setPosition(5, 330);

        healCatch = new Texture(Gdx.files.internal("heal.png"));
        healCatchSprite = new Sprite(healCatch);
        healCatchSprite.setSize(17.5f, 20);
        healCatchSprite.setPosition(5, 305);

        catcher = new Texture(Gdx.files.internal("catcher.png"));
        catcherSprite = new Sprite(catcher);
        catcherSprite.setSize(20, 20);
        catcherSprite.setPosition(5, 280);

        basket1 = new Texture(Gdx.files.internal("basket1.png"));
        basket2 = new Texture(Gdx.files.internal("basket2.png"));
        basket3 = new Texture(Gdx.files.internal("basketball_hoop.png"));
        basketClose3 = new Texture(Gdx.files.internal("basketball_hoop_close.png"));
        basket4 = new Texture(Gdx.files.internal("basket_bonus.png"));
        basketClose4 = new Texture(Gdx.files.internal("basket_bonus_close.png"));
        basket5 = new Texture(Gdx.files.internal("shopping.png"));
        basketClose5 = new Texture(Gdx.files.internal("shopping_close.png"));
        basket6 = new Texture(Gdx.files.internal("shopping-basket1.png"));
        basketClose6 = new Texture(Gdx.files.internal("shopping-basket_close1.png"));
        basket7 = new Texture(Gdx.files.internal("shopping-basket2.png"));
        basketClose7 = new Texture(Gdx.files.internal("shopping-basket_close2.png"));
        basket8 = new Texture(Gdx.files.internal("shopping-basket3.png"));
        basketClose8 = new Texture(Gdx.files.internal("shopping-basket_close3.png"));

        shopItem1 = new ShopItem(basket1, 80, 60, 180 + 35, 280);
        shopItem2 = new ShopItem(basket2, 80, 66, shopItem1.itemSprite.getX() + 150, shopItem1.itemSprite.getY());
        shopItem3 = new ShopItem(basket3, 60, 80, shopItem2.itemSprite.getX() + 150, shopItem1.itemSprite.getY(), isBasketBallHoopActive, basketClose3);

        shopItem4 = new ShopItem(basket4, 60, 80, shopItem1.itemSprite.getX(), shopItem1.itemSprite.getY() - 100, isBonusActive, basketClose4);
        shopItem5 = new ShopItem(basket5, 80, 75, shopItem2.itemSprite.getX(), shopItem4.itemSprite.getY(), isShoppingBasket1Active, basketClose5);
        shopItem6 = new ShopItem(basket6, 80, 80, shopItem3.itemSprite.getX(), shopItem4.itemSprite.getY(), isShoppingBasket2Active, basketClose6);

        shopItem7 = new ShopItem(basket7, 80, 80, shopItem1.itemSprite.getX(), shopItem4.itemSprite.getY() - 100, isShoppingBasket3Active, basketClose7);
        shopItem8 = new ShopItem(basket8, 80, 80, shopItem2.itemSprite.getX(), shopItem7.itemSprite.getY(), isShoppingBasket4Active, basketClose8);

        camera = new OrthographicCamera(); //*
        camera.setToOrtho(false, 800, 480); //*
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.3f, 0.8f, 0.9f, 1); //*
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //*
        camera.update(); //*
        game.batch.setProjectionMatrix(camera.combined); //*
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Some Catch!!! ", 25, 250);
        game.font.draw(game.batch, "Rules", 50, 460);
        startSprite.draw(game.batch);
        shopSprite.draw(game.batch);
        catch1Sprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch +1 score", catch1Sprite.getX() + 30, catch1Sprite.getY() + 20);
        catch2Sprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch +5 score", catch2Sprite.getX() + 30, catch2Sprite.getY() + 15);
        bonusCatchSprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch -50% speed", bonusCatchSprite.getX() + 30, bonusCatchSprite.getY() + 20);
        poisonCatchSprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch -1 live", poisonCatchSprite.getX() + 30, poisonCatchSprite.getY() + 18);
        healCatchSprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch +1 live", healCatchSprite.getX() + 30, healCatchSprite.getY() + 18);
        catcherSprite.draw(game.batch);
        game.font.draw(game.batch, "- if catch +1 CAT-cher", catcherSprite.getX() + 30, catcherSprite.getY() + 18);

        if (isSettingShow) {
            settingsMenu();
            settingsHoverSprite.draw(game.batch);
        } else settingsSprite.draw(game.batch);

        if (isShopShow) {
            shopMenu();
        }

        hover();
        handleTouch();
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && isSettingShow) {
            isSettingShow = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && isShopShow) {
            isShopShow = false;
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        start.dispose();
        shop.dispose();
        if (back != null) back.dispose();
        settings.dispose();
        catch1.dispose();
        catch2.dispose();
        bonusCatch.dispose();
        poisonCatch.dispose();
        healCatch.dispose();
        catcher.dispose();
        radioOff.dispose();
        radioOnn.dispose();
        basket1.dispose();
        basket2.dispose();
        basket3.dispose();
        basket4.dispose();
        basket5.dispose();
        basket6.dispose();
        basket7.dispose();
        basket8.dispose();

        if (startHover != null) startHover.dispose();
        if (shopHover != null) shopHover.dispose();
        if (settingsHover != null) settingsHover.dispose();
        if (backHover != null) backHover.dispose();
        basketClose3.dispose();
        basketClose4.dispose();
        basketClose5.dispose();
        basketClose6.dispose();
        basketClose7.dispose();
        basketClose8.dispose();

        save.putBoolean("Basket1", isShoppingBasket1Active);
        save.putBoolean("Basket2", isShoppingBasket2Active);
        save.putBoolean("Basket3", isShoppingBasket3Active);
        save.putBoolean("Basket4", isShoppingBasket4Active);
//        save.putBoolean("Basket1", false);
//        save.putBoolean("Basket2", false);
//        save.putBoolean("Basket3", false);
//        save.putBoolean("Basket4", false);
        save.putInteger("catcherCoin", catcherCoin);
        save.flush();


    }

    public void settingsMenu() {

        Texture alert;
        Sprite alertSprite;

        alert = new Texture(Gdx.files.internal("alert.png"));
        alertSprite = new Sprite(alert);
        alertSprite.setSize(200, 150);
        alertSprite.setPosition(400 - (alertSprite.getWidth() / 2), 280 - (alertSprite.getHeight() / 2));

        alertSprite.draw(game.batch);
        game.font.setColor(0, 0, 0, 1);
        game.font.draw(game.batch, "Music - ON/OFF", alertSprite.getX() + 15, alertSprite.getY() + alertSprite.getHeight() - 30);
        radioButton1(alertSprite.getX() + alertSprite.getWidth() - 55, alertSprite.getY() + alertSprite.getHeight() - 46);
        game.font.draw(game.batch, "Sound - ON/OFF", alertSprite.getX() + 15, alertSprite.getY() + alertSprite.getHeight() - 62);
        radioButton2(alertSprite.getX() + alertSprite.getWidth() - 55, alertSprite.getY() + alertSprite.getHeight() - 76);
        game.font.setColor(1, 1, 1, 1);
    }

    public void radioButton1(float posX, float posY) {

        if (!isRadioActive1) {
            radioSprite1 = new Sprite(radioOff);
            GameScreen.isMusicPlay = false;
        } else {
            radioSprite1 = new Sprite(radioOnn);
            GameScreen.isMusicPlay = true;
        }
        radioSprite1.setSize(40, 19);
        radioSprite1.setPosition(posX, posY);
        radioSprite1.draw(game.batch);

    }

    public void radioButton2(float posX, float poxY) {

        if (!isRadioActive2) {
            radioSprite2 = new Sprite(radioOff);
            GameScreen.isSoundPlay = false;
        } else {
            radioSprite2 = new Sprite(radioOnn);
            GameScreen.isSoundPlay = true;
        }
        radioSprite2.setSize(40, 19);
        radioSprite2.setPosition(posX, poxY);
        radioSprite2.draw(game.batch);
    }

    public void shopMenu() {

        Texture shopBack;
        Sprite shopBackSprite;

        Texture alert;
        Sprite alertSprite;

        Sprite cathcerSprite;

        shopBack = new Texture(Gdx.files.internal("shopMenu.png"));
        shopBackSprite = new Sprite(shopBack);
        shopBackSprite.setSize(800, 480);
        shopBackSprite.setPosition(0, 0);
        shopBackSprite.draw(game.batch);

        back = new Texture(Gdx.files.internal("back.png"));
        backSprite = new Sprite(back);
        backSprite.setSize(80, 80);
        backSprite.setPosition(5, 20);
        backSprite.draw(game.batch);

        alert = new Texture("alert.png");
        alertSprite = new Sprite(alert);
        alertSprite.setSize(430, 350);
        alertSprite.setPosition(400 - alertSprite.getWidth() / 2, 240 - alertSprite.getHeight() / 2);
        alertSprite.draw(game.batch);

        cathcerSprite = new Sprite(catcher);
        cathcerSprite.setSize(20, 20);
        cathcerSprite.setPosition(alertSprite.getX() + 10, alertSprite.getY() + alertSprite.getHeight() - 25);

        shopItem1.itemSprite.draw(game.batch);
        shopItem2.itemSprite.draw(game.batch);
        shopItem3.itemSprite.draw(game.batch);
        shopItem4.itemSprite.draw(game.batch);
        shopItem5.itemSprite.draw(game.batch);
        shopItem6.itemSprite.draw(game.batch);
        shopItem7.itemSprite.draw(game.batch);
        shopItem8.itemSprite.draw(game.batch);

        cathcerSprite.draw(game.batch);
        game.font.draw(game.batch, " = " + catcherCoin, cathcerSprite.getX() + 22, cathcerSprite.getY() + (cathcerSprite.getHeight() / 2) + 5);

        if (choose == basket1) {
            game.font.draw(game.batch, "Simple shopping basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
        } else if (choose == basket2) {
            game.font.draw(game.batch, "Picnic basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
        } else if (choose == basket3) {
            if (isBasketBallHoopActive) {
                game.font.draw(game.batch, "Basket ball hoop", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, "Score " + BASKET_BALL_HOOP + " points to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else if (choose == basket4) {
            if (isBonusActive) {
                game.font.draw(game.batch, "Trash basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, "Score " + BONUS_BASKET + " points to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else if (choose == basket5) {
            if (isShoppingBasket1Active) {
                game.font.draw(game.batch, "Red shopping basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, SHOPPING_BASKET1 + " CAT-cher's to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else if (choose == basket6) {
            if (isShoppingBasket2Active) {
                game.font.draw(game.batch, "Blue shopping basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, SHOPPING_BASKET2 + " CAT-cher's to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else if (choose == basket7) {
            if (isShoppingBasket3Active) {
                game.font.draw(game.batch, "Green shopping basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, SHOPPING_BASKET3 + " CAT-cher's to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else if (choose == basket8) {
            if (isShoppingBasket4Active) {
                game.font.draw(game.batch, "Yellow shopping basket", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            } else {
                game.font.draw(game.batch, SHOPPING_BASKET4 + " CAT-cher's to unlock", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
            }
        } else {
            game.font.draw(game.batch, "No basket selected", alertSprite.getX() + (alertSprite.getWidth() / 2) - 60, alertSprite.getY() + alertSprite.getHeight() - 5);
        }
    }


}

