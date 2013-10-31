package be.pxl.minecraft.model;

import java.awt.Image;

public class Item {
    private int _id;
    private int recipeCategory;
    private String recipeImageID;
    private String recipeDescription;
    private String recipeLocations;
    private String usedImages;

    public Item(int recipeID, int recipeCategory, String recipeImageID, String recipeDescription, String recipeLocations, String usedImages) {
        this._id = recipeID;
        this.recipeCategory = recipeCategory;
        this.recipeImageID = recipeImageID;
        this.recipeDescription = recipeDescription;
        this.recipeLocations = recipeLocations;
        this.usedImages = usedImages;
    }

    public int getRecipeID() {
        return _id;
    }

    public void setRecipeID(int recipeID) {
        this._id = recipeID;
    }
    
    public String getRecipeImageID() {
		return recipeImageID;
	}

	public void setRecipeImageID(String recipeImageID) {
		this.recipeImageID = recipeImageID;
	}

	public int getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        this.recipeCategory = recipeCategory;
    }
    
    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeLocations() {
        return recipeLocations;
    }

    public void setRecipeLocations(String recipeLocations) {
        this.recipeLocations = recipeLocations;
    }

	public String getUsedImages() {
		return usedImages;
	}

	public void setUsedImages(String usedImages) {
		this.usedImages = usedImages;
	}
}
