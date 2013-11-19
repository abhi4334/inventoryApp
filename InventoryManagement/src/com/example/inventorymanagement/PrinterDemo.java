package com.example.inventorymanagement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.print.PrintManager;
import android.support.v4.print.PrintHelper;

public class PrinterDemo extends Activity  {

	/*private void doPhotoPrint() {
	    PrintHelper photoPrinter = new PrintHelper(this);
	    photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
	    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
	            R.drawable.droids);
	    photoPrinter.printBitmap("droids.jpg - test print", bitmap);
	}*/
	
	@TargetApi(19)
	private void doPrint() {
	    // Get a PrintManager instance
	    PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);

	    // Set job name, which will be displayed in the print queue
	    String jobName = this.getString(R.string.app_name) + " Document";

	    // Start a print job, passing in a PrintDocumentAdapter implementation
	    // to handle the generation of a print document
	    printManager.print(jobName, new MyPrintDocumentAdapter(this),
	            null); 
	}

}
