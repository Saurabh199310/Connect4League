package com.shivsau.connect4l;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Switch;

public class SignIn extends Activity implements OnTouchListener {
	static String colorOfPlayer1="red",colorOfPlayer2="blue";
	MyHelper mh;
	MyHelper3 mh3;
	EditText et1;
	EditText et2;
	Switch s1,s2;
	int flag1=0,flag2=0;
	String[] dg;String pl1,pl2;
	boolean notFirstTime=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		mh=new MyHelper(this);
		mh3=new MyHelper3(this);
		 et1=(EditText) findViewById(R.id.pl1);
	     et2=(EditText) findViewById(R.id.pl2);
	     s1=(Switch) findViewById(R.id.switch1);
	     s2=(Switch) findViewById(R.id.switch2);
	     s1.setOnTouchListener(this);
	     s2.setOnTouchListener(this);
	     retrv();
	     et1.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus&&notFirstTime)
			        et1.setText("");
				notFirstTime=true;
			}
		});
	     et2.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(hasFocus)
				        et2.setText("");
				}
			});
		}
	
	public void entry(View v){
		if(s1.isChecked())
		{
			pl1=et1.getText().toString();
			pl2=et2.getText().toString();
		}
		if(s2.isChecked())
		{
			pl2=et1.getText().toString();
			pl1=et2.getText().toString();
		}
		
		SQLiteDatabase db=mh.getWritableDatabase();
		SQLiteDatabase db3=mh3.getWritableDatabase();
		db.execSQL("delete from info");
		db3.execSQL("delete from viewScore");
		
		ContentValues cv=new ContentValues();
		cv.put("pl1", pl1);
		cv.put("pl2", pl2);
		ContentValues cv2=new ContentValues();
		cv2.put("name1", pl1);
		cv2.put("name2", pl2);
		
		long id=db.insert("info", null, cv);
		db3.insert("viewScore", null, cv2);
		if(id!=-1){
			Intent intent=new Intent(this,Magic.class);
			startActivity(intent);
			
		}else{
			//Toast.makeText(this,"Data was not inserted", Toast.LENGTH_SHORT).show();
		}
		db.close();
		db3.close();
		cv.clear();
		cv2.clear();
		
	}
	public void retrv(){
		SQLiteDatabase db=mh.getReadableDatabase();
		        Cursor cv=db.rawQuery("SELECT pl1,pl2 FROM info",null);
		        		        	 while(cv.moveToNext()){
			               
			               String pl1 = cv.getString(0);
			               String pl2 = cv.getString(1);
			                et1.setText(pl1);
			                et2.setText(pl2);
			                cv.close();
			                db.close();
			        }
		         }

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.switch1:
			if(event.getAction()==MotionEvent.ACTION_UP)
			{
				if(s1.isChecked()!=s2.isChecked())
				s2.toggle();
			}
			break;
		case R.id.switch2:
			if(event.getAction()==MotionEvent.ACTION_UP)
			{
				if(s1.isChecked()!=s2.isChecked())
				s1.toggle();
			}
			break;
			default:
				break;
		}
		return false;
	}

	public void clrTxt(View v)
	{
		switch(v.getId())
		{
		case R.id.pl1:
			et1.setText("");
			break;
		case R.id.pl2:
			et2.setText("");
			break;
		}
	}
}
