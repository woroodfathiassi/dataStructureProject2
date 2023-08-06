package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	String backgroundImage = "C:\\Users\\Admin\\Desktop\\comp-242(DataStructures)\\secondProject\\src\\img\\Mahindra-XUV700-Price-List.jpg";
	String iconImage = "C:\\\\Users\\\\Admin\\\\Desktop\\\\comp-242(DataStructures)\\\\secondProject\\\\src\\img\\carr.jpg";
	String style = "-fx-background-color:#13678A";
	String carsFile = "cars.txt";
	String ordersFile = "orders.txt";

	String brabra;
	String modmod;
	String yeayea;
	String colcol;
	String pripri;

	Label brandLable = new Label();

	File Cfile = new File(carsFile);
	File Ofile = new File(ordersFile);

	DLL myDLL = new DLL();
	DLL cars = new DLL();
	SLL mySLL = new SLL();
	SLL orders = new SLL();

	ordSLL queue = new ordSLL();
	ordSLL stack = new ordSLL();

	Node node;
	Node curr;
	Node1 node1;
	ordNode nodorder;
	ordNode nnn;

	ObservableList<String> brandsName = FXCollections.observableArrayList();
	ComboBox<String> brandCombobox = new ComboBox<>(brandsName);

	ObservableList<String> brandModel = FXCollections.observableArrayList();
	ComboBox<String> brandModelCombobox = new ComboBox<>(brandModel);

	ObservableList<String> brandYear = FXCollections.observableArrayList();
	ComboBox<String> brandYearCombobox = new ComboBox<>(brandYear);

	ObservableList<String> brandColor = FXCollections.observableArrayList();
	ComboBox<String> brandColorCombobox = new ComboBox<>(brandColor);

	ObservableList<String> brandPrice = FXCollections.observableArrayList();
	ComboBox<String> brandPriceCombobox = new ComboBox<>(brandPrice);

	ObservableList<String> b = FXCollections.observableArrayList();
	ComboBox<String> bb = new ComboBox<>(b);

	TableView CarsClientTV = new TableView<>();
	TableView cliOTV = new TableView<>();
	TableView admTV = new TableView<>();
	TableView brandTV = new TableView<>();
	TableView carTV = new TableView<>();
	TableView<Report> reportTV = new TableView<>();
	TableView stackTV = new TableView<>();

	@Override
	public void start(Stage stage) {
		stage.setTitle("Car Agency");

		try {
			// create files
			Cfile.createNewFile();
			Ofile.createNewFile();

			// create file chooser button for cars file :
			FileChooser forCars = new FileChooser();

// ------------------- put image on load button -----------------
			Image imgd = new Image(
					"C:\\Users\\Admin\\Desktop\\comp-242(DataStructures)\\secondProject\\src\\img\\d.png");
			ImageView viewd = new ImageView(imgd);
			viewd.setFitHeight(25);
			viewd.setPreserveRatio(true);

			Button loadCarsFile = new Button();
			loadCarsFile.setStyle(style);
			loadCarsFile.setTextFill(Color.AZURE);
			loadCarsFile.setLayoutX(10);
			loadCarsFile.setLayoutY(470);
			loadCarsFile.setFont(new Font(15));
			loadCarsFile.setGraphic(viewd);
			loadCarsFile.setText("Cars");
			// readCarsFile();

			loadCarsFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Cfile = forCars.showOpenDialog(stage);
					try {
						readCarsFile();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			});

			// create file chooser button for orders file :
			FileChooser forOrders = new FileChooser();

// ------------------- put image on load2 button -----------------
			Image imgd2 = new Image(
					"C:\\Users\\Admin\\Desktop\\comp-242(DataStructures)\\secondProject\\src\\img\\d.png");
			ImageView viewd2 = new ImageView(imgd2);
			viewd2.setFitHeight(25);
			viewd2.setPreserveRatio(true);

			Button loadOrdersFile = new Button();
			loadOrdersFile.setStyle(style);
			loadOrdersFile.setTextFill(Color.AZURE);
			loadOrdersFile.setLayoutX(100);
			loadOrdersFile.setLayoutY(470);
			loadOrdersFile.setFont(new Font(15));
			loadOrdersFile.setGraphic(viewd2);
			loadOrdersFile.setText("Orders");
			// readOrdersFile();

			loadOrdersFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					Ofile = forOrders.showOpenDialog(stage);
					try {
						readOrdersFile();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			});

			readCarsFile();
			// readOrdersFile();

			myDLL.printList();

			curr = myDLL.getfirst();

			Button save = new Button("Save cars");
			save.setOnAction(event -> {
				try {
					myDLL.printList();
					readCarsFile();
					saveInCarsFile();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			Button save1 = new Button("Save orders");
			save1.setLayoutY(20);
			save1.setOnAction(event -> {
				try {
					myDLL.printList();
					readCarsFile();
					saveInOrdersFile();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			// Make a first stage :-
			ImageView firstBG = new ImageView(backgroundImage);
			firstBG.setOpacity(0.6);
			Image firstIcon = new Image(iconImage);
			stage.getIcons().add(firstIcon);

			Pane firstPane = new Pane();

			Text text = new Text("Car Agency");
			text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 60));
			text.setFill(Color.rgb(19, 103, 138));
			text.setStroke(Color.AZURE);
			text.setLayoutX(155);
			text.setLayoutY(140);

//-------------------create Client stage :--------------------------------------			
			Button clientButton = new Button("Client");
			clientButton.setStyle(style);
			clientButton.setTextFill(Color.AZURE);
			clientButton.setLayoutX(160);
			clientButton.setLayoutY(300);
			clientButton.setFont(new Font(30));

			Stage clientStage = new Stage();
			clientStage.setTitle("Client");

			ImageView clientBG = new ImageView(backgroundImage);
			clientBG.setOpacity(0.6);
			Image clientIcon = new Image(iconImage);
			clientStage.getIcons().add(clientIcon);

			clientButton.setOnAction(e -> {
				clientStage.close();
				brandCombobox.setValue("Brand");
//-------------- create client tab ------------------------------	

				Pane root1 = new Pane();

				TabPane clientTabPane = new TabPane();
				Tab cli = new Tab("Client");
//------------------- put image on home button -----------------			
				Image img = new Image(
						"C:\\Users\\Admin\\Desktop\\comp-242(DataStructures)\\secondProject\\src\\img\\i.png");
				ImageView view = new ImageView(img);
				view.setFitHeight(25);
				view.setPreserveRatio(true);

				Button home = new Button();
				home.setGraphic(view);
				home.setLayoutX(10);
				home.setLayoutY(10);
				home.setPrefSize(30, 30);

				TextField cName = new TextField();
				cName.setPromptText("Client Name :");
				cName.setStyle("-fx-background-color : #1988b7");
				cName.setLayoutY(30);
				cName.setLayoutX(90);

				TextField cPhone = new TextField();
				cPhone.setPromptText("Client Phone :");
				cPhone.setStyle("-fx-background-color : #1988b7");
				cPhone.setLayoutY(30);
				cPhone.setLayoutX(260);

				TextField orderD = new TextField();
				orderD.setPromptText("OrderDate(dd/mm/yyyy):");
				orderD.setStyle("-fx-background-color : #1988b7");
				orderD.setLayoutY(30);
				orderD.setLayoutX(425);


//-----------------fill each comboBoxes for filters ------------------------------				
				fillBrandComboBox1();

				carsTableView("", "", "", "", "");
				CarsClientTV.getItems().clear();

				brandCombobox.setValue("Brand");

				// brandCombobox.getSelectionModel().select(0);

				brandModelCombobox.setValue("Model");
				brandModelCombobox.setPrefSize(90, 40);

				brandYearCombobox.setValue("Year");
				brandYearCombobox.setPrefSize(80, 40);

				brandColorCombobox.setValue("Color");
				brandColorCombobox.setPrefSize(80, 40);

				brandPriceCombobox.setValue("Price");
				brandPriceCombobox.setPrefSize(80, 40);

				brandCombobox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {

						// fill model combobox
						brandModelCombobox.getItems().clear();

						brabra = brandCombobox.getSelectionModel().getSelectedItem();

						Node1 temp = ((Brand) myDLL.get(brabra.toString()).getElement()).getList().getFirst();
						brandModel.add("Model");
						// for (int i = 0; i < ((Brand)
						// myDLL.get(brabra).getElement()).getList().getSize(); i++)
						while (temp != null) {
							if (((Cars) temp.getElement()).getBrand().equals(brabra))
								if (!brandModel.contains(((Cars) temp.getElement()).getModel()))
									brandModel.add(((Cars) temp.getElement()).getModel());
							temp = temp.getNext();
						}

						brandModelCombobox.getSelectionModel().select(0);
						// fill year combobox
						brandYearCombobox.getItems().clear();
						Node1 temp1 = ((Brand) myDLL.get(brabra).getElement()).getList().getFirst();
						brandYear.add("Year");
						for (int i = 0; i < ((Brand) myDLL.get(brabra).getElement()).getList().getSize(); i++) {
							if (((Cars) temp1.getElement()).getBrand().equals(brabra)) {
								int y = ((Cars) temp1.getElement()).getYear();
								if (!brandYear.contains(String.valueOf(y))) {
									brandYear.add(String.valueOf(y));
								}
							}
							temp1 = temp1.getNext();
						}
						brandYearCombobox.getSelectionModel().select(0);
						// fill color combobox
						brandColorCombobox.getItems().clear();
						Node1 temp2 = ((Brand) myDLL.get(brabra).getElement()).getList().getFirst();
						brandColor.add("Color");
						for (int i = 0; i < ((Brand) myDLL.get(brabra).getElement()).getList().getSize(); i++) {
							if (((Cars) temp2.getElement()).getBrand().equals(brabra))
								if (!brandColor.contains(((Cars) temp2.getElement()).getColor()))
									brandColor.add(((Cars) temp2.getElement()).getColor());
							temp2 = temp2.getNext();
						}
						brandColorCombobox.getSelectionModel().select(0);
						// fill price combobox
						brandPriceCombobox.getItems().clear();
						Node1 temp3 = ((Brand) myDLL.get(brabra).getElement()).getList().getFirst();
						brandPrice.add("Price");
						for (int i = 0; i < ((Brand) myDLL.get(brabra).getElement()).getList().getSize(); i++) {
							if (((Cars) temp3.getElement()).getBrand().equals(brabra))
								if (!brandPrice.contains(((Cars) temp3.getElement()).getPrice()))
									brandPrice.add(((Cars) temp3.getElement()).getPrice());
							temp3 = temp3.getNext();
						}
						brandPriceCombobox.getSelectionModel().select(0);

						carsTableView(brabra, "Model", "Year", "Color", "Price");
					}
				});

				brandModelCombobox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {

						if (brandYearCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)
								&& brandPriceCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", "Color", "Price");
						} else if (brandYearCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", "Color", pripri);
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, "Color", "Price");
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", colcol, "Price");
						} else if (brandYearCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", colcol, pripri);
						} else if (brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, "Color", pripri);
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, "Price");
						} else {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, pripri);
						}
					}
				});

				brandYearCombobox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
						if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)
								&& brandPriceCombobox.getSelectionModel().isSelected(0)) {
							carsTableView(brabra, "Model", yeayea, "Color", "Price");
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, "Color", "Price");
						} else if (brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, "Color", pripri);
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, "Price");
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)) {
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, colcol, pripri);
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandPriceCombobox.getSelectionModel().isSelected(0)) {
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, colcol, "Price");
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, "Color", pripri);
						} else {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, pripri);
						}
					}
				});

				brandColorCombobox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
						if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)
								&& brandPriceCombobox.getSelectionModel().isSelected(0)) {
							carsTableView(brabra, "Model", "Year", colcol, "Price");
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", colcol, "Price");
						} else if (brandYearCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", colcol, pripri);
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandPriceCombobox.getSelectionModel().isSelected(0)) {
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, colcol, "Price");
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)) {
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", "Year", colcol, pripri);
						} else if (brandPriceCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, "Price");
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)) {
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, colcol, pripri);
						} else {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, pripri);
						}
					}
				});

				brandPriceCombobox.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {

						pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
						if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)) {
							carsTableView(brabra, "Model", "Year", "Color", pripri);
						} else if (brandYearCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							System.out.println(modmod + " " + pripri);
							carsTableView(brabra, modmod, "Year", "Color", pripri);
						} else if (brandYearCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, "Year", colcol, pripri);
						} else if (brandColorCombobox.getSelectionModel().isSelected(0)) {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, "Color", pripri);
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandYearCombobox.getSelectionModel().isSelected(0)) {
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", "Year", colcol, pripri);
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)
								&& brandColorCombobox.getSelectionModel().isSelected(0)) {
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, "Color", pripri);
						} else if (brandModelCombobox.getSelectionModel().isSelected(0)) {
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, "Model", yeayea, colcol, pripri);
						} else {
							modmod = brandModelCombobox.getSelectionModel().getSelectedItem();
							colcol = brandColorCombobox.getSelectionModel().getSelectedItem();
							yeayea = brandYearCombobox.getSelectionModel().getSelectedItem();
							pripri = brandPriceCombobox.getSelectionModel().getSelectedItem();
							carsTableView(brabra, modmod, yeayea, colcol, pripri);
						}
					}
				});

				HBox comboboxes = new HBox(10);
				comboboxes.getChildren().addAll(brandCombobox, brandModelCombobox, brandYearCombobox,
						brandColorCombobox, brandPriceCombobox);
				comboboxes.setLayoutX(90);
				comboboxes.setLayoutY(75);

