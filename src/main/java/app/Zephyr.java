package app;

import java.io.IOException;

import commands.AbstractCommand;
import controllers.Parser;
import controllers.Storage;
import controllers.Ui;
import datastructures.TaskList;
import exceptions.ZephyrException;

/**
 * The main class for the app.Zephyr application.
 * This class is responsible for initializing the user interface, storage, parser,
 * and task list, as well as running the main application loop.
 */
public class Zephyr {

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructs a app.Zephyr application with the specified file path for task storage.
     *
     * @param filePath the file path where tasks are stored in markdown format
     */
    public Zephyr(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(storage, ui);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * This method prints the welcome message, then enters a loop to read user commands,
     * execute them, and check for an exit command. When an exit command is received,
     * the loop terminates and the tasks are saved to storage.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                AbstractCommand c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ZephyrException e) {
                ui.println(e.getMessage());
            }
        }
        try {
            storage.saveFile(tasks.getTasks());
        } catch (IOException e) {
            ui.printSavingError();
        }
    }

    /**
     * Takes in user input and process it with the existing Zephyr implementations
     *
     * @param userInput This is for processing user input
     * @return Zephyr's response
     */
    public String getResponse(String userInput) {
        AbstractCommand c = parser.parse(userInput);
        c.execute(tasks, ui, storage);
        return ui.getOutput();
    }

    /**
     * The main entry point of the app.Zephyr application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Zephyr("data/tasks.md").run();
    }
}
