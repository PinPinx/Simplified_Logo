package view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import view.dialogs.DialogBox;
import view.dialogs.InputDialogBox;
import view.dialogs.TextInputDialogBox;
import view.turtle.TurtleImage;
import model.PenUpdate;
import model.TurtleUpdate;
import model.ViewInitializer;
import model.ViewUpdate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class TurtleWindow extends Group implements Observer {

    private Canvas mainCanvas;
    private GraphicsContext mainGC;
    private double myWidth;
    private double myHeight;
    private IntegerProperty bgColorID;
    private Integer initialColorIndex = 1;

    private int numTurtles;
    private int activeTurtleID;

    private boolean showInactiveTurtles;

    private Group myLayers = new Group();
    private Map<Integer, GraphicsContext> gc = new HashMap<>();
    private Map<Integer, TurtleImage> myTurtles = new HashMap<>();
    private Palette myPalette = new Palette();
    private Group myTImages = new Group();

    private ContextMenu contextMenu;
    private Menu turtleImages;
    private Menu imagePalettes;
    private Menu colorPalettes;
    private ToggleGroup imagePaletteGroup = new ToggleGroup();
    private ToggleGroup colorPaletteGroup = new ToggleGroup();
    
    private final static double DISPLAY_IMAGE_SIZE = 90;
    
    public TurtleWindow (double width, double height) {

        myWidth = width;
        myHeight = height;
        numTurtles = 0;

        mainCanvas = new Canvas(myWidth, myHeight);
        mainGC = mainCanvas.getGraphicsContext2D();
        bgColorID = new SimpleIntegerProperty(initialColorIndex);
        changeBackground(Color.GRAY);

        showInactiveTurtles = true;

        initializeMenu();

        this.getChildren().addAll(mainCanvas, myLayers, myTImages);

        addTurtle();

        activeTurtleID = Collections.min(myTurtles.keySet());

    }

    private void initializeMenu () {
        setTurtleImagesMenu();
        
        setDefaultImagePalette();
        setImagePaletteMenu();

        setDefaultColorPalette();
        setColorPaletteMenu();

        contextMenu = new ContextMenu(turtleImages, imagePalettes, colorPalettes);

        mainCanvas.setOnMouseClicked(e -> {
            popMyMenu(e);

        });

    }

    private void popMyMenu (MouseEvent event) {
        setTurtleImagesMenu();
        setImagePaletteMenu();
        setColorPaletteMenu();
        contextMenu = new ContextMenu(turtleImages, imagePalettes, colorPalettes);
        contextMenu.show(this, event.getX(), event.getY());
    }

    private void setDefaultImagePalette () {
        ImageIndex def_1 =
                new ImageIndex(0, "Turtle", (new Image(getClass()
                        .getResourceAsStream("/resources/images/turtle-top-view.png"))));
        ImageIndex def_2 =
                new ImageIndex(1, "Triangle", (new Image(getClass()
                        .getResourceAsStream("/resources/images/triangular.jpg"))));
        ImageIndex def_3 =
                new ImageIndex(2, "Star", (new Image(getClass()
                        .getResourceAsStream("/resources/images/star.png"))));
        myPalette.addImage(def_1, def_2, def_3);
    }

    private void setDefaultColorPalette () {
        ColorIndex def_1 = new ColorIndex(0, "Red", Color.RED);
        ColorIndex def_2 = new ColorIndex(1, "Gray", Color.GRAY);
        ColorIndex def_3 = new ColorIndex(2, "Green", Color.GREEN);
        myPalette.addColor(def_1, def_2, def_3);
    }
    
    private void setTurtleImagesMenu() {
        turtleImages = new Menu("View current turtle images");
        turtleImages.setOnAction(e -> {
            DialogBox turtleView = new DialogBox("Current turtle images");
            turtleView.addItem(this.getInteractiveTurtleImagesList());
            turtleView.show();
        });
    }
    
    private HBox getInteractiveTurtleImagesList () {
        HBox tImagesList = new HBox();
        for (int id : myTurtles.keySet()) {
            VBox turtle = new VBox();
            Label turtleLabel = new Label(Integer.toString(id));
            ImageView image = new ImageView(myTurtles.get(id).getImage());
            image.setFitHeight(DISPLAY_IMAGE_SIZE);
            image.setFitWidth(DISPLAY_IMAGE_SIZE);
            image.setOnMouseClicked(e -> updateTurtleImage(makeImageChooser().showOpenDialog(null), id, image));
            turtle.getChildren().addAll(turtleLabel, image);
            tImagesList.getChildren().add(turtle);
        }
        return tImagesList;
    }
    
    private void updateTurtleImage (File file, int id, ImageView representation) {
        if (file != null) {
            myTurtles.get(id).changeImage(file);
            representation.setImage(new Image(file.toURI().toString()));
        }
    }
    
    /*
    public Map<Integer, Image> getTurtleImages() {
        Map<Integer, Image> turtles = new HashMap<Integer,Image>();
        for (int id: myTurtles.keySet()) {
            turtles.put(id, myTurtles.get(id).getImage());
        }
        return turtles;
    }
    */

    private void setImagePaletteMenu () {
        imagePalettes = new Menu("Image Palettes List");
        ArrayList<ImageIndex> currentImageList = myPalette.getImageList();
        for (ImageIndex imgx : currentImageList) {
            RadioMenuItem imgChoice = new RadioMenuItem(imgx.getIndex() + " " + imgx.getName());
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

    private void setColorPaletteMenu () {
        colorPalettes = new Menu("Color Palettes List");
        ArrayList<ColorIndex> currentColorList = myPalette.getColorList();
        for (ColorIndex colx : currentColorList) {
            RadioMenuItem colorChoice = new RadioMenuItem(colx.getIndex() + " " + colx.getName());
            colorChoice.setToggleGroup(colorPaletteGroup);
            colorChoice.setOnAction(e -> {
                modifyColorPaletteMenu(colx.getIndex());
            });
            colorPalettes.getItems().add(colorChoice);
        }

        RadioMenuItem colorChoice = new RadioMenuItem("Add new index... ");
        colorChoice.setToggleGroup(colorPaletteGroup);
        colorChoice.setOnAction(changeColor -> {
            modifyColorPaletteMenu(myPalette.colorListSize());
        });
        colorPalettes.getItems().add(colorChoice);
    }

    private void modifyImagePaletteMenu (int index) {
        JFileChooser imageChooser = new JFileChooser(System.getProperties()
                .getProperty("user.dir") + "/src/resources/images");
        imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int retval = imageChooser.showOpenDialog(null);
        if (retval != JFileChooser.APPROVE_OPTION) {
            return;
        }
        InputDialogBox dialog = new TextInputDialogBox("Type in a name for your image or shape");
        String indexName = ((String) dialog.showInputDialog()).trim();
        ImageIndex imgx =
                new ImageIndex(index, indexName, new Image(imageChooser.getSelectedFile().toURI()
                        .toString()));
        myPalette.updateImage(index, imgx);
        return;
    }

    private void modifyColorPaletteMenu (int index) {
        java.awt.Color color =
                JColorChooser.showDialog(null, "Choose color to add to palette", null);
        InputDialogBox dialog = new TextInputDialogBox("Type in a name for your color");
        String indexName = ((String) dialog.showInputDialog()).trim();
        ColorIndex colx = new ColorIndex(index, indexName, color);
        myPalette.updateColor(index, colx);
        return;
    }

    public void addTurtle () {
        int i = 0;

        while (myTurtles.keySet().contains(i)) {
            i++;
        }

        addTurtle(i);
    }

    public void addTurtle (int id) {
        numTurtles++;
        Canvas layer = new Canvas(mainCanvas.getWidth(), mainCanvas.getWidth());
        layer.setOnMouseClicked(e -> {
            popMyMenu(e);
        });
        GraphicsContext layerGC = layer.getGraphicsContext2D();
        gc.put(id, layerGC);

        TurtleImage turtle = new TurtleImage(layerGC, id);
        turtle.updatePalette(myPalette);
        myTurtles.put(id, turtle);
        myTurtles.get(id).hide(!showInactiveTurtles);
        myLayers.getChildren().add(layer);
        myTImages.getChildren().add(turtle);

        this.getChildren().add(turtle.getStamps());
    }


    private FileChooser makeImageChooser () {
        FileChooser imageChooser = new FileChooser();
        imageChooser.setTitle("Choose new image file");
        imageChooser.getExtensionFilters().addAll(
                                                  new ExtensionFilter("Images", Arrays
                                                          .asList("*.jpg", "*.png",
                                                                  "*.gif")));
        return imageChooser;
    }

    public void updateAnimationSpeed (double speed) {
        for (int turtleID : myTurtles.keySet()) {
            myTurtles.get(turtleID).setAnimationSpeed(speed);
        }
    }

    public void changeBackground (Color c) {
        mainGC.setFill(c);
        mainGC.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
    }

    public Color getBackgroundColor () {
        return (Color) mainGC.getFill();
    }

    public void changePenColor (Color c, int turtleID) {
        gc.get(turtleID).setStroke(c);

    }

    public void changeTurtleImage (File file, int turtleID) {
        myTurtles.get(turtleID).changeImage(file);

    }

    public List<Integer> getAvailableTurtles () {
        List<Integer> turtleIDs = new ArrayList<Integer>(myTurtles.keySet());
        Collections.sort(turtleIDs);
        return turtleIDs;
    }

    public int getNumOfTurtles () {
        return numTurtles;
    }

    public void toggleInactiveTurtles (boolean show) {
        showInactiveTurtles = show;

        for (int id : myTurtles.keySet()) {
            if (id != activeTurtleID) {
                myTurtles.get(id).hide(!show);
            }
        }

    }

    public void enterDebugMode () {
        for (Map.Entry<Integer, TurtleImage> turtle : myTurtles.entrySet()) {
            turtle.getValue().setDebugMode();
        }
    }

    public void exitDebugMode () {
        for (Map.Entry<Integer, TurtleImage> turtle : myTurtles.entrySet()) {
            turtle.getValue().setRunMode();
        }
    }

    public void stepDebug () {
        for (Map.Entry<Integer, TurtleImage> turtle : myTurtles.entrySet()) {
            turtle.getValue().popUpdate();
        }
    }

    @Override
    public void update (Object update) {
        if (update instanceof TurtleUpdate) {
            TurtleUpdate tu = (TurtleUpdate) update;
            int id = tu.getTurtleID();

            if (!myTurtles.keySet().contains(id)) {
                addTurtle(id);
            }

            if (id != activeTurtleID) {
                myTurtles.get(activeTurtleID).hide(!showInactiveTurtles);
                activeTurtleID = id;
                myTurtles.get(id).hide(false);
            }

            myTurtles.get(id).addUpdate(tu);
        }

        if (update instanceof ViewUpdate) {

            ViewUpdate vu = (ViewUpdate) update;

            changeBackground(myPalette.getColor(bgColorID.getValue()));

            myPalette.updateColor(vu.getPaletteIndex(), vu.getPaletteR(), vu.getPaletteG(),
                                  vu.getPaletteB());

            for (Map.Entry<Integer, TurtleImage> turtle : myTurtles.entrySet()) {
                turtle.getValue().updatePalette(myPalette);
            }
            if (vu.isClearStamps()) {
                for (int id : myTurtles.keySet()) {
                    myTurtles.get(id).clearStamps();
                }
            }

        }

        if (update instanceof PenUpdate) {
            PenUpdate pu = (PenUpdate) update;
            myTurtles.get(pu.getTurtleID()).updatePalette(myPalette);
            myTurtles.get(pu.getTurtleID()).setPenProperties(pu);
        }

        if (update instanceof ViewInitializer) {
            ViewInitializer vi = (ViewInitializer) update;
            bgColorID = vi.getBackgroundID();
        }

    }

}
