/* FormulabarEditor.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jan 19, 2012 9:58:35 AM , Created by sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.test.zss;

import org.openqa.selenium.WebDriver;
import org.zkoss.test.ConditionalTimeBlocker;
import org.zkoss.test.JQueryFactory;
import org.zkoss.test.Widget;

import com.google.inject.Inject;

/**
 * @author sam
 *
 */
public class FormulabarEditor extends Widget {

	/**
	 * @param widgetId
	 * @param webDriver
	 */
	@Inject
	/*package*/ FormulabarEditor(SheetCtrl sheet, JQueryFactory jqFactory, ConditionalTimeBlocker timeBlocker, WebDriver webDriver) {
		super(sheet.widgetScript() + ".formulabarEditor", jqFactory, timeBlocker, webDriver);
	}
}
