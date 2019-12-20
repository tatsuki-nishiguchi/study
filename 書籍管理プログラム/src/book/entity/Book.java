package book.entity;

public class Book {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private String genre;
	private int price;
    private String valuate;

	/**
	 * IDを取得する。
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author セットする author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher セットする publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre セットする genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return valuate
	 */
	public String getValuate() {
		return valuate;
	}

	/**
	 * @param valuate セットする valuate
	 */
	public void setValuate(String valuate) {
		this.valuate = valuate;
	}
}

