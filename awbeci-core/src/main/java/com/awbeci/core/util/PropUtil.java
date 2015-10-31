package com.awbeci.core.util;

import com.awbeci.core.fault.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性配置文件解析工具
 * Created by [Sun Wang] on 2015/10/30.
 */
public class PropUtil {


    /**
     * 默认配置文件名
     */
    public static final String DEFAULT_CONFIG_NAME = "config";

    /**
     * 配置文件后缀名
     */
    public static final String PROPERTIES_SUFFIX = ".properties";

    /**
     * 解析缓存
     */
    private static final Map<String, Properties> properties = new ConcurrentHashMap<String, Properties>();

    /**
     * 获取指定属性文件的配置项值
     *
     * @param filename
     * @param key
     * @return
     */
    public static String get(String filename, String key) {
        filename = getFileNameWithoutSuffix(filename);
        if (!properties.containsKey(filename)) {
            parsePropertiesFile(filename, !(DEFAULT_CONFIG_NAME + PROPERTIES_SUFFIX).equals(filename));    //解析文件
        }
        Properties prop = properties.get(filename);
        if (prop != null) {
            return prop.getProperty(key);
        }
        return null;
    }

    /**
     * 获取默认属性文件中的值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return get(DEFAULT_CONFIG_NAME, key);
    }

    /**
     * 获取配置项值
     *
     * @param filename 可选，属性文件名
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static int getInt(String filename, String key, Integer defaults) {
        String value = get(filename, key);
        try {
            if (value == null) {
                if (defaults == null) {
                    throw new ApplicationException();
                } else {
                    return defaults;
                }
            } else {
                return Integer.valueOf(value);
            }
        } catch (Exception e) {
            throw getClassCastError(value, int.class);
        }
    }

    /**
     * 获取配置项值
     *
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static int getInt(String key, Integer defaults) {
        return getInt("", key, defaults);
    }

    /**
     * 获取配置项值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getInt(key, null);
    }

    /**
     * 获取配置项值
     *
     * @param filename 可选，属性文件名
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static long getLong(String filename, String key, Long defaults) {
        String value = get(filename, key);
        try {
            if (value == null) {
                if (defaults == null) {
                    throw new ApplicationException();
                } else {
                    return defaults;
                }
            } else {
                return Long.valueOf(value);
            }
        } catch (Exception e) {
            throw getClassCastError(value, long.class);
        }
    }

    /**
     * 获取配置项值
     *
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static long getLong(String key, Long defaults) {
        return getLong("", key, defaults);
    }

    /**
     * 获取配置项值
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * 获取配置项值
     *
     * @param filename 可选，属性文件名
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static float getFloat(String filename, String key, Float defaults) {
        String value = get(filename, key);
        try {
            if (value == null) {
                if (defaults == null) {
                    throw new ApplicationException();
                } else {
                    return defaults;
                }
            } else {
                return Float.valueOf(value);
            }
        } catch (Exception e) {
            throw getClassCastError(value, float.class);
        }
    }

    /**
     * 获取配置项值
     *
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static float getFloat(String key, Float defaults) {
        return getFloat("", key, defaults);
    }

    /**
     * 获取配置项值
     *
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return getFloat(key, null);
    }

    /**
     * 获取配置项值
     *
     * @param filename 可选，属性文件名
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static double getDouble(String filename, String key, Double defaults) {
        String value = get(filename, key);
        try {
            if (value == null) {
                if (defaults == null) {
                    throw new ApplicationException();
                } else {
                    return defaults;
                }
            } else {
                return Double.valueOf(value);
            }
        } catch (Exception e) {
            throw getClassCastError(value, double.class);
        }
    }

    /**
     * 获取配置项值
     *
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static double getDouble(String key, Double defaults) {
        return getDouble("", key, defaults);
    }

    /**
     * 获取配置项值
     *
     * @param key
     * @return
     */
    public static double getDouble(String key) {
        return getDouble(key, null);
    }

    /**
     * 获取配置项值
     *
     * @param filename 可选，属性文件名
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static boolean getBoolean(String filename, String key, Boolean defaults) {
        String value = get(filename, key);
        try {
            if (value == null) {
                if (defaults == null) {
                    throw new ApplicationException();
                } else {
                    return defaults;
                }
            } else {
                return Boolean.valueOf(value);
            }
        } catch (Exception e) {
            throw getClassCastError(value, boolean.class);
        }
    }

    /**
     * 获取配置项值
     *
     * @param key      必须，配置项
     * @param defaults 可选，默认值
     * @return
     */
    public static boolean getBoolean(String key, Boolean defaults) {
        return getBoolean("", key, defaults);
    }

    /**
     * 获取配置项值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    /**
     * 处理文件名，使解析文件时可以带/不带扩展名
     *
     * @param filename
     * @return
     */
    private static String getFileNameWithoutSuffix(String filename) {
        if (filename == null || "".equals(filename.trim())) filename = DEFAULT_CONFIG_NAME;
        if (!filename.toLowerCase().endsWith(PROPERTIES_SUFFIX)) {
            return filename + PROPERTIES_SUFFIX;
        }
        return filename;
    }

    /**
     * 解析文件
     *
     * @param filename          属性文件名，可以包括/不包括文件名后缀
     * @param fileNotFoundError 文件不存在时是否抛出异常
     */
    private static void parsePropertiesFile(String filename, boolean fileNotFoundError) {
        Properties prop = new Properties();
        InputStream is = null;
        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        try {
            if (is != null) {
                prop.load(is);
                properties.put(filename, prop);
            } else if (fileNotFoundError) {
                throw new ApplicationException("配置文件不存在: [ {} ]", filename);
            }
        } catch (IOException e) {
            throw new ApplicationException("解析属性文件时出错：[ {} ]", filename, e);
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * 类型转换异常
     *
     * @param value
     * @param clazz
     * @return
     */
    private static ApplicationException getClassCastError(String value, Class clazz) {
        return new ApplicationException("{} 不能被转成一个 {} 类型", value, clazz.getName());
    }

}
