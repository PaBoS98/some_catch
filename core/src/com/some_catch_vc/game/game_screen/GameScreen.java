package com.some_catch_vc.game.game_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.some_catch_vc.game.SomeCatchVC;

import java.util.Iterator;


public class GameScreen implements Screen {

    final SomeCatchVC game;
    Vector3 touchPos;
    OrthographicCamera camera;
    Sound catchFoodSound;
    Sound catchPoison;
    Sound catchHeal;
    Sound catchSlow;
    Sound catchCoin;
    Music backgroundMusic;
    Texture background;
    Texture background1;
    Texture background2;
    Texture background3;
    Sprite backgroundSprite;
    Sprite background1Sprite;
    Sprite background2Sprite;
    Sprite background3Sprite;

    // basket
    Texture basket1;
    Texture basket2;
    Texture basketBonus;
    Texture basketBallHoop;
    Texture shoppingBasket1;
    Texture shoppingBasket2;
    Texture shoppingBasket3;
    Texture shoppingBasket4;
    Rectangle basket;

    // catch
    Texture catch1;
    Texture catch2;
    Texture bonusCatch;
    Texture poisonCatch;
    Texture healCatch;
    Texture catcher;

    Array<Rectangle> drops1;
    Array<Rectangle> drops2;
    Array<Rectangle> bonusDrops;
    Array<Rectangle> poisonDrops;
    Array<Rectangle> healDrops;
    Array<Rectangle> catcherDrops;

    // Times spawn
    long timeSpawn1 = 550000000L;
    long lastDropTime1;
    long addDropTime;

    long timeSpawn2 = 2500000000L;
    long lastDropTime2;

    long timeSpawnBonus = 5500000000L;
    long lastBonusTime;

    long timeSpawnPoison = 4500000000L;
    long lastPoisonTime;

    long timeSpawnHeal = 6000000000L;
    long lastHealTime;

    long timeSpawnCatcher = 7500000000L;
    long lastCatcherTime;

    static int chooseHero;
    static int dropsGathered;
    int saveDropsGathered;
    int lives = 3;
    int speedFall = 200;
    int level;
    int random;
    long timeAlert;
    boolean isTimerActive;

    static boolean isMusicPlay = true;
    static boolean isSoundPlay = true;

