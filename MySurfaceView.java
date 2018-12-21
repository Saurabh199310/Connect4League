package com.shivsau.connect4l;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Runnable, SensorEventListener {

	
	SurfaceHolder holder;
	Thread myThread;
	boolean isRunning=true;
	private Bitmap bmpIcon;
    int xPos = 0;
    int yPos = 0;
    int deltaX = 5;
    int deltaY = 5;
    int iconWidth;
    int iconHeight;
    int accx=0;
    int accy=0;
    SensorManager sm;
	Sensor s;
	

	
	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		
	}
	
	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void init()
	{
		holder=getHolder();
		bmpIcon = BitmapFactory.decodeResource(getResources(), 
			    R.drawable.bubble);

			  iconWidth = bmpIcon.getWidth();
			  iconHeight = bmpIcon.getHeight();
			  
		sm = (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
		s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		holder.addCallback(new SurfaceHolder.Callback(){

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				resume();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				pause();
			}
			
		});
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning)
		{
			Canvas canvas= holder.lockCanvas();
			if(canvas != null){
			    synchronized (holder) {
			canvas.drawColor(Color.parseColor("#ffff99"));
		     canvas.drawBitmap(bmpIcon, xPos, yPos, null);
		        deltaX=accx;
		        deltaY=accy;
		        
		        if(deltaX > 0){
		         if(xPos >= getWidth() - iconWidth){
		             deltaX = 0;
		            }
		        }else{
		         if(xPos <= 0){
		             deltaX =0;
		            }
		        }
		        xPos += deltaX;
		        
		        if(deltaY > 0){
		         if(yPos >= getHeight() - iconHeight){
		             deltaY = 0;
		            }
		        }else{
		         if(yPos <= 0){
		             deltaY = 0;
		            }
		        }
		        yPos += deltaY;
			    }
		        holder.unlockCanvasAndPost(canvas);
		        
		}
	}
	}

	public void resume() {
		// TODO Auto-generated method stub
		sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
		isRunning=true;
		myThread= new Thread(this);
		myThread.start();
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
		sm.unregisterListener(this);
		isRunning=false;
		while(true)
		{
			try {
				myThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		myThread=null;
	}

	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		accx=-(int) Math.ceil(event.values[0]);
		accy=(int) Math.ceil(event.values[1]);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void update(float x, float y) {
		// TODO Auto-generated method stub
		xPos=(int) Math.ceil(x);
		yPos=(int) Math.ceil(y);
	}
}