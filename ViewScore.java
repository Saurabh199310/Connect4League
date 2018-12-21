package com.shivsau.connect4l;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewScore extends Activity{
	ListView lv;
	MyHelper3 mh3;
	String name1=null,name2=null;
	TextView sc;
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewscore);
		sc=(TextView) findViewById(R.id.Result);
		mh3=new MyHelper3(this);
		lv=(ListView) findViewById(R.id.listView1);
		String[] fromColumns = {"name1","name2"};
		int[] toViews = {R.id.player1, R.id.player2};
		SQLiteDatabase db3=mh3.getWritableDatabase();
		Cursor cursor=db3.rawQuery("SELECT * FROM viewScore",null);
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.custom_adapter, cursor, fromColumns, toViews, 0);
		lv.setAdapter(adapter);
		Cursor cursor2=db3.rawQuery("SELECT * FROM viewScore",null);
		int i=1;
		int score1=0,score2=0;
	
			if(cursor2.moveToNext()){
				name1=cursor2.getString(1);
				name2=cursor2.getString(2);
				}	
		while(cursor2.moveToNext()){
			score1+=Integer.parseInt(cursor2.getString(1));
			score2+=Integer.parseInt(cursor2.getString(2));
			i++;
		}
		if(score1>score2)
		{
			sc.setText(name1+" won.");
		}else if(score2>score1)
			sc.setText(name2+" won.");
		else
			sc.setText("Its a TIE");
		db3.close();
	}	
	
}