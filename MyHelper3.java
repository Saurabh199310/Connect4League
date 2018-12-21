package com.shivsau.connect4l;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper3  extends SQLiteOpenHelper{

	Context con;
	public MyHelper3(Context context) {
		super(context,"MyDatabase3", null,1);
		con=context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try{
		db.execSQL("create table viewScore(_id INTEGER PRIMARY KEY AUTOINCREMENT, name1 TEXT NOT NULL,name2 TEXT NOT NULL);");
				
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void delete(SQLiteDatabase db){
		db.execSQL("delete * from info");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}