package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddInventorySuccess extends ListActivity {
	
	Intent intent;
	
	//DBTools dbtools = new DBTools(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_inventory_success);
		
		//ArrayList<HashMap<String, String>> allPhoneList = dbtools.getAllPhoneList();
		
		
			/*ListView listview = getListView();
			ListAdapter adapter = new SimpleAdapter(AddInventorySuccess.this, allPhoneList, R.layout.activity_add_inventory_success, new String[]{"type","company"}, new int[]{R.id.InventoryTypeEditText,R.id.InventoryCompanyEditText});
			//listview.setAdapter(adapter);
			
			setListAdapter(adapter);*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_inventory_success, menu);
		return true;
	}

}
