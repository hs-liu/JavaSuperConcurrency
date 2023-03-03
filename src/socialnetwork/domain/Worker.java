package socialnetwork.domain;

import java.util.Optional;

public class Worker extends Thread {

  private final Backlog backlog;
  private boolean interrupted = false;

  public Worker(Backlog backlog) {
    this.backlog = backlog;
  }

  @Override
  public void run() {
    while (!interrupted) {
      Optional<Task> nxtTask = backlog.getNextTaskToProcess();
      if (nxtTask.isPresent()) {
        process(nxtTask.get());
      } else {
        try {
          Thread.sleep(3090);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void interrupt() {
    this.interrupted = true;
  }

  public void process(Task task) {
    if (task.getCommand().equals(Task.Command.DELETE)) {
      if (!task.getBoard().deleteMessage(task.getMessage())) {
        backlog.add(task);
      }
    } else if (task.getCommand() == Task.Command.POST) {
      //put msg on the board
      task.getBoard().addMessage(task.getMessage());
    }
  }
}
