package com.shivsau.connect4l;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class Magic extends Activity implements OnClickListener, OnTouchListener{

	Button coln1;
	Button coln2;
	Button coln3;
	Button coln4;
	Button coln5;
	MyHelper mh;
	MyHelper2 mh2;
	MyHelper3 mh3;
	SQLiteDatabase db3;
	int c=0;
	int c1=0;
	int c2=0;
	int c3=0;
	int c4=0;
	int c5=0;
	int k=0;
	int p=0;
	int f=0;
	int h=0;
	int flag=0;
	String name1,name2;
	String colourOfPlayer1,colourOfPlayer2;
	Switch swtch;
	int sau;
	
	int[][] arr=new int[5][5];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sau=MainActivity.fuck;
		if(sau==0){
		setContentView(R.layout.activity_magic2);
		}else if(sau==1){
			setContentView(R.layout.activity_magic_4league);
			mh3=new MyHelper3(this);
		    db3=mh3.getWritableDatabase();
		}
		swtch=(Switch) findViewById(R.id.switch11);
		swtch.setOnTouchListener(this);
		mh=new MyHelper(this);
		mh2=new MyHelper2(this);
		
		SQLiteDatabase db=mh.getReadableDatabase();
		Cursor cv=db.rawQuery("SELECT pl1,pl2 FROM info",null);
		while(cv.moveToNext()){
		name1=cv.getString(0);
		name2=cv.getString(1);}
		cv.close();
		db.close();
		coln1=(Button)findViewById(R.id.col1);
		coln1.setOnClickListener(this);
		coln2=(Button)findViewById(R.id.col2);
		coln2.setOnClickListener(this);
		coln3=(Button)findViewById(R.id.col3);
		coln3.setOnClickListener(this);
		coln4=(Button)findViewById(R.id.col4);
		coln4.setOnClickListener(this);
		coln5=(Button)findViewById(R.id.col5);
		coln5.setOnClickListener(this);
		
		colourOfPlayer1=SignIn.colorOfPlayer1;
		colourOfPlayer2=SignIn.colorOfPlayer2;
		
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
				arr[i][j]=0;
		}
		
		Intent in= getIntent();
		char ch = in.getCharExtra("key", 'n');
		if(ch=='y'&& MainActivity.fuck2==1)
		{
		MainActivity.fuck2=0;
		resume();
		}
	}
	public void viewScore(View v){
		Intent intent=new Intent(this,ViewScore.class);
		startActivity(intent);
	}
