package socialnetwork.domain;

import java.util.Optional;

public class FineSyncBacklog implements Backlog {
  private LockableNode<Task> headNode;
  private LockableNode<Task> tailNode;
  private int size = 0;

  public FineSyncBacklog() {
    headNode = new LockableNode<>(null, Integer.MIN_VALUE, null);
    tailNode = new LockableNode<>(null, Integer.MAX_VALUE, null);
    headNode.setNxtNode(tailNode);
  }

  private LockablePosition find(LockableNode start, int id) {
    LockableNode<Task> prev;
    LockableNode<Task> curr;
    prev = start;
    prev.lock();
    curr = start.getNxtNode();
    curr.lock();
    while (curr.getId() < id) {
      prev.unlock();
      prev = curr;
      curr = curr.getNxtNode();
      curr.lock();
    }
    return new LockablePosition(prev, curr);
  }


  @Override
  public boolean add(Task task) {
    assert task != null;
    LockableNode<Task> newNode = new LockableNode(task);
    LockableNode<Task> prev = null;
    LockableNode<Task> curr = null;
    try {
      LockablePosition position = find(headNode, task.getId());
      prev = position.getPrev();
      curr = position.getCurr();
      if (curr.getId() == task.getId()) {
        return false;
      } else {
        newNode.setNxtNode(curr);
        prev.setNxtNode(newNode);
        size += 1;
        return true;
      }
    } finally {
      prev.unlock();
      curr.unlock();
    }
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    if (headNode.getNxtNode().getId() == tailNode.getId()) {
      return Optional.empty();
    } else {
      LockableNode<Task> curr = null;
      try {
        headNode.lock();
        curr = headNode.getNxtNode();
        curr.lock();
        headNode.setNxtNode(curr.getNxtNode());
        size -= 1;
        return Optional.of(curr.getValue());
      } finally {
        headNode.unlock();
        curr.unlock();
      }
    }
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return size;
  }
}
