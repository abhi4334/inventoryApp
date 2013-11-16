package com.example.inventorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SuccessActivity extends Activity {

	TextView orderMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		
		orderMsg = (TextView) findViewById(R.id.orderTextView);
		Bundle b = getIntent().getExtras();
        String[] resultArr = b.getStringArray("selectedItems");
		ListView lv = (ListView) findViewById(R.id.ResultReservationList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.success, menu);
		return true;
	}
	
	public void goToHomePage(View view)
	{
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}

}
