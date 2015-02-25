package com.example.indicatortagviewsample;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Rect;
import android.widget.TextView;

public class TagIndicatorTextView extends TextView{

	String mTagToDisplay;
	Paint mIndicatorPaint;
	Rect mClipBounds;
	RectF mOvalRect;
	int mBGColor;
	
	public TagIndicatorTextView(Context context) {
		super(context);
		
		// init indicator bg oval painter
		mIndicatorPaint = new Paint(getPaint());
		mIndicatorPaint.setAntiAlias(true);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		super.onSizeChanged(w, h, oldw, oldh);
		
		mClipBounds = new Rect();
		// recalculate the region when view size changes
		mOvalRect = null;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		// if there is nothing to draw, then don't draw here
		if(mTagToDisplay == null)
			return;
		
		// expand area to draw for indicator on the right top by region UNION
		canvas.getClipBounds(mClipBounds);
		canvas.clipRect(mClipBounds.right - mClipBounds.width() / 2, 
				mClipBounds.top - mClipBounds.height() /2, 
				mClipBounds.right + mClipBounds.width() / 2, 
				mClipBounds.top + mClipBounds.height() /2, 
				Region.Op.UNION);
		
		// Consider actual text width when drawing
		float[] widths = new float[mTagToDisplay.length()];
		getPaint().getTextWidths(mTagToDisplay, widths);
		
		float totalTextWidth = 0;
		for(float t : widths) {
			totalTextWidth += t;
		}
		
		// adjust start y pos to draw the text
		Paint.FontMetrics  fontMtx = getPaint().getFontMetrics();
		float y = mClipBounds.top - fontMtx.ascent - getPaint().getTextSize() / 2 ;
		
		// create the oval bg for text
		if(mOvalRect == null) {
			
			// get enough space to show one char 
			if(totalTextWidth < getPaint().getTextSize())
				totalTextWidth = getPaint().getTextSize();
			
			mOvalRect  = new RectF(mClipBounds.right - totalTextWidth / (float)1.5, 
					 mClipBounds.top - getPaint().getTextSize() / (float)1.5 , 
					mClipBounds.right + totalTextWidth / (float)1.5, 
					mClipBounds.top + getPaint().getTextSize() / (float)1.5);
		}
		
		// draw bg oval and then draw txt on top of it
		canvas.drawOval(mOvalRect, mIndicatorPaint);
		canvas.drawText(mTagToDisplay, mClipBounds.right - totalTextWidth / 2, y, getPaint());
	}
	
	/*
	 * set indicator text and bgColor to show  
	 */
	public void setIndicator(String tagNum, int bgColor) {
		mTagToDisplay = tagNum;
		mIndicatorPaint.setColor(bgColor);
	}
}