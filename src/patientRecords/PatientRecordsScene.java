package patientRecords;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import mainView.MainViewScene;
import javafx.beans.property.SimpleStringProperty;

public class PatientRecordsScene {
	// component/node variables
	private Stage stage;
	private Scene scene;
	public TableView tableView  = new TableView();
	Button button1 = new Button("Info");
	Button button2 = new Button("Info");
	Button button3 = new Button("Info");
	Button button4 = new Button("Info");
	Button button5 = new Button("Info");
	Button button6 = new Button("Info");
	Button button7 = new Button("Info");
	Button button8 = new Button("Info");
	Button button9 = new Button("Info");
	Button button10 = new Button("Info");
	Button button11 = new Button("Info");
	Button button12 = new Button("Info");
	Button button13 = new Button("Info");

	public PatientRecordsScene(Stage stage, double sizeX, double sizeY) {
		this.stage = stage;

		// Contents
		BorderPane content = new BorderPane();
		BorderPane btnArea = new BorderPane();
		BorderPane headerArea = new BorderPane();
		VBox vbox = new VBox();
		tableView.setEditable(false);
		TableCell cell = new TableCell();
		Button backBtn = new Button("Back");
		Label databaseLabel = new Label ("Patient Database");
		databaseLabel.setFont(MainViewScene.MAIN_FONT_HEADING);
		TextArea textArea = new TextArea();
		textArea.setMaxSize(1000, 30);
		textArea.setMinSize(190, 30);
		
		// Create button click handler
		EventHandler<ActionEvent> backBtnClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				scene = new MainViewScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		};
		// add handler to button
		backBtn.setOnAction(backBtnClick);

		// content layout and styles
		content.setStyle("-fx-background-color: rgb(102,179,233)");
		btnArea.setPadding(new Insets(10, 10, 10, 10));
		headerArea.setPadding(new Insets(20, 10, 10, 20));
		tableView.setPadding(new Insets(20, 20, 20, 20));
		
		final ObservableList<Patients> patient = FXCollections.observableArrayList (
				new Patients("Blair, Amelia", "32", "F", "159", "60", "13/6/2005", "N/A", "0400 000 000", "128 Bundaberg Road, Semaphore"),
				new Patients("Cage, David", "50", "M", "165", "80", "25/3/2016", "N/A", "0422 000 000", "5 Second Street, Morgan"),
				new Patients("Doe, James", "70", "M", "140", "50", "13/6/1967", "N/A", "0445 050 555", "129 Sundenberg Drive, Hemisphere"),
				new Patients("Dechart, Bryan", "33", "M", "170", "70", "19/8/2014", "N/A", "0410 101 010", "128 Bundaberg Road, Semaphore"),
				new Patients("Gavin, Klavier", "26", "M", "180", "75", "11/9/2014", "N/A", "0499 999 999", "28 Kalimna Road, Nuriootpa"),
				new Patients("Parke, Evan", "35", "M", "160", "65", "18/8/2019", "16/7/2020", "0488 888 888", "50 Holden Way, Elizabeth"),
				new Patients("Smith, Cornelius", "18", "M", "155", "50", "21/5/2015", "N/A", "0477 777 777", "102 Red Creek Road, Murray Bridge"),
				new Patients("Williams Abby", "20", "F", "130", "35", "31/1/2001", "N/A", "0401 111 111", "50 Tanner Street, Ebenezer"),
				new Patients("Williams, Connor", "28", "M", "182", "80", "27/5/2009", "N/A", "0421 012 012", "24 Dechart Avenue, Semaphore"),
				new Patients("Williams, Edward", "80", "M", "168", "102", "28/2/2020", "24/4/2020", "0421 421 421", "55 Henry Moss Court, Robertstown"),
				new Patients("Williams, Gloria", "60", "F", "150", "105", "4/11/1995", "N/A", "0485 630 809", "64 Marloo Street, Salisbury"),
				new Patients("Williams, Hank", "51", "M", "146", "85", "20/4/2020", "N/A", "0426 851 201", "56 Clancey Street, Sedan"),
				new Patients("Williams, Jesse", "38", "M", "168", "74", "17/7/2018", "31/7/2020", "0455 555 555", "1000 Old Town Road, Towita")
		);
		
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Patients, String> fullNameCol = new TableColumn<Patients, String>("Full Name");
		fullNameCol.setMinWidth(80);
		fullNameCol.setPrefWidth(150);
		fullNameCol.setMaxWidth(200);
		fullNameCol.setCellValueFactory(new PropertyValueFactory("fullName"));
		TableColumn<Patients, String> ageCol = new TableColumn<Patients, String>("Age");
		ageCol.setMinWidth(40);
		ageCol.setPrefWidth(60);
		ageCol.setMaxWidth(80);
		ageCol.setCellValueFactory(new PropertyValueFactory("age"));
		TableColumn<Patients, String> genderCol = new TableColumn<Patients, String>("Gender");
		genderCol.setMinWidth(50);
		genderCol.setPrefWidth(70);
		genderCol.setMaxWidth(90);
		genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
		TableColumn<Patients, String> heightCol = new TableColumn<Patients, String>("Height");
		heightCol.setMinWidth(50);
		heightCol.setPrefWidth(70);
		heightCol.setMaxWidth(90);
		heightCol.setCellValueFactory(new PropertyValueFactory("height"));
		TableColumn<Patients, String> weightCol = new TableColumn<Patients, String>("Weight");
		weightCol.setMinWidth(50);
		weightCol.setPrefWidth(70);
		weightCol.setMaxWidth(90);
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		TableColumn<Patients, String> doLVCol = new TableColumn<Patients, String> ("Date of Last Visit");
		doLVCol.setMinWidth(100);
		doLVCol.setPrefWidth(120);
		doLVCol.setMaxWidth(130);
		doLVCol.setCellValueFactory(new PropertyValueFactory("doLV"));
		TableColumn<Patients, String> doNVCol = new TableColumn<Patients, String> ("Date of Next Visit");
		doNVCol.setMinWidth(100);
		doNVCol.setPrefWidth(120);
		doNVCol.setMaxWidth(130);
		doNVCol.setCellValueFactory(new PropertyValueFactory("doNV"));
		TableColumn<Patients, String> numberCol = new TableColumn<Patients, String>("Phone Number");
		numberCol.setMinWidth(90);
		numberCol.setPrefWidth(100);
		numberCol.setMaxWidth(120);
		numberCol.setCellValueFactory(new PropertyValueFactory("phone"));
		TableColumn<Patients, String> addressCol = new TableColumn<Patients, String>("Address");
		addressCol.setMinWidth(150);
		addressCol.setPrefWidth(220);
		addressCol.setMaxWidth(400);
		addressCol.setCellValueFactory(new PropertyValueFactory("address"));
		
