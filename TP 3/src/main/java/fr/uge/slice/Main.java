package fr.uge.slice;

public class Main {
  public static void main(String[] args) {
/*    String[] array = new String[] { "foo", "bar" };
    Slice<String> slice = Slice.array(array);
    System.out.println(slice);   // [foo, bar]*/

/*    String[] array = new String[] { "foo", "bar", "baz", "whizz" };
    Slice<String> slice = Slice.array(array, 1, 3);*/

    String[] array = new String[] { "foo", "bar", "baz", "whizz" };
    Slice<String> slice = Slice.array(array);
    Slice<String> slice2 = slice.subSlice(1, 3);
  }
}