package pac.testcase.utils;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.Deque;

public class SizeUtils {

    public static void premain(String args, Instrumentation inst) {
        Instrumentation instrumentation = inst;
	}

	public static long fullSizeOf(Object object) throws IllegalAccessException {
        Deque<Object> toBeQueue = new ArrayDeque<>();
		toBeQueue.add(object);
		long size = 0L;
		while (toBeQueue.size() > 0) {
			Object obj = toBeQueue.poll();
			// sizeOf的时候已经计基本类型和引用的长度，包括数组
			//size += skipObject(visited, obj) ? 0L : sizeOf(obj);
			Class<?> tmpObjClass = obj.getClass();
			if (tmpObjClass.isArray()) {
				// [I , [F 基本类型名字长度是2
				if (tmpObjClass.getName().length() > 2) {
					for (int i = 0, len = Array.getLength(obj); i < len; i++) {
						Object tmp = Array.get(obj, i);
						if (tmp != null) {
							// 非基本类型需要深度遍历其对象
							toBeQueue.add(Array.get(obj, i));
						}
					}
				}
			} else {
				while (tmpObjClass != null) {
					Field[] fields = tmpObjClass.getDeclaredFields();
					for (Field field : fields) {
						if (Modifier.isStatic(field.getModifiers()) // 静态不计
								|| field.getType().isPrimitive()) { // 基本类型不重复计
							continue;
						}

						field.setAccessible(true);
						Object fieldValue = field.get(obj);
						if (fieldValue == null) {
							continue;
						}
						toBeQueue.add(fieldValue);
					}
					tmpObjClass = tmpObjClass.getSuperclass();
				}
			}
		}
		return size;
	}
}