//-------------------- next brand ----------------------------------------	
				Button nextbra = new Button(">");
				nextbra.setStyle("-fx-background-color : #1988b7");
				nextbra.setTextFill(Color.AZURE);
				nextbra.setFont(new Font(15));
				nextbra.setLayoutX(390);
				nextbra.setLayoutY(550);

				curr = myDLL.getfirst();

				nextbra.setOnAction(event -> {
					curr = curr.getNext();
					// bra.setText(((Brand) curr.getElement()).getBrand());
					brandCombobox.setValue(((Brand) curr.getElement()).getBrand());
				});

//------------------- prev brand -----------------------------------------
				Button prevbra = new Button("<");
				prevbra.setStyle("-fx-background-color : #1988b7");
				prevbra.setTextFill(Color.AZURE);
				prevbra.setFont(new Font(15));
				prevbra.setLayoutX(215);
				prevbra.setLayoutY(550);

				prevbra.setOnAction(event -> {
					curr = curr.getPrev();
					// bra.setText(((Brand) curr.getElement()).getBrand());
					brandCombobox.setValue(((Brand) curr.getElement()).getBrand());
				});

//--------------create Order object and add it into Q: -------------------------------				
				Button addOrder = new Button("Add to cart");
				addOrder.setStyle("-fx-background-color : #1988b7");
				addOrder.setTextFill(Color.AZURE);
				addOrder.setFont(new Font(15));
				addOrder.setLayoutX(270);
				addOrder.setLayoutY(550);

				addOrder.setOnAction(event -> {
					Cars c = (Cars) CarsClientTV.getSelectionModel().getSelectedItem();
					if (cName.getText().trim().isEmpty() || cPhone.getText().trim().isEmpty()
							|| orderD.getText().trim().isEmpty() || c == (null)) {
						Stage warning = new Stage();
						Pane wPane = new Pane();
						Text wLable = new Text("\n" + "\n" + "    Plese write your name ,phone number\n"
								+ "    and Order date ,or select a car");
						wPane.getChildren().add(wLable);
						Scene wScene = new Scene(wPane, 250, 70);
						warning.setScene(wScene);
						warning.show();
					} else {
						Date d = null;
						try {
							d = new SimpleDateFormat("dd/MM/yyyy").parse(orderD.getText().trim());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Order o = new Order(cName.getText().trim(), cPhone.getText().trim(),
								brandCombobox.getSelectionModel().getSelectedItem(), c.getModel(), c.getYear(),
								c.getColor(), c.getPrice(), d, "InProcess");
						queue.addLast(o);

					}
				});

//---------------create client's orders tab :-------------------------------
				Tab cliO = new Tab("MyOrders");

				Pane root2 = new Pane();
				ImageView clientOBG = new ImageView(backgroundImage);
				clientOBG.setOpacity(0.6);

//				cliO.setOnSelectionChanged(event -> {
//					cliOTV.getItems().clear();
//					cliOTV.getColumns().clear();
//					clientOrdersTV(cName.getText().trim(), cPhone.getText().trim());
//				});
//				

				cli.setOnSelectionChanged(event -> {
					cliOTV.getColumns().clear();
					cliOTV.getItems().clear();
					clientOrdersTV(cName.getText().trim(), cPhone.getText().trim());
					cliOTV.refresh();

				});

//----------------button if I want to delete my order --------------------------------				
				Button deleteO = new Button("Delete");
				deleteO.setStyle("-fx-background-color : #1988b7");
				deleteO.setTextFill(Color.AZURE);
				deleteO.setLayoutX(370);
				deleteO.setLayoutY(480);
				deleteO.setFont(new Font(18));
				deleteO.setPrefSize(90, 40);

				deleteO.setOnAction(event -> {
					Stage del = new Stage();
					Pane delpane = new Pane();
					Text t = new Text("Are you sure ?");
					t.setFont(new Font(20));
					t.setLayoutX(30);
					t.setLayoutY(30);

					Button nsu = new Button("No");
					nsu.setStyle("-fx-background-color : #1988b7");
					nsu.setTextFill(Color.AZURE);
					nsu.setLayoutX(30);
					nsu.setLayoutY(70);
					nsu.setFont(new Font(15));
					nsu.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							del.close();
						}
					});

					Button su = new Button("Yes");
					su.setStyle("-fx-background-color : #1988b7");
					su.setTextFill(Color.AZURE);
					su.setLayoutX(100);
					su.setLayoutY(70);
					su.setFont(new Font(15));
					su.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							Order o = (Order) cliOTV.getSelectionModel().getSelectedItem();
							if (o != null) {
								ordNode temp;

								for (int i = 0; i < queue.getSize(); i++) {
									nnn = queue.getFirst();
									if (((Order) nnn.getElement()).getBrand().equals(o.getBrand())
											&& ((Order) nnn.getElement()).getModel().equals(o.getModel())
											&& ((Order) nnn.getElement()).getYear() == (o.getYear())
											&& ((Order) nnn.getElement()).getColor().equals(o.getColor())
											&& ((Order) nnn.getElement()).getPrice().equals(o.getPrice())
											&& ((Order) nnn.getElement()).getOrderDate().equals(o.getOrderDate())
											&& ((Order) nnn.getElement()).getOrderDate().equals(o.getOrderDate())) {
										queue.removeFirst();
									}
									temp = queue.getFirst();
									queue.addLast(new Order(((Order) temp.getElement()).getCname(),
											((Order) temp.getElement()).getCphone(),
											((Order) temp.getElement()).getBrand(),
											((Order) temp.getElement()).getModel(),
											((Order) temp.getElement()).getYear(),
											((Order) temp.getElement()).getColor(),
											((Order) temp.getElement()).getPrice(),
											((Order) temp.getElement()).getOrderDate(),
											((Order) temp.getElement()).getOrderStatus()));
									queue.removeFirst();
								}
								cliOTV.getItems().remove(o);
								cliOTV.refresh();

								del.close();
							}
						}

					});

					delpane.getChildren().addAll(t, nsu, su);
					Scene delscene = new Scene(delpane, 180, 120);
					del.setScene(delscene);
					del.show();

				});