    public GameScreen(final SomeCatchVC game) {
        this.game = game;

        background = new Texture(Gdx.files.internal("backgroud.png"));
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(800, 480);
        backgroundSprite.setPosition(0, 0);

        background1 = new Texture(Gdx.files.internal("backgroud1.png"));
        background1Sprite = new Sprite(background1);
        background1Sprite.setSize(800, 480);
        background1Sprite.setPosition(0, 0);

        background2 = new Texture(Gdx.files.internal("backgroud2.png"));
        background2Sprite = new Sprite(background2);
        background2Sprite.setSize(800, 480);
        background2Sprite.setPosition(0, 0);

        background3 = new Texture(Gdx.files.internal("background3.png"));
        background3Sprite = new Sprite(background3);
        background3Sprite.setSize(800, 480);
        background3Sprite.setPosition(0, 0);

        random = MathUtils.random(0, 4);

        basket1 = new Texture(Gdx.files.internal("basket1.png"));
        basket2 = new Texture(Gdx.files.internal("basket2.png"));
        basketBonus = new Texture(Gdx.files.internal("basket_bonus.png"));
        basketBallHoop = new Texture(Gdx.files.internal("basketball_hoop.png"));
        shoppingBasket1 = new Texture(Gdx.files.internal("shopping.png"));
        shoppingBasket2 = new Texture(Gdx.files.internal("shopping-basket1.png"));
        shoppingBasket3 = new Texture(Gdx.files.internal("shopping-basket2.png"));
        shoppingBasket4 = new Texture(Gdx.files.internal("shopping-basket3.png"));

        catch1 = new Texture(Gdx.files.internal("catch1.png"));
        catch2 = new Texture(Gdx.files.internal("catch2.png"));
        bonusCatch = new Texture(Gdx.files.internal("bonus.png"));
        poisonCatch = new Texture(Gdx.files.internal("poison.png"));
        healCatch = new Texture(Gdx.files.internal("heal.png"));
        catcher = new Texture(Gdx.files.internal("catcher.png"));

        drops1 = new Array<>();
        spawnDrop1();
        drops2 = new Array<>();
        bonusDrops = new Array<>();
        poisonDrops = new Array<>();
        healDrops = new Array<>();
        catcherDrops = new Array<>();

        basket = new Rectangle();
        basket.x = 400 - 32;
        basket.y = 20;
        if (chooseHero == 1 || chooseHero == 7 || chooseHero == 8) {
            basket.width = 80;
            basket.height = 48;
        } else if (chooseHero == 2) {
            basket.width = 80;
            basket.height = 30;
        } else if (chooseHero == 3 || chooseHero == 4) {
            basket.width = 80;
            basket.height = 100;
        } else if (chooseHero == 5) {
            basket.width = 80;
            basket.height = 45;
        } else if (chooseHero == 6) {
            basket.width = 80;
            basket.height = 55;
        } else {
            basket.width = 80;
            basket.height = 48;
        }

        catchFoodSound = Gdx.audio.newSound(Gdx.files.internal("catch_food.mp3"));
        catchPoison = Gdx.audio.newSound(Gdx.files.internal("catch_poison.mp3"));
        catchHeal = Gdx.audio.newSound(Gdx.files.internal("catch_heal.mp3"));
        catchSlow = Gdx.audio.newSound(Gdx.files.internal("catch_slow.mp3"));
        catchCoin = Gdx.audio.newSound(Gdx.files.internal("catchCoin.mp3"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.55f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        touchPos = new Vector3();
    }

    public void openingAlert(Texture basket, int width, int height) {

        Sprite basketOpenSprite;
        Texture background;
        Sprite backgroundSprite;

        background = new Texture(Gdx.files.internal("alert.png"));
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(195, 60);
        backgroundSprite.setPosition(0, 0);

        basketOpenSprite = new Sprite(basket);
        basketOpenSprite.setSize(width, height);
        basketOpenSprite.setPosition(5, 10);

        backgroundSprite.draw(game.batch);
        basketOpenSprite.draw(game.batch);
        game.font.setColor(0, 0, 0, 0.8f);
        game.font.draw(game.batch, " - you open new basket", basketOpenSprite.getX() + width + 5, backgroundSprite.getHeight() / 2 + 4);
        game.font.setColor(1, 1, 1, 1);
    }

    public void spawnDrop1() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 40;
        drop.height = 60;
        drops1.add(drop);
        lastDropTime1 = TimeUtils.nanoTime();
    }

    public void spawnDrop2() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 40;
        drop.height = 60;
        drops2.add(drop);
        lastDropTime2 = TimeUtils.nanoTime();
    }

