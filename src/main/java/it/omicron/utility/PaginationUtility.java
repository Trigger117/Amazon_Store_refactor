package it.omicron.utility;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.omicron.model.PaginationResponse;

@Component
public class PaginationUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(PaginationUtility.class);
	
	public PaginationResponse buildPaginatinatedResponse(List<?> records, Integer fromRecord, Integer toRecord, Integer totalRecord, String recordType) {
		logger.info("buildPaginatinatedResponse - START");
		PaginationResponse paginationResponse = new PaginationResponse();
		paginationResponse.setFromRecord(fromRecord);
		paginationResponse.setToRecord(toRecord);
		paginationResponse.setTotalRecord(totalRecord);
		paginationResponse.setRecordType(recordType);
		paginationResponse.setRecords(records);
		logger.info("buildPaginatinatedResponse - END");
		return paginationResponse;
	}
	
}
