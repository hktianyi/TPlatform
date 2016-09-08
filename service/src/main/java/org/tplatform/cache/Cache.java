//package org.tplatform.cache;
//
//import java.util.Collection;
//import java.util.Set;
//
///**
// * Created by Tianyi on 2015/9/29.
// */
//public interface Cache<K, V> {
//
//  /**
//   * Returns the Cached value stored under the specified {@code key} or
//   * {@code null} if there is no Cache entry for that {@code key}.
//   *
//   * @param key the key that the value was previous added with
//   * @return the cached object or {@code null} if there is no entry for the specified {@code key}
//   */
//  public V get(K key);
//
//  /**
//   * Adds a Cache entry.
//   *
//   * @param key   the key used to identify the object being stored.
//   * @param value the value to be stored in the cache.
//   * @return the previous value associated with the given {@code key} or {@code null} if there was previous value
//   */
//  public V put(K key, V value);
//
//  /**
//   * Remove the cache entry corresponding to the specified key.
//   *
//   * @param key the key of the entry to be removed.
//   * @return the previous value associated with the given {@code key} or {@code null} if there was previous value
//   */
//  public V remove(K key);
//
//  /**
//   * Clear all entries from the cache.
//   */
//  public void clear();
//
//  /**
//   * Returns the number of entries in the cache.
//   *
//   * @return the number of entries in the cache.
//   */
//  public int size();
//
//  /**
//   * Returns a view of all the keys for entries contained in this cache.
//   *
//   * @return a view of all the keys for entries contained in this cache.
//   */
//  public Set<K> keys();
//
//  /**
//   * Returns a view of all of the values contained in this cache.
//   *
//   * @return a view of all of the values contained in this cache.
//   */
//  public Collection<V> values();
//}