// ----------------create button to update client's info------------------------
				Button upcli = new Button("Update Cinfo.");
				upcli.setStyle("-fx-background-color : #1988b7");
				upcli.setTextFill(Color.AZURE);
				upcli.setLayoutX(180);
				upcli.setLayoutY(480);
				upcli.setFont(new Font(18));
				// upcli.setPrefSize(90, 40);

				Stage upclistage = new Stage();
				upcli.setOnAction(event -> {

					Pane upclipane = new Pane();

					Label cname = new Label("Name");
					cname.setStyle("-fx-background-color : #1988b7");
					// cname.setTextFill(Color.AZURE);
					cname.setFont(new Font(17));
					TextField cname_ = new TextField(cName.getText().trim());
					cname_.setPromptText("Customer Name");

					Label cphone = new Label("Phone");
					cphone.setStyle("-fx-background-color : #1988b7");
					// cphone.setTextFill(Color.AZURE);
					cphone.setFont(new Font(17));
					TextField cphone_ = new TextField(cPhone.getText().trim());
					cphone_.setPromptText("Customer Phone");

					Button up = new Button("Update");
					up.setStyle("-fx-background-color : #1988b7");
					// up.setTextFill(Color.AZURE);

					up.setOnAction(new EventHandler<ActionEvent>() {
						@SuppressWarnings("unlikely-arg-type")
						@Override
						public void handle(ActionEvent arg0) {

							if (!cName.getText().trim().isEmpty() && !cPhone.getText().trim().isEmpty()) {
								ordNode temp;
								String nam = cName.getText().trim();
								String phon = cPhone.getText().trim();
								for (int i = 0; i < queue.getSize(); i++) {

									nodorder = queue.getFirst();
									if (((Order) nodorder.getElement()).getCname().equals(nam)
											&& ((Order) nodorder.getElement()).getCphone().equals(phon)) {
										try {

//											Date d = new SimpleDateFormat("dd/MM/yyyy").parse(orderd_.getText().trim());
											((Order) nodorder.getElement()).setCname(cname_.getText().trim());
											((Order) nodorder.getElement()).setCphone(cphone_.getText().trim());
//											((Order) nodorder.getElement()).setOrderDate(d);
											cName.setText(cname_.getText().trim());
											cPhone.setText(cphone_.getText().trim());
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
									temp = queue.getFirst();
									queue.addLast(new Order(((Order) temp.getElement()).getCname(),
											((Order) temp.getElement()).getCphone(),
											((Order) temp.getElement()).getBrand(),
											((Order) temp.getElement()).getModel(),
											((Order) temp.getElement()).getYear(),
											((Order) temp.getElement()).getColor(),
											((Order) temp.getElement()).getPrice(),
											((Order) temp.getElement()).getOrderDate(),
											((Order) temp.getElement()).getOrderStatus()));
									queue.removeFirst();

								}
								upclistage.close();
							}
						}
					});

					VBox v = new VBox(15);
					v.getChildren().addAll(cname, cname_, cphone, cphone_, up);
					v.setPadding(new Insets(20));

					upclipane.getChildren().add(v);
					Scene upcliscene = new Scene(upclipane, 210, 270);
					upclistage.setScene(upcliscene);
					upclistage.show();
				});

				root1.getChildren().addAll(clientBG);
				root1.getChildren().addAll(comboboxes, CarsClientTV, cName, cPhone, orderD, addOrder,
						// bra,
						nextbra, prevbra, home);

				root2.getChildren().add(clientOBG);
				root2.getChildren().addAll(cliOTV, deleteO, upcli);

				// back to first stage (main page)
				home.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						clientStage.close();
						stage.show();
					}
				});

				cli.setContent(root1);
				cliO.setContent(root2);
				cli.setClosable(false);
				cliO.setClosable(false);
				clientTabPane.getTabs().addAll(cli, cliO);
				Scene cliScene = new Scene(clientTabPane, 650, 630);
				clientStage.setScene(cliScene);
				clientStage.show();

			});

//-------------------create Admin stage :--------------------------------------
			Button adminButton = new Button("Admin");
			adminButton.setStyle(style);
			adminButton.setTextFill(Color.AZURE);
			adminButton.setLayoutX(360);
			adminButton.setLayoutY(300);
			adminButton.setFont(new Font(30));

			Stage adminStage = new Stage();
			adminStage.setTitle("Admin");

			ImageView adminBG = new ImageView(backgroundImage);
			adminBG.setOpacity(0.6);
			Image adminIcon = new Image(iconImage);
			adminStage.getIcons().add(adminIcon);

			adminButton.setOnAction(e -> {

				TabPane adminTabPane = new TabPane();
//----------------create admin tab : --------------------------------------------				
				Tab adm = new Tab("Admin");

				Pane admPane = new Pane();

				Image img = new Image(
						"C:\\Users\\Admin\\Desktop\\comp-242(DataStructures)\\secondProject\\src\\img\\i.png");
				ImageView view = new ImageView(img);
				view.setFitHeight(25);
				view.setPreserveRatio(true);

				Button home = new Button();
				home.setGraphic(view);
				home.setLayoutX(10);
				home.setLayoutY(10);
				home.setPrefSize(30, 30);

				home.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						adminStage.close();
						stage.show();
					}
				});
//-----------------table view to display the queue orders------------------------------
				adminTableView();

//--------------delete order by admin-------------------------------------------------
				Button deleteOrder = new Button("Delete");
				deleteOrder.setStyle(style);
				deleteOrder.setTextFill(Color.AZURE);
				deleteOrder.setFont(new Font(17));

				deleteOrder.setOnAction(event -> {
					Stage delord = new Stage();
					delord.setTitle("Delete Order!!");
					Pane delordPane = new Pane();
					Text t = new Text("Are you sure ?");
					t.setFont(new Font(20));
					t.setLayoutX(60);
					t.setLayoutY(30);

					Button nsu = new Button("No");
					nsu.setStyle("-fx-background-color : #1988b7");
					nsu.setTextFill(Color.AZURE);
					nsu.setLayoutX(60);
					nsu.setLayoutY(70);
					nsu.setFont(new Font(15));
					nsu.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							delord.close();
						}
					});

					Button su = new Button("Yes");
					su.setStyle("-fx-background-color : #1988b7");
					su.setTextFill(Color.AZURE);
					su.setLayoutX(130);
					su.setLayoutY(70);
					su.setFont(new Font(15));
					su.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							if (queue.getSize() != 0) {
								queue.removeFirst();
								admTV.getItems().remove(0);
								// adminTableView();
								admTV.refresh();
							}
							delord.close();
						}
					});
					delordPane.getChildren().addAll(t, nsu, su);
					Scene delscene = new Scene(delordPane, 250, 120);
					delord.setScene(delscene);
					delord.show();
				});

//-------------- add order at last by admin--------------------------------------------
				Button addLast = new Button("Add Last");
				addLast.setStyle(style);
				addLast.setTextFill(Color.AZURE);
				addLast.setFont(new Font(17));

				addLast.setOnAction(event -> {
					ordNode n1;
					n1 = queue.getFirst();
					queue.removeFirst();
					queue.addLast(new Order(((Order) n1.getElement()).getCname(), ((Order) n1.getElement()).getCphone(),
							((Order) n1.getElement()).getBrand(), ((Order) n1.getElement()).getModel(),
							((Order) n1.getElement()).getYear(), ((Order) n1.getElement()).getColor(),
							((Order) n1.getElement()).getPrice(), ((Order) n1.getElement()).getOrderDate(),
							((Order) n1.getElement()).getOrderStatus()));

					admTV.getItems().clear();
					admTV.getColumns().clear();
					adminTableView();
				});

//--------------process order by admin------------------------------------------
				Button process = new Button("Process Order");
				process.setStyle(style);
				process.setTextFill(Color.AZURE);
				process.setFont(new Font(17));

				process.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Stage toFinish = new Stage();
						Pane topane = new Pane();

						Text totext = new Text();
						totext.setText("Process order and ...");
						totext.setFont(new Font(20));
						totext.setLayoutX(30);
						totext.setLayoutY(30);
						Button ending = new Button("Ending");
						ending.setStyle(style);
						ending.setTextFill(Color.AZURE);
						ending.setFont(new Font(17));

						ending.setOnAction(event -> {
							ordNode n1;
							n1 = queue.getFirst();
							// remove order from queue

							((Order) n1.getElement()).setOrderStatus("Finished");
							// add order into stack
							stack.addLast(new Order(((Order) n1.getElement()).getCname(),
									((Order) n1.getElement()).getCphone(), ((Order) n1.getElement()).getBrand(),
									((Order) n1.getElement()).getModel(), ((Order) n1.getElement()).getYear(),
									((Order) n1.getElement()).getColor(), ((Order) n1.getElement()).getPrice(),
									((Order) n1.getElement()).getOrderDate(),
									((Order) n1.getElement()).getOrderStatus()));
							queue.removeFirst();
							admTV.getItems().clear();
							admTV.getColumns().clear();
							adminTableView();
							stackTableView();

							toFinish.close();
						});

						Button finishdelete = new Button("Delete type of car ,same orders and ending");
						finishdelete.setStyle(style);
						finishdelete.setTextFill(Color.AZURE);
						finishdelete.setFont(new Font(17));

						finishdelete.setOnAction(event -> {
							ordNode n1;
							n1 = queue.getFirst();
							// remove order from queue
							queue.removeFirst();
							((Order) n1.getElement()).setOrderStatus("Finished");
							// add order into stack
							stack.addLast(new Order(((Order) n1.getElement()).getCname(),
									((Order) n1.getElement()).getCphone(), ((Order) n1.getElement()).getBrand(),
									((Order) n1.getElement()).getModel(), ((Order) n1.getElement()).getYear(),
									((Order) n1.getElement()).getColor(), ((Order) n1.getElement()).getPrice(),
									((Order) n1.getElement()).getOrderDate(),
									((Order) n1.getElement()).getOrderStatus()));

							// remove the type of car
							node1 = ((Brand) myDLL.get(((Order) n1.getElement()).getBrand()).getElement()).getList()
									.getFirst();

							for (int i = 0; i < ((Brand) myDLL.get(((Order) n1.getElement()).getBrand()).getElement())
									.getList().getSize(); i++) {
								System.out.println(i);
								if (((Cars) node1.getElement()).getBrand().equals(((Order) n1.getElement()).getBrand())
										&& ((Cars) node1.getElement()).getYear() == ((Order) n1.getElement()).getYear()
										&& ((Cars) node1.getElement()).getModel()
												.equals(((Order) n1.getElement()).getModel())
										&& ((Cars) node1.getElement()).getColor()
												.equals(((Order) n1.getElement()).getColor())
										&& ((Cars) node1.getElement()).getPrice()
												.equals(((Order) n1.getElement()).getPrice())) {

									((Brand) myDLL.get(((Order) n1.getElement()).getBrand()).getElement()).getList()
											.remove(new Cars(((Cars) node1.getElement()).getBrand(),
													((Cars) node1.getElement()).getModel(),
													((Cars) node1.getElement()).getYear(),
													((Cars) node1.getElement()).getColor(),
													((Cars) node1.getElement()).getPrice()));
								}

								node1 = node1.getNext();
							}

							// remove the same orders
							ordNode tempo;
							for (int i = 0; i < queue.getSize(); i++) {
								nnn = queue.getFirst();
								if (((Order) nnn.getElement()).getBrand().equals(((Order) n1.getElement()).getBrand())
										&& ((Order) nnn.getElement()).getModel()
												.equals(((Order) n1.getElement()).getModel())
										&& ((Order) nnn.getElement()).getYear() == (((Order) n1.getElement()).getYear())
										&& ((Order) nnn.getElement()).getColor()
												.equals(((Order) n1.getElement()).getColor())
										&& ((Order) nnn.getElement()).getPrice()
												.equals(((Order) n1.getElement()).getPrice())) {
									queue.removeFirst();
								}
								tempo = queue.getFirst();
								queue.removeFirst();
								queue.addLast(new Order(((Order) tempo.getElement()).getCname(),
										((Order) tempo.getElement()).getCphone(),
										((Order) tempo.getElement()).getBrand(),
										((Order) tempo.getElement()).getModel(), ((Order) tempo.getElement()).getYear(),
										((Order) tempo.getElement()).getColor(),
										((Order) tempo.getElement()).getPrice(),
										((Order) tempo.getElement()).getOrderDate(),
										((Order) tempo.getElement()).getOrderStatus()));
							}

							admTV.getItems().clear();
							admTV.getColumns().clear();
							adminTableView();
							queue.printList();
							toFinish.close();
						});

						VBox to = new VBox(20);
						to.setPadding(new Insets(20));
						to.getChildren().addAll(totext, ending, finishdelete);

						topane.getChildren().addAll(to);
						Scene toscene = new Scene(topane, 400, 200);
						toFinish.setScene(toscene);
						toFinish.show();

					}
				});

				HBox adminbuttons = new HBox(15);
				adminbuttons.getChildren().addAll(deleteOrder, addLast, process);
				adminbuttons.setLayoutX(175);
				adminbuttons.setLayoutY(500);

				admPane.getChildren().add(adminBG);
				admPane.getChildren().addAll(admTV, adminbuttons, home);

