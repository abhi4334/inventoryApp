package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.Map;

import com.example.inventorymanagement.beans.InventoryInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ListInventoryForEditActivity extends Activity {

	ListView editInventoryPageListView;
	//SparseBooleanArray checked;
	TextView itemIdTextView;
	ArrayAdapter<String> adapter;
	ArrayList<String> selectedItems = new ArrayList<String>();
	ArrayList<String> heading = new ArrayList<String>();
	ArrayList<String> itemIdList = new ArrayList<String>();
	ArrayList<String> selectedItemIdList = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_inventory_for_edit);
		
		final DBTools dbtools = new DBTools(this.getApplicationContext());
		ArrayList<InventoryInfo> allPhoneList = dbtools.getAllPhoneList();
		
		for(InventoryInfo obj: allPhoneList)
		{
			heading.add(obj.getType()+": "+obj.getCompany()+", "+obj.getName()+", "+obj.getVersion()+", "+obj.getQty());
			itemIdList.add(obj.getId());
		}
		
		editInventoryPageListView = (ListView) findViewById(R.id.EditInventoryPageListView);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayAdapter<ArrayList> simpleAdpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1,heading);
		editInventoryPageListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		editInventoryPageListView.setAdapter(simpleAdpt);
		
		OnItemClickListener onItemClickListener =new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long id) 
			{
		         //Toast.makeText(ReserveActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
				 // Toast.makeText(ReserveActivity.this, "Abhi Shah", Toast.LENGTH_SHORT).show();
		         //Log.d("Values",id+" "+position+" "+clickedView.getText().toString());
		         
		         //itemIdTextView = (TextView) view.findViewById(R.id.EditInventoryitemId);
		         //String itemId = itemIdTextView.getText().toString();
		         //Toast.makeText(ReserveActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		         //getApplication() returns the application that owns this activity
				 String itemId = itemIdList.get(position);
		         Log.d("Clicked Id",itemId);
		         Intent intent = new Intent(getApplication(), EditInventoryActivity.class);
		         intent.putExtra("id",itemId);
		         startActivity(intent);
			}
		};
		
		editInventoryPageListView.setOnItemClickListener(onItemClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_inventory_for_edit, menu);
		return true;
	}

	public void goToHomePage(View view)
	{
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}
	
}
