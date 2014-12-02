package com.wanghui.trackforman.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ChartView extends View {
	/**
	 * 坐标原点x坐标
	 */
	private int mX = 0;
	/**
	 * 坐标原点x坐标
	 */
	private int mY = 0;
	/**
	 * x轴每个刻度的理论上表示的值
	 */
	private double mXScale = 10;
	/**
	 * y轴每个刻度的理论上表示的值
	 */
	private double mYScale = 10;
	/**
	 * x轴的长度
	 */
	private int mXLength = 100;
	/**
	 * y轴的长度
	 */
	private int mYLength = 100;
	/**
	 * x轴一个刻度表示的实际的像素
	 */
	private float mXRule = 10;
	/**
	 * y轴一个刻度表示的实际的像素
	 */
	private float mYRule = 10;
	/**
	 * 坐标点的map
	 */
	private float[] mPoints;

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void initView(int x, int y, double xScale, double yScale, int xLen, int yLen, float[] points) {
		this.mX = x;
		this.mY = y;
		this.mXScale = xScale;
		this.mYScale = yScale;
		this.mXLength = xLen;
		this.mYLength = yLen;
		this.mXRule = (float) (xLen / xScale);
		this.mYRule = (float) (yLen / yScale);
		this.mPoints = points;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		paint.setTextSize(12);

		// 画坐标轴
		canvas.drawLine(mX, mY, mX, mY - mYLength, paint);
		canvas.drawLine(mX, mY - mYLength, mX - 3, mY - mYLength + 6, paint);
		canvas.drawLine(mX, mY - mYLength, mX + 3, mY - mYLength + 6, paint);
		canvas.drawLine(mX, mY, mX + mYLength, mY, paint);
		canvas.drawLine(mX + mXLength, mY, mX + mXLength - 6, mY - 3, paint);
		canvas.drawLine(mX + mXLength, mY, mX + mXLength - 6, mY + 3, paint);

		// 绘制横竖坐标的刻度值
		for (int i = 0; i * mYRule < mYLength; i++) {
			canvas.drawLine(mX, mY - i * mYRule, mX + 3, mY - i * mYRule, paint);
			canvas.drawText("" + i * mXScale, mX - 20, mY - i * mYRule - 5, paint);
		}
		for (int i = 0; i * mXRule < mXLength; i++) {
			canvas.drawLine(mX + i * mXRule, mY, mX + i * mXRule, mY + 3, paint);
			canvas.drawText("" + i * mXScale, mX + i * mXRule - 5, mY + 3, paint);
		}

		// 绘制出坐标点和各个点之间的连线
		if (null == mPoints) {
			return;
		}
		turnRealValue(mPoints);

		for (int i = 0, len = mPoints.length; i < len; i++) {
			if (2 * i + 1 < len) {
				canvas.drawCircle(mPoints[2 * i], mPoints[2 * i + 1], 2, paint);
				if (i == 0) {
					canvas.drawLine(mX, mY, mPoints[2 * i], mPoints[2 * i + 1], paint);
				} else {
					canvas.drawLine(mPoints[2 * (i - 1)], mPoints[2 * (i - 1) + 1], mPoints[i], mPoints[2 * i + 1], paint);
				}
			}
		}

	}

	/**
	 * 把坐标点转换成真实的px
	 * 
	 * @param points
	 */
	private void turnRealValue(float[] points) {
		for (int i = 0, len = mPoints.length; i < len; i++) {
			if (2 * i + 1 < len) {
				mPoints[2 * i] = (float) (points[2 * i] / mXScale * mXRule) + mX;
				mPoints[2 * i + 1] = (float) (mY - points[2 * i + 1] / mYScale * mXRule);
			}
		}
	}

}
