package business;

import java.util.List;

public interface IBusiness<T, K> {
    // <T, K> generic
    // T: entity chúng ta làm việc, K: kiểu dữ liệu của khoá chính.
    List<T> finAll();
    boolean creat(T t);
    boolean update(T t);
    boolean delete(K k);
    T findById(K k);

}
