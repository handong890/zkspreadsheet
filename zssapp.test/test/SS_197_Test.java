/* order_test_1Test.java

	Purpose:
		
	Description:
		
	History:
		Sep, 7, 2010 17:30:59 PM

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

This program is distributed under Apache License Version 2.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/

//sort ==> custom sort B13~B17, add second condition and downward first condition to the bottom 
public class SS_197_Test extends SSAbstractTestCase {
	@Override
	protected void executeTest() {
		rightClickCells(1,12,1,16);
	
		mouseOver(jq("a.z-menu-cnt:eq(3)"));		
		waitResponse();
		click(jq("$customSort a.z-menu-item-cnt"));
		waitResponse();
		
		//verify
		verifyTrue(jq("$_customSortDialog").isVisible());	
		verifyFalse(jq("$sortWin @combobox i.z-combobox-rounded-btn-readonly:eq(3)").exists());

		click(jq("$addBtn img"));
		waitResponse();
		
		//verify
		verifyTrue(jq("$_customSortDialog @combobox i.z-combobox-rounded-btn-readonly:eq(3)").exists());

		click(jq("$_customSortDialog @listcell div.z-overflow-hidden:eq(0)"));
		waitResponse();
		
		//verify
		verifyTrue(jq(".z-listitem:eq(0)").hasClass("z-listitem-seld"));

		click(jq("$downBtn img"));
		waitResponse();

		//verify
		verifyTrue(jq(".z-listitem:eq(1)").hasClass("z-listitem-seld"));
	}
}



