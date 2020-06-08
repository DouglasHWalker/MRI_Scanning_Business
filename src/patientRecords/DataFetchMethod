package patientRecords;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class DataFetchMethods {
	       
	class MyIntegerTableCell extends TableCell<Patients, Integer> {
		public void updateItem(Integer item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
            setGraphic(null);
		}
		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
	
	class MyStringTableCell extends TableCell<Patients, String> {
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}
		
		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}
	
	class MyEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			TableCell c = (TableCell) e.getSource();
			int index = c.getIndex();
			System.out.println("");
		}
	}
}
