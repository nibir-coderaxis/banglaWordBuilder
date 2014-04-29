package com.example.test2;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class Scissor extends Sprite
{

	public Scissor(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager)
	{
		super(pX, pY, pTextureRegion, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
	{
		switch (pSceneTouchEvent.getAction()) 
		{
		
		//On touch, enabling the merge
		case TouchEvent.ACTION_DOWN:
			
			break;
		
		//On release stopping the merge
		case TouchEvent.ACTION_UP:
				
				scissorPath();
			
			break;
		
		//On Move, making it Drag
		case TouchEvent.ACTION_MOVE:
			
			this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, 
					pSceneTouchEvent.getY() - this.getHeight() / 2);
			
			break;
		}

		return true;
	}
	
	public void scissorPath()
	{
		final Path scissorPath = new Path(2).to(MainActivity.Scissor.getX(), MainActivity.Scissor.getY())
				.to(MainActivity.CAMERA_WIDTH - 50, MainActivity.CAMERA_HEIGHT - 50);
		
		MainActivity.Scissor.registerEntityModifier(new PathModifier((float)0.3, scissorPath, null, new IPathModifierListener() {
			@Override
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
			{
				Debug.d("onPathStarted");
			}

			@Override
			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
			
			}

			@Override
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				Debug.d("onPathWaypointFinished: " + pWaypointIndex);
			}

			@Override
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
			{
				MainActivity.count1 = 0;
				MainActivity.count2 = 0;
				
			}
		}));
	}

}
