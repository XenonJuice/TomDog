


package util;


import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * 适配器类，它将 <code>Enumeration</code>
 * 包装在一个 Java2 集合类对象 <code>Iterator</code> 周围，
 * 便现有返回 Enumeration 的 API 可以轻松地在新的集合之上运行。
 * 提供了构造函数以便轻松创建这样的包装器。
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2003/04/29 21:57:39 $
 */

public final class Enumerator implements Enumeration {


    // ----------------------------------------------------------- Constructors


    /**
     * Return an Enumeration over the values of the specified Collection.
     *
     * @param collection Collection whose values should be enumerated
     */
    public Enumerator(Collection collection) {

        this(collection.iterator());

    }


    /**
     * Return an Enumeration over the values of the specified Collection.
     *
     * @param collection Collection whose values should be enumerated
     * @param clone      true to clone iterator
     */
    public Enumerator(Collection collection, boolean clone) {

        this(collection.iterator(), clone);

    }


    /**
     * Return an Enumeration over the values returned by the
     * specified Iterator.
     *
     * @param iterator Iterator to be wrapped
     */
    public Enumerator(Iterator iterator) {

        super();
        this.iterator = iterator;

    }


    /**
     * Return an Enumeration over the values returned by the
     * specified Iterator.
     *
     * @param iterator Iterator to be wrapped
     * @param clone    true to clone iterator
     *                 <p>
     *                 对原Iterator进行克隆会创建一个浅拷贝，主要考虑到多线程环境下，
     *                 如果调用该类的构造器却不进行拷贝会导致原集合和本类共享一个迭代器，如果其他线程
     *                 对原集合进行修改，会导致CME异常。
     */
    public Enumerator(Iterator iterator, boolean clone) {

        super();
        if (clone) {
            this.iterator = iterator;
        } else {
            List list = new LinkedList();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            this.iterator = list.iterator();
        }

    }


    /**
     * Return an Enumeration over the values of the specified Map.
     *
     * @param map Map whose values should be enumerated
     */
    public Enumerator(Map map) {

        this(map.values().iterator());

    }


    /**
     * Return an Enumeration over the values of the specified Map.
     *
     * @param map   Map whose values should be enumerated
     * @param clone true to clone iterator
     */
    public Enumerator(Map map, boolean clone) {

        this(map.values().iterator(), clone);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The <code>Iterator</code> over which the <code>Enumeration</code>
     * represented by this class actually operates.
     */
    private Iterator iterator = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Tests if this enumeration contains more elements.
     *
     * @return <code>true</code> if and only if this enumeration object
     * contains at least one more element to provide, <code>false</code>
     * otherwise
     */
    public boolean hasMoreElements() {

        return (iterator.hasNext());

    }


    /**
     * Returns the next element of this enumeration if this enumeration
     * has at least one more element to provide.
     *
     * @return the next element of this enumeration
     * @throws NoSuchElementException if no more elements exist
     */
    public Object nextElement() throws NoSuchElementException {

        return (iterator.next());

    }


}