    public void spawnBonusDrops() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 40;
        drop.height = 60;
        bonusDrops.add(drop);
        lastBonusTime = TimeUtils.nanoTime();
    }

    public void spawnPoisonDrops() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 40;
        drop.height = 45;
        poisonDrops.add(drop);
        lastPoisonTime = TimeUtils.nanoTime();
    }

    public void spawnHealDrops() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 60;
        drop.height = 68;
        healDrops.add(drop);
        lastHealTime = TimeUtils.nanoTime();
    }

    public void spawnCatcherDrops() {

        Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - 80);
        drop.y = 480;
        drop.width = 55;
        drop.height = 55;
        catcherDrops.add(drop);
        lastCatcherTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        if (isMusicPlay) {
            backgroundMusic.play();
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.3f, 1f, 0.8f, 1); //*
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //*

        camera.update(); //*
        game.batch.setProjectionMatrix(camera.combined); //*

        game.batch.begin();

        if (random == 1) {
            backgroundSprite.draw(game.batch);
        } else if (random == 2) {
            background1Sprite.draw(game.batch);
        } else if (random == 3) {
            background2Sprite.draw(game.batch);
        } else if (random == 4) {
            background3Sprite.draw(game.batch);
        }

        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 5, 475);
        game.font.draw(game.batch, "Lives: " + lives, 745, 475);
        if (level < 10) {
            game.font.draw(game.batch, "Level: " + level, 400, 475);
        } else game.font.draw(game.batch, "Level: Max level", 400, 475);

        if (chooseHero == 1) {
            game.batch.draw(basket1, basket.x, basket.y);
        } else if (chooseHero == 2) {
            game.batch.draw(basket2, basket.x, basket.y);
        } else if (chooseHero == 3) {
            game.batch.draw(basketBonus, basket.x, basket.y);
        }else if (chooseHero == 4) {
            game.batch.draw(basketBallHoop, basket.x, basket.y);
        }else if (chooseHero == 5) {
            game.batch.draw(shoppingBasket1, basket.x, basket.y);
        }else if (chooseHero == 6) {
            game.batch.draw(shoppingBasket2, basket.x, basket.y);
        }else if (chooseHero == 7) {
            game.batch.draw(shoppingBasket3, basket.x, basket.y);
        } else if (chooseHero == 8) {
            game.batch.draw(shoppingBasket4, basket.x, basket.y);
        } else game.batch.draw(basket1, basket.x, basket.y);

        for (Rectangle drop : drops1) {
            game.batch.draw(catch1, drop.x, drop.y);
        }
        for (Rectangle drop : drops2) {
            game.batch.draw(catch2, drop.x, drop.y);
        }
        for (Rectangle drop : bonusDrops) {
            game.batch.draw(bonusCatch, drop.x, drop.y);
        }
        for (Rectangle drop : poisonDrops) {
            game.batch.draw(poisonCatch, drop.x, drop.y);
        }
        for (Rectangle drop : healDrops) {
            game.batch.draw(healCatch, drop.x, drop.y);
        }
        for (Rectangle drop : catcherDrops) {
            game.batch.draw(catcher, drop.x, drop.y);
        }

        if (dropsGathered >= MainMenuScreen.BONUS_BASKET && !MainMenuScreen.isBonusActive) {
            openingAlert(basketBonus, 30, 38);
            if (!isTimerActive) {
                timeAlert = TimeUtils.nanoTime();
                isTimerActive = true;
            }
            if (TimeUtils.nanoTime() - timeAlert > 8000000000L) {
                MainMenuScreen.isBonusActive = true;
                isTimerActive = false;
            }
        }
        if (dropsGathered >= MainMenuScreen.BASKET_BALL_HOOP && !MainMenuScreen.isBasketBallHoopActive) {
            openingAlert(basketBallHoop, 30, 38);
            if (!isTimerActive) {
                timeAlert = TimeUtils.nanoTime();
                isTimerActive = true;
            }
            if (TimeUtils.nanoTime() - timeAlert > 8000000000L) {
                MainMenuScreen.isBasketBallHoopActive = true;
                isTimerActive = false;
            }
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            basket.x = touchPos.x - 32;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            basket.x -= 600 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            basket.x += 600 * Gdx.graphics.getDeltaTime();

        if (basket.x < 0) basket.x = 0;
        if (basket.x > 800 - 80) basket.x = 800 - 80;

        if (TimeUtils.nanoTime() - lastDropTime1 > timeSpawn1 + addDropTime) {
            addDropTime = (long) (Math.random() * 400000000);
            spawnDrop1();
        }

        if (dropsGathered > 10 && TimeUtils.nanoTime() - lastDropTime2 > timeSpawn2) {
            timeSpawn2 = MathUtils.random(timeSpawn2, timeSpawn2 + 1500000000L);
            spawnDrop2();
        }

        if (level >= 3 && TimeUtils.nanoTime() - lastPoisonTime > timeSpawnPoison) {
            timeSpawnPoison = MathUtils.random(timeSpawnPoison - 1000000000L, timeSpawnPoison + 1500000000L);
            spawnPoisonDrops();
        }

        if (level >= 5 && TimeUtils.nanoTime() - lastBonusTime > timeSpawnBonus && speedFall > 180) {
            timeSpawnBonus = MathUtils.random(timeSpawnBonus, timeSpawnBonus + 2000000000L);
            spawnBonusDrops();

        }

        if (level >= 6 && TimeUtils.nanoTime() - lastHealTime > timeSpawnHeal) {
            timeSpawnHeal = MathUtils.random(timeSpawnHeal, timeSpawnHeal + 2000000000L);
            spawnHealDrops();
        }

        if (level >= 10 && TimeUtils.nanoTime() - lastCatcherTime > timeSpawnCatcher) {
            timeSpawnCatcher = MathUtils.random(timeSpawnCatcher, timeSpawnCatcher + 2000000000L);
            spawnCatcherDrops();
        }

        if (dropsGathered > saveDropsGathered + 10) {
            saveDropsGathered = dropsGathered;
            level++;

            switch (level) {
                case 1:
                    speedFall = (int) (speedFall + 1.1);
                    timeSpawn1 = 500000000L;
                    break;
                case 2:
                    speedFall = (int) (speedFall + 1.1);
                    break;
                case 3:
                    timeSpawn1 = 450000000L;
                    break;
                case 4:
                    timeSpawn1 = 400000000L;
                    break;
                case 5:
                    speedFall = (int) (speedFall + 1.1);
                    timeSpawn1 = 420000000L;
                    break;
                case 6:
                    speedFall = (int) (speedFall + 1.1);
                    timeSpawn1 = 300000000L;
                    break;
                case 7:
                    speedFall = speedFall + 50;
                    timeSpawn1 = 220000000L;
                    break;
                case 8:
                    speedFall = speedFall + 50;
                    timeSpawn1 = 100000000L;
                    break;
                case 9:
                    speedFall = speedFall + 50;
                    timeSpawn1 = 50000000L;
                    break;
                case 10:
                    speedFall = speedFall + 50;
                    timeSpawn1 = 35000000L;
                    timeSpawnPoison -= 2000000000L;
                    break;
                default:
                    speedFall = (int) (speedFall * 1.08f);
            }
        }


        Iterator<Rectangle> iter1 = drops1.iterator();
        while (iter1.hasNext()) {
            Rectangle drop = iter1.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iter1.remove();
                lives--;
            }
            if (drop.overlaps(basket)) {
                dropsGathered++;
                if (isSoundPlay) {
                    catchFoodSound.play(0.6f);
                }
                iter1.remove();
            }
        }

        Iterator<Rectangle> iter2 = drops2.iterator();
        while (iter2.hasNext()) {
            Rectangle drop = iter2.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iter2.remove();
                lives--;
            }
            if (drop.overlaps(basket)) {
                dropsGathered += 5;
                if (isSoundPlay) {
                    catchFoodSound.play(0.6f);
                }
                iter2.remove();
            }
        }

        Iterator<Rectangle> iterBonus = bonusDrops.iterator();
        while (iterBonus.hasNext()) {
            Rectangle drop = iterBonus.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iterBonus.remove();
            }
            if (drop.overlaps(basket)) {
                speedFall = (int) (speedFall * 0.5f);
                if (isSoundPlay) {
                    catchSlow.play(0.6f);
                }
                iterBonus.remove();
            }
        }

        Iterator<Rectangle> iterPoison = poisonDrops.iterator();
        while (iterPoison.hasNext()) {
            Rectangle drop = iterPoison.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iterPoison.remove();
            }
            if (drop.overlaps(basket)) {
                if (isSoundPlay) {
                    catchPoison.play(0.6f);
                }
                lives--;
                iterPoison.remove();
            }
        }

        Iterator<Rectangle> iterHeal = healDrops.iterator();
        while (iterHeal.hasNext()) {
            Rectangle drop = iterHeal.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iterHeal.remove();
            }
            if (drop.overlaps(basket)) {
                if (isSoundPlay) {
                    catchHeal.play(0.6f);
                }
                lives++;
                iterHeal.remove();
            }
        }

        Iterator<Rectangle> iterCatcher = catcherDrops.iterator();
        while (iterCatcher.hasNext()) {
            Rectangle drop = iterCatcher.next();
            drop.y -= speedFall * Gdx.graphics.getDeltaTime();
            if (drop.y < -basket.height) {
                iterCatcher.remove();
            }
            if (drop.overlaps(basket)) {
                if (isSoundPlay) {
                    catchCoin.play(1.2f);
                }
                MainMenuScreen.catcherCoin++;
                iterCatcher.remove();
            }
        }

        if (lives < 1) {
            game.setScreen(new Fail(game));
            dispose();
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
        basket1.dispose();
        basket2.dispose();
        basketBonus.dispose();
        basketBallHoop.dispose();
        shoppingBasket1.dispose();
        shoppingBasket2.dispose();
        shoppingBasket3.dispose();
        shoppingBasket4.dispose();

        catch1.dispose();
        catch2.dispose();
        poisonCatch.dispose();
        bonusCatch.dispose();
        healCatch.dispose();
        catcher.dispose();

        backgroundMusic.dispose();
        catchFoodSound.dispose();
        catchSlow.dispose();
        catchHeal.dispose();
        catchPoison.dispose();
        catchCoin.dispose();

        background.dispose();
        background1.dispose();
        background2.dispose();
        background3.dispose();

        MainMenuScreen.save.putBoolean("BasketBallHoop", MainMenuScreen.isBasketBallHoopActive);
        MainMenuScreen.save.putBoolean("BonusBasket", MainMenuScreen.isBonusActive);
//        MainMenuScreen.save.putBoolean("BasketBallHoop", false);
//        MainMenuScreen.save.putBoolean("BonusBasket", false);
        MainMenuScreen.save.putInteger("catcherCoin", MainMenuScreen.catcherCoin);
        MainMenuScreen.save.flush();

    }
}
