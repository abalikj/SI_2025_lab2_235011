import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SILab2Test {
  private final SILab2 lab = new SILab2();
    @Test
    void checkCart() {
        Item milk = new Item("Milk", 5, 350, 0);  // 1690
        Item bread = new Item("Bread", 2, 100, 0.1);  // 2 * 100 * (1 - 0.1) = 180


        var items = List.of(milk,bread);


        String cardNumber = "1234567812345678";


        double expectedSum = 1870;


        assertEquals(expectedSum, lab.checkCart(items, cardNumber), "The calculated sum is incorrect.");
    }
    @Test
    void testEveryStatement() {
        // Test slucaj 1: allItems == null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "1234567812345678")
        );
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // Test slucaj 2: Presmetki,validno ime,validna karticka
        List<Item> items2 = List.of(
                new Item("Milk", 5, 350, 0),      // -30 + 350*5 = 1720
                new Item("Bread", 2, 100, 0.1)    // 100*0.9*2 = 180
        );
        double expectedSum2 = 1720 - 30 + 180 ; // = 1870
        assertEquals(expectedSum2, SILab2.checkCart(items2, "1234567812345678"));

        // Test slucaj 3: prazno ime
        List<Item> items3 = List.of(
                new Item(null, 1, 100, 0)
        );
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items3, "1234567812345678")
        );
        assertEquals("Invalid item!", ex3.getMessage());

        // Test slucaj 4: invaliden znak vo kartickata
        List<Item> items4 = List.of(
                new Item("Butter", 1, 50, 0.2)
        );
        RuntimeException ex4 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items4, "12345678abcd5678")
        );
        assertEquals("Invalid character in card number!", ex4.getMessage());

        // Test slucaj 5: broj na cifri na karticakata nevaliden
        List<Item> items5 = List.of(
                new Item("Lemon", 2, 100, 0.5)
        );
        RuntimeException ex5 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items5, "")
        );
        assertEquals("Invalid card number!", ex5.getMessage());

    }
    @Test
    public void testMultipleCondition() {
        // Validna kartickaa
        String card = "1234567890123456";

        // TC1: A=true, B=true, C=false
        Item i1 = new Item("Item1", 5, 400, 0.1);
        assertEquals(400 * 0.9 * 5 - 30, SILab2.checkCart(List.of(i1), card));

        // TC2: A=false, B=false, C=false
        Item i2 = new Item("Item2", 5, 100, 0.0);
        assertEquals(100 * 5, SILab2.checkCart(List.of(i2), card));

        // TC3: A=false, B=false, C=true
        Item i3 = new Item("Item3", 11, 100, 0.0);
        assertEquals(100 * 11 - 30, SILab2.checkCart(List.of(i3), card));

        // TC4: A=false, B=true, C=false
        Item i4 = new Item("Item4", 5, 100, 0.2);
        assertEquals(100 * 0.8 * 5 - 30, SILab2.checkCart(List.of(i4), card));

        // TC5: A=false, B=true, C=true
        Item i5 = new Item("Item5", 15, 100, 0.2);
        assertEquals(100 * 0.8 * 15 - 30, SILab2.checkCart(List.of(i5), card));

        // TC6: A=true, B=false, C=false
        Item i6 = new Item("Item6", 5, 400, 0.0);
        assertEquals(400 * 5 - 30, SILab2.checkCart(List.of(i6), card));

        // TC7: A=true, B=false, C=true
        Item i7 = new Item("Item7", 15, 400, 0.0);
        assertEquals(400 * 15 - 30, SILab2.checkCart(List.of(i7), card));


        // TC8: A=true, B=true, C=true
        Item i8 = new Item("Item8", 20, 400, 0.1);
        assertEquals(7170, SILab2.checkCart(List.of(i8), card));
    }
}




