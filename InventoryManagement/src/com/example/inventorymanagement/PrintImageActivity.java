package com.example.inventorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.print.PrintHelper;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class PrintImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_print_image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.print_image, menu);
		return true;
	}

	public void printImage(View view)
	{
		PrintHelper photoPrinter = new PrintHelper(this);
		photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.infostretch);
		photoPrinter.printBitmap("droids.jpg - test print", bitmap);
		Intent intent = new Intent(this, PrintImageActivity.class);
		Toast.makeText(getApplicationContext(), "Image Send to Printer", Toast.LENGTH_SHORT).show();
		startActivity(intent);
	}
	
	public void goTOHomePage(View view)
	{
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}
	
}
