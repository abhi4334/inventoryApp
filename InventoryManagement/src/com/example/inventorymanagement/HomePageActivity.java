package com.example.inventorymanagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomePageActivity extends Activity {
	
	private static String userName=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		Intent intent = getIntent();
		userName = intent.getStringExtra("userName");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}
	
	public void OpenReservePage(View view)
	{
		Intent intent = new Intent(this, ReserveActivity.class);
		startActivity(intent);
		
	}
	
	public void OpenAddInventoryPage(View view){
		
		Intent intent = new Intent(this, AddInventoryActivity.class);
	    // intent.putExtra(EXTRA_MESSAGE,message);
	    startActivity(intent);
	}

	public void EditInventory(View view){
		
		Intent intent = new Intent(this, ListInventoryForEditActivity.class);
	    intent.putExtra("userName",userName);
	    startActivity(intent);
	}
	
	public void EditOrder(View view)
	{
		Intent intent = new Intent(this, EditOrderActivity.class);
		intent.putExtra("userName",userName);
	    startActivity(intent);
	}
	
	public void startCamera(View view) {
		
		Intent intent = new Intent(this, CameraActivity.class);
	    startActivity(intent);
	}
}
