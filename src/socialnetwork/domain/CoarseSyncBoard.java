package socialnetwork.domain;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CoarseSyncBoard extends OrderedBoard {
  private final Lock lock = new ReentrantLock();

  @Override
  public boolean addMessage(Message message) {
    lock.lock();
    try {
      return super.addMessage(message);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean deleteMessage(Message message) {
    lock.lock();
    try {
      return super.deleteMessage(message);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() {
    lock.lock();
    try {
      return super.size();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public List<Message> getBoardSnapshot() {
    lock.lock();
    try {
      return super.getBoardSnapshot();
    } finally {
      lock.unlock();
    }
  }
}