private void resume() {
		// TODO Auto-generated method stub
	String s;
	int[][] a={{0x7f050060,0x7f05005f,0x7f05005e,0x7f05005d,0x7f05005c},{0x7f05005b,0x7f05005a,0x7f050059,0x7f050058,0x7f050057},{0x7f050056,0x7f050055,0x7f050054,0x7f050053,0x7f050052},{0x7f050051,0x7f050050,0x7f05004f,0x7f05004e,0x7f05004d},{0x7f05004c,0x7f05004b,0x7f05004a,0x7f050049,0x7f050048}};
	int index=0;
	char ch;
	SQLiteDatabase db2=mh2.getReadableDatabase();
	Cursor cursor=db2.rawQuery("SELECT * FROM arrVal", null);
	while(cursor.moveToNext()){
		s=cursor.getString(0);
		c=cursor.getInt(1);
		c1=cursor.getInt(2);
		c2=cursor.getInt(3);
		c3=cursor.getInt(4);
		c4=cursor.getInt(5);
		c5=cursor.getInt(6);
		p=cursor.getInt(7);
		f=cursor.getInt(8);
		h=cursor.getInt(9);
		cursor.close();
		db2.close();
	if(c1>0||c2>0||c3>0||c4>0||c5>0)
	{
		if(c==(c1+c2+c3+c4+c5))
			swtch.setChecked(true);
		else
			swtch.setChecked(false);
		swtch.setEnabled(false);
	}
	for(int i=0;i<5;i++){
		for(int j=0;j<5;j++){
			ch=s.charAt(index++);
			if(ch=='A')
			arr[i][j]=0;
			if(ch=='B')
				arr[i][j]=1;
			if(ch=='C')
				arr[i][j]=2;
			if(arr[i][j]==1){
				ImageView iv=(ImageView) findViewById(a[i][j]);
				iv.setImageResource(R.drawable.red_ball);
			}
			if(arr[i][j]==2){
				ImageView iv=(ImageView) findViewById(a[i][j]);
				iv.setImageResource(R.drawable.blue_ball);
			}
			
		}
	}
	}
	}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch(item.getItemId()){
	case R.id.item1:
		if(sau==1){
			 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		      alertDialogBuilder.setMessage("Clicking on NEW GAME will erase data from Score Table and a new league will start." +
		      		"Do you want to start a new league?");
		      
		      alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface arg0, int arg1) {
		        	 db3.execSQL("delete from viewScore");
		     		ContentValues cv=new ContentValues();
		     		cv.put("name1", name1);
		     		cv.put("name2", name2);
		     		db3.insert("viewScore", null, cv);
		     		db3.close();
		     		cv.clear();
		     		Intent inte=getIntent();
		     		finish();
		     		startActivity(inte);  
		         }
		      });
		      
		      alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		         }
		      });
		      
		      AlertDialog alertDialog = alertDialogBuilder.create();
		      alertDialog.show();
		
		}
		else if(sau==0){
			if((c1==0&&c2==0&&c3==0&&c4==0&&c5==0)||p==1||(c1==5&&c2==5&&c3==5&&c4==5&&c5==5&&p==0))
			{
			Intent intent=getIntent();
			finish();
			startActivity(intent);
		}else
		{
			AlertDialog.Builder dial = new AlertDialog.Builder(this);
			dial.setMessage("Are you sure you want to start a new game?");
			dial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent=getIntent();
					finish();
					startActivity(intent);
				}
			});
			dial.setNegativeButton("No", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});
			AlertDialog dials = dial.create();
		      dials.show();
		}
		}
		return true;
	case R.id.item2:
		if(p!=1&&h==0)
		{
		int i=0;h=1;
		int j=0;
		int[][] a={{0x7f050060,0x7f05005f,0x7f05005e,0x7f05005d,0x7f05005c},{0x7f05005b,0x7f05005a,0x7f050059,0x7f050058,0x7f050057},{0x7f050056,0x7f050055,0x7f050054,0x7f050053,0x7f050052},{0x7f050051,0x7f050050,0x7f05004f,0x7f05004e,0x7f05004d},{0x7f05004c,0x7f05004b,0x7f05004a,0x7f050049,0x7f050048}};
		if(f==1)
			{
				i=c1-1;
				j=0;
				ImageView iv=(ImageView)findViewById(a[i][j]);
				iv.setImageResource(R.drawable.blnk_ball);
				arr[i][j]=0;
				c--;c1--;
			}else if(f==2)
			{
				
					i=c2-1;
					j=1;
					ImageView iv=(ImageView)findViewById(a[i][j]);
					iv.setImageResource(R.drawable.blnk_ball);
					arr[i][j]=0;
					c--;c2--;
			}else if(f==3)
			{
				
					i=c3-1;
				
					j=2;
					ImageView iv=(ImageView)findViewById(a[i][j]);
					iv.setImageResource(R.drawable.blnk_ball);
					arr[i][j]=0;
					c--;c3--;
			}else if(f==4)
			{
				
					i=c4-1;
				
					j=3;
					ImageView iv=(ImageView)findViewById(a[i][j]);
					iv.setImageResource(R.drawable.blnk_ball);
					arr[i][j]=0;
					c--;c4--;
			}else if(f==5)
			{
				
				i=c5-1;
			
				j=4;
				ImageView iv=(ImageView)findViewById(a[i][j]);
				iv.setImageResource(R.drawable.blnk_ball);
				arr[i][j]=0;
				c--;c5--;
			}else
				{}
			}
		if(c1==0&&c2==0&&c3==0&&c4==0&&c5==0)
			swtch.setEnabled(true);
			return true;
	case R.id.item3:
		Intent intent =new Intent(this,Infouser.class);
		startActivity(intent);
			default:
				return true;
	}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		String s="";
		int i,j;
		for(i=0;i<5;i++){
			for(j=0;j<5;j++)
				s+=(char)(arr[i][j]+65);
		}
		SQLiteDatabase db=mh2.getWritableDatabase();
		db.execSQL("delete from arrVal");
		ContentValues cv=new ContentValues();
		cv.put("value", s);
		cv.put("c",c);
		cv.put("co",c1);
		cv.put("ct",c2);
		cv.put("cth",c3);
		cv.put("cf",c4);
		cv.put("cfi",c5);
		cv.put("p",p);
		cv.put("h",h);
		cv.put("f",f);
		cv.put("fuck", sau);
		db.insert("arrVal", null,cv);
		db.close();
		cv.clear();
	}
	

	@Override
	public void onClick(View v) {
  switch (v.getId()) {
		
			
			case R.id.col1:
			if(p==0)
			{
			if((c%2==0)&&(c1<5))
			{f=1;h=0;
				if(c1==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zz);
				iv.setImageResource(R.drawable.red_ball);
				arr[0][0]=1;
				check();
			}if(c1==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oz);
				iv.setImageResource(R.drawable.red_ball);
				arr[1][0]=1;
				check();
			}if(c1==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tz);
				iv.setImageResource(R.drawable.red_ball);
				arr[2][0]=1;
				check();
			}if(c1==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.thz);
				iv.setImageResource(R.drawable.red_ball);
				arr[3][0]=1;
				check();
			}
			if(c1==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fz);
				iv.setImageResource(R.drawable.red_ball);
				arr[4][0]=1;
				check();
			}
			}
			if((c%2==1)&&(c1<5))
			{f=1;h=0;
				if(c1==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zz);
				iv.setImageResource(R.drawable.blue_ball);
				arr[0][0]=2;
				check();
			}if(c1==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oz);
				iv.setImageResource(R.drawable.blue_ball);
				arr[1][0]=2;
				check();
			} if(c1==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tz);
				iv.setImageResource(R.drawable.blue_ball);
				arr[2][0]=2;
				check();
			}if(c1==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.thz);
				iv.setImageResource(R.drawable.blue_ball);
				arr[3][0]=2;
				check();
			}
			if(c1==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fz);
				iv.setImageResource(R.drawable.blue_ball);
				arr[4][0]=2;
				check();
			}
			}
			if(c1==4)
			{
				c--;
				c1=5;
				checkForDraw();
			}
			if(c1<4)
			{
			c1++;
			c++;
			}
			}
			break;
			
			
		case R.id.col2:
			if(p==0)
			{
			if((c%2==0)&&(c2<5))
			{f=2;h=0;
				if(c2==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zo);
				iv.setImageResource(R.drawable.red_ball);
				arr[0][1]=1;
				check();
			}if(c2==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oo);
				iv.setImageResource(R.drawable.red_ball);
				arr[1][1]=1;
				check();
			}if(c2==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.to);
				iv.setImageResource(R.drawable.red_ball);
				arr[2][1]=1;
				check();
			}if(c2==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.tho);
				iv.setImageResource(R.drawable.red_ball);
				arr[3][1]=1;
				check();
			}if(c2==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fo);
				iv.setImageResource(R.drawable.red_ball);
				arr[4][1]=1;
				check();
			}
			}
			if((c%2==1)&&(c2<5))
			{f=2;h=0;
				if(c2==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zo);
				iv.setImageResource(R.drawable.blue_ball);
				arr[0][1]=2;
				check();
			}if(c2==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oo);
				iv.setImageResource(R.drawable.blue_ball);
				arr[1][1]=2;
				check();
			}if(c2==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.to);
				iv.setImageResource(R.drawable.blue_ball);
				arr[2][1]=2;
				check();
			}if(c2==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.tho);
				iv.setImageResource(R.drawable.blue_ball);
				arr[3][1]=2;
				check();
			}if(c2==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fo);
				iv.setImageResource(R.drawable.blue_ball);
				arr[4][1]=2;
				check();
			}
			}
			if(c2==4)
			{
				c--;
				c2=5;
				checkForDraw();
			}
			if(c2<4)
			{
			c2++;
			c++;
			}
			}
			break;
			
			
		case R.id.col3:
			if(p==0)
			{
			if((c%2==0)&&(c3<5))
			{f=3;h=0;
				if(c3==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zt);
				iv.setImageResource(R.drawable.red_ball);
				arr[0][2]=1;
				check();
			}if(c3==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.ot);
				iv.setImageResource(R.drawable.red_ball);
				arr[1][2]=1;
				check();
			}if(c3==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tt);
				iv.setImageResource(R.drawable.red_ball);
				arr[2][2]=1;
				check();
			}if(c3==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.tht);
				iv.setImageResource(R.drawable.red_ball);
				arr[3][2]=1;
				check();
			}if(c3==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.ft);
				iv.setImageResource(R.drawable.red_ball);
				arr[4][2]=1;
				check();
			}
			}
			if((c%2==1)&&(c3<5))
			{f=3;h=0;
				if(c3==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zt);
				iv.setImageResource(R.drawable.blue_ball);
				arr[0][2]=2;
				check();
			}if(c3==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.ot);
				iv.setImageResource(R.drawable.blue_ball);
				arr[1][2]=2;
				check();
			}if(c3==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tt);
				iv.setImageResource(R.drawable.blue_ball);
				arr[2][2]=2;
				check();
			}if(c3==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.tht);
				iv.setImageResource(R.drawable.blue_ball);
				arr[3][2]=2;
				check();
			}if(c3==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.ft);
				iv.setImageResource(R.drawable.blue_ball);
				arr[4][2]=2;
				check();
			}
			}
			if(c3==4)
			{
				c--;
				c3=5;
				checkForDraw();
			}
			if(c3<4)
			{
			c3++;
			c++;
			}
			}
			break;
			
			
		case R.id.col4:
			if(p==0)
			{
			if((c%2==0)&&(c4<5))
			{f=4;h=0;
				if(c4==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zth);
				iv.setImageResource(R.drawable.red_ball);
				arr[0][3]=1;
				check();
			}if(c4==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oth);
				iv.setImageResource(R.drawable.red_ball);
				arr[1][3]=1;
				check();
				}if(c4==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tth);
				iv.setImageResource(R.drawable.red_ball);
				arr[2][3]=1;
				check();
			}if(c4==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.thth);
				iv.setImageResource(R.drawable.red_ball);
				arr[3][3]=1;
				check();
			}if(c4==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fth);
				iv.setImageResource(R.drawable.red_ball);
				arr[4][3]=1;
				check();
			}
			}
			if((c%2==1)&&(c4<5))
			{f=4;h=0;
				if(c4==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zth);
				iv.setImageResource(R.drawable.blue_ball);
				arr[0][3]=2;
				check();
			}if(c4==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.oth);
				iv.setImageResource(R.drawable.blue_ball);
				arr[1][3]=2;
				check();
			}if(c4==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tth);
				iv.setImageResource(R.drawable.blue_ball);
				arr[2][3]=2;
				check();
			}if(c4==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.thth);
				iv.setImageResource(R.drawable.blue_ball);
				arr[3][3]=2;
				check();
			}if(c4==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.fth);
				iv.setImageResource(R.drawable.blue_ball);
				arr[4][3]=2;
				check();
			}
			}
			if(c4==4)
			{
				c--;
				c4=5;
				checkForDraw();
			}
			if(c4<4)
			{
			c4++;
			c++;
			
			}
			}
			break;
		case R.id.col5:
			if(p==0)
			{
			if((c%2==0)&&(c5<5))
			{f=5;h=0;
				if(c5==0)
				{
				ImageView iv=(ImageView)findViewById(R.id.zf);
				iv.setImageResource(R.drawable.red_ball);
				arr[0][4]=1;
				check();
			}if(c5==1)
			{
				ImageView iv=(ImageView)findViewById(R.id.of);
				iv.setImageResource(R.drawable.red_ball);
				arr[1][4]=1;
				check();
				}if(c5==2)
			{
				ImageView iv=(ImageView)findViewById(R.id.tf);
				iv.setImageResource(R.drawable.red_ball);
				arr[2][4]=1;
				check();
			}if(c5==3)
			{
				ImageView iv=(ImageView)findViewById(R.id.thf);
				iv.setImageResource(R.drawable.red_ball);
				arr[3][4]=1;
				check();
			}if(c5==4)
			{
				ImageView iv=(ImageView)findViewById(R.id.ff);
				iv.setImageResource(R.drawable.red_ball);
				arr[4][4]=1;
				check();
			}
			}
			if((c%2==1)&&(c5<5))
			{f=5;h=0;
			if(c5==0)
			{
			ImageView iv=(ImageView)findViewById(R.id.zf);
			iv.setImageResource(R.drawable.blue_ball);
			arr[0][4]=2;
			check();
			}if(c5==1)
			{
			ImageView iv=(ImageView)findViewById(R.id.of);
			iv.setImageResource(R.drawable.blue_ball);
			arr[1][4]=2;
			check();
			}if(c5==2)
			{
			ImageView iv=(ImageView)findViewById(R.id.tf);
			iv.setImageResource(R.drawable.blue_ball);
			arr[2][4]=2;
			check();
			}if(c5==3)
			{
			ImageView iv=(ImageView)findViewById(R.id.thf);
			iv.setImageResource(R.drawable.blue_ball);
			arr[3][4]=2;
			check();
			}if(c5==4)
			{
			ImageView iv=(ImageView)findViewById(R.id.ff);
			iv.setImageResource(R.drawable.blue_ball);
			arr[4][4]=2;
			check();
			}
			}
			if(c5==4)
			{
				c--;
				c5=5;
				checkForDraw();
			}
			if(c5<4)
			{
			c5++;
			c++;
			}
			}
			break;
		}}
