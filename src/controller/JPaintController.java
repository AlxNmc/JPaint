package controller;

import commands.*;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
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
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand().run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand().run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand().run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand().run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand().run());
        uiModule.addEvent(EventName.UNDO, () -> CommandHistory.undo());
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
    }
}
