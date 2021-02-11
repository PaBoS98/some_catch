package com.some_catch_vc.game.game_screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShopItem {

    Texture item;
    Texture closeItem;
    Sprite itemSprite;
    int wight;
    int height;
    float posX;
    float posY;

    public ShopItem(Texture item, int wight, int height, float posX, float posY) {

        this.item = item;
        this.wight = wight;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        this.itemSprite = new Sprite(this.item);
        this.itemSprite.setSize(wight, height);
        this.itemSprite.setPosition(posX, posY);


    }

    public ShopItem(Texture item, int wight, int height, float posX, float posY, boolean isClose, Texture closeItem) {

        this.item = item;
        this.closeItem = closeItem;
        this.wight = wight;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        if (isClose) {
            this.itemSprite = new Sprite(this.item);
        } else {
            this.itemSprite = new Sprite(this.closeItem);
        }
        this.itemSprite.setSize(wight, height);
        this.itemSprite.setPosition(posX, posY);


    }

}
