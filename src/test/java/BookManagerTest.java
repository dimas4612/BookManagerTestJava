import org.example.Book;
import org.example.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
    }

    @Test
    @DisplayName("Test menambahkan buku")
    void testAddBook() {
        Book buku = new Book("Pemrograman", "Andi", 2020);
        bookManager.addBook(buku);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang ada")
    void testRemoveExistingBook() {
        Book buku = new Book("Basis Data", "ErLangga", 2021);
        bookManager.addBook(buku);

        boolean removed = bookManager.removeBook("Basis Data");
        assertTrue(removed);
        assertEquals(0, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
        boolean removed = bookManager.removeBook("Buku Fiktif");
        assertFalse(removed); // harus false karena buku tidak ada
        assertEquals(0, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test mencari buku berdasarkan author")
    void testFindBooksByAuthor() {
        Book buku1 = new Book("Algoritma", "Budi", 2019);
        Book buku2 = new Book("Struktur Data", "Budi", 2020);
        Book buku3 = new Book("Matematika", "Sari", 2021);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        List<Book> hasil = bookManager.findBooksByAuthor("Budi");
        assertEquals(2, hasil.size());
        assertTrue(hasil.contains(buku1));
        assertTrue(hasil.contains(buku2));
    }

    @Test
    @DisplayName("Test mendapatkan semua buku")
    void testGetAllBooks() {
        Book buku1 = new Book("Jaringan Komputer", "Andi", 2018);
        Book buku2 = new Book("AI Dasar", "Sinta", 2022);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);

        List<Book> semuaBuku = bookManager.getAllBooks();
        assertEquals(2, semuaBuku.size());
        assertTrue(semuaBuku.contains(buku1));
        assertTrue(semuaBuku.contains(buku2));
    }

    @Test
    void testInvalidBook() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Author", 2020);
        });
        assertEquals("Judul tidak boleh kosong", exception.getMessage());
    }

    @Test
    void testInvalidAuthor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Judul", "", 2020);
        });
        assertEquals("Penulis tidak boleh kosong", exception.getMessage());
    }

    @Test
    void testInvalidYear() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Judul", "Author", -2020);
        });
        assertEquals("Tahun harus angka positif", exception.getMessage());
    }
}