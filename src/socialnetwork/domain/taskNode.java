package socialnetwork.domain;

public class taskNode<T> {
  private T value;
  private taskNode<T> nxtNode;

  public taskNode (T value) {
    this.value = value;
    this.nxtNode = null;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return this.value;
  }

  public void setNxtNode(taskNode<T> nxtNode) {
    this.nxtNode = nxtNode;
  }

  public taskNode<T> getNxtNode() {
    return this.nxtNode;
  }

}
