package socialnetwork.domain;

import java.util.ArrayList;
import java.util.List;

public class orderedBoard implements Board {
  private final msgNode<Message> headNode;
  private final msgNode<Message> tailNode;
  private int size = 0;

  public orderedBoard() {
    headNode = new msgNode<>(null, Integer.MIN_VALUE, null);
    tailNode = new msgNode<>(null, Integer.MAX_VALUE, null);
    headNode.setNxtNode(tailNode);
  }

  private positionNode<msgNode> find(msgNode<Message> start, int id) {
    msgNode<Message> prevNode;
    msgNode<Message> currNode = start;
    do {
      prevNode = currNode;
      currNode = currNode.getNxtNode();
    } while (currNode.getId() < id);
    return new positionNode<>(prevNode, currNode);
  }

  @Override
  public boolean addMessage(Message message) {
    msgNode<Message> newNode = new msgNode<>(message, message.getMessageId());
    positionNode<msgNode> position = find(headNode, message.getMessageId());
    if (position.getCurr().getId() == message.getMessageId()) {
      return false;
    } else {
      newNode.setNxtNode(position.getCurr());
      position.getPrev().setNxtNode(newNode);
      size += 1;
      return true;
    }
  }

  @Override
  public boolean deleteMessage(Message message) {
    positionNode<msgNode> position = find(headNode, message.getMessageId());
    if (position.getCurr().getId() == message.getMessageId()) {
      position.getPrev().setNxtNode(position.getCurr().getNxtNode());
      size -= 1;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public List<Message> getBoardSnapshot() {
    List<Message> msgLst = new ArrayList<>();
    msgNode<Message> currentNode = headNode.getNxtNode();
    while (currentNode.getId() != tailNode.getId()) {
      msgLst.add(0, currentNode.getValue());
      currentNode = currentNode.getNxtNode();
    }
    return msgLst;
  }
}
