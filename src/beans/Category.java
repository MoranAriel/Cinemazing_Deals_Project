package beans;

public enum Category {
  DEAFAULT(0),
  NEW_GEAR(1),
  RENTAL_GEAR(2),
  PRODUCTION(3),
  POST_PRODUCTION(4),
  MOVIE_THEATERS(5),
  STREAMING(6),
  DVD_AND_BLURAY(7);

  private int id;

  Category(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}