/* KeyboardDirector.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jan 18, 2012 4:06:44 PM , Created by sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.test.zss;

import java.util.Iterator;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.zkoss.test.Browser;
import org.zkoss.test.CompundKey;
import org.zkoss.test.ConditionalTimeBlocker;
import org.zkoss.test.JQuery;
import org.zkoss.test.JavascriptActions;
import org.zkoss.test.Keycode;
import org.zkoss.test.MouseButton;

import com.google.common.collect.Iterators;
import com.google.inject.Inject;

/**
 * @author sam
 *
 */
public class KeyboardDirector {
	
	final WebDriver webDriver;
	
	final Spreadsheet spreadsheet;
	
	final ConditionalTimeBlocker timeBlocker;
	
	final Browser browser;
	
	@Inject
	/*package*/ KeyboardDirector (Spreadsheet spreadsheet, Browser browser,
			ConditionalTimeBlocker timeBlocker, WebDriver webDriver) {
		this.spreadsheet = spreadsheet;
		this.timeBlocker = timeBlocker;
		this.webDriver = webDriver;
		this.browser = browser;
	}
	
	public void sendKeys(int row, int col, CharSequence keys) {
		spreadsheet.focus(row, col);
		
		//TODO: test selenium: shall not lost input char
		if (keys.length() > 0) {
			final CharSequence first = keys.subSequence(0, 1);
			WebElement webElement = spreadsheet.jq$focus().getWebElement();
			webElement.sendKeys(first);
			timeBlocker.waitUntil(1);
			timeBlocker.waitResponse();
			
			if (keys.length() > 1) {
				try {
					final CharSequence rest = keys.subSequence(1, keys.length());

					webElement = spreadsheet.getInlineEditor().getWebElement();
					webElement.sendKeys(rest);
					timeBlocker.waitResponse();	
				} catch (ElementNotVisibleException ex) {
					//if protect sheet, cannot edit, will throw ElementNotVisibleException
				}	
			}
		} else {
			delete(row, col);
		}
	}
	
	public void setEditText(int tRow, int lCol, int bRow, int rCol, String[] texts) {
		Iterator<String> t = Iterators.cycle(texts);
		
		for (int r = tRow; r <= bRow; r++) {
			for (int c = lCol; c <= rCol; c++) {
				String txt = t.next();
				setEditText(r, c, txt);
			}
		}
	}
	
	public void setEditText(int row, int col, CharSequence keys) {
		//TODO: not always work correctly, fix it.
		sendKeys(row, col, keys);
		
		try {
//			spreadsheet.getInlineEditor().jq$n().getWebElement().sendKeys(Keys.ENTER);
			
			new JavascriptActions(webDriver)
			.enter(spreadsheet.getInlineEditor().jq$n())
			.perform();
		} catch (ElementNotVisibleException ex) {
			//protect sheet will cause ElementNotVisibleException ex
		}
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	
	public void enter(JQuery target) {
		if (target == null) {
			target = spreadsheet.jq$focus();
		}
		new JavascriptActions(webDriver).enter(target).perform();
		timeBlocker.waitResponse();
	}
	
	public void esc(JQuery target) {
		if (target == null) {
			target = spreadsheet.jq$focus();
		}
		new JavascriptActions(webDriver).esc(target).perform();
		timeBlocker.waitResponse();
	}
	
	public void delete(int row, int col) {
		spreadsheet.focus(row, col);
		JQuery target = spreadsheet.jq$n();
		new JavascriptActions(webDriver).delete(target).perform();
		timeBlocker.waitResponse();
	}

	public void ctrlCopy(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlCopy(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitResponse();
	}
	
	public void ctrlCut(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlCut(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitResponse();
	}
	
	public void ctrlPaste(int row, int col) {
		spreadsheet.focus(row, col);
		
		new JavascriptActions(webDriver)
		.ctrlPaste(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitResponse();
	}
	
	public void ctrlFontBold(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlFontBold(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	
	public void ctrlFontItalic(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlFontItalic(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	
	public void ctrlFontUnderline(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlFontUnderline(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	
	public void ctrlDelete(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlDelete(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	

	public void ctrlD(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		new JavascriptActions(webDriver)
		.ctrlD(spreadsheet.jq$n())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
	
	public void delete(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.setSelection(tRow, lCol, bRow, rCol);
		
		JQuery target = spreadsheet.jq$n();
		new JavascriptActions(webDriver)
		.keyDown(target, Keycode.DELETE.intValue())
		.keyDown(target, Keycode.DELETE.intValue())
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}


	public void shiftSelect(int tRow, int lCol, int bRow, int rCol) {
		spreadsheet.focus(tRow, lCol);
		
		JQuery target = spreadsheet.getCell(bRow, rCol).jq$n();
		new JavascriptActions(webDriver)
		.mouseDown(target, MouseButton.LEFT, CompundKey.SHIFT)
		.mouseUp(target, MouseButton.LEFT, CompundKey.SHIFT)
		.perform();
		
		timeBlocker.waitUntil(1);
		timeBlocker.waitResponse();
	}
}
