package com.example.indicatortagviewsample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TagViewSampleActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TagIndicatorTextView v1 = new TagIndicatorTextView(this);
		TagIndicatorTextView v2 = new TagIndicatorTextView(this);
		TagIndicatorTextView v3 = new TagIndicatorTextView(this);
		
		v1.setText("TAG1");
		v2.setText("TAG2");
		v3.setText("TAG3");
		
		v1.setIndicator("9", Color.RED);
		v2.setIndicator("99", Color.BLUE);
		v3.setIndicator("999", Color.YELLOW);
		
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.topMargin = 30;
	
		ll.addView(v1, lp);
		ll.addView(v2, lp);
		ll.addView(v3, lp);
		
		setContentView(ll);
	}

}
