package com.example.canvasmatrixdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
//下面用于颜色变换
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private Button btn_scale, btn_rotate, btn_translate, btn_skew;
	private ImageView iv_base, iv_after;
	private Bitmap baseBitmap, updateBitmap;
	private Paint paint;
	private SeekBar sb1, sb2, sb3;//sb4, sb5;
	//private ImageView iv;
	//private Bitmap bitmap, updateBitmap;
	private Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_scale = (Button) findViewById(R.id.btn_scale);
		btn_rotate = (Button) findViewById(R.id.btn_rotate);
		btn_translate = (Button) findViewById(R.id.btn_translate);
		btn_skew = (Button) findViewById(R.id.btn_skew);

		btn_scale.setOnClickListener(click);
		btn_rotate.setOnClickListener(click);
		btn_translate.setOnClickListener(click);
		btn_skew.setOnClickListener(click);

		iv_base = (ImageView) findViewById(R.id.iv_base);
		iv_after = (ImageView) findViewById(R.id.iv_after);

		//baseBitmap = BitmapFactory.decodeResource(getResources(),
			//	R.drawable.ic_launcher);
		baseBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.baboon);//读入图片资源
		iv_base.setImageBitmap(baseBitmap);

		paint = new Paint();
		paint.setAntiAlias(true);
		
		//用于颜色变换
			//iv_ = (ImageView) findViewById(R.id.iv);
			sb1 = (SeekBar) findViewById(R.id.sb1);
			sb2 = (SeekBar) findViewById(R.id.sb2);
			sb3 = (SeekBar) findViewById(R.id.sb3);

			//bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);

			updateBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
					baseBitmap.getHeight(), baseBitmap.getConfig());
			canvas = new Canvas(updateBitmap);
			paint = new Paint();
			final ColorMatrix cm = new ColorMatrix();
			paint.setColorFilter(new ColorMatrixColorFilter(cm));
			paint.setColor(Color.BLACK);
			paint.setAntiAlias(true);
			final Matrix matrix = new Matrix();
			canvas.drawBitmap(baseBitmap, matrix, paint);
			iv_base.setImageBitmap(updateBitmap);

			/**
			 * RGB三原色 红色值的设置
			 */
			sb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					int progress = seekBar.getProgress();
					cm.set(new float[] { progress / 128f, 0, 0, 0, 0,// 红色值
							0, 1, 0, 0, 0,// 绿色值
							0, 0, 1, 0, 0,// 蓝色值
							0, 0, 0, 1, 0 // 透明度
					});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(baseBitmap, matrix, paint);
					iv_base.setImageBitmap(updateBitmap);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {

				}
			});

			/**
			 * RGB三原色 绿色值的设置
			 */
			sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					int progress = seekBar.getProgress();
					cm.set(new float[] { 1, 0, 0, 0, 0,// 红色值
							0, progress / 128f, 0, 0, 0,// 绿色值
							0, 0, 1, 0, 0,// 蓝色值
							0, 0, 0, 1, 0 // 透明度
					});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(baseBitmap, matrix, paint);
					iv_base.setImageBitmap(updateBitmap);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {

				}
			});

			/**
			 * RGB三原色 蓝色值的设置
			 */
			sb3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					int progress = seekBar.getProgress();
					cm.set(new float[] { 1, 0, 0, 0, 0,// 红色值
							0, 1, 0, 0, 0,// 绿色值
							0, 0, progress / 128f, 0, 0,// 蓝色值
							0, 0, 0, 1, 0 // 透明度
					});
					paint.setColorFilter(new ColorMatrixColorFilter(cm));
					canvas.drawBitmap(baseBitmap, matrix, paint);
					iv_base.setImageBitmap(updateBitmap);
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {

				}
			});
	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			/*
			setScale(float sx, float sy, float px, float py) 放大 
			setSkew(float kx, float ky, float px, float py) 斜切 
			setTranslate(float dx, float dy)                       平移 
			setRotate(float degrees, float px, float py)    旋转 */
			
			switch (v.getId()) {
			case R.id.btn_scale:
				bitmapScale(2.0f, 4.0f);//宽高都变
				break;
			case R.id.btn_rotate:
				bitmapRotate(130);
				break;
			case R.id.btn_translate:
				bitmapTranslate(100f, 100f);
				break;
			case R.id.btn_skew:
				bitmapSkew(0.2f, 0.4f);
				break;
			default:
				break;
			}

		}
	};

	protected void bitmapScale(float x, float y) {
		Bitmap afterBitmap = Bitmap.createBitmap(
				(int) (baseBitmap.getWidth() * x),
				(int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
		Canvas canvas = new Canvas(afterBitmap);
		Matrix matrix = new Matrix();
		matrix.setScale(x, y);
		canvas.drawBitmap(baseBitmap, matrix, paint);
		iv_after.setImageBitmap(afterBitmap);
	}

	protected void bitmapSkew(float dx, float dy) {
		Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth()
				+ (int) (baseBitmap.getWidth() * dx), baseBitmap.getHeight()
				+ (int) (baseBitmap.getHeight() * dy), baseBitmap.getConfig());
		Canvas canvas = new Canvas(afterBitmap);
		Matrix matrix = new Matrix();
		matrix.setSkew(dx, dy);
		canvas.drawBitmap(baseBitmap, matrix, paint);
		iv_after.setImageBitmap(afterBitmap);
	}

	protected void bitmapTranslate(float dx, float dy) {
		Bitmap afterBitmap = Bitmap.createBitmap(
				(int) (baseBitmap.getWidth() + dx),
				(int) (baseBitmap.getHeight() + dy), baseBitmap.getConfig());
		Canvas canvas = new Canvas(afterBitmap);
		Matrix matrix = new Matrix();
		matrix.setTranslate(dx, dy);
		canvas.drawBitmap(baseBitmap, matrix, paint);
		iv_after.setImageBitmap(afterBitmap);
	}

	protected void bitmapRotate(float degrees) {
		//¨
		Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
				baseBitmap.getHeight(), baseBitmap.getConfig());
		Canvas canvas = new Canvas(afterBitmap);
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
				baseBitmap.getHeight() / 2);
		canvas.drawBitmap(baseBitmap, matrix, paint);
		iv_after.setImageBitmap(afterBitmap);
	}
	

}

	
