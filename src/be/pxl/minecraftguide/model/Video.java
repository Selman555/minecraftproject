package be.pxl.minecraftguide.model;

public class Video {

	public int icon;
    public String title;
    public String text;
        
	public Video(int icon, String title, String text) {
        super();
        this.icon = icon;
        this.title = title;
        this.text = text;
    }
    public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
