package be.pxl.minecraftguide.model;

public class Recipe {
    private int _id;
    private int recipeCategory;
    private int recipeImageID;
    private String recipeDescription;
    private int[] recipeLocations;

    public Recipe(int recipeID, int recipeCategory, int recipeImageID, String recipeDescription, int[] recipeLocations) {
        this._id = recipeID;
        this.recipeCategory = recipeCategory;
        this.recipeImageID = recipeImageID;
        this.recipeDescription = recipeDescription;
        this.recipeLocations = recipeLocations;
    }

    public int getRecipeID() {
        return _id;
    }

    public void setRecipeID(int recipeID) {
        this._id = recipeID;
    }
    
    public int getRecipeImageID() {
		return recipeImageID;
	}

	public void setRecipeImageID(int recipeImageID) {
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

    public int[] getRecipeLocations() {
        return recipeLocations;
    }

    public void setRecipeLocations(int[] recipeLocations) {
        this.recipeLocations = recipeLocations;
    }
}
