public class UnknownCommand extends AbstractCommand {

    UnknownCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printUnknown();
    }

    @Override
    public void isValidCommand() {
        throw new ZephyrException("Unknown command.");
    }

}
