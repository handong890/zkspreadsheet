package org.zkoss.zss.ngapi.impl;

import java.util.HashSet;

import org.zkoss.zss.ngmodel.CellRegion;
import org.zkoss.zss.ngmodel.ModelEvents;
import org.zkoss.zss.ngmodel.NBook;
import org.zkoss.zss.ngmodel.NBookSeries;
import org.zkoss.zss.ngmodel.NSheet;
import org.zkoss.zss.ngmodel.impl.AbstractBookAdv;
import org.zkoss.zss.ngmodel.sys.dependency.ObjectRef;
import org.zkoss.zss.ngmodel.sys.dependency.ObjectRef.ObjectType;
import org.zkoss.zss.ngmodel.sys.dependency.Ref;
import org.zkoss.zss.ngmodel.sys.dependency.Ref.RefType;

/*package*/ class RefNotifyContentChangeHelper extends RefHelperBase{

	public RefNotifyContentChangeHelper(NBookSeries bookSeries) {
		super(bookSeries);
	}

	public void notifyContentChange(HashSet<Ref> dependentSet) {
		// clear formula cache
		for (Ref dependent : dependentSet) {
			System.out.println(">>> Notify "+dependent);
			//clear the dependent's formula cache since the precedent is changed.
			if (dependent.getType() == RefType.CELL || dependent.getType() == RefType.AREA) {
				handleCellRef(dependent);
			} else if (dependent.getType() == RefType.OBJECT) {
				if(((ObjectRef)dependent).getObjectType()==ObjectType.CHART){
					handleChartRef((ObjectRef)dependent);
				}else if(((ObjectRef)dependent).getObjectType()==ObjectType.DATA_VALIDATION){
					handleDataValidationRef((ObjectRef)dependent);
				}
			} else {// TODO another

			}
		}
	}

	private void handleChartRef(ObjectRef dependent) {
		NBook book = bookSeries.getBook(dependent.getBookName());
		if(book==null) return;
		NSheet sheet = book.getSheetByName(dependent.getSheetName());
		if(sheet==null) return;
		String[] ids = dependent.getObjectIdPath();
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CHART_CONTENT_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID,ids[0])));
				
	}
	
	private void handleDataValidationRef(ObjectRef dependent) {
		NBook book = bookSeries.getBook(dependent.getBookName());
		if(book==null) return;
		NSheet sheet = book.getSheetByName(dependent.getSheetName());
		if(sheet==null) return;
		String[] ids = dependent.getObjectIdPath();
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_DATA_VALIDATION_CONTENT_CHANGE,sheet,
				ModelEvents.createDataMap(ModelEvents.PARAM_OBJECT_ID,ids[0])));
	}

	private void handleCellRef(Ref notify) {
		NBook book = bookSeries.getBook(notify.getBookName());
		if(book==null) return;
		NSheet sheet = book.getSheetByName(notify.getSheetName());
		if(sheet==null) return;
		((AbstractBookAdv) book).sendModelEvent(ModelEvents.createModelEvent(ModelEvents.ON_CELL_CONTENT_CHANGE,sheet,
				new CellRegion(notify.getRow(),notify.getColumn(),notify.getLastRow(),notify.getLastColumn())));
	}
}
