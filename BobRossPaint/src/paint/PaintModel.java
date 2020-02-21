package paint;


import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {
	
	private ArrayList<DrawingCommand> commands = new ArrayList<DrawingCommand>();
	private LineWidthCommand lastLineWidthCommand;
	
	private double opacity = 1.0;
	
	private ArrayList<DrawingCommand> redoCommands = new ArrayList<DrawingCommand>();
	
	private boolean isRedo = false;

	/**
	 * Execute all DrawingCommands, drawing the Canvas to the specified GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	public void draw(GraphicsContext g) {
		
		g.setFill(Color.BLACK);
		g.setStroke(Color.BLACK);
		g.setLineWidth(1);
		
		for(DrawingCommand drawingCommand: commands) {
			drawingCommand.execute(g);
		}
	}

	/**
	 * Add a new DrawingCommand to the list of commands
	 * @param drawingCommand	The DrawingCommand to add
	 */
	public void addDrawingCommand(DrawingCommand drawingCommand) {
		this.commands.add(drawingCommand);
		this.setChanged();
		this.notifyObservers();
		emptyRedo();
	}
	
	public void addLineWidthCommand(LineWidthCommand command)
	{
		this.addDrawingCommand(command);
		this.lastLineWidthCommand = command;
	}
	
	public LineWidthCommand getLastLineWidthCommand()
	{
		return this.lastLineWidthCommand;
	}
	/**
	 * Return the last DrawingCommand added. If the queue is empty, return null
	 * @return The last DrawingCommand added
	 */
	public DrawingCommand getLastCommand() {
		int i = this.commands.size() - 1;
		if (i >= 0) {
			return this.commands.get(this.commands.size() - 1);
		}
		else {
			return null;
		}
	}
	
	public DrawingCommand getLastRedoCommand() {
		int i = this.redoCommands.size() - 1;
		if (i >= 0) {
			return this.redoCommands.get(this.redoCommands.size() - 1);
		}
		else {
			return null;
		}
	}
	
	public ArrayList<DrawingCommand> getCommands()
	{
		return this.commands;
	}
	
	public void setCommands(ArrayList<DrawingCommand> commands)
	{
		this.commands = commands;
	}
	
	public void undo()
	{
		DrawingCommand last = this.getLastCommand();
		while (last != null && !last.isUndo())
		{
			this.redoCommands.add(last);
			this.commands.remove(this.commands.size() - 1);
			last = this.getLastCommand();
			this.isRedo = true;
		}
		if(last != null)
		{
			this.redoCommands.add(last);
			this.commands.remove(this.commands.size() - 1);
			this.isRedo = true;
		}
	}
	
	public void redo()
	{
		DrawingCommand last = this.getLastRedoCommand();
		while (last != null && !last.isUndo())
		{
			this.commands.add(last);
			this.redoCommands.remove(this.redoCommands.size() - 1);
			last = this.getLastRedoCommand();
		}
		if(last != null)
		{
			this.commands.add(last);
			this.redoCommands.remove(this.redoCommands.size() - 1);
		}
		if(this.redoCommands.size() == 0)
		{
			this.isRedo = false;
		}
	}
	
	public void emptyRedo()
	{
		if(isRedo)
		{
			this.redoCommands = new ArrayList<DrawingCommand>();
		}
	}
	
	public void changeOpacity(double new_opacity)
	{
		this.opacity = new_opacity;
	}
	
	public double get_opacity()
	{
		return this.opacity;
	}
	/**
	 * Clear the DrawingCommand list, except the last added Style
	 * commands
	 */
	public void clear() {
		// Find the last Style commands
		ColorCommand lastColorCommand = null;
		LineWidthCommand lastLineWidthCommand = null;
		for (int i = 0; i < this.commands.size(); i++) {
			if (this.commands.get(i) instanceof ColorCommand) {
				lastColorCommand = (ColorCommand) this.commands.get(i);
			}
			else if (this.commands.get(i) instanceof LineWidthCommand) {
				lastLineWidthCommand = (LineWidthCommand) this.commands.get(i);
			}
		}
		
		// Clear the canvas
		this.commands.clear();
		
		// Add back last commands
		if (lastColorCommand != null) {
			this.addDrawingCommand(lastColorCommand);
		}
		if (lastLineWidthCommand != null) {
			this.addDrawingCommand(lastLineWidthCommand);
		}
		
		
		
		// Notify
		this.setChanged();
		this.notifyObservers();
	}
	
}
