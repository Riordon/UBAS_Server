package com.ubas.util;

public class Parameter {

	private int website;// 网站ID
	private long startTime; // 查询开始时间
	private long endTime; // 查询结束时间
	private int customerType;// 客户类型ID
	private int fasion;// 风格ID
	private int registerType;// 注册类型ID
	private String account;// 客户账号
	private int currPage; // 当前第几页（分页中用到）
	private int showNumber;// 总共显示多少条（分页中用到）
	private long areaNo;// 所属区域代码
	private String[] searchMouth;// 搜索
	private String url;// 浏览URL
	private int[] columns;// 选择栏目ID集合
	private int[] sorts;// 频道集合
	private String expandField;// 扩展查询字段（需要的时候用到）
	public int getWebsite() {
		return website;
	}
	public void setWebsite(int website) {
		this.website = website;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getCustomerType() {
		return customerType;
	}
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	public int getFasion() {
		return fasion;
	}
	public void setFasion(int fasion) {
		this.fasion = fasion;
	}
	public int getRegisterType() {
		return registerType;
	}
	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}
	public long getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(long areaNo) {
		this.areaNo = areaNo;
	}
	public String[] getSearchMouth() {
		return searchMouth;
	}
	public void setSearchMouth(String[] searchMouth) {
		this.searchMouth = searchMouth;
	}
	public int[] getColumns() {
		return columns;
	}
	public void setColumns(int[] columns) {
		this.columns = columns;
	}
	public int[] getSorts() {
		return sorts;
	}
	public void setSorts(int[] sorts) {
		this.sorts = sorts;
	}
	public String getExpandField() {
		return expandField;
	}
	public void setExpandField(String expandField) {
		this.expandField = expandField;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
