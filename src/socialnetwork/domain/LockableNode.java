package socialnetwork.domain;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockableNode<T> {
  private final Lock lock = new ReentrantLock();
  private T value;
  private int id;
  private LockableNode nxtNode;

  public LockableNode(T value) {
    this(value, value.hashCode(), null);
  }

  public LockableNode(T value, LockableNode nxtNode) {
    this(value, value.hashCode(), nxtNode);
  }

  public LockableNode(T value, int id, LockableNode nxtNode) {
    this.value = value;
    this.id = id;
    this.nxtNode = nxtNode;
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  public void setNxtNode(LockableNode nxtNode) {
    this.nxtNode = nxtNode;
  }

  public LockableNode getNxtNode() {
    return this.nxtNode;
  }

  public void setId(int id) {
    this.id = id;
  }

  public  int getId() {
    return this.id;
  }
}