private void checkForDraw() {
		// TODO Auto-generated method stub
	if(c1==5&&c2==5&&c3==5&&c4==5&&c5==5&&p==0)
	{
		if(sau==0)
			Toast.makeText(this, "It's a Draw!!", Toast.LENGTH_LONG).show();
		else
		{
			ContentValues cv2 =new ContentValues();
			cv2.put("name1", ""+0);
			cv2.put("name2", ""+0);
			Toast.makeText(this, "It's a Draw!!", Toast.LENGTH_LONG).show();
			long id=db3.insert("viewScore", null, cv2);
			if(id!=-1){
				db3.close();
				cv2.clear();
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getApplicationContext(),Magic.class);
						finish();
						startActivity(intent);	
					}
					
				}, 1000);
			
		}
	}
	}
	}
private void check() {
		// TODO Auto-generated method stub
	swtch.setEnabled(false);
		if(chkh()>0)
		{
		k=chkh();
	}
		else if(chkv()>0)
	{
		k=chkv();
	} 
		else if(chkd()>0)
	{
		k=chkd();
	}else
	{}
		
		if(k>0)
		{
			p=1;
			if(sau==1){
				ContentValues cv=new ContentValues();
			if(k==1){
					cv.put("name1", ""+1);
					cv.put("name2",""+0);
				Toast.makeText(this, ""+name1+" Won", Toast.LENGTH_LONG).show();
				}
				
			else{
				cv.put("name1",""+ 0);
				cv.put("name2", ""+1);
				Toast.makeText(this, ""+name2+" Won", Toast.LENGTH_LONG).show();
			}
			long id=db3.insert("viewScore", null, cv);
			if(id!=-1){
				
				db3.close();
				cv.clear();
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getApplicationContext(),Magic.class);
						finish();
						startActivity(intent);	
					}
					
				}, 1000);
				
				
			}else{
				//Toast.makeText(this,"View Score entries were not inserted", Toast.LENGTH_SHORT).show();
			}
			}
			if(k>0){
			if(sau==0){
				if(k==1)
					Toast.makeText(this, ""+name1+" Won", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(this, ""+name2+" Won", Toast.LENGTH_LONG).show();
			}
	}}
		
}



	private int chkh() {
		// TODO Auto-generated method stub
		int i,j,cm=0;
		ml:for(i=0;i<5;i++)
		{
			for(j=0;j<5;j++)
			{
				if((arr[i][j]!=0)&&(j!=3)&&(j!=2)&&(j!=4))
				{
				if(arr[i][j]==arr[i][j+1])
				{
					if(arr[i][j]==arr[i][j+2] && arr[i][j]==arr[i][j+3])
						{cm=arr[i][j];
						break ml;
				}
				}
			}
			}
		}
		
		
		return cm;
	}

	private int chkv() {
		// TODO Auto-generated method stub
		int i,j,cm=0;
		ml:for(j=0;j<5;j++)
		{
			for(i=0;i<5;i++)
			{
				if((arr[i][j]!=0)&&(i!=3)&&(i!=2)&&(i!=4))
				{
				if(arr[i][j]==arr[i+1][j])
				{
					if(arr[i][j]==arr[i+2][j]&& arr[i][j]==arr[i+3][j])
						{cm=arr[i][j];
						break ml;
				}
				}
			}
			}
		}
		
		return cm;
	}
	
	private int chkd() {
		// TODO Auto-generated method stub
		int i,j,cm=0;
		ml:for(i=0;i<5;i++)
		{
			for(j=0;j<5;j++)
			{
				if(arr[i][j]!=0)
				{
					if(i==0&&j==0)
					{
						if((arr[i][j]==arr[i+1][j+1])&&(arr[i][j]==arr[i+2][j+2])&&(arr[i][j]==arr[i+3][j+3]))
							{
							cm=arr[i][j];
							break ml;
							}
							
					}
					else if(i==0&&j==1)
					{
						if((arr[i][j]==arr[i+1][j+1])&&(arr[i][j]==arr[i+2][j+2])&&(arr[i][j]==arr[i+3][j+3]))
						{
						cm=arr[i][j];
						break ml;
						}
					
					}
					else if(i==1&&j==0)
					{
						if((arr[i][j]==arr[i+1][j+1])&&(arr[i][j]==arr[i+2][j+2])&&(arr[i][j]==arr[i+3][j+3]))
						{
						cm=arr[i][j];
						break ml;
						}
						
					}
					else if(i==1&&j==1)
					{
						if((arr[i][j]==arr[i+1][j+1])&&(arr[i][j]==arr[i+2][j+2])&&(arr[i][j]==arr[i+3][j+3]))
						{
						cm=arr[i][j];
						break ml;
						}
						
					}
					else if(i==3&&j==0)
					{
						if((arr[i][j]==arr[i-1][j+1])&&(arr[i][j]==arr[i-2][j+2])&&(arr[i][j]==arr[i-3][j+3]))
						{
							cm=arr[i][j];
							break ml;
						}
					}
					else if(i==3&&j==1)
					{
						if((arr[i][j]==arr[i-1][j+1])&&(arr[i][j]==arr[i-2][j+2])&&(arr[i][j]==arr[i-3][j+3]))
						{
							cm=arr[i][j];
							break ml;
						}
					}
					else if(i==4&&j==0)
					{
						if((arr[i][j]==arr[i-1][j+1])&&(arr[i][j]==arr[i-2][j+2])&&(arr[i][j]==arr[i-3][j+3]))
						{
							cm=arr[i][j];
							break ml;
						}
					}
					else if(i==4&&j==1)
					{
						if((arr[i][j]==arr[i-1][j+1])&&(arr[i][j]==arr[i-2][j+2])&&(arr[i][j]==arr[i-3][j+3]))
						{
							cm=arr[i][j];
							break ml;
						}
					}
					else
					{}
				}
			}
		}
		return cm;
	}
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_UP && (c1==0&&c2==0&&c3==0&&c4==0&&c5==0))
		{
			if(!swtch.isChecked())
			c=0;
		else
			c=1;
		}
		
		return false;
	}	
}