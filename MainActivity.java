package com.shivsau.connect4l;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity{
 static int fuck=0;
 static int fuck2=1;
 MySurfaceView ob;
 private Handler handler;
	int flag=0;
	boolean firstTime=true;
	ImageView i1;
	ImageView i2;
	ImageView i3;
	ImageView i4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		ob= new MySurfaceView(this);
		setContentView(R.layout.start);
		i1=(ImageView) findViewById(R.id.iv1);
		i2=(ImageView) findViewById(R.id.iv2);
		i3=(ImageView) findViewById(R.id.iv3);
		i4=(ImageView) findViewById(R.id.iv4);
		
		 handler= new Handler();
		 runnable.run();
		 firstTime=false;
		}
	Runnable runnable=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			update();
		handler.postDelayed(runnable, 500);	
		}

		private void update() {
			// TODO Auto-generated method stub

	    	if (flag==0)
	    	{flag=1;
	        i1.setImageResource(R.drawable.blue_ball);
	        i2.setImageResource(R.drawable.blue_ball);
	        i3.setImageResource(R.drawable.blue_ball);
	        i4.setImageResource(R.drawable.blue_ball);
	    	}else
	    	{
	    		flag=0;
	    		i1.setImageResource(R.drawable.red_ball);
	    		i2.setImageResource(R.drawable.red_ball);
	    		i3.setImageResource(R.drawable.red_ball);
	    		i4.setImageResource(R.drawable.red_ball);
	    	}
		}
		
	};
	public void newg(View v){
		fuck=0;
		Intent intent=new Intent(this,SignIn.class);
		startActivity(intent);
		}
	public void newl(View v){
		fuck=1;
		Intent intent=new Intent(this,SignIn.class);
		startActivity(intent);
	}
		public void bClicked(View v)
		{
			MyHelper2 mh =new MyHelper2(this);
			SQLiteDatabase db2 = mh.getReadableDatabase();
			Cursor cursor= db2.rawQuery("SELECT fuck FROM arrVal", null);
			if(cursor.moveToNext())
			{
				fuck=cursor.getInt(0);
			}
			Intent i=new Intent(this,Magic.class);
			i.putExtra("key",'y');
			fuck2=1;
			startActivity(i);
		}
		
		public void goToInfo(View v)
		{
			Intent i =new Intent(this, Infouser.class);
			startActivity(i);
		}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ob.resume();
		if(firstTime)
		runnable.run();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ob.pause();
		firstTime=true;
		handler.removeCallbacks(runnable);
	}	
}
