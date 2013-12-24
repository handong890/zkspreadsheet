/*

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/12/01 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ngmodel.impl;

import java.util.Collections;
import java.util.Iterator;

import org.zkoss.zss.ngmodel.NCellStyle;
import org.zkoss.zss.ngmodel.NSheet;
import org.zkoss.zss.ngmodel.util.Validations;
/**
 * 
 * @author dennis
 * @since 3.5.0
 */
public class RowImpl extends AbstractRowAdv {
	private static final long serialVersionUID = 1L;

	private AbstractSheetAdv sheet;

	private final BiIndexPool<AbstractCellAdv> cells = new BiIndexPool<AbstractCellAdv>();

	private AbstractCellStyleAdv cellStyle;
	
	private Integer height;
	private boolean hidden = false;
	private boolean customHeight = false;

	public RowImpl(AbstractSheetAdv sheet) {
		this.sheet = sheet;
	}

	@Override
	public NSheet getSheet() {
		checkOrphan();
		return sheet;
	}

	@Override
	public int getIndex() {
		checkOrphan();
		return sheet.getRowIndex(this);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	AbstractCellAdv getCell(int columnIdx, boolean proxy) {
		AbstractCellAdv cellObj = cells.get(columnIdx);
		if (cellObj != null) {
			return cellObj;
		}
		checkOrphan();
		return proxy ? new CellProxy(sheet, getIndex(), columnIdx) : null;
	}

	@Override
	AbstractCellAdv getOrCreateCell(int columnIdx) {
		AbstractCellAdv cellObj = cells.get(columnIdx);
		if (cellObj == null) {
			checkOrphan();
			cellObj = new CellImpl(this);
			cells.put(columnIdx, cellObj);
		}
		return cellObj;
	}

	@Override
	int getCellIndex(AbstractCellAdv cell) {
		return cells.get(cell);
	}

	@Override
	public int getStartCellIndex() {
		return cells.firstKey();
	}

	@Override
	public int getEndCellIndex() {
		return cells.lastKey();
	}

	@Override
	protected void onModelInternalEvent(ModelInternalEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearCell(int start, int end) {
		// clear before move relation
		for (AbstractCellAdv cell : cells.subValues(start, end)) {
			cell.destroy();
		}
		cells.clear(start, end);
	}

	@Override
	public void insertCell(int cellIdx, int size) {
		if (size <= 0)
			return;

		cells.insert(cellIdx, size);
	}

	@Override
	public void deleteCell(int cellIdx, int size) {
		if (size <= 0)
			return;
		// clear before move relation
		for (AbstractCellAdv cell : cells.subValues(cellIdx, cellIdx + size)) {
			cell.destroy();
		}

		cells.delete(cellIdx, size);
	}

	@Override
	public void checkOrphan() {
		if (sheet == null) {
			throw new IllegalStateException("doesn't connect to parent");
		}
	}

	@Override
	public void destroy() {
		checkOrphan();
		for (AbstractCellAdv cell : cells.values()) {
			cell.destroy();
		}
		sheet = null;
	}

	@Override
	public NCellStyle getCellStyle() {
		return getCellStyle(false);
	}

	@Override
	public NCellStyle getCellStyle(boolean local) {
		if (local || cellStyle != null) {
			return cellStyle;
		}
		checkOrphan();
		return sheet.getBook().getDefaultCellStyle();
	}

	@Override
	public void setCellStyle(NCellStyle cellStyle) {
		Validations.argNotNull(cellStyle);
		Validations.argInstance(cellStyle, CellStyleImpl.class);
		this.cellStyle = (CellStyleImpl) cellStyle;
	}

	@Override
	public int getHeight() {
		if(height!=null){
			return height.intValue();
		}
		checkOrphan();
		return getSheet().getDefaultRowHeight();
	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

	@Override
	public void setHeight(int height) {
		this.height = Integer.valueOf(height);
	}

	@Override
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public boolean isCustomHeight() {
		return customHeight;
	}
	@Override
	public void setCustomHeight(boolean custom) {
		customHeight = custom;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterator<AbstractCellAdv> getCellIterator() {
		return Collections.unmodifiableCollection(cells.values()).iterator();
	}
}
