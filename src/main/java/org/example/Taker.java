package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.Deque;

public class Taker {

    private final Deque<IMemento> undoStack = new ArrayDeque<>();
    private final Deque<IMemento> redoStack = new ArrayDeque<>();

    private ObservableList<IMemento> historyList = FXCollections.observableArrayList();

    public void saveState(IMemento memento) {
        undoStack.push(memento);
        redoStack.clear(); // Clear redo stack on new action
        historyList.add(0, memento);
    }

    public IMemento undo(IMemento currentState) {
        if (undoStack.isEmpty()) {
        return null;
    }
        redoStack.push(currentState);
        IMemento previousState = undoStack.pop();
        return previousState;
    }
    public IMemento redo(IMemento currentState) {
        if (redoStack.isEmpty()) {
            return null;
        }
        undoStack.push(currentState);
        IMemento nextState = redoStack.pop();
        return nextState;
    }

    public ObservableList<IMemento> getHistoryList() {
        return historyList;
    }
}
