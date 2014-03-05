package org.zkoss.zss.range.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.zss.model.*;
import org.zkoss.zss.model.impl.AbstractBookAdv;

/*package*/ class NotifyChangeHelper{

	public void notifyRowColumnSizeChange(HashSet<SheetRegion> notifySet) {
		for (SheetRegion notify : notifySet) {
			notifyRowColumnSizeChange(notify);
		}
	}

	public void notifyRowColumnSizeChange(SheetRegion notify) {
		((AbstractBookAdv) notify.getSheet().getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_ROW_COLUMN_SIZE_CHANGE,
				notify.getSheet(),
				new CellRegion(notify.getRow(),notify.getColumn(),notify.getLastRow(),notify.getLastColumn())));
	}
	
	
	public void notifySheetAutoFilterChange(SSheet sheet) {
		((AbstractBookAdv) sheet.getBook())
				.sendModelEvent(ModelEvents.createModelEvent(
						ModelEvents.ON_AUTOFILTER_CHANGE, sheet));
	}

	public void notifySheetFreezeChange(SSheet sheet) {
		((AbstractBookAdv) sheet.getBook())
				.sendModelEvent(ModelEvents.createModelEvent(
						ModelEvents.ON_FREEZE_CHANGE, sheet));
	}
	
	public void notifySheetPictureAdd(SSheet sheet, String id){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_PICTURE_ADD, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}

	public void notifySheetPictureDelete(SSheet sheet, String id) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_PICTURE_DELETE, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}

	public void notifySheetPictureMove(SSheet sheet, String id) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_PICTURE_UPDATE, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}

	public void notifySheetChartAdd(SSheet sheet, String id){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CHART_ADD, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}
	
	public void notifySheetChartDelete(SSheet sheet, String id){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CHART_DELETE, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}
	
	public void notifySheetChartUpdate(SSheet sheet, String id){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CHART_UPDATE, 
				sheet, ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID, id)));
	}
	
	public void notifyMergeRemove(Set<SheetRegion> toRemove) {
		for(SheetRegion notify:toRemove){//remove the final remove list
			notifyMergeRemove(notify);
		}
	}
	public void notifyMergeRemove(SheetRegion notify) {
		SBook book = notify.getSheet().getBook();
		System.out.println(">>> Notify remove merge "+notify.getReferenceString());
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_MERGE_DELETE,notify.getSheet(),
				notify.getRegion()));
	}
	
	public void notifyMergeAdd(Set<SheetRegion> toAdd) {
		for(SheetRegion notify:toAdd){
			notifyMergeAdd(notify);
		}
	}
	public void notifyMergeAdd(SheetRegion notify) {
		SBook book = notify.getSheet().getBook();
		System.out.println(">>> Notify add merge "+notify.getReferenceString());
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_MERGE_ADD,notify.getSheet(),
				notify.getRegion()));
	}

	public void notifyCellChange(Set<SheetRegion> cellNotifySet) {
		for(SheetRegion notify:cellNotifySet){
			notifyCellChange(notify);
		}
	}
	public void notifyCellChange(SheetRegion notify) {
		SBook book = notify.getSheet().getBook();
		System.out.println(">>> Notify update cell "+notify.getReferenceString());
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CELL_CONTENT_CHANGE,notify.getSheet(),
				notify.getRegion()));
	}
	
	public void notifySheetDelete(SBook book,SSheet deletedSheet,int deletedIndex){
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_SHEET_DELETE,book,
				ModelEvents.createDataMap(ModelEvents.PARAM_SHEET,deletedSheet,ModelEvents.PARAM_INDEX,deletedIndex)));
	}
	
	public void notifySheetCreate(SSheet sheet){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_SHEET_CREATE,sheet));
	}
	
	public void notifySheetNameChange(SSheet sheet,String oldName){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_SHEET_NAME_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OLD_NAME,oldName)));
	}
	
	public void notifySheetReorder(SSheet sheet,int oldIdx){
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_SHEET_ORDER_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OLD_INDEX,oldIdx)));
	}

	public void notifyDataValidationChange(SSheet sheet, String validationId) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_DATA_VALIDATION_CONTENT_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID,validationId)));
	}

	public void notifyChartChange(SSheet sheet, String chartId) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CHART_CONTENT_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID,chartId)));
	}

	public void notifyCustomEvent(String customEventName, SSheet sheet,
			Object data) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(customEventName,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_CUSTOM_DATA,data)));
	}

	public void notifyDisplayGirdline(SSheet sheet, boolean show) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_DISPLAY_GRIDLINE_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_ENABLED,show)));
	}
	
	public void notifyProtectSheet(SSheet sheet, boolean protect) {
		((AbstractBookAdv) sheet.getBook()).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_PROTECT_SHEET_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_ENABLED,protect)));
	}
	public void notifyInsertDelete(List<InsertDeleteUpdate> insertDeleteNofitySet) {
		// make sure they are orderly
		for(InsertDeleteUpdate update : insertDeleteNofitySet) {
			notifyInsertDelete(update);
		}
	}
	public void notifyInsertDelete(InsertDeleteUpdate update) {
		// create event, then fire it
		String eventName;
		CellRegion region;
		if(update.isRow()) {
			eventName = update.isInserted() ? ModelEvents.ON_ROW_INSERT : ModelEvents.ON_ROW_DELETE;
			region = new CellRegion(update.getIndex(), 0, update.getLastIndex(), 0);
		} else {
			eventName = update.isInserted() ? ModelEvents.ON_COLUMN_INSERT : ModelEvents.ON_COLUMN_DELETE;
			region = new CellRegion(0, update.getIndex(), 0, update.getLastIndex());
		}
		SSheet sheet = update.getSheet();
		ModelEvent event = ModelEvents.createModelEvent(eventName, sheet, region);
		((AbstractBookAdv)sheet.getBook()).sendModelEvent(event);
	}
}