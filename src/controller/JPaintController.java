package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import main.Clipboard;
import main.AbstractCanvas;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final Clipboard clipboard;
    private final AbstractCanvas abstractCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, Clipboard clipboard, AbstractCanvas abstractCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.clipboard = clipboard;
        this.abstractCanvas = abstractCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> clipboard.copy());
        uiModule.addEvent(EventName.PASTE, () -> abstractCanvas.paste());
        uiModule.addEvent(EventName.DELETE, () -> abstractCanvas.deleteSelected());
    }
}
