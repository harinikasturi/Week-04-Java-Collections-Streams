import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BookRecommendationService {

    private static final String TARGET_GENRE = "Science Fiction";
    private static final double MIN_RATING = 4.0;
    private static final int MAX_RECOMMENDATIONS = 10;

    public static List<List<BookRecommendation>> getPaginatedRecommendations(
            List<Book> books, int pageSize) {

        // Validate input parameters
        if (books == null) {
            throw new IllegalArgumentException("Book list cannot be null");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive");
        }

        try {
            // Process books in a single stream pipeline with proper error handling
            List<BookRecommendation> recommendations = Optional.ofNullable(books)
                    .orElseGet(Collections::emptyList)
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(b -> TARGET_GENRE.equalsIgnoreCase(b.getGenre()))
                    .filter(b -> b.getRating() >= MIN_RATING)
                    .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                    .limit(MAX_RECOMMENDATIONS)
                    .map(b -> new BookRecommendation(b.getTitle(), b.getRating()))
                    .collect(Collectors.toList());

            // Handle empty results
            if (recommendations.isEmpty()) {
                return Collections.emptyList();
            }

            // Create paginated results with proper bounds checking
            return partitionList(recommendations, pageSize);

        } catch (Exception e) {
            System.err.println("Error processing recommendations: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<List<BookRecommendation>> partitionList(
            List<BookRecommendation> recommendations, int pageSize) {

        int totalItems = recommendations.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        List<List<BookRecommendation>> paginated = new ArrayList<>(totalPages);

        for (int i = 0; i < totalItems; i += pageSize) {
            int end = Math.min(i + pageSize, totalItems);
            paginated.add(recommendations.subList(i, end));
        }

        return paginated;
    }
}

// Enhanced Immutable Book class with validation
class Book {
    private final String title;
    private final String author;
    private final String genre;
    private final double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = validateString(title, "Title");
        this.author = validateString(author, "Author");
        this.genre = validateString(genre, "Genre");
        this.rating = validateRating(rating);
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private double validateRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        return rating;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
}

// Enhanced Immutable BookRecommendation class
class BookRecommendation {
    private final String title;
    private final double rating;
    private final String formattedRating;

    public BookRecommendation(String title, double rating) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.rating = validateRating(rating);
        this.formattedRating = String.format("%.1f★", rating);
    }

    private double validateRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Invalid rating value: " + rating);
        }
        return rating;
    }

    // Getters
    public String getTitle() { return title; }
    public double getRating() { return rating; }
    public String getFormattedRating() { return formattedRating; }

    @Override
    public String toString() {
        return String.format("%s (%s)", title, formattedRating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookRecommendation)) return false;
        BookRecommendation that = (BookRecommendation) o;
        return Double.compare(that.rating, rating) == 0 &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating);
    }
}