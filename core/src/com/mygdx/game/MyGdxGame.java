package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture[] imgs = new Texture[4];
	OrthographicCamera mapCamera, vehicleCamera;
	float x = 100, y = 100;
	int time = 0;
	int rotationD, srotdeg;
	int vehSpeed = 100;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		imgs[0] = new Texture("mapa.png");
		imgs[1] = new Texture("libgdxgridtest.png");
		imgs[2] = new Texture("Audi.png");
		imgs[3] = new Texture("collisionMap.png");
		mapCamera = new OrthographicCamera();
		vehicleCamera = new OrthographicCamera();
	}

	@Override
	public void render () {
		rotationD = 0;

		if (Gdx.input.isTouched()) {
			for (int i = 0; i < 10 ; i++) {
				if (Gdx.input.getX(i) > 0.6 * Gdx.graphics.getWidth()) {
					// TODO
					rotationD += 50;
				}

				if (Gdx.input.getX(i) < 0.4 * Gdx.graphics.getWidth()) {
					// TODO
					rotationD -= 50;
				}
			}
		}

		srotdeg -= rotationD * Gdx.graphics.getDeltaTime();
		float forward = vehSpeed * Gdx.graphics.getDeltaTime();
		float srotrad = (float) (srotdeg / 180 * Math.PI);
		x += Math.cos(srotrad) * forward;
		y += Math.sin(srotrad) * forward;

/*
		if(x > 700){
			y = time;
		}
		else
			x = time;
*/

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapCamera.setToOrtho(false, 800, 480);
		mapCamera.translate(-400, -240);
		mapCamera.translate(x, y);
		mapCamera.update();

		vehicleCamera.setToOrtho(false, 800, 480);
		vehicleCamera.translate(-400, -240);
		vehicleCamera.translate(25, 25);
		vehicleCamera.rotate(srotdeg - 90);
		vehicleCamera.update();

		batch.begin();
		batch.setProjectionMatrix(mapCamera.combined);
		batch.draw(imgs[0], 0, 0);
		batch.draw(imgs[1], 0, 0);
		batch.setProjectionMatrix(vehicleCamera.combined);
		batch.draw(imgs[2], 0, 0);
		batch.end();


		/*if(x > 700 && y > 700)
			time -= Gdx.graphics.getDeltaTime() * 100;
		else
			time += Gdx.graphics.getDeltaTime() * 100;*/
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		imgs[0].dispose();
		imgs[1].dispose();
		imgs[2].dispose();
		imgs[3].dispose();
	}
}