//-------------------create car's brand tab : --------------------------------
				Tab brandTab = new Tab("Brands");

				Pane brandPane = new Pane();
				ImageView brandPaneBG = new ImageView(backgroundImage);
				brandPaneBG.setOpacity(0.6);

				createBrandTV();

				TextField newBrand = new TextField();
				newBrand.setPromptText("New Brand");
				newBrand.setLayoutX(180);
				newBrand.setLayoutY(403);
				newBrand.setPrefSize(160, 35);

				Button add = new Button("Add");
				add.setStyle(style);
				add.setTextFill(Color.AZURE);
				add.setFont(new Font(17));
				add.setLayoutX(370);
				add.setLayoutY(400);

				// add new brand
				add.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						if (!newBrand.getText().trim().isEmpty()) {
							myDLL.addSorted(new Brand(newBrand.getText()));
							newBrand.clear();
							createBrandTV();
							fillBrand();
						}
					}
				});

				Button rem = new Button("Delete");
				rem.setStyle(style);
				rem.setTextFill(Color.AZURE);
				rem.setFont(new Font(17));
				rem.setLayoutX(360);
				rem.setLayoutY(450);

				// delete brand
				rem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Stage del = new Stage();
						Pane delpane = new Pane();
						Text t = new Text("Are you sure ?" + "\n'Your confirmation will delete all cars "
								+ "\nand orders for this brand'");
						t.setFont(new Font(18));
						t.setLayoutX(30);
						t.setLayoutY(30);

						Button nsu = new Button("No");
						nsu.setStyle("-fx-background-color : #1988b7");
						nsu.setTextFill(Color.AZURE);
						nsu.setLayoutX(30);
						nsu.setLayoutY(100);
						nsu.setFont(new Font(15));
						nsu.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								del.close();
							}
						});

						Button su = new Button("Yes");
						su.setStyle("-fx-background-color : #1988b7");
						su.setTextFill(Color.AZURE);
						su.setLayoutX(100);
						su.setLayoutY(100);
						su.setFont(new Font(15));

						su.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Brand selbrand = (Brand) brandTV.getSelectionModel().getSelectedItem();

								if (selbrand != null) {
									// delete cars in single linked list for this brand
									node1 = ((Brand) myDLL.get(selbrand.getBrand()).getElement()).getList().getFirst();
									for (int i = 0; i < ((Brand) myDLL.get(selbrand.getBrand()).getElement()).getList()
											.getSize(); i++) {
										((Brand) myDLL.get(selbrand.getBrand()).getElement()).getList().removeFirst();
									}
									// remove the orders
									ordNode tempp;
									for (int a = 0; a < queue.getSize(); a++) {
										nnn = queue.getFirst();
										if (((Order) nnn.getElement()).getBrand().equals(selbrand.getBrand())) {
											System.out.println("bbbbbb");
											queue.removeFirst();
										}
										tempp = queue.getFirst();
										queue.addLast(new Order(((Order) tempp.getElement()).getCname(),
												((Order) tempp.getElement()).getCphone(),
												((Order) tempp.getElement()).getBrand(),
												((Order) tempp.getElement()).getModel(),
												((Order) tempp.getElement()).getYear(),
												((Order) tempp.getElement()).getColor(),
												((Order) tempp.getElement()).getPrice(),
												((Order) tempp.getElement()).getOrderDate(),
												((Order) tempp.getElement()).getOrderStatus()));
										queue.removeFirst();
										adminTableView();
									}

									// delete brand from double linked list
									node = myDLL.getfirst();
									myDLL.remove(((Brand) myDLL.get(selbrand.getBrand()).getElement()));
									myDLL.printList();
									createBrandTV();
									createCarTV(myDLL.getfirst().getElement().toString());
									brandLable.setText(myDLL.getfirst().getElement().toString());
								}

								del.close();
								createCarTV(selbrand.getBrand());
							}

						});

						delpane.getChildren().addAll(t, nsu, su);
						Scene delscene = new Scene(delpane, 350, 150);
						del.setScene(delscene);
						del.show();
						fillBrand();
					}

				});

				brandPane.getChildren().add(brandPaneBG);
				brandPane.getChildren().addAll(brandTV, add, newBrand, rem);

//-------------------create cars tab :- ----------------------------------------
				Tab carsTab = new Tab("Cars");

				Pane carsPane = new Pane();
				ImageView carsPaneBG = new ImageView(backgroundImage);
				carsPaneBG.setOpacity(0.6);

				brandLable.setStyle("-fx-background-color : #1988b7");
				// brandLable.setTextFill(Color.AZURE);
				brandLable.setFont(new Font(17));
				brandLable.setLayoutX(237);
				brandLable.setLayoutY(43);

				TextField carM = new TextField();
				carM.setPromptText("Model");

				TextField carY = new TextField();
				carY.setPromptText("Year");

				TextField carC = new TextField();
				carC.setPromptText("Color");

				TextField carP = new TextField();
				carP.setPromptText("Price");

				Button addcar = new Button("Add");
				addcar.setStyle("-fx-background-color : #1988b7");
				addcar.setTextFill(Color.AZURE);
				addcar.setLayoutX(460);
				addcar.setLayoutY(490);
				// addcar.setFont(new Font(15));

				// add new car
				addcar.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						try {
							// add new car
							if (!carM.getText().trim().isEmpty() && !carY.getText().trim().isEmpty()
									&& !carC.getText().trim().isEmpty() && !carP.getText().trim().isEmpty()) {

								((Brand) myDLL.get(brandLable.getText()).getElement()).getList()
										.addSorted(new Cars(brandLable.getText(), carM.getText().trim(),
												Integer.parseInt(carY.getText().trim()), carC.getText().trim(),
												carP.getText().trim()));
								createCarTV(brandLable.getText());
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						fillBrand();
						reportTableView();
						carM.clear();
						carY.clear();
						carC.clear();
						carP.clear();
						
						
					}
				});

				// delete car
				Button deletecar = new Button("Delete");
				deletecar.setStyle("-fx-background-color : #1988b7");
				deletecar.setTextFill(Color.AZURE);
				deletecar.setFont(new Font(15));
				deletecar.setLayoutX(285);
				deletecar.setLayoutY(530);

				deletecar.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {

						Stage del = new Stage();
						Pane delpane = new Pane();
						Text t = new Text("Are you sure ?" + "\n'Your confirmation will delete all "
								+ "\n orders for this car!!!'");
						t.setFont(new Font(18));
						t.setLayoutX(30);
						t.setLayoutY(30);

						Button nsu = new Button("No");
						nsu.setStyle("-fx-background-color : #1988b7");
						nsu.setTextFill(Color.AZURE);
						nsu.setLayoutX(30);
						nsu.setLayoutY(100);
						nsu.setFont(new Font(15));
						nsu.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								del.close();
							}
						});

						Button su = new Button("Yes");
						su.setStyle("-fx-background-color : #1988b7");
						su.setTextFill(Color.AZURE);
						su.setLayoutX(100);
						su.setLayoutY(100);
						su.setFont(new Font(15));
						Cars c = (Cars) carTV.getSelectionModel().getSelectedItem();
						Cars cc = new Cars(brandLable.getText(), c.getModel(), c.getYear(), c.getColor(), c.getPrice());
						su.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								// remove a car from single linked list

								if (c != null) {
									((Brand) myDLL.get(brandLable.getText()).getElement()).getList()
											.remove(new Cars(((Cars) cc).getBrand(), ((Cars) cc).getModel(),
													((Cars) cc).getYear(), ((Cars) cc).getColor(),
													((Cars) cc).getPrice()));
									createCarTV(brandLable.getText());
									// remove the orders in this car
									ordNode tempp;
									for (int a = 0; a < queue.getSize(); a++) {
										nnn = queue.getFirst();
										if (((Order) nnn.getElement()).getBrand().equals(cc.getBrand())
												&& ((Order) nnn.getElement()).getYear() == (cc.getYear())
												&& ((Order) nnn.getElement()).getColor().equals(cc.getColor())
												&& ((Order) nnn.getElement()).getModel().equals(cc.getModel())
												&& ((Order) nnn.getElement()).getPrice().equals(cc.getPrice())) {
											queue.removeFirst();
										}
										tempp = queue.getFirst();
										queue.addLast(new Order(((Order) tempp.getElement()).getCname(),
												((Order) tempp.getElement()).getCphone(),
												((Order) tempp.getElement()).getBrand(),
												((Order) tempp.getElement()).getModel(),
												((Order) tempp.getElement()).getYear(),
												((Order) tempp.getElement()).getColor(),
												((Order) tempp.getElement()).getPrice(),
												((Order) tempp.getElement()).getOrderDate(),
												((Order) tempp.getElement()).getOrderStatus()));
										queue.removeFirst();
										adminTableView();
										createCarTV(cc.getBrand());

									}
								}
								del.close();
								reportTableView();
							}
						});

						delpane.getChildren().addAll(t, nsu, su);
						Scene delscene = new Scene(delpane, 350, 150);
						del.setScene(delscene);
						del.show();
						fillBrand();
					}

				});

				Button upcar = new Button("Update");
				upcar.setStyle("-fx-background-color : #1988b7");
				upcar.setTextFill(Color.AZURE);
				upcar.setLayoutX(460);
				upcar.setLayoutY(450);

				TextField carMup = new TextField();
				carMup.setPromptText("Model");

				TextField carYup = new TextField();
				carYup.setPromptText("Year");

				TextField carCup = new TextField();
				carCup.setPromptText("Color");

				TextField carPup = new TextField();
				carPup.setPromptText("Price");

				// update car info
				upcar.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Cars tcar = (Cars) carTV.getSelectionModel().getSelectedItem();
						Cars ttcar = new Cars(brandLable.getText(), tcar.getModel(), tcar.getYear(), tcar.getColor(),
								tcar.getPrice());
						if (tcar != null) {
							if (!carMup.getText().trim().isEmpty() && !carYup.getText().trim().isEmpty()
									&& !carCup.getText().trim().isEmpty() && !carPup.getText().trim().isEmpty()) {
								node1 = ((Brand) myDLL.get(ttcar.getBrand()).getElement()).getList().getFirst();
								// update car information from single linked list
								((Brand) myDLL.get(ttcar.getBrand()).getElement()).getList()
										.remove(new Cars(((Cars) ttcar).getBrand(), (ttcar).getModel(),
												((Cars) ttcar).getYear(), ((Cars) ttcar).getColor(),
												((Cars) ttcar).getPrice()));
								try {
									((Brand) myDLL.get(ttcar.getBrand()).getElement()).getList()
											.addSorted(new Cars(ttcar.getBrand(), carMup.getText().trim(),
													Integer.parseInt(carYup.getText().trim()), carCup.getText().trim(),
													carPup.getText().trim()));
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								///////////////////

								carMup.clear();
								carYup.clear();
								carCup.clear();
								carPup.clear();
								
								reportTableView();

							}
						}
						createCarTV(ttcar.getBrand());
						adminTableView();
					}
				});

				HBox hbox = new HBox(15);
				hbox.getChildren().addAll(carM, carY, carC, carP);
				hbox.setLayoutX(130);
				hbox.setLayoutY(490);
				hbox.setPrefSize(320, 40);

				HBox hboxup = new HBox(15);
				hboxup.getChildren().addAll(carMup, carYup, carCup, carPup);
				hboxup.setLayoutX(130);
				hboxup.setLayoutY(450);
				hboxup.setPrefSize(320, 40);
				fillBrand();
				bb.setLayoutX(130);
				bb.setLayoutY(40);
				bb.setStyle("-fx-background-color : #1988b7");
