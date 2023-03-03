package socialnetwork.domain;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CoarseSyncBacklog extends linkedBacklog {
  private final Lock lock = new ReentrantLock();

  @Override
  public boolean add(Task task) {
    lock.lock();
    try {
      return super.add(task);
    } finally {
      lock.unlock();
    }
  };

  @Override
  public Optional<Task> getNextTaskToProcess() {
    lock.lock();
    try {
      return super.getNextTaskToProcess();
    } finally {
      lock.unlock();
    }
  };

  @Override
  public int numberOfTasksInTheBacklog() {
    lock.lock();
    try {
      return super.numberOfTasksInTheBacklog();
    } finally {
      lock.unlock();
    }
  };

}
