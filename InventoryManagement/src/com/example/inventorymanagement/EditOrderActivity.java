package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventorymanagement.beans.InventoryInfo;

public class EditOrderActivity extends Activity {

	private static String userName = null;
	ListView cancelOrderListView;
	Button cancelOrderButton;
	TextView message;
	DBTools dbtools;
	
	ArrayList<String> heading = new ArrayList<String>();
	ArrayList<String> itemIdList = new ArrayList<String>();
	ArrayList<String> selectedItems = new ArrayList<String>();
	ArrayList<String> selectedItemIdList = new ArrayList<String>();
	private final int VISIBLE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_order);
		dbtools = new DBTools(this.getApplicationContext());
		Intent intent = getIntent();
		userName = intent.getStringExtra("userName");
		cancelOrderButton = (Button)findViewById(R.id.CancelOrderButton);
		
		ArrayList<InventoryInfo> reservedItemList = dbtools.getReservedItemForUser(userName);

		if(reservedItemList.size() == 0)
		{
			message = (TextView)findViewById(R.id.NoReservedItemMessage);
			message.setVisibility(VISIBLE);
		}
		for (InventoryInfo obj : reservedItemList) {
			heading.add(obj.getType() + ": " + obj.getCompany() + ", "+ obj.getName() + ", " + obj.getVersion());
			itemIdList.add(obj.getId());
		}

		ArrayList<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();

		for (int i = 0; i < heading.size(); i++) {
			resultMapList.add(createEntry(heading.get(i), "")); // here "" is for Sub Heading
		}

		//Log.d("total entries", String.valueOf(resultMapList.size()));
		cancelOrderListView = (ListView) findViewById(R.id.CancelOrderListView);
		ListAdapter simpleAdpt = new SimpleAdapter(this, resultMapList,android.R.layout.simple_list_item_checked, new String[] {"heading", "subHeading" }, new int[] {android.R.id.text1, android.R.id.text2 });
		cancelOrderListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		cancelOrderListView.setAdapter(simpleAdpt);

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {

				SparseBooleanArray checked = cancelOrderListView
						.getCheckedItemPositions();
				Log.d("Ch: Size", String.valueOf(checked.size()));

				for (int i = 0; i < checked.size(); i++) {
					// Item position in adapter
					int position = checked.keyAt(i);

					// Add sport if it is checked i.e.) == TRUE!
					if (checked.valueAt(i)) {
						selectedItems.add(heading.get(position));
						selectedItemIdList.add(itemIdList.get(position));
					}
				}

				boolean isCanceled = dbtools.cancelOrder(selectedItemIdList);
				if (isCanceled) {
					Intent intent = new Intent(getApplicationContext(),
			                EditOrderActivity.class);
					 Toast.makeText(getApplicationContext(), "Items Cancelled", Toast.LENGTH_SHORT).show();
					startActivity(intent);
				}
				else{
					Intent intent = new Intent(getApplicationContext(),
			                ErrorActivity.class);
		        	startActivity(intent);
				}

			}
		};
		
		cancelOrderButton.setOnClickListener(onClickListener);
		
		OnItemClickListener onItemClickListener =new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long id) 
			{
				CheckedTextView textview = (CheckedTextView)view;
			    textview.setChecked(!textview.isChecked());
			}
		};
		
		cancelOrderListView.setOnItemClickListener(onItemClickListener);
	}

	public HashMap<String, String> createEntry(String heading, String subHeading) {
		HashMap<String, String> entry = new HashMap<String, String>();
		entry.put("heading", heading);
		entry.put("subHeading", subHeading);
		return entry;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_order, menu);
		return true;
	}

	public void goTOHomePage(View view) {
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}
}
