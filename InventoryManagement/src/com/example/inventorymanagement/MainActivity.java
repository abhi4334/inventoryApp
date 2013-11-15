package com.example.inventorymanagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void login(View view) {
	    
	    EditText idEditText = (EditText) findViewById(R.id.loginIdEditText);
	    EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
	    String login_id = idEditText.getText().toString();
	    String password = passwordEditText.getText().toString();
	    if(login_id.equals("abhi") && password.equals("4334"))
	    {
	    	Intent intent = new Intent(this, HomePageActivity.class);
		    intent.putExtra("userName",login_id);
		    startActivity(intent);
	    }
	    else
	    {
	    	TextView textView = new TextView(this);
		    textView.setTextSize(15);
		    textView.setPadding(0, 100, 0, 0);
		    textView.setText("Invalid Password");

		    // Set the text view as the activity layout
		    setContentView(textView);
	    }
	    


	}

}
