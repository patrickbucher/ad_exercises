package ch.hslu.ad.sw02.ex02;

public final class Element<T> {

    private final T data;

    private Element<T> previous;
    private Element<T> next;

    public Element(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Element<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Element<T> previous) {
        this.previous = previous;
    }

    public Element<T> getNext() {
        return next;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Element<?>)) {
            return false;
        }
        Element<?> otherElement = (Element<?>) other;
        if (!otherElement.getData().getClass().equals(data.getClass())) {
            return false;
        }
        return data.equals(otherElement.data);
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += 31 * ((data == null) ? 0 : data.hashCode());
        return hashCode;

    }
}
