package imgManage;

public class capture {
	String base_url ;
	double pix_w ;
	double pix_h ;
	String filename ;
	public String getBase_url() {
		return base_url;
	}
	public void setBase_url(String base_url) {
		this.base_url = base_url;
	}
	public double getPix_w() {
		return pix_w;
	}
	public void setPix_w(double pix_w) {
		this.pix_w = pix_w;
	}
	public double getPix_h() {
		return pix_h;
	}
	public void setPix_h(double pix_h) {
		this.pix_h = pix_h;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public  capture() {
		super();
	}
}
