package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class DeadlineTaskTest {

    @Test
    public void testConstructorAndGetters() {
        LocalDate date = LocalDate.of(2025, 2, 17);
        DeadlineTask task = new DeadlineTask("Submit report", date);

        assertEquals("Submit report", task.getDescription());
        assertEquals(date, task.getBy());
        assertEquals("deadline", task.getTaskType());
    }

    @Test
    public void testToString() {
        LocalDate date = LocalDate.of(2025, 2, 17);
        DeadlineTask task = new DeadlineTask("Finish project", date);

        String expected = "[D][ ] Finish project (by: " + date + ")";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToMarkdownString() {
        LocalDate date = LocalDate.of(2025, 2, 17);
        DeadlineTask task = new DeadlineTask("Complete documentation", date);

        String expected = "- [ ] D: Complete documentation (by: " + date + ")";
        assertEquals(expected, task.toMarkdownString());
    }

    @Test
    public void testParseString_valid() {
        LocalDate date = LocalDate.of(2025, 2, 17);

        String input = "Write blog post (by: " + DeadlineTask.parseDate(date) + ")";

        DeadlineTask parsedTask = DeadlineTask.parseString(input);

        assertNotNull(parsedTask);
        assertEquals("Write blog post", parsedTask.getDescription());
        assertEquals(date, parsedTask.getBy());
    }

    @Test
    public void testParseString_invalidFormat() {
        String input = "Invalid format string";
        assertNull(DeadlineTask.parseString(input));
    }

    @Test
    public void testParseString_invalidDate() {
        String input = "Write blog post (by: invalid-date)";
        assertNull(DeadlineTask.parseString(input));
    }
}