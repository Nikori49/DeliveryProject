package DB.entity;

public class Route {
    Long id;

    String start;
    String destination;
    int length;

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", length=" + length +
                '}';
    }

    public Route() {
    }

    public Route(Long id, String start, String destination, int length) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
