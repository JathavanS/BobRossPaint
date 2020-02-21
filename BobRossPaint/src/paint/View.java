package paint;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private FillStyleChooserPanel fillStyleChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	private LineWidthChooserPanel lineWidthChooserPanel;
	private BobRossColorPanel bobRossColorPanel;
	private BrushSizeChooserPanel brushSizeChooserPanel;
	private HBox bobRossPanel; 
	private OpacityChooserPanel opacityChooserPanel;
	WebView webView;
	Stage stage;
	private boolean BobRoss;
	double originalHeight;
	
	public View(PaintModel model, Stage stage) {

		this.model = model;
		this.stage = stage;
		initUI(stage);
		this.BobRoss = true;
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.fillStyleChooserPanel = new FillStyleChooserPanel(this);
		this.colorChooserPanel = new ColorChooserPanel(this);
		this.lineWidthChooserPanel = new LineWidthChooserPanel(this);
		this.brushSizeChooserPanel = new BrushSizeChooserPanel(this);
		
		this.bobRossPanel = new HBox();
		
		
		
		this.opacityChooserPanel = new OpacityChooserPanel(this, this.colorChooserPanel);
		GridPane lineWidthGridPane = new GridPane();
		lineWidthGridPane.add(lineWidthChooserPanel, 0, 0);
		lineWidthGridPane.add(opacityChooserPanel, 0, 1);
		
		this.webView = new WebView();
		this.webView.setPrefSize(0.0, 0.0);
		
		BorderPane root = new BorderPane();
		
		double spacing = 8;
		
		/**
		 * Tool panels to put the various panels above
		 */
		VBox leftToolPanel = new VBox();
		leftToolPanel.setSpacing(spacing);
		HBox bottomToolPanel = new HBox();
		bottomToolPanel.setSpacing(spacing);
		bottomToolPanel.setPadding(new Insets(4, 4, 4, 4));
		
		/*
		 * The shape chooser and the fill style chooser will go on the left.
		 * The color chooser and line width chooser will go on the bottom.
		 */
		leftToolPanel.getChildren().add(this.shapeChooserPanel);
		leftToolPanel.getChildren().add(new Separator(Orientation.HORIZONTAL));
		leftToolPanel.getChildren().add(this.fillStyleChooserPanel);
		
		bottomToolPanel.getChildren().add(this.colorChooserPanel);
		bottomToolPanel.getChildren().add(new Separator(Orientation.VERTICAL));
		bottomToolPanel.getChildren().add(lineWidthGridPane);
		//bottomToolPanel.getChildren().add(this.opacityChooserPanel);
		bottomToolPanel.getChildren().add(new Separator(Orientation.VERTICAL));
		bottomToolPanel.getChildren().add(this.bobRossPanel);
	//	bottomToolPanel.getChildren().add(this.brushSizeChooserPanel);
		
		
		
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(leftToolPanel);
		root.setBottom(bottomToolPanel);
		//root.getBottom().g
		root.setRight(webView);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
	//	final KeyCombination keyCombCtrZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
	//	final KeyCombination keyCombCtrY = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			
			public void handle(KeyEvent event) {
				//this.shapeManipulatorStrategy.handle(this, event);
				//System.out.println("yes");
				if(event.isShortcutDown() && !getPaintPanel().getMouseDown())
				{
					if(event.getCode() == KeyCode.Z)
					{
						System.out.println("Undo");
						model.undo();
						getPaintPanel().repaint();
					}
					else if(event.getCode() == KeyCode.Y)
					{
						System.out.println("Redo");
						model.redo();
						getPaintPanel().repaint();
					}
				}
			}
		});
		
		
		stage.show();
		stage.setWidth(580.0);
		this.originalHeight = this.stage.getHeight();
		
		openBobRoss();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	/**
	 * Return this View's FillStyleChooserPanel
	 * @return This View's FillStyleChooserPanel
	 */
	public FillStyleChooserPanel getFillStyleChooserPanel() {
		return fillStyleChooserPanel;
	}

	/**
	 * @return colorChooserPanel
	 */
	public ColorChooserPanel getColorChooserPanel()
	{
		return this.colorChooserPanel;
	}
	
	public LineWidthChooserPanel getLineWidthChooserPanel()
	{
		return this.lineWidthChooserPanel;
	}
	
	public BrushSizeChooserPanel getBrushSizeChooserPanel()
	{
		return this.brushSizeChooserPanel;
	}
	
	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Clear canvas");
		menuItem.setOnAction(new ClearCanvasEventHandler(this.getPaintPanel()));
		menu.getItems().add(menuItem);
		
		menu.getItems().add(new SeparatorMenuItem());
		
		menuItem = new MenuItem("Bob Ross");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);

		return menuBar;
	}
	
	public double getOpacity()
	{
		return this.model.get_opacity();
	}
	//closes the Bob Ross stuff and returns the program to normal settings
	public void closeBobRoss()
	{
		this.stage.setResizable(true);
		this.stage.setHeight(this.originalHeight);
		this.stage.setWidth(580.0);
		this.stage.setMinWidth(0.0);
		this.webView.setPrefSize(0.0, 0.0);
		this.webView.getEngine().load("");
		this.bobRossColorPanel.clearPanel();
		this.bobRossPanel.getChildren().clear();
		this.paintPanel.getCanvas().relocate(0.0, 0.0);
		this.paintPanel.originalHeight();
		this.BobRoss = false;
	}
	
	public void openBobRoss()
	{
		this.BobRoss = true;
		
		try {
			
			this.stage.setFullScreen(false);
			//checks to see if the video can be properly loaded. closes the video if it can't.
			this.webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>()
					{

						@Override
						public void changed(ObservableValue<? extends State> arg0, State arg1, State arg2) {
							if(arg2 == State.FAILED)
							{
								System.out.println("Failed to load video.Internet connection required");
								//closeBobRoss();
								
							}
						}
				
					});
			//loads the youtube video with autoplay on, user controls off, annotations off
			this.webView.getEngine().load(
					"https://www.youtube.com/embed/lLWEXRAnQd0"
					+ "?autoplay=1;iv_load_policy=3"
					);
			// disables users ability to change the program's size
			this.stage.setResizable(false);
			this.stage.setHeight(this.originalHeight);
			this.stage.setWidth(1110.0);
			this.stage.setMinWidth(1110.0);
			this.paintPanel.getCanvas().relocate(0.0, 0.0);
			this.paintPanel.originalHeight();
			this.webView.setPrefSize(540, 500);
			//adds the Bob Ross colors
			this.bobRossColorPanel = new BobRossColorPanel(this.colorChooserPanel,22);
			this.brushSizeChooserPanel = new BrushSizeChooserPanel(this);
			this.bobRossPanel.getChildren().add(this.bobRossColorPanel);
			this.bobRossPanel.getChildren().add(new Separator(Orientation.VERTICAL));
			this.bobRossPanel.getChildren().add(this.brushSizeChooserPanel);
			this.bobRossColorPanel.Populate();
			
		}
		catch(Exception e)
		{
			System.out.println("Failed to load video.Internet connection required");
		}
	}

	@Override
	public void handle(ActionEvent event) {
		//closes the program if the user clicks on the exit button in the top menu bar
		if(((MenuItem)event.getSource()).getText().equals("Exit"))
		{
			this.stage.close();
		}
		//opens the Bob Ross video if the user clicks on the Bob Ross button in the top menu bar
		else if(((MenuItem)event.getSource()).getText().equals("Bob Ross"))
		{
			if(this.BobRoss)
			{
				//closed the Bob Ross video if the user clicks on the "Bob Ross" button again
				this.closeBobRoss();
			}
			else
			{
				this.openBobRoss();		
			}
		}
		else if(((MenuItem)event.getSource()).getText().equals("Undo"))
		{
			model.undo();
			getPaintPanel().repaint();
		}
		else if(((MenuItem)event.getSource()).getText().equals("Redo"))
		{
			model.redo();
			getPaintPanel().repaint();
		}
		else if(((MenuItem)event.getSource()).getText().equals("Save"))
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("Saved Projects"));
			fileChooser.getExtensionFilters().addAll(
	        	     new FileChooser.ExtensionFilter("DAT File", "*.dat")
	        	);
			
	        File selectedFile = fileChooser.showSaveDialog(stage);
	        if(selectedFile != null)
	        {
	        	try {
					FileOutputStream fileOut = new FileOutputStream( selectedFile.getAbsolutePath());
					System.out.println(selectedFile.getAbsolutePath());
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(this.model.getCommands());
					out.close();
					fileOut.close();
				} 
	        	catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        }
	        else
	        {
	        	System.out.println("File Chooser Save Error");
	        }
	     
	     
		}
		else if(((MenuItem)event.getSource()).getText().equals("Open"))
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("Saved Projects"));
			fileChooser.getExtensionFilters().addAll(
	        	     new FileChooser.ExtensionFilter("DAT File", "*.dat")
	        	);
			
	        File selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile != null)
			{
				FileInputStream fileIn = null;
				ObjectInputStream in = null;
				try
				{
					 fileIn = new FileInputStream( selectedFile.getAbsolutePath());
					 in = new ObjectInputStream(fileIn);
					ArrayList<DrawingCommand> new_commands = (ArrayList<DrawingCommand>)(in.readObject());
					this.model.setCommands(new_commands);
					this.paintPanel.repaint();
				} 
	        	catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						in.close();
						fileIn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			else
			{
				System.out.println("File Chooser Open Error");
			}
		}
		System.out.println(((MenuItem)event.getSource()).getText());
	}
}
