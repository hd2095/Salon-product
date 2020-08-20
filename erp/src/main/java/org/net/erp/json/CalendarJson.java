package org.net.erp.json;

public class CalendarJson {

	private String title;
	
	private String start;
	
	private String description;
	
	private String end;
	
	private ExtendedPropsJson extendedProps;
	
	private String url;
	
	private String className;

	public ExtendedPropsJson getExtendedProps() {
		return extendedProps;
	}

	public void setExtendedProps(ExtendedPropsJson extendedProps) {
		this.extendedProps = extendedProps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