		/*for(int i = 0; i < 13; ++i) {
			button[i] = new Button();
			button[i].setOnAction(this::handleButtonAction);
		}*/
		
		addButton();
		tableView.setItems(patient);
		tableView.getColumns().addAll(fullNameCol, ageCol, genderCol, heightCol, weightCol, doLVCol, doNVCol, numberCol, addressCol);
		vbox.getChildren().addAll(databaseLabel, tableView);
		
		content.setCenter(vbox);
		content.setTop(headerArea);
		headerArea.setLeft(databaseLabel);
		headerArea.setCenter(textArea);
		content.setBottom(btnArea);
		btnArea.setLeft(backBtn);
		
		// SCENE!!!
		scene = new Scene(content, sizeX, sizeY);
		
	}
	
	private void handleButtonAction(ActionEvent e) {
		if(e.getSource() == button13) {
			scene = new PatientDetails(stage, scene.getWidth(), scene.getHeight()).getScene();
			stage.setScene(scene);
		}
	}
	
	public void addButton() {
		
		TableColumn<Patients, String> btnCol = new TableColumn<Patients, String>("Info");
    	btnCol.setMinWidth(50);
    	btnCol.setPrefWidth(70);
    	btnCol.setMaxWidth(100);
    	
    	Callback<TableColumn<Patients, String>, TableCell<Patients, String>> cellFactory = new Callback<TableColumn<Patients, String>, TableCell<Patients, String>>() {
            @Override
            public TableCell<Patients, String> call(final TableColumn<Patients, String> param) {
                final TableCell<Patients, String> cell = new TableCell<Patients, String>() {

                    private final Button btn = new Button("Info");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Patients data = getTableView().getItems().get(getIndex());
                            if(data.get(getIndex()) == 13) {
                            	
                            }
                        });
                    }

                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        tableView.getColumns().add(btnCol);
        btnCol.setCellFactory(cellFactory);
	}
	public Scene getScene() {
		return this.scene;
	}
}
