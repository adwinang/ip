import java.io.IOException;

public class Zephyr {

    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;
    private final Parser parser;


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

    public static void main(String[] args) {
        new Zephyr("data/tasks.md").run();
    }
}
