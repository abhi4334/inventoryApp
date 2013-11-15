package com.example.inventorymanagement;

import java.util.ArrayList;
import java.util.Date;

import com.example.inventorymanagement.beans.InventoryInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditInventoryActivity extends Activity {

	private final String requiredError ="This field is Required";

	EditText typeET;
	EditText IMEIET;
	EditText inventoryNameET;
	EditText companyET;
	EditText versionET;
	EditText buyDateET;
	EditText qtyET;
	Button editInventoryButton;
	
	String itemId;
	String type;
	String IMEI;
	String inventoryName;
	String company;
	String version;
	Date buyDate;
	String qty;
	
	DBTools dbtools;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_inventory);
		
		Intent intent = getIntent();
		itemId = intent.getStringExtra("id");
		dbtools = new DBTools(this.getApplicationContext());
		if(savedInstanceState == null)
		{
			//just started
			//dbtools = new DBTools(this.getApplicationContext());
		}
		else{
			//app is being restored. Write stored values to EditTextViews 
			//dbtools = (DBTools)savedInstanceState.get("latestdbtools");
		}
		
		/*
		 * initialize EditText
		 */
		typeET = (EditText)findViewById(R.id.EditInventoryTypeEditText);
		IMEIET = (EditText)findViewById(R.id.EditInventoryIMEIEditText);
		inventoryNameET = (EditText)findViewById(R.id.EditInventoryNameEditText);
		companyET = (EditText)findViewById(R.id.EditInventoryCompanyEditText);
		versionET = (EditText)findViewById(R.id.EditInventoryVersionEditText);
		buyDateET = (EditText)findViewById(R.id.EditInventoryBuyDateEditText);
		qtyET = (EditText)findViewById(R.id.EditInventoryQtyEditText);
		editInventoryButton = (Button)findViewById(R.id.EditInventoryToSQLButton);
		
		InventoryInfo inventoryInfo = dbtools.getInventoryById(itemId);
		typeET.setText(inventoryInfo.getType());
		IMEIET.setText(inventoryInfo.getImei());
		inventoryNameET.setText(inventoryInfo.getName());
		companyET.setText(inventoryInfo.getCompany());
		versionET.setText(inventoryInfo.getVersion());
		buyDateET.setText(inventoryInfo.getBuyDate());
		qtyET.setText(inventoryInfo.getQty());
		
		IMEIET.setOnFocusChangeListener(typeListener);
		inventoryNameET.setOnFocusChangeListener(IMEIListener);
		companyET.setOnFocusChangeListener(inventoryNameListener);
		versionET.setOnFocusChangeListener(companyListener);
		buyDateET.setOnFocusChangeListener(versionListener);
		qtyET.setOnFocusChangeListener(buyDateListener);
		editInventoryButton.setOnFocusChangeListener(qtyListener);
		//addInventoryButton.setOnClickListener(addInventoryButtonListener);
		
		//typeET.addTextChangedListener(typeWatcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*
	 * All Listeners
	 * */
	
	private TextWatcher typeWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			if (type.equalsIgnoreCase("")||type.equalsIgnoreCase(null) )
			{
				typeET.setError(requiredError);
			}
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (type.equalsIgnoreCase("")||type.equalsIgnoreCase(null) )
			{
				typeET.setError(requiredError);
				typeET.requestFocus();
			}
			
		}
	};
	
	private OnFocusChangeListener typeListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			type  = typeET.getText().toString().trim();
			if(type.equalsIgnoreCase("")||type.equalsIgnoreCase(null)){
				typeET.setError(requiredError);
			}
			else{
				//typeET.setError(null);
			}
		}
	};
	
	private OnFocusChangeListener IMEIListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			IMEI  = IMEIET.getText().toString().trim();
			if(IMEI.equalsIgnoreCase("")||IMEI.equalsIgnoreCase(null)){
				IMEIET.setError(requiredError);
			}
			else{
				//IMEIET.setError(null);
			}
		}
	};
	
	private OnFocusChangeListener inventoryNameListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			inventoryName  = inventoryNameET.getText().toString().trim();
			if(inventoryName.equalsIgnoreCase("")||inventoryName.equalsIgnoreCase(null)){
				inventoryNameET.setError(requiredError);
			}
			else{
				//inventoryNameET.setError(null);
			}
		}
	};
	
	private OnFocusChangeListener companyListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			company  = companyET.getText().toString().trim();
			if(company.equalsIgnoreCase("")||company.equalsIgnoreCase(null)){
				companyET.setError(requiredError);
			}
			else{
				//companyET.setError(null);
			}
		}
	};
	
	private OnFocusChangeListener versionListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			version  = versionET.getText().toString().trim();
			if(version.equalsIgnoreCase("")||version.equalsIgnoreCase(null)){
				versionET.setError(requiredError);
			}
			else{
				//versionET.setError(null);
			}
		}
	};
	
	private OnFocusChangeListener buyDateListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			
			/*buyDate  = (Date) buyDateET.getText();
			if((buyDate).equals("")||buyDate.equals(null)){
				buyDateET.setError(requiredError);
			}
			else{
				
			}*/
			//buyDateET.setError(null);
		}
	};
	
	
	private OnFocusChangeListener qtyListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			qty  = qtyET.getText().toString();
			if((qty).equals("")||qty.equals(null)){
				qtyET.setError(requiredError);
			}
			else{
				//qtyET.setError(null);
			}
		}
	};
	
	private OnClickListener editInventoryButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
		}
	};
	
	public void editInventory(View view){
		
		/*if(typeET.getError().equals(null)&&IMEIET.getError().equals(null)&&inventoryNameET.getError().equals(null)&&companyET.getError().equals(null)
				&& versionET.getError().equals(null)&&buyDateET.getError().equals(null)&&qtyET.getError().equals(null))
		{*/
		DBTools dbtools = new DBTools(this.getApplicationContext());
		InventoryInfo inventoryInfo = new InventoryInfo();
		inventoryInfo.setId(itemId);	
		inventoryInfo.setType(typeET.getText().toString());
		inventoryInfo.setImei(IMEIET.getText().toString());
		inventoryInfo.setName(inventoryNameET.getText().toString());
		inventoryInfo.setCompany(companyET.getText().toString());
		inventoryInfo.setVersion(versionET.getText().toString());
		inventoryInfo.setBuyDate(buyDateET.getText().toString());
		inventoryInfo.setQty(qtyET.getText().toString());
		inventoryInfo.setAvailable("0");
		inventoryInfo.setReservedBy("admin");
		
		boolean isUpdated = dbtools.updateInventory(inventoryInfo);
		if(isUpdated)
		{
			Intent intent = new Intent(getApplicationContext(),
	                HomePageActivity.class);
        	startActivity(intent);
		}
		else{
			Intent intent = new Intent(getApplicationContext(),
	                ErrorActivity.class);
        	startActivity(intent);
		}
		/*ArrayList<InventoryInfo> allPhoneList = dbtools.getRecentlyAddedItem(inventoryInfo);
		
		ArrayList<String> result = new ArrayList<String>();
		for(InventoryInfo obj: allPhoneList)
		{
			result.add(obj.getType()+" "+obj.getImei()+" "+obj.getName());
		}
		
		Intent intent = new Intent(this, AddInventorySuccessViewActivity.class);
		intent.putExtra("db",result);
		startActivity(intent);
	*/
		/*}
		else{
			
		}*/
		
	}
	
	public void listInventoryForEdit(View view)
	{
		Intent intent = new Intent(this, ListInventoryForEditActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putSerializable("latestdbtools",dbtools);
	}

}
