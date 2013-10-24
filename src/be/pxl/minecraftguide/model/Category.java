package be.pxl.minecraftguide.model;

public class Category {
	private int _id;
	private int categoryImageID;
	private String categoryDescription;
	
	public Category(int _id, int categoryImage, String categoryDescription) {
		this._id = _id;
		this.categoryImageID = categoryImage;
		this.categoryDescription = categoryDescription;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getCategoryImage() {
		return categoryImageID;
	}
	public void setCategoryImage(int categoryImage) {
		this.categoryImageID = categoryImage;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	
}
