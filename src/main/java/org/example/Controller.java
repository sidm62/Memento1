package org.example;



public class Controller {
    private Model model;
    private Gui gui;
    private Taker taker;


    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.taker = new Taker();


        saveToHistory();
    }

    public void setOption(int optionNumber, int choice) {
        model.setOption(optionNumber, choice);
        saveToHistory();
        gui.updateGui();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        model.setIsSelected(isSelected);
        saveToHistory();
        gui.updateGui();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        IMemento currentState = model.createMemento();
        IMemento previousState = taker.undo(currentState);

        if (previousState != null) {
            model.restoreState(previousState);
            gui.updateGui();
        } else {
            System.out.println("No state to undo");
        }
    }

    public void redo() {
        IMemento currentState = model.createMemento();
        IMemento nextState = taker.redo(currentState);

        if (nextState != null) {
            model.restoreState(nextState);
            gui.updateGui();
        } else {
            System.out.println("No state to redo");
        }
    }
    public void restoreState(IMemento memento) {
        if (memento != null) {
            model.restoreState(memento);
            gui.updateGui();
        }
    }
    public void openHistoryWindow() {
       HistoryWindow.display(taker, this);
    }

    void saveToHistory() {
        IMemento currentState = model.createMemento();
        taker.saveState(currentState);
    }



}