package socialnetwork.domain;

import java.util.List;
import java.util.Optional;

public interface Backlog {

  boolean add(Task task);

  Optional<Task> getNextTaskToProcess();

  int numberOfTasksInTheBacklog();

}
