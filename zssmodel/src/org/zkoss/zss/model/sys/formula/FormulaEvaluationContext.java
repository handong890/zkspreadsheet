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
package org.zkoss.zss.model.sys.formula;

import java.io.Serializable;

import org.zkoss.xel.FunctionMapper;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zss.model.SBook;
import org.zkoss.zss.model.SCell;
import org.zkoss.zss.model.SSheet;
import org.zkoss.zss.model.sys.AbstractContext;
import org.zkoss.zss.model.sys.dependency.Ref;

/**
 * 
 * @author dennis
 * @since 3.5.0
 */
public class FormulaEvaluationContext extends AbstractContext implements Serializable {
	private static final long serialVersionUID = 2411072362379525686L;
	
	private final SBook _book;
	private final SSheet _sheet;
	private final SCell _cell;
	private FunctionMapper _functionMapper;
	private VariableResolver _vairableResolver;
	private final Ref _dependent;
	private final int[] _offset; //ZSS-1142: [rowOffset, colOffset] for ConditionalFormatting relative formula evaluation

	public FormulaEvaluationContext(SCell cell,Ref dependent) {
		this(cell.getSheet().getBook(), cell.getSheet(), cell,dependent, null);
	}

	public FormulaEvaluationContext(SSheet sheet,Ref dependent) {
		this(sheet.getBook(), sheet, null,dependent, null);
	}

	public FormulaEvaluationContext(SBook book,Ref dependent) {
		this(book, null, null,dependent, null);
	}

	//ZSS-1142
	public FormulaEvaluationContext(SSheet sheet,Ref dependent, int[] offset) {
		this(sheet.getBook(), sheet, null,dependent, offset);
	}

	private FormulaEvaluationContext(SBook book, SSheet sheet, SCell cell,Ref dependent, int[] offset) { //ZSS-1142
		this._book = book;
		this._sheet = sheet;
		this._cell = cell;
		this._dependent = dependent;
		EvaluationContributor contributor = book instanceof EvaluationContributorContainer? 
				((EvaluationContributorContainer)book).getEvaluationContributor():null;
		if(contributor!=null){
			_functionMapper = contributor.getFunctionMaper(book);
			_vairableResolver = contributor.getVariableResolver(book);
		}
		this._offset = offset; //ZSS-1142
	}

	public SBook getBook() {
		return _book;
	}

	public SSheet getSheet() {
		return _sheet;
	}

	public SCell getCell() {
		return _cell;
	}
	public Ref getDependent() {
		return _dependent;
	}	
	
	public FunctionMapper getFunctionMapper() {
		return _functionMapper;
	}
	
	public VariableResolver getVariableResolver() {
		return _vairableResolver;
	}
	
	//ZSS-1142
	public int[] getOffset() {
		return _offset;
	}
}
