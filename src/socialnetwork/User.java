package socialnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import socialnetwork.domain.Message;

public class User extends Thread {

  private static final AtomicInteger nextId = new AtomicInteger(0);

  protected final SocialNetwork socialNetwork;
  private final int id;
  private final String name;

  public User(String username, SocialNetwork socialNetwork) {
    this.name = username;
    this.id = User.nextId.getAndIncrement();
    this.socialNetwork = socialNetwork;
  }

  public int getUserId() {
    return id;
  }

  private Set<User> getAllUsers() {
    return socialNetwork.getAllUsers();
  }

  private void sendMsg() {
    List<User> receUers = new ArrayList<>();
    //pick some users and send msg to them
    //get random list of users
    Random random = new Random();
    Set<User> allUser = socialNetwork.getAllUsers();
    for (User user : allUser) {
      if (random.nextBoolean()) {
        receUers.add(user);
      }
    }
    //send msg to them
    socialNetwork.postMessage(this, receUers, "how's life?");
  }

  private void deleteMsg() {
    Random random = new Random();
    //List<Message> msgLst = socialNetwork.userBoard(this).getBoardSnapshot();
    List<Message> msgLst = socialNetwork.getBoards().get(this).getBoardSnapshot();
    if (!msgLst.isEmpty()) {
      for (Message msg : msgLst) {
        if (random.nextBoolean()) {
          socialNetwork.deleteMessage(msg);
        }
      }
    }
  }


  @Override
  public void run() {
    Random random = new Random();
    do {
      do {
        Set<User> allUsers = socialNetwork.getAllUsers();
      } while (random.nextBoolean());
      do {
        sendMsg();
      } while (random.nextBoolean());
      do {
        //get user board
        deleteMsg();
      } while (random.nextBoolean());
    } while (random.nextBoolean());
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  @Override
  public int hashCode() {
    return id;
  }
}
