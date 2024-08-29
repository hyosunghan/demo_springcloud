package com.example.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author moc
 * @date 2019-02-18 17:13
 */


public class ReflectHelper {
    /**
     * 获取成员变量的修饰符
     *
     * @param clazz
     * @param field
     * @return
     * @throws Exception
     */
    public static <T> int getFieldModifier(Class<T> clazz, String field) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field))
                return fields[i].getModifiers();
        }
        throw new Exception(clazz + " has no field \"" + field + "\"");
    }

    /**
     * [对象]根据成员变量名称获取其值
     *
     * @param clazzInstance
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> Object getFieldValue(Object clazzInstance, Object field)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field[] fields = clazzInstance.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                fields[i].setAccessible(true);
                return fields[i].get(clazzInstance);
            }
        }
        return null;
    }

    /**
     * [类]根据成员变量名称获取其值（默认值）
     *
     * @param clazz
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> Object getFieldValue(Class<T> clazz, String field) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                fields[i].setAccessible(true);
                return fields[i].get(clazz.newInstance());
            }
        }
        return null;
    }

    /**
     * 利用反射获取指定对象的指定属性
     *
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @return 目标属性的值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object getFieldValue(Object obj, String fieldName)
            throws IllegalArgumentException, IllegalAccessException {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            result = field.get(obj);
        }
        return result;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     *
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @param fieldValue
     *            目标值
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue)
            throws IllegalArgumentException, IllegalAccessException {
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, fieldValue);
        }
    }

    /**
     * 获取所有的成员变量,包括父类
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> Field[] getClassFieldsAndSuperClassFields(Class<T> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        if (clazz.getSuperclass() == null)
            throw new Exception(clazz.getName() + "没有父类");
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();

        Field[] allFields = new Field[fields.length + superFields.length];

        for (int i = 0; i < fields.length; i++)
            allFields[i] = fields[i];
        for (int i = 0; i < superFields.length; i++)
            allFields[fields.length + i] = superFields[i];
        return allFields;
    }

    /**
     * 获取所有的成员变量
     *
     * @param clazz
     * @return
     */
    public static <T> String[] getFields(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldsArray = new String[fields.length];
        for (int i = 0; i < fields.length; i++)
            fieldsArray[i] = fields[i].getName();
        return fieldsArray;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     *
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @return 目标字段
     */
    public static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
            }
        }
        return field;
    }

    /**
     * 指定类，调用指定的无参方法
     * @param clazz
     * @param method
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> Object invoke(Class<T> clazz, String method) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        Object instance = clazz.newInstance();
        Method m = clazz.getMethod(method, new Class[] {});
        return m.invoke(instance, new Object[] {});
    }

    /**
     * 通过对象，访问其方法
     * @param clazzInstance
     * @param method
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> Object invoke(Object clazzInstance, String method)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException {
        Method m = clazzInstance.getClass().getMethod(method, new Class[] {});
        return m.invoke(clazzInstance, new Object[] {});
    }

    /**
     * 指定类，调用指定的方法
     * @param clazz
     * @param method
     * @param paramClasses
     * @param params
     * @return Object
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> Object invoke(Class<T> clazz, String method, Class<T>[] paramClasses, Object[] params)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, InvocationTargetException {
        Object instance = clazz.newInstance();
        Method _m = clazz.getMethod(method, paramClasses);
        return _m.invoke(instance, params);
    }

    /**
     * 通过类的实例，调用指定的方法
     *
     * @param clazzInstance
     * @param method
     * @param paramClasses
     * @param params
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> Object invoke(Object clazzInstance, String method, Class<T>[] paramClasses, Object[] params)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, InvocationTargetException {
        Method _m = clazzInstance.getClass().getMethod(method, paramClasses);
        return _m.invoke(clazzInstance, params);
    }

    /**
     * 获取某个类的某个方法(当前类和父类)
     */
    public static Method getMethod(String methodName, Class<?> clazz) {
        Method method = null;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName);
                break;
            } catch (Exception e) {
            }
        }
        if (method == null)
            throw new RuntimeException("没有" + methodName + "方法");
        return method;
    }

    /**
     * 获取某个类的某个方法(当前类和父类) 带一个参数
     */
    public static Method getMethod(String methodName, Class<?> clazz, Class<?> paramType) {
        Method method = null;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, paramType);
                if (method != null)
                    return method;
            } catch (Exception e) {
            }
        }
        if (method == null)
            throw new RuntimeException("没有" + methodName + "方法");
        return method;
    }

    /**
     * 获取成员方法的修饰符
     *
     * @param clazz
     * @param method
     * @return
     * @throws Exception
     */
    public static <T> int getMethodModifier(Class<T> clazz, String method) throws Exception {
        Method[] m = clazz.getDeclaredMethods();
        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().equals(m))
                return m[i].getModifiers();
        }
        throw new Exception(clazz + " has no method \"" + m + "\"");
    }

    /**
     * 获取类上的注解
     *
     * @param targetAnnotationClass 目标注解
     * @param targetObjcetClass     目标类
     * @return 目标注解实例
     */
    public static <T extends Annotation> T getClassAnnotation(Class<T> targetAnnotationClass, Class<T> targetObjcetClass) {
        return targetObjcetClass.getAnnotation(targetAnnotationClass);
    }

    /**
     * 获取类上的注解
     *
     * @return 目标注解实例
     */
    public static Annotation getClassAnnotation(Class<?> targetAnnotationClass, Object obj) {
        return getClassAnnotation(targetAnnotationClass, obj.getClass());
    }

    /**
     * 获取类上的注解
     *
     * @return 目标注解实例
     */
    public static Annotation getClassAnnotation(Class<?> targetAnnotationClass, String clazz) {
        try {
            return getClassAnnotation(targetAnnotationClass, Class.forName(clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取方法上的注解
     */
    public static <T extends Annotation> T getMethodAnnotation(Method method, Class<T> targetAnnotationClass) {
        return method.getAnnotation(targetAnnotationClass);
    }

    /**
     * 获取属性上的注解
     */
    public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> targetAnnotationClass) {
        return field.getAnnotation(targetAnnotationClass);
    }

    /**
     * 获取注解某个属性的值
     *
     * @param methodName
     *            属性名
     * @param annotation
     *            目标注解
     * @param <T>
     *            返回类型
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnnotationValue(String methodName, Annotation annotation) {
        try {
            Method method = annotation.annotationType().getMethod(methodName);
            Object object = method.invoke(annotation);
            return (T) object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取某个类的某个方法上的某个注解的属性
     *
     * @param methodName
     *            注解属性的名字
     * @param targetAnnotationClass
     *            目标注解
     * @param targetObjecMethodName
     *            目标类的方法
     * @param targetObjectClass
     *            目标类
     * @param <T>
     *            返回值类型
     */
    public static <T extends Annotation> T getMethodAnnotationValue(String methodName, Class<T> targetAnnotationClass,
                                                                    String targetObjecMethodName, Class<?> targetObjectClass) {
        Method method = getMethod(targetObjecMethodName, targetObjectClass);
        Annotation annotation = getMethodAnnotation(method, targetAnnotationClass);
        return getAnnotationValue(methodName, annotation);
    }

    /**
     *
     * @param methodName
     *            注解属性名
     * @param targetAnnotationClass
     *            目标注解
     * @param targetObjecFieldName
     *            目标属性名字
     * @param targetObjectClass
     *            目标类
     * @param <T>
     *            返回值类型
     */
    public static <T extends Annotation> T getFieldAnnotationValue(String methodName, Class<T> targetAnnotationClass,
                                                                   String targetObjecFieldName, Class<?> targetObjectClass) {
        Field field = getField(targetObjectClass, targetObjecFieldName);
        Annotation annotation = getFieldAnnotation(field, targetAnnotationClass);
        return getAnnotationValue(methodName, annotation);
    }

    /**
     * 判断 clazz是否是target的子类型或者相等
     */
    public static boolean isSubClassOrEquesClass(Class<?> clazz, Class<?> target) {
        if (clazz == target)
            return true;
        while (clazz != Object.class) {
            if (clazz == target)
                return true;
            clazz = clazz.getSuperclass();
        }
        return false;
    }

    /**
     * 通过属性赋值
     */
    public static void setValueByField(String fieldName, Object object, Object value) {
        Field field = getField(object.getClass(), fieldName);
        setValueByField(field, object, value);
    }

    /**
     * 通过属性赋值
     */
    public static void setValueByField(Field field, Object object, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
                field.set(object, value);
                field.setAccessible(false);
            } else {
                field.set(object, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过set方法赋值
     */
    public static void setValueBySetMethod(Field field, Object object, Object value) {
        if (object == null)
            throw new RuntimeException("实例对象不能为空");
        if (value == null)
            return;
        setValueBySetMethod(field.getName(), object, value);
    }

    /**
     * 通过set方法赋值
     */
    public static void setValueBySetMethod(String fieldName, Object object, Object value) {
        if (object == null)
            throw new RuntimeException("实例对象不能为空");
        if (value == null)
            return;
        try {
            String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method setMethod = getMethod(setMethodName, object.getClass(), value.getClass());
            setMethod.invoke(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
