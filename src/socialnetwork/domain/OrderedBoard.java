package socialnetwork.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderedBoard implements Board{
  private final MsgNode<Message> headNode;
  private final MsgNode<Message> tailNode;
  private int size = 0;

  public OrderedBoard() {
    headNode = new MsgNode<>(null, Integer.MIN_VALUE, null);
    tailNode = new MsgNode<>(null, Integer.MAX_VALUE, null);
    headNode.setNxtNode(tailNode);
  }

  private PositionNode<MsgNode> find(MsgNode<Message> start, int id) {
    MsgNode<Message> prevNode;
    MsgNode<Message> currNode = start;
    do {
      prevNode = currNode;
      currNode = currNode.getNxtNode();
    } while (currNode.getId() < id);
    return new PositionNode<>(prevNode, currNode);
  }

  @Override
  public boolean addMessage(Message message) {
    MsgNode<Message> newNode = new MsgNode<>(message, message.getMessageId());
    PositionNode<MsgNode> position = find(headNode, message.getMessageId());
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
    PositionNode<MsgNode> position = find(headNode, message.getMessageId());
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
    MsgNode<Message> currentNode = headNode.getNxtNode();
    while (currentNode.getId() != tailNode.getId()) {
      msgLst.add(0, currentNode.getValue());
      currentNode = currentNode.getNxtNode();
    }
    return msgLst;
  }
}
