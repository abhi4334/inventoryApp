package com.example.inventorymanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.inventorymanagement.beans.InventoryInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String available = "true";
	private String reservedBy = "admin";
	private String falseString = "false";
	private String trueString = "true";
	private String username = "Abhi";
	private String adminname = "admin";
	private String stringZero = "0";

	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "inventorymgmt.db";
	// Contacts table name
	private static final String TABLE_INVENTORY = "inventory";
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TYPE = "type";
	private static final String KEY_IMEI = "imei";
	private static final String KEY_NAME = "name";
	private static final String KEY_COMPANY = "company";
	private static final String KEY_VERSION = "version";
	private static final String KEY_BUYDATE = "buyDate";
	private static final String KEY_QTY = "qty";
	private static final String KEY_AVAILABLE = "available";
	private static final String KEY_RESERVEDBY = "reservedBy";

	private static final String SQL_CREATE_QUERY = "CREATE TABLE "+TABLE_INVENTORY +"("+KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
			KEY_TYPE+" TEXT,"+ KEY_IMEI+" TEXT,"+KEY_NAME+" TEXT,"+KEY_COMPANY+" TEXT,"+
			KEY_VERSION+" TEXT,"+KEY_BUYDATE+" TEXT,"+KEY_QTY+" TEXT,"+
			KEY_AVAILABLE+" TEXT,"+KEY_RESERVEDBY+" TEXT)"; 
	
	public DBTools(Context applicationcontext) {
		super(applicationcontext, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
		db.execSQL(SQL_CREATE_QUERY);
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try{
		String query = "DROP TABLE IF EXISTS "+TABLE_INVENTORY;
		db.execSQL(query);
		onCreate(db);
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
	}

	public void insertInventory(InventoryInfo inventoryInfo){
		try{
		//this will open a database for read and write.
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, inventoryInfo.getType() );
		values.put(KEY_IMEI, inventoryInfo.getImei() );
		values.put(KEY_NAME, inventoryInfo.getName() );
		values.put(KEY_COMPANY, inventoryInfo.getCompany() );
		values.put(KEY_VERSION, inventoryInfo.getVersion() );
		values.put(KEY_BUYDATE, inventoryInfo.getBuyDate() );
		values.put(KEY_QTY, inventoryInfo.getQty());
		values.put(KEY_AVAILABLE, inventoryInfo.getAvailable() );
		values.put(KEY_RESERVEDBY, inventoryInfo.getReservedBy() );
		
		database.insert(TABLE_INVENTORY, null, values);
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
	}
	
	/*
	 * To Do: Get type from user click i.e. button and pass that text as where type = "". 
	 * One method for All.
	 */
	public ArrayList<InventoryInfo> getAllPhoneList()
	{
		ArrayList<InventoryInfo> allPhoneList =new ArrayList<InventoryInfo>();
		try{
		SQLiteDatabase database = this.getReadableDatabase();
		
		String query = "SELECT * FROM "+TABLE_INVENTORY+" WHERE "+KEY_QTY+"!="+stringZero;	// +" where "+KEY_QTY+"!="+stringZero;
		
		// Cursor provides read/write access to data returned from database query.
		//rawQuery will execute query and return the result.
		Cursor cursor = database.rawQuery(query, null);
		
		if(cursor.moveToFirst())
		{	
			do{
				InventoryInfo inventoryInfo = new InventoryInfo();
				inventoryInfo.setId(cursor.getString(0));
				inventoryInfo.setType(cursor.getString(1)); 
				inventoryInfo.setImei(cursor.getString(2));
				inventoryInfo.setName(cursor.getString(3));
				inventoryInfo.setCompany(cursor.getString(4));
				inventoryInfo.setVersion(cursor.getString(5));
				inventoryInfo.setBuyDate(cursor.getString(6));
				inventoryInfo.setQty(cursor.getString(7));
				inventoryInfo.setAvailable(cursor.getString(8));
				inventoryInfo.setReservedBy(cursor.getString(9));
				
				allPhoneList.add(inventoryInfo);
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
		
		return allPhoneList;
	}
	
	public InventoryInfo getInventoryById(String itemId)
	{
		InventoryInfo invemtoryInfoById = new InventoryInfo();
		try{
		SQLiteDatabase database = this.getReadableDatabase();
		
		String query = "SELECT * FROM "+TABLE_INVENTORY+" WHERE "+KEY_ID+"="+itemId;
		Cursor cursor = database.rawQuery(query, null);
		
		if(cursor.moveToFirst())
		{	
			do{
				invemtoryInfoById.setId(cursor.getString(0));
				invemtoryInfoById.setType(cursor.getString(1));
				invemtoryInfoById.setImei(cursor.getString(2));
				invemtoryInfoById.setName(cursor.getString(3));
				invemtoryInfoById.setCompany(cursor.getString(4));
				invemtoryInfoById.setVersion(cursor.getString(5));
				invemtoryInfoById.setBuyDate(cursor.getString(6));
				invemtoryInfoById.setQty(cursor.getString(7));
				invemtoryInfoById.setAvailable(cursor.getString(8));
				invemtoryInfoById.setReservedBy(cursor.getString(9));
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
		return invemtoryInfoById;
	}
	
	public ArrayList<InventoryInfo> getRecentlyAddedItem(InventoryInfo inventoryInfo)
	{
		ArrayList<InventoryInfo> recentlyAddedItem;
		recentlyAddedItem =new ArrayList<InventoryInfo>();
		try{
		SQLiteDatabase database = this.getReadableDatabase();
		
		String query = "SELECT * FROM "+TABLE_INVENTORY+" WHERE "+KEY_IMEI+ "="+inventoryInfo.getImei();
		
		Cursor cursor = database.rawQuery(query, null);
		
		if(cursor.moveToFirst())
		{	
			do{
				InventoryInfo inventoryInfoRet = new InventoryInfo();
				inventoryInfoRet.setId(cursor.getString(0));
				inventoryInfoRet.setType(cursor.getString(1));
				inventoryInfoRet.setImei(cursor.getString(2));
				inventoryInfoRet.setName(cursor.getString(3));
				inventoryInfoRet.setCompany(cursor.getString(4));
				inventoryInfoRet.setVersion(cursor.getString(5));
				inventoryInfoRet.setBuyDate(cursor.getString(6));
				inventoryInfoRet.setQty(cursor.getString(7));
				inventoryInfoRet.setAvailable(cursor.getString(8));
				inventoryInfoRet.setReservedBy(cursor.getString(9));
				
				recentlyAddedItem.add(inventoryInfoRet);
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
		return recentlyAddedItem;
	}
	
	public boolean reserveItems(ArrayList<String> selectedItemList) {
		boolean isSuccess = false;
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			for (String itemId : selectedItemList) {
				
				Log.d("Item Id", itemId);
				int newQty = Integer.parseInt(getInventoryById(itemId).getQty()) - 1;
				Log.d("new Qty", String.valueOf(newQty));
				
				/*//String query = "UPDATE "+TABLE_INVENTORY+ " SET "+KEY_QTY+" = "+String.valueOf(newQty)+", "+KEY_AVAILABLE+" = "+falseString+", "+KEY_RESERVEDBY+" = "+username+"' WHERE "+KEY_ID+"="+Integer.parseInt(itemId);
				String query = "UPDATE "+TABLE_INVENTORY+ " SET "+KEY_QTY+" = "+"'"+String.valueOf(newQty)+"',"+KEY_AVAILABLE+" = "+"'"+falseString+"',"+KEY_RESERVEDBY+" = "+"'"+username+"' WHERE "+KEY_ID+"="+itemId;
				Cursor cursor = database.rawQuery(query,null); 
				cursor.close();*/
				 
				ContentValues values = new ContentValues();
				values.put(KEY_QTY, String.valueOf(newQty));
				values.put(KEY_AVAILABLE, falseString);
				values.put(KEY_RESERVEDBY, username);
				database.update(TABLE_INVENTORY, values, KEY_ID +"=?",new String[] {itemId});
			}
			
			isSuccess = true;
			Log.d("isSuccess","True");
		} catch (Exception e) {
			isSuccess = false;
			Log.d("Exception:",e.getMessage());
		}
		return isSuccess;
	}
	
	public boolean cancelOrder(ArrayList<String> selectedItemList) {
		boolean isSuccess = false;
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			for (String itemId : selectedItemList) {
				int newQty = Integer.parseInt(getInventoryById(itemId).getQty()) + 1;
			
				ContentValues values = new ContentValues();
				values.put(KEY_QTY, String.valueOf(newQty));
				values.put(KEY_AVAILABLE, trueString);
				values.put(KEY_RESERVEDBY, adminname);
				database.update(TABLE_INVENTORY, values, KEY_ID + " = ?",new String[] { itemId });
			}
			
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			Log.d("Exception:",e.getMessage());
			return isSuccess;
		}
		return isSuccess;
	}
	
	public ArrayList<InventoryInfo> getReservedItemForUser(String userName)
	{
		ArrayList<InventoryInfo> reserveItemList;
		reserveItemList =new ArrayList<InventoryInfo>();
		try{
		SQLiteDatabase database = this.getReadableDatabase();
		
		String query = "SELECT * FROM "+TABLE_INVENTORY+" WHERE "+KEY_RESERVEDBY+"="+userName;
		Cursor cursor = database.rawQuery(query, null);
		if(cursor.moveToFirst())
		{	
			do{
				InventoryInfo inventoryInfo = new InventoryInfo();
				inventoryInfo.setId(cursor.getString(0));
				inventoryInfo.setType(cursor.getString(1));
				inventoryInfo.setImei(cursor.getString(2));
				inventoryInfo.setName(cursor.getString(3));
				inventoryInfo.setCompany(cursor.getString(4));
				inventoryInfo.setVersion(cursor.getString(5));
				inventoryInfo.setBuyDate(cursor.getString(6));
				inventoryInfo.setQty(cursor.getString(7));
				inventoryInfo.setAvailable(cursor.getString(8));
				inventoryInfo.setReservedBy(cursor.getString(9));
				
				reserveItemList.add(inventoryInfo);
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		} catch (Exception e) {
			Log.d("Exception:",e.getMessage());
		}
		return reserveItemList;
	}
	
	public boolean updateInventory(InventoryInfo inventoryInfo)
	{
		boolean isUpdated = false;
		try{
			SQLiteDatabase database = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(KEY_TYPE, inventoryInfo.getType() );
			values.put(KEY_IMEI, inventoryInfo.getImei() );
			values.put(KEY_NAME, inventoryInfo.getName() );
			values.put(KEY_COMPANY, inventoryInfo.getCompany() );
			values.put(KEY_VERSION, inventoryInfo.getVersion() );
			values.put(KEY_BUYDATE, inventoryInfo.getBuyDate() );
			values.put(KEY_QTY, inventoryInfo.getQty());
			values.put(KEY_AVAILABLE, inventoryInfo.getAvailable() );
			values.put(KEY_RESERVEDBY, inventoryInfo.getReservedBy() );
			
			database.update(TABLE_INVENTORY, values, KEY_ID + " = ?",new String[] { inventoryInfo.getId() });
			isUpdated = true;
		} catch (Exception e) {
			isUpdated = false;
			Log.d("Exception:",e.getMessage());
		}
		return isUpdated;
	}
	
	/*
	 * Getter for available and Reserved by.
	 * Default is 0 and admin.
	 * */
	public String getAvailable() {
		return available;
	}

	public String getReservedBy() {
		return reservedBy;
	}
	
}
