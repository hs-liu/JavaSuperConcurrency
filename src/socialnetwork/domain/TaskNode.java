package socialnetwork.domain;

public class TaskNode<T> {
  private T value;
  private TaskNode<T> nxtNode;

  public TaskNode(T value) {
    this.value = value;
    this.nxtNode = null;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  public void setNxtNode(TaskNode<T> nxtNode) {
    this.nxtNode = nxtNode;
  }

  public TaskNode<T> getNxtNode() {
    return this.nxtNode;
  }
}
