//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Final Project
// Files: HashTableADT.java, HashTable.java, FarmSociety.java, FarmReport.java, 
//        Main.java, Report.java, Input.java, test.java, IllegalNullKeyException.java, 
//        KeyNotFoundException.java, executable files, screenshots, batch file, manifest.txt,
//        README, design.pdf, Annual_Report.txt
// Course: CS400 / Spring 2020
// Lecture Number: 002
// Description: Making a milk community database
//
// Team: Gautam Agarwal,Shreyans Saraogi, Yash Himmatramka, Pragyan Das, Nick Mathew
// Email: gagarwal8@wisc.edu
// Lecturer's Name: Professor Debra Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

//Various libraries imported
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import application.Input;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

/**
 * Main application which runs the GUI and calls other classes
 * 
 * @author yashhimmatramka, shreyanssaraogi, nickmathew
 *
 */
public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;
	private File selectedNewFarm;
	private FarmSociety fm;
	private Report fr;
	private ObservableList<FarmReport> FarmReportList, AnnualReportList, MonthlyReportList, DateRangeReportList;
	Alert alert = new Alert(AlertType.CONFIRMATION, "Input file contains errors. No data was added.", ButtonType.OK);
	Alert alert1 = new Alert(AlertType.CONFIRMATION, "Unable to find data. Make sure entries are valid.",
			ButtonType.OK);

	// setting the window width and height
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 250;
	private static final String APP_TITLE = "Milk Weights!";

	Stage currStage;
	Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8, scene9, scene10, scene11, scene12;
	TableView<FarmReport> farmReportTable, annualReportTable, monthlyReportTable, dateRangeReportTable;

	@Override
	@SuppressWarnings("unchecked")
	/**
	 * method which begins the GUI
	 */
	public void start(Stage primaryStage) throws Exception {
		// save args example
		currStage = primaryStage;
		args = this.getParameters().getRaw();
		fm = new FarmSociety();
		// Scene 1 start
		//
		//
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_CENTER);
		Label lbl = new Label("Welcome to Milk Farm\n" + "What would you like to do?");
		lbl.setFont(new Font("Arial", 30));

		hbox.getChildren().add(lbl);

		Button button = new Button("Add new data for new Farm");
		button.setOnAction(e -> currStage.setScene(scene2));

		Button bn = new Button("Add new data for an old farm");
		bn.setOnAction(e -> currStage.setScene(scene3));

		Button bn1 = new Button("View existing data");
		bn1.setOnAction(e -> currStage.setScene(scene4));

		HBox pane = new HBox();
		pane.setMargin(button, new Insets(0, 10, 0, 0)); // This is where you should be looking at.
		pane.setMargin(bn, new Insets(0, 10, 0, 0));
		pane.setPadding(new Insets(25));
		pane.getChildren().addAll(button, bn, bn1);

		BorderPane root = new BorderPane();

		root.setTop(hbox);
		root.setCenter(pane);
		//
		//
		// Scene 1 end

		// Scene 2 start
		//
		//
		HBox hbox1 = new HBox();
		hbox1.setAlignment(Pos.TOP_CENTER);
		Label lbl1 = new Label("Attach the data to be added for new farm\n" + "(The data should be in csv format)");
		lbl1.setFont(new Font("Arial", 30));

		hbox1.getChildren().add(lbl1);

		Button back = new Button("Back");
		back.setOnAction(e -> currStage.setScene(scene1));

		Button chooseFile = new Button("Choose File");
		FileChooser fileChooser = new FileChooser();
		chooseFile.setOnAction(e -> {
			selectedNewFarm = fileChooser.showOpenDialog(primaryStage);
			try {
				fm = Input.reading(selectedNewFarm, fm);
				currStage.setScene(scene1);
			} catch (NumberFormatException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (IllegalNullKeyException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (IOException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			}
		});

		BorderPane bp = new BorderPane();
		bp.setTop(hbox1);
		bp.setBottom(back);
		bp.setRight(chooseFile);
		//
		//
		// Scene 2 end

		// Scene 3 start
		//
		//
		HBox hbox2 = new HBox();
		hbox2.setAlignment(Pos.TOP_CENTER);
		Label lbl2 = new Label("Enter the FarmID and the data\n" + "(The data should be in csv format)");
		lbl2.setFont(new Font("Arial", 30));

		hbox2.getChildren().add(lbl2);

		Button back1 = new Button("Back");
		back1.setOnAction(e -> currStage.setScene(scene1));

		HBox hb100 = new HBox();
		Label lb100 = new Label("Farm ID:");
		TextField inp100 = new TextField();
		hb100.setMargin(lb100, new Insets(30, 10, 10, 10));
		hb100.setMargin(inp100, new Insets(30, 10, 10, 10));
		hb100.getChildren().addAll(lb100, inp100);

		HBox hb110 = new HBox();
		Label lb110 = new Label("Year:");
		TextField input110 = new TextField();
		hb110.setMargin(lb110, new Insets(0, 10, 10, 10));
		hb110.setMargin(input110, new Insets(0, 10, 10, 10));
		hb110.getChildren().addAll(lb110, input110);

		HBox hb120 = new HBox();
		Label lb120 = new Label("Month:");
		TextField inp120 = new TextField();
		hb120.setMargin(lb120, new Insets(30, 10, 10, 10));
		hb120.setMargin(inp120, new Insets(30, 10, 10, 10));
		hb120.getChildren().addAll(lb120, inp120);

		HBox hb130 = new HBox();
		Label lb130 = new Label("Day:");
		TextField input130 = new TextField();
		hb130.setMargin(lb130, new Insets(0, 10, 10, 10));
		hb130.setMargin(input130, new Insets(0, 10, 10, 10));
		hb130.getChildren().addAll(lb130, input130);

		HBox hb140 = new HBox();
		Label lb140 = new Label("Farm Data:");
		TextField input140 = new TextField();
		hb140.setMargin(lb140, new Insets(0, 10, 10, 10));
		hb140.setMargin(input130, new Insets(0, 10, 10, 10));
		hb140.getChildren().addAll(lb140, input140);

		Button sub = new Button("Submit");

		sub.setOnAction(e -> {

			try {
				fm = Input.inputOnlyOne(inp100.getText() + input110.getText(), input140.getText(), fm, inp120.getText(),
						input130.getText());
			} catch (NumberFormatException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (IllegalNullKeyException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				alert.showAndWait();
				e1.printStackTrace();
			}
		});

		VBox vbox100 = new VBox();
		vbox100.getChildren().setAll(hb100, hb110, hb120, hb130, hb140, sub);

		BorderPane bp1 = new BorderPane();
		bp1.setTop(hbox2);
		bp1.setBottom(back1);
		bp1.setLeft(vbox100);
		//
		//
		// Scene 3 end

		// Scene 4 start
		//
		//
		HBox hbox3 = new HBox();
		hbox3.setAlignment(Pos.TOP_CENTER);
		Label lbl3 = new Label("What type of report would you like to see?");
		lbl3.setFont(new Font("Arial", 30));

		hbox3.getChildren().add(lbl3);

		Button back2 = new Button("Back");
		back2.setOnAction(e -> currStage.setScene(scene1));

		Button farmReport = new Button("Farm Report");
		farmReport.setOnAction(e -> currStage.setScene(scene5));
		Button annualReport = new Button("Annual Report");
		annualReport.setOnAction(e -> currStage.setScene(scene6));
		HBox pane1 = new HBox();
		pane1.setMargin(farmReport, new Insets(25, 100, 0, 75)); // This is where you should be looking at.
		pane1.setMargin(annualReport, new Insets(25, 0, 0, 0));
		// pane1.setPadding(new Insets(25));
		pane1.getChildren().addAll(farmReport, annualReport);

		Button drReport = new Button("Date Range Report");
		drReport.setOnAction(e -> currStage.setScene(scene7));
		Button monthlyReport = new Button("Monthly Report");
		monthlyReport.setOnAction(e -> currStage.setScene(scene8));
		HBox pane2 = new HBox();
		pane2.setMargin(drReport, new Insets(25, 100, 0, 75)); // This is where you should be looking at.
		pane1.setMargin(monthlyReport, new Insets(25, 0, 0, 0));
		// pane2.setPadding(new Insets(25));
		pane2.getChildren().addAll(drReport, monthlyReport);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(pane1, pane2);

		BorderPane bp2 = new BorderPane();
		bp2.setTop(hbox3);
		bp2.setBottom(back2);
		bp2.setCenter(vbox);
		//
		//
		// Scene 4 end

		// Scene 5 start, Farm Report
		//
		//
		////////////////////////// Set up GUI screen
		// elements///////////////////////////////
		HBox hbox4 = new HBox();
		hbox4.setAlignment(Pos.TOP_CENTER);
		Label lbl4 = new Label("Please enter the FarmID and the year");
		lbl4.setFont(new Font("Arial", 30));
		hbox4.getChildren().add(lbl4);

		HBox hb = new HBox();
		Label lb = new Label("Farm ID Number:");
		TextField inp = new TextField();
		hb.setMargin(lb, new Insets(30, 10, 10, 10));
		hb.setMargin(inp, new Insets(30, 10, 10, 10));
		hb.getChildren().addAll(lb, inp);

		HBox hb1 = new HBox();
		Label lb1 = new Label("Year:");
		TextField input1 = new TextField();
		hb1.setMargin(lb1, new Insets(0, 10, 10, 10));
		hb1.setMargin(input1, new Insets(0, 10, 10, 10));
		hb1.getChildren().addAll(lb1, input1);
		///////////////////////////////////////////////////////////////////////////////////

		// declare labels
		Label lblFarmReport = new Label();
		Label yearInput = new Label();
		Label farmIDInput = new Label();
		HBox hb2 = new HBox();

		// set up columns that govern format of
		// table////////////////////////////////////////
		TableColumn<FarmReport, String> month = new TableColumn<>("Month");
		month.setMinWidth(100);
		month.setCellValueFactory(new PropertyValueFactory<>("month"));

		TableColumn<FarmReport, Integer> weight = new TableColumn<>("Milk Weight");
		weight.setMinWidth(100);
		weight.setCellValueFactory(new PropertyValueFactory<>("monthTotal"));

		TableColumn<FarmReport, Double> percent = new TableColumn<>("Percent of Total");
		percent.setMinWidth(100);
		percent.setCellValueFactory(new PropertyValueFactory<>("percentOfMonthlyTotal"));
		////////////////////////////////////////////////////////////////////////////////////

		farmReportTable = new TableView<>();
		fr = new Report();

		// initialize the table with something that is not
		// null//////////////////////////////
		try {
			FarmReportList = fr.getFarmReports("Farm 1", "2019", fm);
		} catch (IllegalNullKeyException i) {
		} catch (KeyNotFoundException k) {
		}
		farmReportTable.setItems(FarmReportList);
		farmReportTable.getColumns().addAll(month, weight, percent);
		////////////////////////////////////////////////////////////////////////////////////

		/// on submit
		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			lblFarmReport.setText(input1.getText() + " data for Farm #" + inp.getText()); // set the title for the
																							// report
			// save the inputs
			yearInput.setText(input1.getText());
			farmIDInput.setText(inp.getText());
			// update the report
			try {
				FarmReportList = fr.getFarmReports("Farm " + farmIDInput.getText().strip(), yearInput.getText().strip(),
						fm); // get the user input
				farmReportTable.setItems(FarmReportList);
				currStage.setScene(scene9);// go to the report scene
			} catch (IllegalNullKeyException i) {
				alert1.showAndWait();
			} catch (KeyNotFoundException k) {
				alert1.showAndWait();
			}
		});

		///// finish GUI setup///////////////////////////////////////
		hb2.setMargin(submit, new Insets(10, 10, 10, 250));
		hb2.getChildren().addAll(submit);

		VBox vb = new VBox();
		// vb.setMargin(hb, new Insets(10, 10, 10, 10));
		vb.getChildren().addAll(hb, hb1, hb2);

		Button back3 = new Button("Back");
		back3.setOnAction(e -> currStage.setScene(scene4));

		BorderPane bp3 = new BorderPane();
		bp3.setTop(hbox4);
		bp3.setBottom(back3);
		bp3.setCenter(vb);
		/////////////////////////////////////////////////////////////

		//
		//
		// Scene 5 end

		// Farm Report working scene 9
		//
		//
		// set up the Table
		VBox vbFarmReport = new VBox();
		vbFarmReport.getChildren().addAll(farmReportTable);

		BorderPane bpFarmReport = new BorderPane();

		Button backFarmReport = new Button("Back");
		backFarmReport.setOnAction(e -> currStage.setScene(scene5));

		Button printFarmReport = new Button("Print");
		printFarmReport.setOnAction(e -> Input.outputTxt(FarmReportList, "FarmReport"));

		lblFarmReport.setFont(new Font("Arial", 30));

		bpFarmReport.setTop(lblFarmReport);
		bpFarmReport.setCenter(vbFarmReport);
		bpFarmReport.setBottom(backFarmReport);
		bpFarmReport.setRight(printFarmReport);
		//
		//
		// farm report working end

		// Scene 6 start, Annual report
		//
		// Setup follows same structure as Farm Report
		HBox hbox5 = new HBox();
		hbox5.setAlignment(Pos.TOP_CENTER);
		Label lbl5 = new Label("Please enter the year");
		lbl5.setFont(new Font("Arial", 30));
		hbox5.getChildren().add(lbl5);

		HBox hb3 = new HBox();
		Label lb3 = new Label("Year:");
		TextField input3 = new TextField();
		hb3.setMargin(lb3, new Insets(30, 10, 10, 10));
		hb3.setMargin(input3, new Insets(30, 10, 10, 10));
		hb3.getChildren().addAll(lb3, input3);

		Label lblAnnualReport = new Label();
		HBox hb4 = new HBox();
		Button submit1 = new Button("Submit");
		hb4.setMargin(submit1, new Insets(10, 10, 10, 250));

		final TableColumn<FarmReport, String> farmID = new TableColumn<>("Farm ID");
		farmID.setMinWidth(100);
		farmID.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		TableColumn<FarmReport, Integer> weightAnnual = new TableColumn<>("Milk Weight");
		weightAnnual.setMinWidth(100);
		weightAnnual.setCellValueFactory(new PropertyValueFactory<>("yearTotal"));

		TableColumn<FarmReport, Double> percentAnnual = new TableColumn<>("Percent of Year Total");
		percentAnnual.setMinWidth(100);
		percentAnnual.setCellValueFactory(new PropertyValueFactory<>("percentOfYearlyTotal"));

		annualReportTable = new TableView<>();

		// initialize table
		try {
			AnnualReportList = Report.getAnnualReports(input3.getText(), fm);
		} catch (IllegalNullKeyException i) {
		} catch (KeyNotFoundException k) {
		}
		annualReportTable.setItems(AnnualReportList);
		annualReportTable.getColumns().addAll(farmID, weightAnnual, percentAnnual);

		// on submit
		hb4.getChildren().addAll(submit1);
		submit1.setOnAction(e -> {
			lblAnnualReport.setText("Annual Data for " + input3.getText());
			yearInput.setText(input3.getText());// get inputs
			try { // update table
				AnnualReportList = Report.getAnnualReports(yearInput.getText().strip(), fm);
				annualReportTable.setItems(AnnualReportList);
				currStage.setScene(scene10);
			} catch (IllegalNullKeyException i) {
				alert1.showAndWait();
			} catch (KeyNotFoundException k) {
				alert1.showAndWait();
			}

		});

		VBox vb1 = new VBox();
		vb1.setMargin(hb3, new Insets(10, 10, 10, 10));
		vb1.getChildren().addAll(hb3, hb4);

		Button back4 = new Button("Back");
		back4.setOnAction(e -> currStage.setScene(scene4));

		BorderPane bp4 = new BorderPane();
		bp4.setTop(hbox5);
		bp4.setBottom(back4);
		bp4.setCenter(vb1);

		//
		//
		// Scene 6 end

		// Annual Report working
		//
		//
		// set up the Table

		VBox vbAnnualReport = new VBox();
		vbAnnualReport.getChildren().addAll(annualReportTable);

		BorderPane bpAnnualReport = new BorderPane();

		Button backAnnualReport = new Button("Back");
		backAnnualReport.setOnAction(e -> currStage.setScene(scene6));

		Button printAnnualReport = new Button("Print");
		printAnnualReport.setOnAction(e -> Input.outputTxt(AnnualReportList, "Annual_Report"));

		lblAnnualReport.setFont(new Font("Arial", 30));

		bpAnnualReport.setTop(lblAnnualReport);
		bpAnnualReport.setCenter(vbAnnualReport);
		bpAnnualReport.setBottom(backAnnualReport);
		bpAnnualReport.setRight(printAnnualReport);
		//
		//
		// Annual report working end

		// Scene 7 start, Date Range Report
		//
		// Setup follows same structure as Farm Report
		HBox hbox6 = new HBox();
		hbox6.setAlignment(Pos.TOP_CENTER);
		Label lbl6 = new Label("Please enter the start and the end date\n" + "(Format : YYYY-MM-DD)");
		lbl6.setFont(new Font("Arial", 30));
		hbox6.getChildren().add(lbl6);

		HBox hb5 = new HBox();
		Label lb5 = new Label("Start Date:");
		TextField input5 = new TextField();
		hb5.setMargin(lb5, new Insets(30, 10, 10, 10));
		hb5.setMargin(input5, new Insets(30, 10, 10, 10));
		hb5.getChildren().addAll(lb5, input5);

		HBox hb6 = new HBox();
		Label lb6 = new Label("End Date:");
		TextField input6 = new TextField();
		hb6.setMargin(lb6, new Insets(0, 10, 10, 10));
		hb6.setMargin(input6, new Insets(0, 10, 10, 10));
		hb6.getChildren().addAll(lb6, input6);

		Label lblDateRangeReport = new Label();
		Label startDateInput = new Label();
		Label endDateInput = new Label();
		HBox hb7 = new HBox();
		Button submit2 = new Button("Submit");
		hb7.setMargin(submit2, new Insets(10, 10, 10, 250));

		final TableColumn<FarmReport, String> farmID1 = new TableColumn<>("Farm ID");
		farmID1.setMinWidth(100);
		farmID1.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		TableColumn<FarmReport, Integer> weightDateRange = new TableColumn<>("Milk Weight");
		weightDateRange.setMinWidth(100);
		weightDateRange.setCellValueFactory(new PropertyValueFactory<>("dateRangeTotal"));

		TableColumn<FarmReport, Double> percentDateRange = new TableColumn<>("Percent of Date Range Total");
		percentDateRange.setMinWidth(100);
		percentDateRange.setCellValueFactory(new PropertyValueFactory<>("percentOfDateRangeTotal"));

		dateRangeReportTable = new TableView<>();
		fr = new Report();

		// initialize table
		try {
			DateRangeReportList = fr.getDateRangeReports("01012019", "01012020", fm);
		} catch (IllegalNullKeyException i) {
		} catch (KeyNotFoundException k) {
		}
		dateRangeReportTable.setItems(DateRangeReportList);
		dateRangeReportTable.getColumns().addAll(farmID1, weightDateRange, percentDateRange);

		// on submit
		hb7.getChildren().addAll(submit2);
		submit2.setOnAction(e -> {
			lblDateRangeReport.setText("Data from: " + input5.getText() + " to " + input6.getText());
			startDateInput.setText(input5.getText());
			endDateInput.setText(input6.getText());
			try { // update report
				String[] startDate = startDateInput.getText().strip().split("-"); // grab and split strings
				String[] endDate = endDateInput.getText().strip().split("-");
				DateRangeReportList = fr.getDateRangeReports(startDate[2] + startDate[1] + startDate[0],
						endDate[2] + endDate[1] + endDate[0], fm); // enter strings in correct format for back end
				dateRangeReportTable.setItems(DateRangeReportList);
				currStage.setScene(scene11);
			} catch (IllegalNullKeyException i) {
				alert1.showAndWait();
			} catch (KeyNotFoundException k) {
				alert1.showAndWait();
			}
		});

		VBox vb2 = new VBox();
		// vb2.setMargin(hb5, new Insets(10, 10, 10, 10));
		vb2.getChildren().addAll(hb5, hb6, hb7);

		Button back5 = new Button("Back");
		back5.setOnAction(e -> currStage.setScene(scene4));

		BorderPane bp5 = new BorderPane();
		bp5.setTop(hbox6);
		bp5.setBottom(back5);
		bp5.setCenter(vb2);
		//
		//
		// Scene 7 end

		// Date-Range Report working
		//
		//
		// set up the Table

		VBox vbDateRangeReport = new VBox();
		vbDateRangeReport.getChildren().addAll(dateRangeReportTable);

		BorderPane bpDateRangeReport = new BorderPane();

		Button backDateRangeReport = new Button("Back");
		backDateRangeReport.setOnAction(e -> currStage.setScene(scene7));

		Button printDateRangeReport = new Button("Print");
		printDateRangeReport.setOnAction(e -> Input.outputTxt(DateRangeReportList, "DateRangeReportList"));

		lblDateRangeReport.setFont(new Font("Arial", 30));

		bpDateRangeReport.setTop(lblDateRangeReport);
		bpDateRangeReport.setCenter(vbDateRangeReport);
		bpDateRangeReport.setBottom(backDateRangeReport);
		bpDateRangeReport.setRight(printDateRangeReport);
		//
		//
		// Date-Range report working end

		// Scene 8 start, Monthly Report
		//
		// Setup follows same structure as Farm Report
		HBox hbox7 = new HBox();
		hbox7.setAlignment(Pos.TOP_CENTER);
		Label lbl7 = new Label("Please enter the month name and the year");
		lbl7.setFont(new Font("Arial", 30));
		hbox7.getChildren().add(lbl7);

		HBox hb8 = new HBox();
		Label lb8 = new Label("Month:");
		TextField input8 = new TextField();
		hb8.setMargin(lb8, new Insets(30, 10, 10, 10));
		hb8.setMargin(input8, new Insets(30, 10, 10, 10));
		hb8.getChildren().addAll(lb8, input8);

		HBox hb9 = new HBox();
		Label lb9 = new Label("Year:");
		TextField input9 = new TextField();
		hb9.setMargin(lb9, new Insets(0, 10, 10, 10));
		hb9.setMargin(input9, new Insets(0, 10, 10, 10));
		hb9.getChildren().addAll(lb9, input9);

		Label lblMonthlyReport = new Label();
		Label monthInput = new Label();
		HBox hb10 = new HBox();
		Button submit3 = new Button("Submit");
		hb10.setMargin(submit3, new Insets(10, 10, 10, 250));

		final TableColumn<FarmReport, String> farmID3 = new TableColumn<>("Farm ID");
		farmID3.setMinWidth(100);
		farmID3.setCellValueFactory(new PropertyValueFactory<>("farmID"));

		TableColumn<FarmReport, Integer> weightMonthly = new TableColumn<>("Milk Weight");
		weightMonthly.setMinWidth(100);
		weightMonthly.setCellValueFactory(new PropertyValueFactory<>("monthTotal"));
		// column
		TableColumn<FarmReport, Double> percentMonthly = new TableColumn<>("Percent of Monthly Total");
		percentMonthly.setMinWidth(100);
		percentMonthly.setCellValueFactory(new PropertyValueFactory<>("percentOfMonthlyTotal"));

		monthlyReportTable = new TableView<>();

		// initialize table
		try {
			MonthlyReportList = fr.getMonthlyReports(input8.getText(), input9.getText(), fm);
		} catch (IllegalNullKeyException i) {
		} catch (KeyNotFoundException k) {
		} catch (Exception d) {
		}
		monthlyReportTable.setItems(MonthlyReportList);
		monthlyReportTable.getColumns().addAll(farmID, weightMonthly, percentMonthly);

		// on submit
		hb10.getChildren().addAll(submit3);
		submit3.setOnAction(e -> {
			lblMonthlyReport.setText("Data for " + input8.getText() + " " + input9.getText());
			// get inputs
			yearInput.setText(input9.getText());
			monthInput.setText(input8.getText());
			try { // update table
				MonthlyReportList = fr.getMonthlyReports(monthInput.getText().strip(), yearInput.getText().strip(), fm);
				monthlyReportTable.setItems(MonthlyReportList);
				currStage.setScene(scene12);
			} catch (IllegalNullKeyException i) {
				alert1.showAndWait();
			} catch (KeyNotFoundException k) {
				alert1.showAndWait();
			} catch (Exception d) {
				alert1.showAndWait();
			}

		});

		VBox vb3 = new VBox();
		// vb3.setMargin(hb8, new Insets(10, 10, 10, 10));
		vb3.getChildren().addAll(hb8, hb9, hb10);

		Button back6 = new Button("Back");
		back6.setOnAction(e -> currStage.setScene(scene4));

		BorderPane bp6 = new BorderPane();
		bp6.setTop(hbox7);
		bp6.setBottom(back6);
		bp6.setCenter(vb3);
		//
		//
		// Scene 8 end

		// Monthly Report scene 12
		//
		//
		// set up the Table

		// make a vertical box - better for displaying data - and add the table to it
		VBox vbMonthlyReport = new VBox();
		vbMonthlyReport.getChildren().addAll(monthlyReportTable);

		BorderPane bpMonthlyReport = new BorderPane();

		Button backMonthlyReport = new Button("Back");
		backMonthlyReport.setOnAction(e -> currStage.setScene(scene8));

		//////////////////////////////// add function to print in the action block
		//////////////////////////////// here/////////////////////////////////////////////////
		Button printMonthlyReport = new Button("Print");// MonthlyReportList
		backFarmReport.setOnAction(e -> currStage.setScene(scene5));
		printMonthlyReport.setOnAction(e -> Input.outputTxt(MonthlyReportList, "Monthly_Report"));
		/////////////////////////////////////////////////////////////////////////////////

		lblMonthlyReport.setFont(new Font("Arial", 30));

		bpMonthlyReport.setTop(lblMonthlyReport);
		bpMonthlyReport.setCenter(vbMonthlyReport);
		bpMonthlyReport.setBottom(backMonthlyReport);
		bpMonthlyReport.setRight(printMonthlyReport);
		//
		//
		// monthly report end

		// set up all scenes
		scene1 = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene2 = new Scene(bp, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene3 = new Scene(bp1, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene4 = new Scene(bp2, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene5 = new Scene(bp3, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene6 = new Scene(bp4, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene7 = new Scene(bp5, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene8 = new Scene(bp6, WINDOW_WIDTH, WINDOW_HEIGHT);
		// For reports, do not set the size of the window as reports
		// can have varying sizes. It automatically adjusts as is.
		scene9 = new Scene(bpFarmReport);
		scene10 = new Scene(bpAnnualReport);
		scene11 = new Scene(bpDateRangeReport);
		scene12 = new Scene(bpMonthlyReport);
		// Add the stuff and set the primary stage
		currStage.setTitle(APP_TITLE);
		currStage.setScene(scene1);

		currStage.show();
	}


	/**
	 * main method which starts the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	// Include fm!=null

}