package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.inventorymanagement.beans.CheckboxModel;
import com.example.inventorymanagement.beans.InventoryInfo;

public class ReserveActivity extends Activity {
	
	private String userName;
	ListView reservePageListView;
	//SparseBooleanArray checked;
	TextView itemIdTextView;
	Button reserveButton;
	ArrayAdapter<String> adapter;
	ArrayList<String> selectedItems = new ArrayList<String>();
	ArrayList<String> heading = new ArrayList<String>();
	ArrayList<String> itemIdList = new ArrayList<String>();
	ArrayList<String> selectedItemIdList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reserve);
		
		Intent intent = getIntent();
		userName= intent.getStringExtra("userName");
		final DBTools dbtools = new DBTools(this.getApplicationContext());
		reserveButton = (Button)findViewById(R.id.ReserveButton);
		ArrayList<InventoryInfo> allPhoneList = dbtools.getAllPhoneList();
		List<CheckboxModel> checkboxModelList = new ArrayList<CheckboxModel>();
		
		ArrayList<String> subHeading = new ArrayList<String>();
		
		for(InventoryInfo obj: allPhoneList)
		{
			heading.add(obj.getType()+": "+obj.getCompany()+", "+obj.getName()+", "+obj.getVersion()+", "+obj.getQty());
			subHeading.add("Qty "+obj.getQty()); //remove subheading
			//String updatedId = String.valueOf(Integer.parseInt(obj.getId())+1);
			itemIdList.add(obj.getId());
			//checkboxModelList.add(getCheckboxModel(obj.getId()));
		}
		
		for(String id:itemIdList)
		{
			Log.d("ID:",id);
		}
		
		ArrayList<Map<String, String>> resultMapList= new ArrayList<Map<String, String>>();
		
		for(int i=0; i<heading.size();i++)
		{
			resultMapList.add(createEntry(heading.get(i),subHeading.get(i)));
		}
	
		Log.d("total entries", String.valueOf(resultMapList.size()));
		reservePageListView = (ListView) findViewById(R.id.ReservePageListView);
		ListAdapter simpleAdpt = new SimpleAdapter(this, resultMapList, android.R.layout.simple_list_item_checked, new String[] {"heading","subHeading"}, new int[] {android.R.id.text1,android.R.id.text2});
		//ListAdapter simpleAdpt = new SimpleAdapter(this, resultMapList, R.layout.item_list_custom_view_layout, new String[] {"heading","subHeading"}, new int[] {R.id.itemHeading,R.id.itemSubHeading});
		
		reservePageListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//ArrayAdapter<CheckboxModel> adapter = new CheckboxArrayAdapter(this,checkboxModelList);
		reservePageListView.setAdapter(simpleAdpt);
		
		OnClickListener onClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SparseBooleanArray checked = reservePageListView.getCheckedItemPositions();
				Log.d("Ch: Size",String.valueOf(checked.size()));
		        
		        for (int i = 0; i < checked.size(); i++) {
		            // Item position in adapter
		            int position = checked.keyAt(i);
		            
		            // Add sport if it is checked i.e.) == TRUE!
		            if (checked.valueAt(i))
		            {
		            	Log.d("Position",String.valueOf(position));
		                //selectedItems.add(adapter.getItem(position));
		            	selectedItems.add(heading.get(position));
		            	selectedItemIdList.add(itemIdList.get(position));
		            }	
		        }
		 
		        String[] outputStrArr = new String[selectedItems.size()];
		        
		        Log.d("Size",String.valueOf(selectedItems.size()));
		 
		        for (int i = 0; i < selectedItems.size(); i++) {
		            outputStrArr[i] = selectedItems.get(i);
		        }
		        
		        /*for(String id:selectedItemIdList)
				{
					Log.d("SID:",id);
				}*/
		        
		        boolean isReserved = dbtools.reserveItems(selectedItemIdList,userName);
		        if(isReserved)
		        {
		        Intent intent = new Intent(getApplicationContext(),
		                SuccessActivity.class);
		 
		        // Create a bundle object
		        Bundle b = new Bundle();
		        b.putStringArray("selectedItems", outputStrArr);
		        // Add the bundle to the intent.
		        intent.putExtras(b);
		        intent.putExtra("userName",userName);
		        // start the ResultActivity
		        startActivity(intent);
		        }
		        else{
		        	Intent intent = new Intent(getApplicationContext(),
			                ErrorActivity.class);
		        	startActivity(intent);
		        }
			}
		};
		reserveButton.setOnClickListener(onClickListener);
		
		OnItemClickListener onItemClickListener =new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long id) 
			{
				/* TextView clickedView = (TextView) view;
		         //Toast.makeText(ReserveActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		        // Toast.makeText(ReserveActivity.this, "Abhi Shah", Toast.LENGTH_SHORT).show();
		         Log.d("Values",id+" "+position+" "+clickedView.getText().toString());
		         
		         itemIdTextView = (TextView) view.findViewById(R.id.itemId);
		         String itemId = itemIdTextView.getText().toString();
		         Toast.makeText(ReserveActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		         //getApplication() returns the application that owns this activity
		         Intent intent = new Intent(getApplication(), EditInventoryActivity.class);
		         intent.putExtra("id",itemId);
		         startActivity(intent);*/
				 //reservePageListView.setItemChecked(position, true);
				CheckedTextView textview = (CheckedTextView)view;
			    textview.setChecked(!textview.isChecked());
			}
		};
		
		reservePageListView.setOnItemClickListener(onItemClickListener);
		
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
		getMenuInflater().inflate(R.menu.reserve, menu);
		return true;
	}

	public void goTOHomePage(View view)
	{
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
	}
	
	public void reserveConfirmation(View view)
	{		
		/*Intent intent = new Intent(this, .class);
		startActivity(intent);*/
	}
}
