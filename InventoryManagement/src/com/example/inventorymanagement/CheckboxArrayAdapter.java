package com.example.inventorymanagement;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.inventorymanagement.beans.CheckboxModel;

public class CheckboxArrayAdapter extends ArrayAdapter<CheckboxModel> {

	  private final List<CheckboxModel> list;
	  private final Activity context;

	  public CheckboxArrayAdapter(Activity context, List<CheckboxModel> list) {
		super(context,R.layout.item_list_custom_view_layout, list);
	    this.context = context;
	    this.list = list;
	  }
	  
	  static class ViewHolder {
		    protected TextView idTextView;
		    protected CheckBox checkbox;
		  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View view = null;
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.item_list_custom_view_layout, null);
	      final ViewHolder viewHolder = new ViewHolder();
	      viewHolder.idTextView = (TextView) view.findViewById(R.id.itemId);
	      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.checkBox1);
	      viewHolder.checkbox
	          .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

	            @Override
	            public void onCheckedChanged(CompoundButton buttonView,
	                boolean isChecked) {
	              CheckboxModel element = (CheckboxModel) viewHolder.checkbox
	                  .getTag();
	              element.setSelected(buttonView.isChecked());
	            }
	          });
	      view.setTag(viewHolder);
	      viewHolder.checkbox.setTag(list.get(position));
	    } else {
	      view = convertView;
	      ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
	    }
	    ViewHolder holder = (ViewHolder) view.getTag();
	    holder.idTextView.setText(list.get(position).getid());
	    holder.checkbox.setChecked(list.get(position).isSelected());
	    return view;
	  }
}
