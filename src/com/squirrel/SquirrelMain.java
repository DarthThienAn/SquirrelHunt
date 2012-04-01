package com.squirrel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

public class SquirrelMain extends Activity implements OnGestureListener {

	private GestureDetector gestureScanner;
	private TextView text;
	private TextView text2;
	private SquirrelView mySquirrelView;
	private boolean started = false;
	private int score;
	private int counter;
	
	private RefreshHandler mRefreshHandler = new RefreshHandler();
	
	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gestureScanner.onTouchEvent(me);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return true;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		return;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		return;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		if (!started)
		{
			setContentView(R.layout.squirrelgame);
			text = (TextView) findViewById(R.id.gametext);
			
			mySquirrelView = (SquirrelView) findViewById(R.id.game);
//			mySquirrelView.updateNew();
			score = 0;
			counter = 0;
			
			started = true;
		}
		if (mySquirrelView != null)
		{
			mySquirrelView.update();
			mRefreshHandler.sleep(50);
			
	//		text.setText("" + e.getX() + " AND " + e.getY());
			
			if (mySquirrelView.checkTouched(e.getX(), e.getY()))
			{
				score += 10;
				counter++;
				text.setText("Score: " + score);
				
				if (counter > 5)
				{
					score += 10 * (counter - 5);
					text.setText("Score: " + score + " - " + counter + "in a row!");
				}
				
					
				mySquirrelView.updateNew();
			}
			else
			{
				text.setText("Miss!");
				counter = 0;
			}
		}		
		if (!started)
			text2.setText("" + e.getRawX() + " AND " + e.getRawY());

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gestureScanner = new GestureDetector(this);

		// remove title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);
		text = (TextView) findViewById(R.id.squirrel_text);
		text2 = (TextView) findViewById(R.id.squirrel_text2);
		
	}
	
	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			if (mySquirrelView != null)
				mySquirrelView.update();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

}	