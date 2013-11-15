package com.example.inventorymanagement.beans;

public class CheckboxModel {

	private String id;
	  private boolean selected;

	  public CheckboxModel(String id) {
	    this.id = id;
	    selected = false;
	  }

	  public String getid() {
	    return id;
	  }

	  public void setid(String id) {
	    this.id = id;
	  }

	  public boolean isSelected() {
	    return selected;
	  }

	  public void setSelected(boolean selected) {
	    this.selected = selected;
	  }
}