//-------------------- next brand in admin page----------------------------------------	
				Button nextbra = new Button(">");
				nextbra.setStyle("-fx-background-color : #1988b7");
				nextbra.setTextFill(Color.AZURE);
				nextbra.setFont(new Font(14));
				nextbra.setLayoutX(370);
				nextbra.setLayoutY(530);

				createCarTV(bb.getSelectionModel().getSelectedItem());
				bb.setOnAction(event -> {
					brandLable.setText(bb.getSelectionModel().getSelectedItem());
					createCarTV(brandLable.getText().trim());
				});

				createCarTV(brandLable.getText().trim());

				nextbra.setOnAction(event -> {
					curr = curr.getNext();
					// brandLable.setText(((Brand) curr.getElement()).getBrand());
					brandLable.setText(bb.getSelectionModel().getSelectedItem());
					createCarTV(brandLable.getText().trim());
					bb.setValue(((Brand) curr.getElement()).getBrand());
				});

//------------------- prev brand in admin page-----------------------------------------
				Button prevbra = new Button("<");
				prevbra.setStyle("-fx-background-color : #1988b7");
				prevbra.setTextFill(Color.AZURE);
				prevbra.setFont(new Font(14));
				prevbra.setLayoutX(235);
				prevbra.setLayoutY(530);

				prevbra.setOnAction(event -> {
					curr = curr.getPrev();
					brandLable.setText(bb.getSelectionModel().getSelectedItem());
					createCarTV(brandLable.getText().trim());
					bb.setValue(((Brand) curr.getElement()).getBrand());
				});

				carsPane.getChildren().add(carsPaneBG);
				carsPane.getChildren().addAll(brandLable, nextbra, prevbra, carTV, hbox, addcar, deletecar, upcar,
						hboxup, bb);

//----------------- create Report tab ------------------------------
				Tab repTab = new Tab("Report");

				Pane repPane = new Pane();
				ImageView repBG = new ImageView(backgroundImage);
				repBG.setOpacity(0.6);

				reportTableView();
				stackTableView();

				repPane.getChildren().add(repBG);
				repPane.getChildren().addAll(reportTV);

//-----------------create stack tab-------------------------------
				Tab staTab = new Tab("Stack");

				Pane staPane = new Pane();
				ImageView staBG = new ImageView(backgroundImage);
				staBG.setOpacity(0.6);

				adminTableView();
				stackTableView();

				Button all = new Button("All");
				all.setStyle("-fx-background-color : #1988b7");
				// last10.setTextFill(Color.AZURE);
				all.setFont(new Font(18));
				all.setLayoutX(400);
				all.setLayoutY(480);

				all.setOnAction(event -> {
					adminTableView();
					stackTableView();
				});

				Button last10 = new Button("Last 10");
				last10.setStyle("-fx-background-color : #1988b7");
				// last10.setTextFill(Color.AZURE);
				last10.setFont(new Font(18));
				last10.setLayoutX(470);
				last10.setLayoutY(480);

				last10.setOnAction(event -> {
					System.out.println(stackTV.getItems().size());
					for (int i = stackTV.getItems().size() - 1; i > 9; i--) {
						stackTV.getItems().remove(i);
					}
				});

				staPane.getChildren().add(staBG);
				staPane.getChildren().addAll(stackTV, all, last10);

				adm.setContent(admPane);
				adm.setClosable(false);

				brandTab.setContent(brandPane);
				brandTab.setClosable(false);

				carsTab.setContent(carsPane);
				carsTab.setClosable(false);

				repTab.setContent(repPane);
				repTab.setClosable(false);

				staTab.setContent(staPane);
				staTab.setClosable(false);

				adminTabPane.getTabs().addAll(adm, staTab, brandTab, carsTab, repTab);
				Scene admScene = new Scene(adminTabPane, 650, 630);
				adminStage.setScene(admScene);
				adminStage.show();

			});
			
