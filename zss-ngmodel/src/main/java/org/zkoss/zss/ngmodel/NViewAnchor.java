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
package org.zkoss.zss.ngmodel;

import java.io.Serializable;
/**
 * Represent a rectangle area by its left-top cell index, width, height, x and y offset within the cell.
 * @author dennis
 * @since 3.5.0
 */
public class NViewAnchor implements Serializable {

	private int rowIndex;
	private int columnIndex;
	private int xOffset;
	private int yOffset;
	private int width;
	private int height;
	public NViewAnchor(int rowIndex, int columnIndex, int width, int height) {
		this(rowIndex,columnIndex,0,0,width,height);
		
	}
	public NViewAnchor(int rowIndex, int columnIndex, int xOffset, int yOffset,
			int width, int height) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the left-top cell's row index
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return the left-top cell's column index
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * The offset is larger if the anchor's position is more to the right of the cell's left border. 
	 * @return the x coordinate within the anchor's left-top cell.
	 */
	public int getXOffset() {
		return xOffset;
	}

	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * The offset is larger if the anchor's position is more to the bottom of the cell's top border.
	 * @return the y coordinate within the anchor's left-top cell.
	 */
	public int getYOffset() {
		return yOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
