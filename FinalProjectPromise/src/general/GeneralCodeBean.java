package general;

public class GeneralCodeBean {
	private String gen_code_id;
	private String gen_code_caption;
	private String parent_id;
	private Integer is_active;
	private Integer index;
	
	public String getGen_code_id() {
		return gen_code_id;
	}
	public void setGen_code_id(String gen_code_id) {
		this.gen_code_id = gen_code_id;
	}
	public String getGen_code_caption() {
		return gen_code_caption;
	}
	public void setGen_code_caption(String gen_code_caption) {
		this.gen_code_caption = gen_code_caption;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getIs_active() {
		return is_active;
	}
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
}
