package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    /**
     * Tests that the task type is correctly set to "todo".
     */
    @Test
    public void testGetTaskType() {
        TodoTask task = new TodoTask("Test todo");
        assertEquals("todo", task.getTaskType());
    }

    /**
     * Tests that the toString method returns a string that includes the "[T]" indicator
     * and the task description. When no tags are added, the tag section should be empty.
     */
    @Test
    public void testToStringWithoutTags() {
        String description = "Test todo";
        TodoTask task = new TodoTask(description);
        String result = task.toString();
        assertNotNull(result);
        assertTrue(result.contains("[T]"), "toString should contain [T]");
        assertTrue(result.contains(description), "toString should contain the task description");
        // Without tags, getTagsContent() returns an empty string.
        assertFalse(result.contains("tags"), "toString should not contain tags when none are added");
    }

    /**
     * Tests that adding tags works correctly and that getTagsContent returns the expected output.
     */
    @Test
    public void testAddTagsAndGetTagsContent() {
        TodoTask task = new TodoTask("Test todo");
        // Initially, getTagsContent should be empty.
        assertEquals("", task.getTagsContent(), "No tags should be present initially.");

        // Add multiple tags.
        task.addTags("urgent", "home");
        // Expected output: "#tags urgent home"
        assertEquals("#tags urgent home", task.getTagsContent(), "Tags should be formatted correctly.");
    }

    /**
     * Tests that the markdown representation includes the todo prefix, the description,
     * and the tags (if added).
     */
    @Test
    public void testToMarkdownStringWithTags() {
        String description = "Test todo";
        TodoTask task = new TodoTask(description);
        // Add tags.
        task.addTags("urgent", "home");
        String markdown = task.toMarkdownString();
        // Expected markdown is built by:
        // super.toMarkdownStringInternal("T: " + description)
        // which returns "- [ " + "T: " + description + getTagsContent()
        // Verify that both the "T:" prefix and the tags are present.
        assertTrue(markdown.contains("T: " + description), "Markdown should contain the 'T:' prefix and the description.");
        assertTrue(markdown.contains("#tags urgent home"), "Markdown should contain the tags.");
    }

    /**
     * Tests that parsing a markdown string creates a TodoTask with the correct description.
     */
    @Test
    public void testParseString() {
        String partial = "Parsed todo";
        TodoTask task = TodoTask.parseString(partial);
        assertNotNull(task, "Parsed task should not be null.");
        assertEquals("todo", task.getTaskType(), "Parsed task should have type 'todo'.");
        assertEquals(partial, task.getDescription(), "Parsed task should have the correct description.");
    }
}