reportTableView();




			firstPane.getChildren().add(firstBG);
			firstPane.getChildren().addAll(text, adminButton, clientButton, loadCarsFile, loadOrdersFile, save, save1);
			Scene firstScene = new Scene(firstPane, 650, 530);
			stage.setScene(firstScene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("resource")
	public void readCarsFile() throws FileNotFoundException, NumberFormatException, ParseException {
		Scanner read = new Scanner(Cfile);
		read.nextLine();
		String[] array = null;
		while (read.hasNext()) {
			array = read.nextLine().split(",");
			/*
			 * 0 -> brand 1 -> model 2 -> year 3 -> color 4 -> price
			 */

			myDLL.addSorted(new Brand(array[0].trim()));

			((Brand) myDLL.get(array[0].trim()).getElement()).getList().addSorted(new Cars(array[0].trim(),
					array[1].trim(), Integer.parseInt(array[2].trim()), array[3].trim(), array[4].trim()));
			cars.addSorted(new Cars(array[0].trim(), array[1].trim(), Integer.parseInt(array[2].trim()),
					array[3].trim(), array[4].trim()));

		}
		myDLL.printList();
	}

	@SuppressWarnings("resource")
	public void readOrdersFile() throws FileNotFoundException, NumberFormatException, ParseException {
		Scanner read = new Scanner(Ofile);
		read.nextLine();

		while (read.hasNext()) {
			String[] array = read.nextLine().split(",");
			/*
			 * 0 -> customerName 1 -> customerPhone 2 -> brand 3 -> model 4 -> year 5 ->
			 * color 6 -> price 7 -> orderDate 8 -> status
			 */
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(array[7].trim());

			if (array[8].trim().equals("Finished"))
				stack.addLast(new Order(array[0].trim(), array[1].trim(), array[2].trim(), array[3].trim(),
						Integer.parseInt(array[4].trim()), array[5].trim(), array[6].trim(), date, array[8].trim()));
			else {

				queue.addLast(new Order(array[0].trim(), array[1].trim(), array[2].trim(), array[3].trim(),
						Integer.parseInt(array[4].trim()), array[5].trim(), array[6].trim(), date, array[8].trim()));
			}
		}
		queue.printList();
		stack.printList();
	}

	public void saveInCarsFile() throws NumberFormatException, FileNotFoundException, ParseException {
		FileChooser filechooser = new FileChooser();
		File e = filechooser.showSaveDialog(new Stage());
		if (e != null) {
			try {
				PrintWriter write = new PrintWriter(e);
				write.print("Brand, Model, Year, Color, Price \n");
				Node current = myDLL.getfirst();
				myDLL.printList();
				System.out.println(myDLL.getSize());

				for (int i = 0; i < myDLL.getSize(); i++) {
					System.out.println("i = " + i);

					Node1 curr = ((Brand) current.getElement()).getList().getFirst();
					for (int j = 0; j < ((Brand) current.getElement()).getList().getSize(); j++) {
						System.out.println("j = " + j);
						write.print(((Cars) curr.getElement()).toString());
						curr = curr.getNext();
					}
					current = current.getNext();
				}
				write.flush();

			} catch (Exception e1) {
			}
		}
	}

	public void saveInOrdersFile() throws NumberFormatException, FileNotFoundException, ParseException {
		FileChooser filechooser = new FileChooser();
		File e = filechooser.showSaveDialog(new Stage());
		if (e != null) {
			try (PrintWriter write = new PrintWriter(e)) {
				write.print("CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, OrderDate, OrderStatus\n");

				ordNode temp2;
				ordSLL stack2 = new ordSLL();

				int size = stack.getSize();
				for (int i = 0; i < size; i++) {
					nnn = stack.getLast();
					System.out.println(((Order) nnn.getElement()).toString() + "  " + i);
					write.print((((Order) nnn.getElement()).toString()));
					stack2.addLast(new Order(((Order) nnn.getElement()).getCname(),
							((Order) nnn.getElement()).getCphone(), ((Order) nnn.getElement()).getBrand(),
							((Order) nnn.getElement()).getModel(), ((Order) nnn.getElement()).getYear(),
							((Order) nnn.getElement()).getColor(), ((Order) nnn.getElement()).getPrice(),
							((Order) nnn.getElement()).getOrderDate(), ((Order) nnn.getElement()).getOrderStatus()));
					stack.removeLast();
				}

				int size2 = stack2.getSize();
				for (int a = 0; a < size2; a++) {
					nnn = stack2.getLast();
					stack.addLast(new Order(((Order) nnn.getElement()).getCname(),
							((Order) nnn.getElement()).getCphone(), ((Order) nnn.getElement()).getBrand(),
							((Order) nnn.getElement()).getModel(), ((Order) nnn.getElement()).getYear(),
							((Order) nnn.getElement()).getColor(), ((Order) nnn.getElement()).getPrice(),
							((Order) nnn.getElement()).getOrderDate(), ((Order) nnn.getElement()).getOrderStatus()));
					stack2.removeLast();
					System.out.println(a);
				}

				ordNode temp;
				for (int q = 0; q < queue.getSize(); q++) {
					nnn = queue.getFirst();
					write.print(((Order) nnn.getElement()).toString());
					System.out.println("worood");
					temp = queue.getFirst();
					queue.addLast(new Order(((Order) temp.getElement()).getCname(),
							((Order) temp.getElement()).getCphone(), ((Order) temp.getElement()).getBrand(),
							((Order) temp.getElement()).getModel(), ((Order) temp.getElement()).getYear(),
							((Order) temp.getElement()).getColor(), ((Order) temp.getElement()).getPrice(),
							((Order) temp.getElement()).getOrderDate(), ((Order) temp.getElement()).getOrderStatus()));
					queue.removeFirst();

				}

			} catch (Exception e2) {
			}
		}
	}

	public void fillBrandComboBox1() {
		brandCombobox.getItems().clear();
		Node temp = myDLL.getfirst();
		brandsName.add("Brand");
		for (int i = 0; i < myDLL.getSize(); i++) {
			brandsName.add(temp.getElement().toString());
			temp = temp.getNext();
		}
		brandCombobox.getSelectionModel().select(0);
		brandCombobox.setPrefSize(95, 40);
	}

	public void fillBrand() {
		bb.getItems().clear();
		Node temp = myDLL.getfirst();
		for (int i = 0; i < myDLL.getSize(); i++) {
			b.add(temp.getElement().toString());
			temp = temp.getNext();
		}
		bb.getSelectionModel().select(0);
		bb.setPrefSize(90, 30);
		brandLable.setText(bb.getSelectionModel().getSelectedItem());
	}

	@SuppressWarnings("unchecked")
	public void carsTableView(String b, String m, String y, String c, String p) {
		CarsClientTV.refresh();
		CarsClientTV.getItems().clear();
		CarsClientTV.getColumns().clear();
		CarsClientTV.setEditable(true);
		CarsClientTV.setPadding(new Insets(10));
		CarsClientTV.setStyle("-fx-background-color : #1988b7");
		CarsClientTV.setLayoutX(130);
		CarsClientTV.setLayoutY(180);
		CarsClientTV.setPrefSize(375, 350);

		TableColumn<Cars, String> model = new TableColumn<Cars, String>("model");
		model.setStyle(style);
		model.setPrefWidth(90);
		model.setCellValueFactory(new PropertyValueFactory<Cars, String>("model"));

		TableColumn<Cars, Integer> year = new TableColumn<Cars, Integer>("year");
		year.setStyle(style);
		year.setPrefWidth(90);
		year.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("year"));

		TableColumn<Cars, String> color = new TableColumn<Cars, String>("color");
		color.setStyle(style);
		color.setPrefWidth(90);
		color.setCellValueFactory(new PropertyValueFactory<Cars, String>("color"));

		TableColumn<Cars, String> price = new TableColumn<Cars, String>("price");
		price.setStyle(style);
		price.setPrefWidth(90);
		price.setCellValueFactory(new PropertyValueFactory<Cars, String>("price"));

		CarsClientTV.getColumns().addAll(model, year, color, price);

		if (b != null && !b.trim().isEmpty() && m == "Model" && y == "Year" && c == "Color" && p == "Price") {
			node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
			while (node1 != null) {
				if (((Cars) node1.getElement()).getBrand().equals(b))
					CarsClientTV.getItems()
							.add(new Cars(((Cars) node1.getElement()).getModel(), ((Cars) node1.getElement()).getYear(),
									((Cars) node1.getElement()).getColor(), ((Cars) node1.getElement()).getPrice()));
				node1 = node1.getNext();
			}
		} else if (b != null) {
			if (!b.trim().isEmpty() && m != "Model" && p == "Price" && y == "Year" && c == "Color") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {

					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getModel().equals(m)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && p == "Price" && c == "Color" && m == "Model") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && c != "Color" && m == "Model" && p == "Price" && y == "Year") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getColor().equals(c)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && c != "Color" && m == "Model" && p != "Price" && y == "Year") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getColor().equals(c)
							&& ((Cars) node1.getElement()).getPrice().equals(p)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && p != "Price" && m == "Model" && c == "Color" && y == "Year") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getPrice().equals(p)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && p != "Price" && m != "Model" && c == "Color" && y == "Year") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					System.out.println(2);
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getPrice().equals(p)
							&& ((Cars) node1.getElement()).getModel().equals(m)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m != "Model" && c == "Color" && p == "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getModel().equals(m)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m == "Model" && c == "Color" && p != "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getPrice().equals(p)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y == "Year" && m != "Model" && c != "Color" && p == "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getColor().equals(c)
							&& ((Cars) node1.getElement()).getModel().equals(m)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y == "Year" && m != "Model" && c != "Color" && p != "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getColor().equals(c)
							&& ((Cars) node1.getElement()).getModel().equals(m)
							&& ((Cars) node1.getElement()).getPrice().equals(p)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m != "Model" && c == "Color" && p != "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getModel().equals(m)
							&& ((Cars) node1.getElement()).getPrice().equals(p)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m != "Model" && c != "Color" && p == "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getModel().equals(m)
							&& ((Cars) node1.getElement()).getColor().equals(c)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m == "Model" && c != "Color" && p != "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getPrice().equals(p)
							&& ((Cars) node1.getElement()).getColor().equals(c)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m != "Model" && c != "Color" && p == "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& ((Cars) node1.getElement()).getColor().equals(c)
							&& ((Cars) node1.getElement()).getModel().equals(m)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}
			if (!b.trim().isEmpty() && y != "Year" && m != "Model" && c != "Color" && p != "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getModel().equals(m)
							&& ((Cars) node1.getElement()).getPrice().equals(p)
							&& ((Cars) node1.getElement()).getColor().equals(c)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

			if (!b.trim().isEmpty() && y != "Year" && m == "Model" && c != "Color" && p == "Price") {
				node1 = ((Brand) myDLL.get(b).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(b)
							&& String.valueOf(((Cars) node1.getElement()).getYear()).equals(y)
							&& ((Cars) node1.getElement()).getColor().equals(c)) {
						CarsClientTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					}
					node1 = node1.getNext();
				}
			}

		}
		CarsClientTV.refresh();
		CarsClientTV.setEditable(false);
	}

	@SuppressWarnings("unchecked")
	public void clientOrdersTV(String name, String phone) {
		String tempname = name;
		System.out.println(name + "  " + phone + "  done");
		cars.printList();
		cliOTV.refresh();
		cliOTV.getItems().clear();
		cliOTV.getColumns().clear();
		cliOTV.setEditable(true);
		cliOTV.setPadding(new Insets(10));
		cliOTV.setStyle("-fx-background-color : #1988b7");
		cliOTV.setLayoutX(20);
		cliOTV.setLayoutY(90);
		cliOTV.setPrefSize(610, 350);

		TableColumn<Order, String> brand = new TableColumn<Order, String>("brand");
		brand.setStyle(style);
		brand.setPrefWidth(90);
		brand.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));

		TableColumn<Order, String> model = new TableColumn<Order, String>("model");
		model.setStyle(style);
		model.setPrefWidth(100);
		model.setCellValueFactory(new PropertyValueFactory<Order, String>("model"));

		TableColumn<Order, Integer> year = new TableColumn<Order, Integer>("year");
		year.setStyle(style);
		year.setPrefWidth(70);
		year.setCellValueFactory(new PropertyValueFactory<Order, Integer>("year"));

		TableColumn<Order, String> color = new TableColumn<Order, String>("color");
		color.setStyle(style);
		color.setPrefWidth(70);
		color.setCellValueFactory(new PropertyValueFactory<Order, String>("color"));

		TableColumn<Order, String> price = new TableColumn<Order, String>("price");
		price.setStyle(style);
		price.setPrefWidth(70);
		price.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));

		TableColumn<Order, String> orderDate = new TableColumn<Order, String>("orderDate");
		orderDate.setStyle(style);
		orderDate.setPrefWidth(100);
		orderDate.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));

		TableColumn<Order, String> orderStatus = new TableColumn<Order, String>("orderStatus");
		orderStatus.setStyle(style);
		orderStatus.setPrefWidth(100);
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));

		cliOTV.getColumns().addAll(brand, model, year, color, price, orderDate, orderStatus);

		if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
