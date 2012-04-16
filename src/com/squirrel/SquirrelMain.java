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
	private int score;
	private int counter;
	private int type;
	
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
				mySquirrelView.setText("Game Over! Score: " + score);
			else
			{				
				mySquirrelView.update();
				
				if (mySquirrelView.checkTouched(e.getX(), e.getY()))
				{
					type = mySquirrelView.getType();
					
					if (type == SquirrelView.BROWNSQ)
					{
						score += 10;
						counter++;
						mySquirrelView.setText("Score: " + score);
					
						if (counter > 5)
						{
							score += 10 * (counter - 5);
							mySquirrelView.setText("Score: " + score + " - " + counter + "in a row!");
						}
					}
					else if (type == SquirrelView.REDSQ)
					{
						score += 100;
						counter++;
						mySquirrelView.setText("Score: " + score);
						
						if (counter > 5)
						{
//							score += 10 * (counter - 5);
							mySquirrelView.setText("Score: " + score + " - " + counter + "in a row!");
						}
					}
					else if (type == SquirrelView.SKUNK)
					{
						score -= 100;
						
						if (score < 0)
							score = 0;
						counter = 0;
						mySquirrelView.setText("Score: " + score + " - Oops!");
					}
					
					
					mySquirrelView.updateNew();
				}
				else //if (type != SKUNK)
				{
					mySquirrelView.setText("Score: " + score + " - Miss!");
					counter = 0;
				}
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
		mySquirrelView.setTextView((TextView) findViewById(R.id.gametext));
		mySquirrelView = (SquirrelView) findViewById(R.id.game);

		score = 0;
		counter = 0;
		
		mySquirrelView.update();
		mySquirrelView.setText("Score: " + score);
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