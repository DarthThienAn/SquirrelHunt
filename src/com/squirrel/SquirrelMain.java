package com.squirrel;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

public class SquirrelMain extends Activity implements OnGestureListener {

	private GestureDetector gestureScanner;
	private SquirrelView mySquirrelView;
	
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
		if (mySquirrelView != null)
		{
			if (mySquirrelView.getGameOver())
				mySquirrelView.setText("Game Over! Score: " + mySquirrelView.getScore());
			else
			{				
				mySquirrelView.update();
				mySquirrelView.touch(e.getX(), e.getY());
			}
		}

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gestureScanner = new GestureDetector(this);

		// remove title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.squirrelgame);
		mySquirrelView = (SquirrelView) findViewById(R.id.game);
		mySquirrelView.setTextView((TextView) findViewById(R.id.gametext));
		
		mySquirrelView.update();
		mySquirrelView.setText("Score: " + mySquirrelView.getScore());
	}
	
	@Override
	protected void onStop() {
		System.exit(0);
	}

//	@Override
//	protected void onPause() {
//		System.exit(0);
//	}
}	