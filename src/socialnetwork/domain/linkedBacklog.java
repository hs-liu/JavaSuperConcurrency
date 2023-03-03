package socialnetwork.domain;

import java.util.Optional;

public class linkedBacklog implements Backlog {

  private taskNode<Task> headNode;
  private taskNode<Task> tailNode;
  private int size = 0;

  public linkedBacklog() {
    headNode = null;
    tailNode = null;
  }


  @Override
  public boolean add(Task task) {
    assert task != null;
    taskNode<Task> newNode = new taskNode<>(task);
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
