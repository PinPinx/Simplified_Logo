package view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import view.dialogs.InputDialogBox;
import view.dialogs.TextInputDialogBox;
import view.turtle.TurtleImage;
import model.TurtleUpdate;
import model.ViewUpdate;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TurtleWindow extends Group implements Observer {
	
	private Canvas mainCanvas;
	private GraphicsContext mainGC;
	private double myWidth;
	private double myHeight;
	private int numTurtles;
	
	private Group myLayers = new Group();
	private HashMap<Integer, GraphicsContext> gc = new HashMap<>();
	private HashMap<Integer, TurtleImage> myTurtles = new HashMap<>();
	private Palette myPalette = new Palette();
	private Group myTImages =  new Group();
	
	
	private ContextMenu contextMenu;
	private Menu imagePalettes;
	private Menu colorPalettes;
	private ToggleGroup imagePaletteGroup = new ToggleGroup();
	private ToggleGroup colorPaletteGroup = new ToggleGroup();

	public TurtleWindow(double width, double height) {

		myWidth = width;
		myHeight = height;
		numTurtles = 0;
		mainCanvas = new Canvas(myWidth, myHeight);
		mainGC = mainCanvas.getGraphicsContext2D();
		initializeMenu();

		this.getChildren().addAll(myLayers, mainCanvas, myTImages);
		
		addTurtle();
		
	}
	
	private void initializeMenu(){
		setDefaultImagePalette();
		setImagePaletteMenu();
		
		setDefaultColorPalette();
		setColorPaletteMenu();
		
		contextMenu = new ContextMenu(imagePalettes, colorPalettes);
		
		mainCanvas.setOnMouseClicked(e->{
			setImagePaletteMenu();
			setColorPaletteMenu();
			contextMenu = new ContextMenu(imagePalettes, colorPalettes);
			popMyMenu(e);
			
		});
		
	}
	
	private void popMyMenu(MouseEvent event){
		contextMenu.show(this, event.getX(), event.getY());
	}
	
	private void setDefaultImagePalette(){
		ImageIndex def_1 = new ImageIndex(0, "Turtle", (new Image(getClass().getResourceAsStream("/resources/images/turtle.gif"))));
		ImageIndex def_2 = new ImageIndex(1, "Traingle", (new Image(getClass().getResourceAsStream("/resources/images/triangular.jpg"))));
		ImageIndex def_3 = new ImageIndex(2, "Star", (new Image(getClass().getResourceAsStream("/resources/images/star.png"))));
		myPalette.addImage(def_1, def_2, def_3);
	}
	
	private void setDefaultColorPalette(){
		ColorIndex def_1 = new ColorIndex(0, "Red", Color.RED);
		ColorIndex def_2 = new ColorIndex(1, "Blue", Color.BLUE);
		ColorIndex def_3 = new ColorIndex(2, "Green", Color.GREEN);
		myPalette.addColor(def_1, def_2, def_3);
	}
	
	private void setImagePaletteMenu(){
		imagePalettes = new Menu("Image Palettes List");
		ArrayList<ImageIndex> currentImageList = myPalette.getImageList();
		for (ImageIndex imgx : currentImageList) {
			RadioMenuItem imgChoice = new RadioMenuItem(imgx.getIndex()+" "+imgx.getName());
			imgChoice.setToggleGroup(imagePaletteGroup);
			imgChoice.setOnAction(changeImage -> {
				modifyImagePaletteMenu(imgx.getIndex());
			});
			imagePalettes.getItems().add(imgChoice);
		}
		
		RadioMenuItem imgChoice = new RadioMenuItem("Add new index... ");
		imgChoice.setToggleGroup(imagePaletteGroup);
		imgChoice.setOnAction(changeImage -> {
			modifyImagePaletteMenu(myPalette.imageListSize());
		});
		imagePalettes.getItems().add(imgChoice);
	}
	
	private void setColorPaletteMenu(){
		colorPalettes = new Menu("Color Palettes List");
		ArrayList<ColorIndex> currentColorList = myPalette.getColorList();
		for (ColorIndex colx : currentColorList){
			RadioMenuItem colorChoice = new RadioMenuItem(colx.getIndex()+" "+colx.getName());
			colorChoice.setToggleGroup(colorPaletteGroup);
			colorChoice.setOnAction(e->{
				modifyColorPaletteMenu(colx.getIndex());
			});
			colorPalettes.getItems().add(colorChoice);
		}
		
		RadioMenuItem colorChoice = new RadioMenuItem("Add new index... ");
		colorChoice.setToggleGroup(colorPaletteGroup);
		colorChoice.setOnAction(changeColor->{
			modifyColorPaletteMenu(myPalette.colorListSize());
		});
		colorPalettes.getItems().add(colorChoice);
	}
	
	private void modifyImagePaletteMenu(int index){
		JFileChooser imageChooser = new JFileChooser(System.getProperties()
				.getProperty("user.dir") + "/src/resources/images");
		imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int retval = imageChooser.showOpenDialog(null);
		if (retval != JFileChooser.APPROVE_OPTION) {
			return;
		}
		InputDialogBox dialog = new TextInputDialogBox("Type in a name for your image or shape");
		String indexName = ((String) dialog.showInputDialog()).trim();
		ImageIndex imgx = new ImageIndex(index, indexName, new Image(imageChooser.getSelectedFile().toURI().toString()));
		myPalette.updateImage(index, imgx);
		return;
	}
	
	private void modifyColorPaletteMenu(int index){
		java.awt.Color color = JColorChooser.showDialog(null, "Choose color to add to palette", null);
		InputDialogBox dialog = new TextInputDialogBox("Type in a name for your color");
		String indexName = ((String) dialog.showInputDialog()).trim();
		ColorIndex colx = new ColorIndex(index, indexName, color);
		myPalette.updateColor(index, colx);
		return;
	}

	public void addTurtle() {
		int i=0;
		while (myTurtles.keySet().contains(i)) {
			i++;
		}
		addTurtle(i);
	}
	

	public void addTurtle(int id) {
		numTurtles++;
		Canvas layer = new Canvas(mainCanvas.getWidth(), mainCanvas.getWidth());
		GraphicsContext layerGC = layer.getGraphicsContext2D();
		gc.put(id, layerGC);
		
		TurtleImage turtle = new TurtleImage(layerGC, id);
		
		myTurtles.put(id, turtle);
		myLayers.getChildren().add(layer);
		myTImages.getChildren().add(turtle);
		
	}
	
	
	public void updateAnimationSpeed(double speed) {
		for (int turtleID: myTurtles.keySet()) {
			myTurtles.get(turtleID).setAnimationSpeed(speed);
		}
	}
	
	
	public void changeBackground(Color c) {
		mainGC.setFill(c);
		mainGC.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
	}
	
	public Color getBackgroundColor() {
		return (Color) mainGC.getFill();
	}
	

	public void changePenColor(Color c, int turtleID) {
		gc.get(turtleID).setStroke(c);

	}

	public void changeTurtleImage(File file, int turtleID) {
		myTurtles.get(turtleID).changeImage(file);

	}

	public List<Integer> getAvailableTurtles() {
		List<Integer> turtleIDs = new ArrayList<Integer>(myTurtles.keySet());
		Collections.sort(turtleIDs);
		return turtleIDs;
	}
	
	public int getNumOfTurtles(){
		return numTurtles;
	}

	
	
	@Override
	public void update(Object update) {
		if(update instanceof TurtleUpdate){
			TurtleUpdate tu = (TurtleUpdate) update;
			int id = tu.getTurtleID();
			if (!myTurtles.keySet().contains(id)) {
				addTurtle(id);
			}
			myTurtles.get(id).update(tu);
		}
		if(update instanceof ViewUpdate){
			//TODO front end peeps
			ViewUpdate vu =  (ViewUpdate) update;
			changeBackground(myPalette.getColor(vu.getBackgroundID()));
			for (Map.Entry<Integer, TurtleImage> ti : myTurtles.entrySet()){
				ti.getValue().update(vu, myPalette);
			}
		}
		
	}

	
}
