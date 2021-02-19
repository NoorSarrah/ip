import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //public static Task[] tasks = new Task[100];
    public static int size = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                printList();

                // marks current task as completed
            } else if (command.contains("done")) {
                taskCompleted(command);

                // adds tasks into list
            } else if (command.contains("delete")) {
                deleteTasks(command);
            } else {
                addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program when input=bye
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void printWelcomeMessage() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // print welcome message
        System.out.println("Hello from\n" + logo);
        printDash();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDash();
    }

    public static void addTasks(String description) {
        printDash();
        System.out.println("\tGot it. I've added this task: ");
        if (description.contains("todo")) {
            description = description.replace("todo", "");
            description = description.strip();
            runTodo(description);
        } else if (description.contains("deadline")) {
            description = description.replace("deadline", "");
            description = description.strip();
            runDeadline(description);
        } else if (description.contains("event")) {
            description = description.replace("event", "");
            description = description.strip();
            runEvent(description);
        }
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        printDash();
    }
    public static void deleteTasks(String command) {

        command = command.replace("delete", " ");
        command = command.strip();

        int count = Integer.parseInt(command);
        --count; // array starts from 0
        System.out.println("\tNoted. I've removed this task:\n" + tasks.get(count).toString());

        tasks.remove(tasks.get(count));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
    }

    public static void taskCompleted(String command) {
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();

        int count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks.get(count).markAsDone();

        printDash();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println(tasks.get(count).toString());
        printDash();
    }

    // print list as a checklist when command=list
    public static void printList() {
        printDash();
        if (tasks.size() != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 1; i <= tasks.size(); ++i) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        } else {
            System.out.println("\tYou do not have any pending task.");
        }
        printDash();
    }

    public static void runDeadline(String description) {
        String[] details = description.split(" /by");
        tasks.add(new Deadline(details[0], details[1]));
    }

    public static void runTodo(String description) {
        tasks.add(new Todo(description));
    }

    public static void runEvent(String description) {
        String[] details = description.split(" /at");
        tasks.add(new Event(details[0], details[1]));
    }

    public static void printDash() {
        System.out.println("-".repeat(50));
    }

}
