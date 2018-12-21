package com.shivsau.connect4l;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class NotifyUser extends BroadcastReceiver {
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		if(Build.VERSION.SDK_INT>=16)
		{
		if(BluetoothAdapter.ACTION_STATE_CHANGED==intent.getAction())
		{
                 if(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_TURNING_ON)
                 {
                	 Notification.Builder mBuilder =
                	            new Notification.Builder(context)
                	            .setSmallIcon(R.drawable.ic_launcher)
                	            .setContentTitle("Connect4League")
                	            .setContentText("Play Connect4League with friends around you.");
                	 
                	 Intent resultIntent = new Intent(context, MainActivity.class);
                	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                	    stackBuilder.addParentStack(MainActivity.class);
                	    stackBuilder.addNextIntent(resultIntent);
                	    PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                	    mBuilder.setContentIntent(resultPendingIntent);
                	    mBuilder.setAutoCancel(true);
                	    

                	        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                	        mNotificationManager.notify(0, mBuilder.build());
                	 
                 }

		}else
		{
			ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if(null!=ni)
			{
			if(ni.getType()==ConnectivityManager.TYPE_WIFI)
			{
				 Notification.Builder mBuilder =
         	            new Notification.Builder(context)
         	            .setSmallIcon(R.drawable.ic_launcher)
         	            .setContentTitle("Connect4League")
         	            .setContentText("Play Connect4League with friends around you.");
         	 
         	 Intent resultIntent = new Intent(context, MainActivity.class);
         	    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
         	    stackBuilder.addParentStack(MainActivity.class);
         	    stackBuilder.addNextIntent(resultIntent);
         	    PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
         	    mBuilder.setContentIntent(resultPendingIntent);
         	    mBuilder.setAutoCancel(true);
         	    

         	        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         	        mNotificationManager.notify(0, mBuilder.build());
         	 
			}
			}
		}
		
		}
	}
}