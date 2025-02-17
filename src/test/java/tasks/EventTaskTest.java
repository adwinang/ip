package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTaskTest {

    private static final DateTimeFormatter FORMATTER = AbstractTask.getFormatter();

    @Test
    public void testConstructorAndGetters() {
        LocalDate from = LocalDate.of(2025, 3, 1);
        LocalDate to = LocalDate.of(2025, 3, 5);
        EventTask task = new EventTask("Attend Conference", from, to);

        assertEquals("Attend Conference", task.getDescription());
        assertEquals(from, task.getFrom());
        assertEquals(to, task.getTo());
        assertEquals("event", task.getTaskType());
    }

    @Test
    public void testToString() {
        LocalDate from = LocalDate.of(2025, 4, 10);
        LocalDate to = LocalDate.of(2025, 4, 12);
        EventTask task = new EventTask("Team Building Retreat", from, to);

        String expected = "[E][ ] Team Building Retreat (from: " + from + " at: " + to + ")";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToMarkdownString() {
        LocalDate from = LocalDate.of(2025, 5, 15);
        LocalDate to = LocalDate.of(2025, 5, 18);
        EventTask task = new EventTask("Annual Company Meeting", from, to);

        String expected = "- [ ] E: Annual Company Meeting (from: " + from + " at: " + to + ")";
        assertEquals(expected, task.toMarkdownString());
    }

    @Test
    public void testParseString_valid() {
        LocalDate from = LocalDate.of(2025, 6, 20);
        LocalDate to = LocalDate.of(2025, 6, 25);
        String formattedFrom = from.format(FORMATTER);
        String formattedTo = to.format(FORMATTER);

        String input = "Workshop Session (from: " + formattedFrom + " at: " + formattedTo + ")";

        EventTask parsedTask = EventTask.parseString(input);

        assertNotNull(parsedTask, "parseString() returned null, check parsing logic.");
        assertEquals("Workshop Session", parsedTask.getDescription());
        assertEquals(from, parsedTask.getFrom());
        assertEquals(to, parsedTask.getTo());
    }

    @Test
    public void testParseString_invalidFormat() {
        String input = "Invalid event format string";
        assertNull(EventTask.parseString(input), "parseString() should return null for invalid format.");
    }

    @Test
    public void testParseString_invalidDate() {
        String input = "Some Event (from: invalid-date at: 2025-06-25)";
        assertNull(EventTask.parseString(input), "parseString() should return null for invalid date.");

        input = "Some Event (from: 2025-06-20 at: invalid-date)";
        assertNull(EventTask.parseString(input), "parseString() should return null for invalid date.");
    }

    @Test
    public void testParseString_missingToDate() {
        String input = "Networking Event (from: 2025-06-20)";
        assertNull(EventTask.parseString(input), "parseString() should return null for missing 'at:' part.");
    }

    @Test
    public void testParseString_missingParenthesis() {
        String input = "Meeting (from: 2025-06-20 at: 2025-06-22";
        assertNull(EventTask.parseString(input), "parseString() should return null if missing closing parenthesis.");
    }
}