package com.some_catch_vc.game.game_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.some_catch_vc.game.MyController;
import com.some_catch_vc.game.SomeCatchVC;

public class Fail implements Screen {
    final SomeCatchVC game;

    OrthographicCamera camera;
    Vector3 temp = new Vector3();
    Music failSound;

    Texture start;
    Texture startHover;
    Sprite startSprite;
    Sprite startHoverSprite;

    Texture close;
    Texture closeHover;
    Sprite closeSprite;
    Sprite closeHoverSprite;

    Texture back;
    Texture backHover;
    Sprite backSprite;
    Sprite backHoverSprite;

    public void handleTouch() {
        if (Gdx.input.justTouched()) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;

            if ((touchX >= closeSprite.getX()) && touchX <= (closeSprite.getX() + closeSprite.getWidth()) && (touchY >= closeSprite.getY()) && touchY <= (closeSprite.getY() + closeSprite.getHeight())) {
                Gdx.app.exit();
            } else if ((touchX >= startSprite.getX()) && touchX <= (startSprite.getX() + startSprite.getWidth()) && (touchY >= startSprite.getY()) && touchY <= (startSprite.getY() + startSprite.getHeight())) {
                GameScreen.dropsGathered = 0;
                game.setScreen(new GameScreen(game));
                dispose();
            } else if ((touchX >= backSprite.getX()) && touchX <= (backSprite.getX() + backSprite.getWidth()) && (touchY >= backSprite.getY()) && touchY <= (backSprite.getY() + backSprite.getHeight())) {
                GameScreen.dropsGathered = 0;
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
    }

    void hover() {
        MyController controller = new MyController();
        Gdx.input.setInputProcessor(controller);
        temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        if (controller.keyDown(Input.Buttons.LEFT)) {
            temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY = temp.y;

            if ((touchX >= closeSprite.getX()) && touchX <= (closeSprite.getX() + closeSprite.getWidth()) && (touchY >= closeSprite.getY()) && touchY <= (closeSprite.getY() + closeSprite.getHeight())) {
                closeHover = new Texture(Gdx.files.internal("close_active.png"));
                closeHoverSprite = new Sprite(closeHover);
                closeHoverSprite.setSize(80, 80);
                closeHoverSprite.setPosition(710, 380);
                closeHoverSprite.draw(game.batch);
            } else if ((touchX >= startSprite.getX()) && touchX <= (startSprite.getX() + startSprite.getWidth()) && (touchY >= startSprite.getY()) && touchY <= (startSprite.getY() + startSprite.getHeight())) {
                startHover = new Texture(Gdx.files.internal("start_hover.png"));
                startHoverSprite = new Sprite(startHover);
                startHoverSprite.setSize(120, 60);
                startHoverSprite.setPosition(startSprite.getX() + 3, startSprite.getY() - 3);
                startHoverSprite.draw(game.batch);
            } else if ((touchX >= backSprite.getX()) && touchX <= (backSprite.getX() + backSprite.getWidth()) && (touchY >= backSprite.getY()) && touchY <= (backSprite.getY() + backSprite.getHeight())) {
                backHover = new Texture(Gdx.files.internal("back_hover.png"));
                backHoverSprite = new Sprite(backHover);
                backHoverSprite.setSize(80, 80);
                backHoverSprite.setPosition(5, 228);
                backHoverSprite.draw(game.batch);
            }
        }
    }

    public Fail(final SomeCatchVC game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        close = new Texture(Gdx.files.internal("close.png"));
        start = new Texture(Gdx.files.internal("start.png"));
        back = new Texture(Gdx.files.internal("back.png"));

        closeSprite = new Sprite(close);
        closeSprite.setSize(80, 80);
        closeSprite.setPosition(710, 380);

        startSprite = new Sprite(start);
        startSprite.setSize(120, 60);
        startSprite.setPosition(400 - 42, 90);

        backSprite = new Sprite(back);
        backSprite.setSize(80, 80);
        backSprite.setPosition(5, 228);

        failSound = Gdx.audio.newMusic(Gdx.files.internal("fail.mp3"));
        failSound.setLooping(false);
        if (GameScreen.isSoundPlay) {
            failSound.play();
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.3f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        closeSprite.draw(game.batch);
        startSprite.draw(game.batch);
        backSprite.draw(game.batch);
        handleTouch();
        hover();
        game.font.setColor(1, 0, 0, 1);
        game.font.draw(game.batch, "Fail!!! ", 400, 240);
        game.font.draw(game.batch, "Score - " + GameScreen.dropsGathered, 385, 215);
        game.font.setColor(0, 0, 0, 1);
        game.font.draw(game.batch, "Touch 'START' to begin!", 600, 50);
        game.font.setColor(1, 1, 1, 1);
        game.batch.end();
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
        failSound.dispose();
        close.dispose();
        back.dispose();

        if (startHover != null) startHover.dispose();
        if (closeHover != null) closeHover.dispose();
        if (backHover != null) backHover.dispose();

    }
}
