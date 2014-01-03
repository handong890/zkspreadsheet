/*

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/12/01 , Created by Hawk
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ngapi.impl.imexp;

import java.io.*;
import org.zkoss.poi.hssf.usermodel.*;
import org.zkoss.poi.ss.usermodel.*;
import org.zkoss.zss.ngmodel.*;
/**
 * 
 * @author Hawk
 * @since 3.5.0
 */
public class NExcelXlsImporter extends AbstractExcelImporter{

	
	@Override
	protected Workbook createPoiBook(InputStream is) throws IOException{
		return new HSSFWorkbook(is);
	}

	@Override
	protected void importExternalBookLinks() {
		// do nothing
		// xls file has no individual external book links
		// every reference has every necessary information including external book index
		// and already resolved by POI
	}
	
	/**
	 * 
	 * @param poiSheet
	 * @return 256
	 */
	private int getLastChangedColumnIndex(Sheet poiSheet) {
		return new HSSFSheetHelper((HSSFSheet)poiSheet).getInternalSheet().getMaxConfiguredColumn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void importColumn(Sheet poiSheet, NSheet sheet, int defaultWidth) {
		int lastChangedColumnIndex = getLastChangedColumnIndex(poiSheet);
		for (int c=0 ; c <= lastChangedColumnIndex ; c++){
			//reference Spreadsheet.updateColWidth()
			NColumn col = sheet.getColumn(c);
			int width = XUtils.getWidthAny(poiSheet, c, CHRACTER_WIDTH);
			boolean hidden = poiSheet.isColumnHidden(c);
			col.setHidden(hidden);
			//to avoid creating unnecessary column with just default value
			if(!hidden && width != defaultWidth){
				col.setWidth(width);
			}
			CellStyle columnStyle = poiSheet.getColumnStyle(c); 
			if (columnStyle != null){
				col.setCellStyle(importCellStyle(columnStyle));
			}
		}
	}

//	@Override
	protected void importChart(Sheet poiSheet, NSheet sheet) {
		// TODO Auto-generated method stub
		
	}


}
