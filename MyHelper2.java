package com.shivsau.connect4l;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper2 extends SQLiteOpenHelper{

	Context con;
	public MyHelper2(Context context) {
		super(context,"MyDatabase2", null,1);
		con=context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try{
		db.execSQL("CREATE TABLE arrVal(value String NOT NULL, c INTEGER NOT NULL, co INTEGER NOT NULL, " +
				" ct INTEGER NOT NULL, cth INTEGER NOT NULL, cf INTEGER NOT NULL, cfi INTEGER NOT NULL," +
				" p INTEGER NOT NULL, f INTEGER NOT NULL, h INTEGER NOT NULL, fuck INTEGER NOT NULL);");		
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	public void delete(SQLiteDatabase db){
		db.execSQL("delete * from arrVal");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}