//------------------inprocess orders-------------------------
			ordNode temp;
			for (int i = 0; i < queue.getSize(); i++) {
				nnn = queue.getFirst();
				if (((Order) nnn.getElement()).getCname().equals(name)
						&& ((Order) nnn.getElement()).getCphone().equals(phone)) {

					cliOTV.getItems()
							.add(new Order(((Order) nnn.getElement()).getBrand(), ((Order) nnn.getElement()).getModel(),
									((Order) nnn.getElement()).getYear(), ((Order) nnn.getElement()).getColor(),
									((Order) nnn.getElement()).getPrice(), ((Order) nnn.getElement()).getOrderDate(),
									((Order) nnn.getElement()).getOrderStatus()));

				}
				temp = queue.getFirst();
				queue.addLast(new Order(((Order) temp.getElement()).getCname(), ((Order) temp.getElement()).getCphone(),
						((Order) temp.getElement()).getBrand(), ((Order) temp.getElement()).getModel(),
						((Order) temp.getElement()).getYear(), ((Order) temp.getElement()).getColor(),
						((Order) temp.getElement()).getPrice(), ((Order) temp.getElement()).getOrderDate(),
						((Order) temp.getElement()).getOrderStatus()));
				queue.removeFirst();
			}
			queue.printList();
		}
