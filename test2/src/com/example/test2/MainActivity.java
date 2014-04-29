package com.example.test2;

import java.io.IOException;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import android.view.Display;

public class MainActivity extends SimpleBaseGameActivity 
{
	// ===========================================================
	// Constants
	// ===========================================================

	public static int CAMERA_WIDTH;
	public static int CAMERA_HEIGHT;
	public String DEBUG_TAG = MainActivity.class.getSimpleName();
	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;
	public Scene mScene;

	// Bitmap Texture For Bangla 'A' Letter

	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion mAkarTextureRegion;
	private ITextureRegion mMOTextureRegion;
	private ITextureRegion mMaTextureRegion;
	private ITextureRegion mS1TextureRegion;
	
	public static float centerX, centerY;

	public static Sprite maImage;
	public static Letter1 akar;
	public static Letter2 mo;
	public static Scissor Scissor;
	public static Sound moSound, akarSound, maSound;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public static boolean mergeEnable1 = false;
	public static boolean mergeEnable2 = false;
	
	public static int count1, count2;

	@Override
	public EngineOptions onCreateEngineOptions()
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay();
		CAMERA_WIDTH = display.getWidth();
		CAMERA_HEIGHT = display.getHeight();

		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		EngineOptions en = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
						   new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		// Setting MuliTouch
		en.getTouchOptions().setNeedsMultiTouch(true);
		// Setting Sound
		en.getAudioOptions().setNeedsSound(true);

		return en;
	}

	@Override
	protected void onCreateResources() 
	{
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512, 512);

		this.mAkarTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "1.png");
		this.mMOTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "mo.png");
		this.mMaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "ma.png");
		this.mS1TextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mBitmapTextureAtlas, this, "s1.png");

		try
		{
			this.mBitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 0, 0));
			this.mBitmapTextureAtlas.load();
		} 
		catch (TextureAtlasBuilderException e) 
		{
			Debug.e(e);
		}

		SoundFactory.setAssetBasePath("mfx/");
		try
		{
			this.akarSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "akar.mp3");
			this.moSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "mo.mp3");
			this.maSound = SoundFactory.createSoundFromAsset(
					this.mEngine.getSoundManager(), this, "ma.mp3");

		} 
		catch (final IOException e) 
		{
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() 
	{
		// TODO Auto-generated method stub
		// this.mEngine.registerUpdateHandler(new FPSLogger());
		mScene = new Scene();
		mScene.setBackground(new Background(Color.WHITE));
		mScene.setTouchAreaBindingOnActionDownEnabled(true);

		centerX = (CAMERA_WIDTH - this.mAkarTextureRegion.getWidth()) / 2;
		centerY = (CAMERA_HEIGHT - this.mAkarTextureRegion.getHeight()) / 2;

		//Setting the maImage invisible
		maImage = new Sprite(CAMERA_WIDTH - 150 , 0, this.mMaTextureRegion, this.getVertexBufferObjectManager());
		mScene.attachChild(maImage);
		maImage.setVisible(false);
		
		mScene.registerUpdateHandler(new TimerHandler((float) 0.09, true,
				new ITimerCallback()
		{
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) 
					{
						// TODO Auto-generated method stub
						
						// Calculating if 'mo' is in the range of 'akar'
						if (mo.getX() - akar.getX() > 90
								&& mo.getX() - akar.getX() < 110
								&& mo.getY() - akar.getY() < 25
								&& mo.getY() - akar.getY() > -25)
						{
							if (mo.getX() == akar.getX() + 100)
							{
								if(Scissor.collidesWith(akar) && Scissor.collidesWith(mo))
								{
									count1++;
									Debug.d("Count1:"+count1);
									if(count1 == 1)
									{
										mergeEnable1 = false;
										mergeEnable2 = false;
										splitPath();
									}
								}
							
							}
							//If letters are in the range but not merged, then enable merging
							else
							{
								mergeEnable1 = true;
							}

						}
						// Calculating if 'akar' is in the range of 'mo'----making 'ma' word
						else if (akar.getX() - mo.getX() > 90
								&& akar.getX() - mo.getX() < 110
								&& akar.getY() - mo.getY() < 25
								&& akar.getY() - mo.getY() > -25) 
						{
							if (mo.getX() + 100 == akar.getX()) 
							{
								//Setting the maImage visible when the letters are merged
								maImage.setVisible(true);
								

								if(Scissor.collidesWith(akar) && Scissor.collidesWith(mo))
								{
									count2++;
									Debug.d("Count2:"+count2);
									if(count2 == 1)
									{
										mergeEnable1 = false;
										mergeEnable2 = false;
										splitPath();
									}
								}
							}
							//If letters are in the range but not merged, then enable merging
							else
							{
								mergeEnable2 = true;
							}
						}
						else
						{
							//Setting the maImage invisible when the letters are not merged
							maImage.setVisible(false);
						}
					}
				}));

		// Bangla Letter 'Akar'
		akar = new Letter1(centerX, centerY, this.mAkarTextureRegion, getVertexBufferObjectManager());

		mScene.registerTouchArea(akar);
		mScene.attachChild(akar);

		// Bangla Letter 'MO'
		mo = new Letter2(centerX + 200, centerY, this.mMOTextureRegion, getVertexBufferObjectManager());

		mScene.registerTouchArea(mo);
		mScene.attachChild(mo);

		Scissor = new Scissor(CAMERA_WIDTH - 50, CAMERA_HEIGHT - 50, this.mS1TextureRegion, this.getVertexBufferObjectManager());
		mScene.registerTouchArea(Scissor);
		mScene.attachChild(Scissor);
		
		return mScene;
	}

	// Method for playing sounds
	public static void playSound(Sound s) 
	{
		// TODO Auto-generated method stub
		if (s.isReleased() == true) 
		{

		}
		else
		{
			s.play();
		}
	}
	
	public void splitPath()
	{
		//Splitting for 'akar' and 'mo'
		if(count1 == 1)
		{
			final Path splitPath = new Path(2).to(akar.getX(), akar.getY())
					.to(akar.getX()-20, akar.getY());
		
			akar.registerEntityModifier(new PathModifier((float)0.3, splitPath, null, new IPathModifierListener() 
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
			
			final Path moPath = new Path(2).to(mo.getX(), mo.getY())
					.to(mo.getX()+20, mo.getY());
		
			mo.registerEntityModifier(new PathModifier((float)0.3, moPath, null, new IPathModifierListener() 
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
		else if(count2 == 1)
		{
			final Path splitPath = new Path(2).to(akar.getX(), akar.getY())
					.to(akar.getX()+20, akar.getY());
		
			akar.registerEntityModifier(new PathModifier((float)0.3, splitPath, null, new IPathModifierListener() 
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
			
			final Path moPath = new Path(2).to(mo.getX(), mo.getY())
					.to(mo.getX()-20, mo.getY());
		
			mo.registerEntityModifier(new PathModifier((float)0.3, moPath, null, new IPathModifierListener() 
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
}
