package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AddInventorySuccessViewActivity extends Activity {

	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_inventory_success_view);
		
		Intent intent = getIntent();
		String result = intent.getStringExtra("db");
		ArrayList<String> addedItemList = new ArrayList<String>();
		addedItemList.add(result);
		listView = (ListView) findViewById(R.id.listViewData);
		ListAdapter simpleAdpt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,addedItemList);
		listView.setAdapter(simpleAdpt);
	}
	
	public HashMap<String, String> createEntry(String key, String name) {
		HashMap<String, String> entry = new HashMap<String, String>();
		entry.put(key, name);
		return entry;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_inventory_success_view, menu);
		return true;
	}
	
	public void goToHomePage(View view){
		Intent intent = new Intent(this, HomePageActivity.class);
		//intent.putExtra("userName", userName);
		startActivity(intent);
	}

}
