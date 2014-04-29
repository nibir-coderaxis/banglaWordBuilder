package com.example.test2;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.util.debug.Debug;

public class Paths 
{
	
	//Paths after using scissor and splitting the words
	public static void splitPath()
	{
		//Splitting for 'akar' and 'mo'
		if(MainActivity.count1 == 1)
		{
			final Path splitPath = new Path(2).to(MainActivity.akar.getX(), MainActivity.akar.getY())
					.to(MainActivity.akar.getX()-20, MainActivity.akar.getY());
		
			MainActivity.akar.registerEntityModifier(new PathModifier((float)0.3, splitPath, null, new IPathModifierListener() 
			{
				@Override
				public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
				{
					Debug.d("onPathStarted");
				}

				@Override
				public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
			
				}

				@Override
				public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
					Debug.d("onPathWaypointFinished: " + pWaypointIndex);
				}

				@Override
				public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
				{

				}
			}));
			
			final Path moPath = new Path(2).to(MainActivity.mo.getX(), MainActivity.mo.getY())
					.to(MainActivity.mo.getX()+20, MainActivity.mo.getY());
		
			MainActivity.mo.registerEntityModifier(new PathModifier((float)0.3, moPath, null, new IPathModifierListener() 
			{
				@Override
				public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
				{
					Debug.d("onPathStarted");
				}

				@Override
				public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
				}

				@Override
				public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointFinished: " + pWaypointIndex);
				}

				@Override
				public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
				{

				}
			}));
		}
		//Splitting for 'mo' and 'akar'
		else if(MainActivity.count2 == 1)
		{
			final Path splitPath = new Path(2).to(MainActivity.akar.getX(), MainActivity.akar.getY())
					.to(MainActivity.akar.getX()+20, MainActivity.akar.getY());
		
			MainActivity.akar.registerEntityModifier(new PathModifier((float)0.3, splitPath, null, new IPathModifierListener() 
			{
				@Override
				public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
				{
					Debug.d("onPathStarted");
				}

				@Override
				public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
			
				}

				@Override
				public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
					Debug.d("onPathWaypointFinished: " + pWaypointIndex);
				}

				@Override
				public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
				{

				}
			}));
			
			final Path moPath = new Path(2).to(MainActivity.mo.getX(), MainActivity.mo.getY())
					.to(MainActivity.mo.getX()-20, MainActivity.mo.getY());
		
			MainActivity.mo.registerEntityModifier(new PathModifier((float)0.3, moPath, null, new IPathModifierListener() 
			{
				@Override
				public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) 
				{
					Debug.d("onPathStarted");
				}

				@Override
				public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointStarted:  " + pWaypointIndex);
				}

				@Override
				public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex)
				{
					Debug.d("onPathWaypointFinished: " + pWaypointIndex);
				}

				@Override
				public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) 
				{

				}
			}));
		}
	}
	
	//Scissor Paths
	public static void scissorPath()
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
