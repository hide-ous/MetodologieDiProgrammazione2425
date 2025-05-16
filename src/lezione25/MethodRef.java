package lezione25;

import java.util.Arrays;
import java.util.Comparator;

public class MethodRef {

    public static void main(String[] args) {
        Arrays.sort(new String[]{"a", "b", "c",}, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Arrays.sort(new String[]{"a", "b", "c",},
                (o1, o2) -> o1.compareTo(o2));

        Arrays.sort(new String[]{"a", "b", "c",}, String::compareTo);
    }
}
