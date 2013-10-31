package be.pxl.minecraftguide.model;

public class Command {
	private int _id;
	private String commandCategory;
	private String commandTitle;
	private String commandDescription;
	
	public Command(int _id, String commandCategory, String commandTitle, String commandDescription) {
		this._id = _id;
		this.commandCategory = commandCategory;
		this.commandTitle = commandTitle;
		this.commandDescription = commandDescription;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getCommandCategory() {
		return commandCategory;
	}
	public void setCommandCategory(String commandCategory) {
		this.commandCategory = commandCategory;
	}
	public String getCommandTitle() {
		return commandTitle;
	}
	public void setCommandTitle(String commandTitle) {
		this.commandTitle = commandTitle;
	}
	public String getCommandDescription() {
		return commandDescription;
	}
	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}

	
}
