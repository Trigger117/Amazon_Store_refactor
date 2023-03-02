package it.omicron.model;

import java.util.List;

public class PaginationResponse {

	private Integer fromRecord;
	private Integer toRecord;
	private Integer totalRecord;
	private String recordType;
	private List<?> records;

	public Integer getFromRecord() {
		return fromRecord;
	}

	public void setFromRecord(Integer fromRecord) {
		this.fromRecord = fromRecord;
	}

	public Integer getToRecord() {
		return toRecord;
	}

	public void setToRecord(Integer toRecord) {
		this.toRecord = toRecord;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}
	
}

