package be.pxl.minecraftguide.model;

public class Video {
	private int _id;
	private int videoImageID;
	private String videoDescription;
	private String videoURL;
	
	public Video(int _id, int videoImageID, String videoDescription, String videoURL) {
		super();
		this._id = _id;
		this.videoImageID = videoImageID;
		this.videoDescription = videoDescription;
		this.videoURL = videoURL;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getVideoImageID() {
		return videoImageID;
	}
	public void setVideoImageID(int videoImageID) {
		this.videoImageID = videoImageID;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	} 
	
}
