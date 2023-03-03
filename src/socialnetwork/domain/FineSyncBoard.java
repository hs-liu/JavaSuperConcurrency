package socialnetwork.domain;

import java.util.ArrayList;
import java.util.List;

public class FineSyncBoard implements Board {
  private final LockableNode<Message> headNode;
  private final LockableNode<Message> tailNode;
  private int size = 0;

  public FineSyncBoard() {
    headNode = new LockableNode<>(null, Integer.MIN_VALUE, null);
    tailNode = new LockableNode<>(null, Integer.MAX_VALUE, null);
    headNode.setNxtNode(tailNode);
  }

  private LockablePosition find(LockableNode start, int id) {
    LockableNode prev, curr;
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
  public boolean addMessage(Message message) {
    assert message != null;
    LockableNode<Message> newNode = new LockableNode<>(message);
    LockableNode prev = null, curr = null;
    try {
      LockablePosition position = find(headNode, message.getMessageId());
      prev = position.getPrev();
      curr = position.getCurr();
      if (curr.getId() == message.getMessageId()) {
        return false;
      } else {
        newNode.setNxtNode(curr);
        prev.setNxtNode(newNode);
        return true;
      }
    } finally {
      prev.unlock();
      curr.unlock();
    }
  }

  @Override
  public boolean deleteMessage(Message message) {
    LockableNode prev = null, curr = null;
    try {
      LockablePosition position = find(headNode, message.getMessageId());
      prev = position.getPrev();
      curr = position.getCurr();
      if (curr.getId() == message.getMessageId()) {
        prev.setNxtNode(curr.getNxtNode());
        size -= 1;
        return true;
      } else {
        return false;
      }
    } finally {
      prev.unlock();
      curr.unlock();
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public List<Message> getBoardSnapshot() {
    List<Message> msgLst = new ArrayList<>();
    LockableNode<Message> prev = headNode;
    prev.lock();
    LockableNode<Message> curr = headNode.getNxtNode();
    curr.lock();
    while (curr.getId() != tailNode.getId()) {
      msgLst.add(0, curr.getValue());
      prev.unlock();
      prev = curr;
      curr = curr.getNxtNode();
      curr.lock();
    }
    prev.unlock();
    curr.unlock();
    return msgLst;

  }
}
