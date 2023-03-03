package socialnetwork;

import socialnetwork.domain.*;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    // Implement logic here following the steps described in the specs
    Backlog backlog = new CoarseSyncBacklog();
    Board board = new CoarseSyncBoard();
    SocialNetwork socialNetwork = new SocialNetwork(backlog);
    Worker[] workers = new Worker[5];
    Arrays.setAll(workers, i -> new Worker(backlog));
    Arrays.stream(workers).forEach(Thread::start);
    User[] users = new User[10];
    Arrays.setAll(users, i -> new User("user" + i, socialNetwork));

    //thread start: start()
    //thread terminate: join()
    Arrays.stream(users).forEach(user -> {
      socialNetwork.register(user, new CoarseSyncBoard());
      user.start();
    });

    //user to finish
    Arrays.stream(users).forEach(user -> {
      try {
        user.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    //if not empty, wait then try again
    while (backlog.numberOfTasksInTheBacklog() != 0) {
      try {
        Thread.sleep(3090);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    Arrays.stream(workers).forEach(worker -> worker.interrupt());
    //worker to finish
    Arrays.stream(workers).forEach(worker -> {
      try {
        worker.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });


  }
}
