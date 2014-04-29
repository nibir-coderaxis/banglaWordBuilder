package com.example.test2;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;


public class Letter1 extends Sprite
{

	public static int letter1Count;
	
	public Letter1(float pX, float pY, ITextureRegion pTextureRegion,VertexBufferObjectManager vertexBufferObjectManager) 
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
				
				//for 'akar' and 'mo'
				if(MainActivity.mergeEnable2==true)
				{
					//'ma' Sound
					MainActivity.playSound(MainActivity.maSound);
				}
				else
				{
					//'mo' Sound
					MainActivity.playSound(MainActivity.akarSound);
				}
				
				break;
			
			//On release stopping the merge
			case TouchEvent.ACTION_UP:
					
				break;
			
			//On Move, making it Drag
			case TouchEvent.ACTION_MOVE:
				
				//for 'akar' and 'mo'
				if(MainActivity.mergeEnable1 == true)
				{
					this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
					pSceneTouchEvent.getY() - this.getHeight() / 2);
					
					MainActivity.mo.setPosition(pSceneTouchEvent.getX()+100 - 
					MainActivity.akar.getWidth() / 2, pSceneTouchEvent.getY() -
					MainActivity.akar.getHeight() / 2);

				}
				//for 'mo' and 'akar'
				else if(MainActivity.mergeEnable2 == true)
				{
					this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, 
					pSceneTouchEvent.getY() - this.getHeight() / 2);
					
					MainActivity.mo.setPosition(pSceneTouchEvent.getX()-100 - 
					MainActivity.akar.getWidth() / 2, pSceneTouchEvent.getY() -
					MainActivity.akar.getHeight() / 2);
				}
				else
				{
					this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, 
					pSceneTouchEvent.getY() - this.getHeight() / 2);
				}
				
				break;
			}

			return true;
		}

}
