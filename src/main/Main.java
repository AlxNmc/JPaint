package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import model.persistence.ApplicationState;
import model.persistence.CanvasManager;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

//TODO write some unit tests

//TODO organize my classes into appropriate packages outside of main
public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        new CanvasManager(paintCanvas);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        MouseHandler mouseHandler = new MouseHandler(appState);
        paintCanvas.addMouseListener(mouseHandler);
    }
}
