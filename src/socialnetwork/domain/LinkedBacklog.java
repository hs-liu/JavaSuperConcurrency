package socialnetwork.domain;

import java.util.Optional;

public class LinkedBacklog implements Backlog {
  private TaskNode<Task> headNode;
  private TaskNode<Task> tailNode;
  private int size = 0;

  public LinkedBacklog() {
    headNode = null;
    tailNode = null;
  }


  @Override
  public boolean add(Task task) {
    assert task != null;
    TaskNode<Task> newNode = new TaskNode<>(task);
    if (headNode == null) {
      headNode = newNode;
      tailNode = headNode;
    } else {
      tailNode.setNxtNode(newNode);
      tailNode = tailNode.getNxtNode();
    }
    size += 1;
    return true;
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    if (headNode == null) {
      return Optional.empty();
    } else {
      Task result = headNode.getValue();
      headNode = headNode.getNxtNode();
      size -= 1;
      return Optional.of(result);
    }
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return size;
  }
}

