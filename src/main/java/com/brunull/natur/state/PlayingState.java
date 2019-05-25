package com.brunull.natur.state;

import com.brunull.natur.AssetManager;
import com.brunull.natur.actor.Actor;
import com.brunull.natur.actor.PlayerActor;
import com.brunull.natur.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class PlayingState extends GameState {

    private ArrayList<Actor> actors;
    private PlayerActor player;

    public PlayingState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void enter() {
        actors = new ArrayList<>();
        try {
			player = new PlayerActor(AssetManager.loadSprite("/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void exit() {
    	gameStateManager.popState();
    }

    @Override
    public void update() {
        for (Actor actor : actors) {
            actor.update();
        }

        player.update();
        
        if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
        	exit();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("This is the playing state.", 5, 15);
        g.drawString("Player X: " + player.getX(), 5, 35);
        g.drawString("Player Y: " + player.getY(), 5, 55);
        
        player.draw(g);

//        Renderer renderer = gameStateManager.getGame().getRenderer();
//        renderer.drawSprite(g, player.getSprite());
    }
}