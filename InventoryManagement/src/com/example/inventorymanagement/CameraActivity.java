package com.example.inventorymanagement;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {
	
	Button ButtonClick;
	int CAMERA_PIC_REQUEST = 100; 
	int  TAKE_PICTURE=0;
	Camera camera;
	///private Uri fileUri;
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		Log.d("Camera","in On Create");
		imageView =(ImageView) findViewById(R.id.ImageView);
	    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //"android.media.action.IMAGE_CAPTURE"
	    //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	    //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	     
	}
	
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	    super.onActivityResult(requestCode, resultCode, data);
	    if(requestCode == CAMERA_PIC_REQUEST){
	    if( resultCode == RESULT_OK)
	    {   
	    	//on success show the picture.
	    	
	        BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inSampleSize = 8;
	        final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
	        image.setImageBitmap(bitmap);
	    }
	    else 
	    {
	        Toast.makeText(getApplicationContext(), "Picture NOt taken", Toast.LENGTH_SHORT).show();
	    }
	    }

	}*/
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
 
          if (requestCode == CAMERA_PIC_REQUEST) {
              Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
              imageView.setImageBitmap(thumbnail);
          }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}
	
	/*public Uri getOutputMediaFileUri(int type) {
	    return Uri.fromFile(getOutputMediaFile(type));
	}
	
	private static File getOutputMediaFile(int type) {
		 
	    // External sdcard location
	    File mediaStorageDir = new File(
	            Environment
	                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
	            IMAGE_DIRECTORY_NAME);
	 
	    // Create the storage directory if it does not exist
	    if (!mediaStorageDir.exists()) {
	        if (!mediaStorageDir.mkdir()) {
	            Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
	                    + IMAGE_DIRECTORY_NAME + " directory");
	            return null;
	        }
	    }
	 
	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
	            Locale.getDefault()).format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator
	                + "IMG_" + timeStamp + ".jpg");
	    } else if (type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator
	                + "VID_" + timeStamp + ".mp4");
	    } else {
	        return null;
	    }
	 
	    return mediaFile;
	}*/

}