//----------------finished orders-----------------------------	
//		if (!tempname.trim().isEmpty() && !phone.trim().isEmpty()) {
//			ordNode temp2;
//			ordSLL stack2 = new ordSLL();
//			ordNode nn;
//			stack.printList();
//			int si = stack.getSize();
//			for (int i = 0; i < si; i++) {
//				nn = stack.getLast();
//				if (((Order) nn.getElement()).getCname().equals(tempname)
//						&& ((Order) nn.getElement()).getCphone().equals(phone)) {
//
//					cliOTV.getItems()
//							.add(new Order(((Order) nn.getElement()).getBrand(), ((Order) nn.getElement()).getModel(),
//									((Order) nn.getElement()).getYear(), ((Order) nn.getElement()).getColor(),
//									((Order) nn.getElement()).getPrice(), ((Order) nn.getElement()).getOrderDate(),
//									((Order) nn.getElement()).getOrderStatus()));
//
//				}
//				stack2.addLast(new Order(((Order) nn.getElement()).getBrand(), ((Order) nn.getElement()).getModel(),
//						((Order) nn.getElement()).getYear(), ((Order) nn.getElement()).getColor(),
//						((Order) nn.getElement()).getPrice(), ((Order) nn.getElement()).getOrderDate(),
//						((Order) nn.getElement()).getOrderStatus()));
//				
//				stack.removeLast();
//			}
//			stack.printList();
//			int ssi = stack2.getSize();
//			for(int a=0 ;a<ssi ;a++) {
//				nn = stack2.getLast();
//				stack.addLast(new Order(((Order) nn.getElement()).getBrand(), ((Order) nn.getElement()).getModel(),
//						((Order) nn.getElement()).getYear(), ((Order) nn.getElement()).getColor(),
//						((Order) nn.getElement()).getPrice(), ((Order) nn.getElement()).getOrderDate(),
//						((Order) nn.getElement()).getOrderStatus()));
//				stack2.removeLast();
//			}
//			stack.printList();
//		}

		cliOTV.refresh();
		cliOTV.setEditable(false);
	}

	@SuppressWarnings("unchecked")
	public void adminTableView() {
		admTV.refresh();
		admTV.getColumns().clear();
		admTV.getItems().clear();
		admTV.setEditable(true);
		admTV.setPadding(new Insets(10));
		admTV.setStyle("-fx-background-color : #1988b7");
		admTV.setLayoutX(20);
		admTV.setLayoutY(90);
		admTV.setPrefSize(610, 350);

		TableColumn<Order, String> Cname = new TableColumn<Order, String>("Cname");
		Cname.setStyle(style);
		Cname.setPrefWidth(80);
		Cname.setCellValueFactory(new PropertyValueFactory<Order, String>("Cname"));

		TableColumn<Order, String> Cphone = new TableColumn<Order, String>("Cphone");
		Cphone.setStyle(style);
		Cphone.setPrefWidth(70);
		Cphone.setCellValueFactory(new PropertyValueFactory<Order, String>("Cphone"));

		TableColumn<Order, String> brand = new TableColumn<Order, String>("brand");
		brand.setStyle(style);
		brand.setPrefWidth(60);
		brand.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));

		TableColumn<Order, String> model = new TableColumn<Order, String>("model");
		model.setStyle(style);
		model.setPrefWidth(60);
		model.setCellValueFactory(new PropertyValueFactory<Order, String>("model"));

		TableColumn<Order, Integer> year = new TableColumn<Order, Integer>("year");
		year.setStyle(style);
		year.setPrefWidth(50);
		year.setCellValueFactory(new PropertyValueFactory<Order, Integer>("year"));

		TableColumn<Order, String> color = new TableColumn<Order, String>("color");
		color.setStyle(style);
		color.setPrefWidth(50);
		color.setCellValueFactory(new PropertyValueFactory<Order, String>("color"));

		TableColumn<Order, String> price = new TableColumn<Order, String>("price");
		price.setStyle(style);
		price.setPrefWidth(50);
		price.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));

		TableColumn<Order, String> orderDate = new TableColumn<Order, String>("orderDate");
		orderDate.setStyle(style);
		orderDate.setPrefWidth(90);
		orderDate.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));

		TableColumn<Order, String> orderStatus = new TableColumn<Order, String>("orderStatus");
		orderStatus.setStyle(style);
		orderStatus.setPrefWidth(90);
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));

		admTV.getColumns().addAll(Cname, Cphone, brand, model, year, color, price, orderDate, orderStatus);

		ordNode temp;
		for (int i = 0; i < queue.getSize(); i++) {
			nnn = queue.getFirst();
			admTV.getItems()
					.add(new Order(((Order) nnn.getElement()).getCname(), ((Order) nnn.getElement()).getCphone(),
							((Order) nnn.getElement()).getBrand(), ((Order) nnn.getElement()).getModel(),
							((Order) nnn.getElement()).getYear(), ((Order) nnn.getElement()).getColor(),
							((Order) nnn.getElement()).getPrice(), ((Order) nnn.getElement()).getOrderDate(),
							((Order) nnn.getElement()).getOrderStatus()));

			temp = queue.getFirst();
			queue.addLast(new Order(((Order) temp.getElement()).getCname(), ((Order) temp.getElement()).getCphone(),
					((Order) temp.getElement()).getBrand(), ((Order) temp.getElement()).getModel(),
					((Order) temp.getElement()).getYear(), ((Order) temp.getElement()).getColor(),
					((Order) temp.getElement()).getPrice(), ((Order) temp.getElement()).getOrderDate(),
					((Order) temp.getElement()).getOrderStatus()));
			queue.removeFirst();

		}

		admTV.refresh();
		admTV.setEditable(false);
	}

	public void createBrandTV() {
		brandTV.refresh();
		brandTV.getItems().clear();
		brandTV.getColumns().clear();
		brandTV.setEditable(true);
		brandTV.setPadding(new Insets(10));
		brandTV.setStyle("-fx-background-color : #1988b7");
		brandTV.setLayoutX(160);
		brandTV.setLayoutY(90);
		brandTV.setPrefSize(290, 300);

		TableColumn<Brand, String> brand = new TableColumn<Brand, String>("brand");
		brand.setStyle(style);
		brand.setPrefWidth(270);
		brand.setCellValueFactory(new PropertyValueFactory<Brand, String>("brand"));
		// brand.setCellFactory(TextFieldTableCell.<Brand>forTableColumn());
		brand.setCellFactory(TextFieldTableCell.forTableColumn());
		brand.setOnEditCommit(new EventHandler<CellEditEvent<Brand, String>>() {
			@Override
			public void handle(CellEditEvent<Brand, String> t) {
				Brand bb = t.getRowValue();
				SLL sll = ((Brand) myDLL.get(bb.getBrand()).getElement()).getList();
				// update brand name in double and single linked list :-

//				myDLL.remove(bb.getBrand());
//				myDLL.addSorted(t.getNewValue());
//				myDLL.printList();
//				((Brand) myDLL.get(t.getNewValue()).getElement()).setList(sll);

				((Brand) myDLL.get(bb.getBrand()).getElement()).setBrand(t.getNewValue());
				node1 = ((Brand) myDLL.get(t.getNewValue()).getElement()).getList().getFirst();
				for (int a = 0; a < ((Brand) myDLL.get(t.getNewValue()).getElement()).getList().getSize(); a++) {
					((Cars) node1.getElement()).setBrand(t.getNewValue());
					node1 = node1.getNext();
				}

				fillBrand();
				reportTableView();
				
				// update in customers orders :-

//				ordNode temp;
//				for (int j = 0; j < queue.getSize(); j++) {
//					nnn = queue.getFirst();
//					if (((Order) nnn.getElement()).getBrand().equals(bb.getBrand())) {
//						((Order) nnn.getElement()).setBrand(t.getNewValue());
//					}
//					temp = queue.getFirst();
//					queue.addLast(new Order(((Order) temp.getElement()).getCname(),
//							((Order) temp.getElement()).getCphone(), ((Order) temp.getElement()).getBrand(),
//							((Order) temp.getElement()).getModel(), ((Order) temp.getElement()).getYear(),
//							((Order) temp.getElement()).getColor(), ((Order) temp.getElement()).getPrice(),
//							((Order) temp.getElement()).getOrderDate(), ((Order) temp.getElement()).getOrderStatus()));
//					queue.removeFirst();
//				}

				adminTableView();

				((Brand) myDLL.get(t.getNewValue()).getElement()).getList().printList();

			}
		});

		brandTV.getColumns().add(brand);

		node = myDLL.getfirst();
		for (int i = 0; i < myDLL.getSize(); i++) {
			brandTV.getItems().add(new Brand(((Brand) node.getElement()).getBrand()));
			node = node.getNext();
		}

		brandTV.refresh();
	}

	public void createCarTV(String s) {
		carTV.refresh();
		carTV.getItems().clear();
		carTV.getColumns().clear();
		carTV.setEditable(true);
		carTV.setPadding(new Insets(10));
		carTV.setStyle("-fx-background-color : #1988b7");
		carTV.setLayoutX(130);
		carTV.setLayoutY(80);
		carTV.setPrefSize(375, 350);

		TableColumn<Cars, String> model = new TableColumn<Cars, String>("model");
		model.setStyle(style);
		model.setPrefWidth(90);
		model.setCellValueFactory(new PropertyValueFactory<Cars, String>("model"));

		TableColumn<Cars, Integer> year = new TableColumn<Cars, Integer>("year");
		year.setStyle(style);
		year.setPrefWidth(90);
		year.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("year"));

		TableColumn<Cars, String> color = new TableColumn<Cars, String>("color");
		color.setStyle(style);
		color.setPrefWidth(90);
		color.setCellValueFactory(new PropertyValueFactory<Cars, String>("color"));

		TableColumn<Cars, String> price = new TableColumn<Cars, String>("price");
		price.setStyle(style);
		price.setPrefWidth(90);
		price.setCellValueFactory(new PropertyValueFactory<Cars, String>("price"));

		carTV.getColumns().addAll(model, year, color, price);

		if (!s.trim().isEmpty()) {
			if (myDLL.get(s) != null) {
				node1 = ((Brand) myDLL.get(s).getElement()).getList().getFirst();
				while (node1 != null) {
					if (((Cars) node1.getElement()).getBrand().equals(s))
						carTV.getItems()
								.add(new Cars(((Cars) node1.getElement()).getModel(),
										((Cars) node1.getElement()).getYear(), ((Cars) node1.getElement()).getColor(),
										((Cars) node1.getElement()).getPrice()));
					node1 = node1.getNext();
				}

				carTV.refresh();
			}
		}
	}

	public void reportTableView() {
		reportTV.refresh();
		reportTV.getItems().clear();
		reportTV.getColumns().clear();
		reportTV.setEditable(true);
		reportTV.setPadding(new Insets(10));
		reportTV.setStyle("-fx-background-color : #1988b7");
		reportTV.setLayoutX(90);
		reportTV.setLayoutY(80);
		reportTV.setPrefSize(460, 350);

		TableColumn<Report, String> brand = new TableColumn<Report, String>("brand");
		brand.setStyle(style);
		brand.setPrefWidth(90);
		brand.setCellValueFactory(new PropertyValueFactory<Report, String>("brand"));

		TableColumn<Report, Integer> price1 = new TableColumn<Report, Integer>("lower price");
		price1.setStyle(style);
		price1.setPrefWidth(90);
		price1.setCellValueFactory(new PropertyValueFactory<Report, Integer>("price1"));

		TableColumn<Report, String> price2 = new TableColumn<Report, String>("heigh price");
		price2.setStyle(style);
		price2.setPrefWidth(90);
		price2.setCellValueFactory(new PropertyValueFactory<Report, String>("price2"));

		TableColumn<Report, String> heigh = new TableColumn<Report, String>("lower brand");
		heigh.setStyle(style);
		heigh.setPrefWidth(150);
		heigh.setCellValueFactory(new PropertyValueFactory<Report, String>("heigh"));

		TableColumn<Report, String> lower = new TableColumn<Report, String>("heigher brand");
		lower.setStyle(style);
		lower.setPrefWidth(150);
		lower.setCellValueFactory(new PropertyValueFactory<Report, String>("lower"));

		reportTV.getColumns().addAll(brand, price1, price2, heigh, lower);

		node = myDLL.getfirst();
		for (int i = 0; i < myDLL.getSize(); i++) {
			reportTV.getItems()
					.add(new Report(((Brand) node.getElement()).getBrand(),
							heighPrice(((Brand) node.getElement()).getBrand()),
							lowerPrice(((Brand) node.getElement()).getBrand()), getHeighModel(), getLowerModel()));
			node = node.getNext();
		}
		System.out.println(getLowerModel());

		reportTV.refresh();
	}

	@SuppressWarnings("unchecked")
	public void stackTableView() {
		stackTV.refresh();
		stackTV.getColumns().clear();
		stackTV.getItems().clear();
		stackTV.setEditable(true);
		stackTV.setPadding(new Insets(10));
		stackTV.setStyle("-fx-background-color : #1988b7");
		stackTV.setLayoutX(20);
		stackTV.setLayoutY(90);
		stackTV.setPrefSize(610, 350);

		TableColumn<Order, String> Cname = new TableColumn<Order, String>("Cname");
		Cname.setStyle(style);
		Cname.setPrefWidth(80);
		Cname.setCellValueFactory(new PropertyValueFactory<Order, String>("Cname"));

		TableColumn<Order, String> Cphone = new TableColumn<Order, String>("Cphone");
		Cphone.setStyle(style);
		Cphone.setPrefWidth(70);
		Cphone.setCellValueFactory(new PropertyValueFactory<Order, String>("Cphone"));

		TableColumn<Order, String> brand = new TableColumn<Order, String>("brand");
		brand.setStyle(style);
		brand.setPrefWidth(60);
		brand.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));

		TableColumn<Order, String> model = new TableColumn<Order, String>("model");
		model.setStyle(style);
		model.setPrefWidth(60);
		model.setCellValueFactory(new PropertyValueFactory<Order, String>("model"));

		TableColumn<Order, Integer> year = new TableColumn<Order, Integer>("year");
		year.setStyle(style);
		year.setPrefWidth(50);
		year.setCellValueFactory(new PropertyValueFactory<Order, Integer>("year"));

		TableColumn<Order, String> color = new TableColumn<Order, String>("color");
		color.setStyle(style);
		color.setPrefWidth(50);
		color.setCellValueFactory(new PropertyValueFactory<Order, String>("color"));

		TableColumn<Order, String> price = new TableColumn<Order, String>("price");
		price.setStyle(style);
		price.setPrefWidth(50);
		price.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));

		TableColumn<Order, String> orderDate = new TableColumn<Order, String>("orderDate");
		orderDate.setStyle(style);
		orderDate.setPrefWidth(90);
		orderDate.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));

		TableColumn<Order, String> orderStatus = new TableColumn<Order, String>("orderStatus");
		orderStatus.setStyle(style);
		orderStatus.setPrefWidth(90);
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));

		stackTV.getColumns().addAll(Cname, Cphone, brand, model, year, color, price, orderDate, orderStatus);

		ordNode temp2;
		ordSLL stack2 = new ordSLL();

		int size = stack.getSize();
		for (int i = 0; i < size; i++) {
			nnn = stack.getLast();

			stackTV.getItems()
					.add(new Order(((Order) nnn.getElement()).getCname(), ((Order) nnn.getElement()).getCphone(),
							((Order) nnn.getElement()).getBrand(), ((Order) nnn.getElement()).getModel(),
							((Order) nnn.getElement()).getYear(), ((Order) nnn.getElement()).getColor(),
							((Order) nnn.getElement()).getPrice(), ((Order) nnn.getElement()).getOrderDate(),
							((Order) nnn.getElement()).getOrderStatus()));

			stack2.addLast(new Order(((Order) nnn.getElement()).getCname(), ((Order) nnn.getElement()).getCphone(),
					((Order) nnn.getElement()).getBrand(), ((Order) nnn.getElement()).getModel(),
					((Order) nnn.getElement()).getYear(), ((Order) nnn.getElement()).getColor(),
					((Order) nnn.getElement()).getPrice(), ((Order) nnn.getElement()).getOrderDate(),
					((Order) nnn.getElement()).getOrderStatus()));

			// nnn=nnn.getNext();
			stack.removeLast();

		}

		System.out.println("stack2");
		stack2.printList();
		int size2 = stack2.getSize();
		for (int a = 0; a < size2; a++) {
			nnn = stack2.getLast();
			stack.addLast(new Order(((Order) nnn.getElement()).getCname(), ((Order) nnn.getElement()).getCphone(),
					((Order) nnn.getElement()).getBrand(), ((Order) nnn.getElement()).getModel(),
					((Order) nnn.getElement()).getYear(), ((Order) nnn.getElement()).getColor(),
					((Order) nnn.getElement()).getPrice(), ((Order) nnn.getElement()).getOrderDate(),
					((Order) nnn.getElement()).getOrderStatus()));
			stack2.removeLast();
			System.out.println(a);
		}
		System.out.println("stack");
		stack.printList();

		stackTV.refresh();
	}

	String he = null;

	public String heighPrice(String s) {
// get heigher price
		String price = "";
		node1 = ((Brand) myDLL.get(s).getElement()).getList().getFirst();
		String temp = ((Cars) node1.getElement()).getPrice().substring(0,
				((Cars) node1.getElement()).getPrice().length() - 1);
		for (int i = 0; i < ((Brand) myDLL.get(s).getElement()).getList().getSize(); i++) {
			price = ((Cars) node1.getElement()).getPrice().substring(0,
					((Cars) node1.getElement()).getPrice().length() - 1);
			if (Integer.parseInt(price) < Integer.parseInt(temp)) {
				temp = price;
			}
			node1 = node1.getNext();
		}

		// get heigher price brand  
		node1 = ((Brand) myDLL.get(s).getElement()).getList().getFirst();
		for (int i = 0; i < ((Brand) myDLL.get(s).getElement()).getList().getSize(); i++) {
			if (((Cars) node1.getElement()).getPrice().equals(temp + "K"))
				he = ((Cars) node1.getElement()).getModel() + "," + ((Cars) node1.getElement()).getColor() + ","
						+ ((Cars) node1.getElement()).getYear();
			node1 = node1.getNext();
		}

		return temp + "K";
	}

	public String getHeighModel() {
		return he;
	}

	String lo = null;

	public String lowerPrice(String s) {
		//to get lower price
		String price = "";
		node1 = ((Brand) myDLL.get(s).getElement()).getList().getFirst();
		String temp = ((Cars) node1.getElement()).getPrice().substring(0,
				((Cars) node1.getElement()).getPrice().length() - 1);
		for (int i = 0; i < ((Brand) myDLL.get(s).getElement()).getList().getSize(); i++) {
			price = ((Cars) node1.getElement()).getPrice().substring(0,
					((Cars) node1.getElement()).getPrice().length() - 1);
			if (Integer.parseInt(price) > Integer.parseInt(temp)) {
				temp = price;
			}
			node1 = node1.getNext();
		}

		//to get the lower price brand 
		node1 = ((Brand) myDLL.get(s).getElement()).getList().getFirst();
		for (int i = 0; i < ((Brand) myDLL.get(s).getElement()).getList().getSize(); i++) {
			if (((Cars) node1.getElement()).getPrice().equals(temp + "K"))
				lo = ((Cars) node1.getElement()).getModel() + "," + ((Cars) node1.getElement()).getColor() + ","
						+ ((Cars) node1.getElement()).getYear();
			node1 = node1.getNext();
		}

		return temp + "K";
	}

	public String getLowerModel() {
		return lo;
	}
}
