package patientRecords;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainView.MainViewScene;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class PatientRecordsScene {
	// component/node variables
	private Stage stage;
	private Scene scene;
	public TableView<Patients> tableView = new TableView();
	private ObservableList<Patients> selectedPatients;
	public BorderPane content = new BorderPane();

	// Colors and styling
	private String background = CLINIC_WHITE;
	private String foreground = BLACK_BLIGHT;
	private String accent = POVIDONE_ORANGE;
	private String btnBackground = CLASSIC_SCRUB_BLUE;
	private Color btnForeground = Color.rgb(249, 246, 246);
	private Color txtForeground = Color.rgb(11, 10, 9);

	// Colors and Styling CONSTANTS
	public static final Font MAIN_FONT_HEADING = Font.loadFont("file:src/fonts/segoeui.ttf", 20);
	public static final Font MAIN_FONT_BODY = Font.loadFont("file:src/fonts/segoeui.ttf", 16);
	public static final Font MAIN_FONT_BUTTONS = Font.loadFont("file:src/fonts/segoeui.ttf", 12);
	public static final String CLINIC_WHITE = "-fx-background-color: rgb(249,246,246)";
	public static final String BLACK_BLIGHT = "-fx-background-color: rgb(11,10,9)";
	public static final String MANSFIELD_GREY = "-fx-background-color: rgb(211,211,211)";
	public static final String CLASSIC_SCRUB_BLUE = "-fx-background-color: rgb(35,91,170)";
	public static final String PALLIATIVE_RED = "-fx-background-color: rgb(208,38,34)";
	public static final String POVIDONE_ORANGE = "-fx-background-color: rgb(246,168,0)";
	public static final String SICKLY_CYAN = "-fx-background-color: rgb(0,200,215)";
	public static final String BLUE_CONTENT_CLR = "-fx-background-color: rgb(112,189,243)";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PatientRecordsScene(Stage stage, double sizeX, double sizeY) {
		this.stage = stage;
		//When opened the stage will be maximized
		stage.setMaximized(true);

		// Contents
		BorderPane btnArea = new BorderPane();
		BorderPane headerArea = new BorderPane();
		VBox vbox = new VBox();
		tableView.setEditable(false);
		Button backBtn = new Button("Back");
		Label databaseLabel = new Label("Patient Database");
		databaseLabel.setFont(MAIN_FONT_HEADING);
		TextArea textArea = new TextArea();
		textArea.setMaxSize(1000, 30);
		textArea.setMinSize(190, 30);
		textArea.setFont(MAIN_FONT_BODY);
		textArea.setPromptText("Please enter the patient name here");

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
		content.setStyle("-fx-background-color: rgb(249,246,246)");
		btnArea.setPadding(new Insets(10, 10, 10, 10));
		headerArea.setPadding(new Insets(20, 10, 10, 20));
		tableView.setPadding(new Insets(20, 20, 20, 20));
		tableView.prefHeightProperty().bind(stage.heightProperty());
		tableView.prefWidthProperty().bind(stage.widthProperty());

		//ObservableList to hold all patients
		final ObservableList<Patients> patient = FXCollections.observableArrayList(
				new Patients("Blair, Amelia", "32", "F", "159", "60", "13/6/2005", "N/A", "0400 000 000",
						"128 Bundaberg Road, Semaphore"),
				new Patients("Cage, David", "50", "M", "165", "80", "25/3/2016", "N/A", "0422 000 000",
						"5 Second Street, Morgan"),
				new Patients("Doe, James", "70", "M", "140", "50", "13/6/1967", "N/A", "0445 050 555",
						"129 Sundenberg Drive, Hemisphere"),
				new Patients("Dechart, Bryan", "33", "M", "170", "70", "19/8/2014", "N/A", "0410 101 010",
						"128 Bundaberg Road, Semaphore"),
				new Patients("Gavin, Klavier", "26", "M", "180", "75", "11/9/2014", "N/A", "0499 999 999",
						"28 Kalimna Road, Nuriootpa"),
				new Patients("Parke, Evan", "35", "M", "160", "65", "18/8/2019", "16/7/2020", "0488 888 888",
						"50 Holden Way, Elizabeth"),
				new Patients("Smith, Cornelius", "18", "M", "155", "50", "21/5/2015", "N/A", "0477 777 777",
						"102 Red Creek Road, Murray Bridge"),
				new Patients("Williams Abby", "20", "F", "130", "35", "31/1/2001", "N/A", "0401 111 111",
						"50 Tanner Street, Ebenezer"),
				new Patients("Williams, Connor", "28", "M", "182", "80", "27/5/2009", "N/A", "0421 012 012",
						"24 Dechart Avenue, Semaphore"),
				new Patients("Williams, Edward", "80", "M", "168", "102", "28/2/2020", "24/4/2020", "0421 421 421",
						"55 Henry Moss Court, Robertstown"),
				new Patients("Williams, Gloria", "60", "F", "150", "105", "4/11/1995", "N/A", "0485 630 809",
						"64 Marloo Street, Salisbury"),
				new Patients("Williams, Hank", "51", "M", "146", "85", "20/4/2020", "N/A", "0426 851 201",
						"56 Clancey Street, Sedan"),
				new Patients("Williams, Jesse", "38", "M", "168", "74", "17/7/2018", "31/7/2020", "0455 555 555",
						"1000 Old Town Road, Towita"),
				//Dummy data to make tableView fill up the center of content
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "),
				new Patients(" ", " ", " ", " ", " ", " ", " ", " ", " "));

		//Setting the label, size and data for tableView
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
		TableColumn<Patients, String> doLVCol = new TableColumn<Patients, String>("Date of Last Visit");
		doLVCol.setMinWidth(100);
		doLVCol.setPrefWidth(120);
		doLVCol.setMaxWidth(130);
		doLVCol.setCellValueFactory(new PropertyValueFactory("doLV"));
		TableColumn<Patients, String> doNVCol = new TableColumn<Patients, String>("Date of Next Visit");
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

		//Adding all required components to tableView
		tableView.setItems(patient);
		tableView.getColumns().addAll(fullNameCol, ageCol, genderCol, heightCol, weightCol, doLVCol, doNVCol, numberCol,
				addressCol);
		vbox.getChildren().addAll(databaseLabel, tableView);

		//Layout
		content.setCenter(vbox);
		content.setTop(headerArea);
		headerArea.setLeft(databaseLabel);
		headerArea.setCenter(textArea);
		content.setBottom(btnArea);
		btnArea.setLeft(backBtn);

		// Search function
		FilteredList<Patients> filteredData = new FilteredList<>(patient, p -> true);

		textArea.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(patients -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (patients.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}

				return false;
			});
		});
		SortedList<Patients> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		tableView.setItems(sortedData);

		//Selection Model
		TableViewSelectionModel<Patients> selectionModel = tableView.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		selectedPatients = selectionModel.getSelectedItems();

		selectedPatients.addListener(new ListChangeListener<Patients>() {
			@Override
			public void onChanged(Change<? extends Patients> change) {
				
				PatientDetails pD = new PatientDetails(stage, sizeX, sizeY);
				
				content.translateXProperty().set(scene.getHeight());
				content.getChildren().add(pD.content);
				
				Timeline timeLine = new Timeline();
				KeyValue kv = new KeyValue(pD.content.translateXProperty(), 0, Interpolator.EASE_IN);
				KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
				timeLine.getKeyFrames().add(kf);
				timeLine.setOnFinished(t -> {
					content.setVisible(false);
				});
				
				timeLine.play();
				
				pD.content.setVisible(true);
				
				scene = new PatientRecordsScene(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
				
				//Set new scene
				scene = new PatientDetails(stage, scene.getWidth(), scene.getHeight()).getScene();
				stage.setScene(scene);
			}
		});

		// SCENE!!!
		scene = new Scene(content, sizeX, sizeY);

	}

	public Scene getScene() {
		return this.scene;
	}

	public void randomMethod() {

	}
}
