/* FontFamilyAction.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/8/5 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ui.impl.ua;

import org.zkoss.util.resource.Labels;
import org.zkoss.zss.api.CellOperationUtil;
import org.zkoss.zss.api.Range;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.Rect;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.ui.UserActionContext;
import org.zkoss.zss.ui.impl.undo.FontStyleAction;
import org.zkoss.zss.ui.sys.UndoableActionManager;

/**
 * @author dennis
 *
 */
public class FontItalicHandler extends AbstractProtectedHandler {

	@Override
	protected boolean processAction(UserActionContext ctx) {
		Sheet sheet = ctx.getSheet();
		Rect selection = ctx.getSelection();
		Range range = Ranges.range(sheet, selection);
		
		boolean italic = !range.getCellStyle().getFont().isItalic();
		
		UndoableActionManager uam = ctx.getSpreadsheet().getUndoableActionManager();
		uam.doAction(new FontStyleAction(Labels.getLabel("zss.undo.fontStyle"),sheet, selection.getRow(), selection.getColumn(), 
				selection.getLastRow(), selection.getLastColumn(), 
				CellOperationUtil.getFontItalicApplier(italic)));
		return true;
	}

}