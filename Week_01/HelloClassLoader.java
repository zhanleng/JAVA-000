package week01;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args)  {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");
            Object obj = helloClass.newInstance();
            Method method = helloClass.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filename = System.getProperty("user.dir") + "/src/week01///Hello.xlass";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            for (int i = bytes.length - 1; i >= 0; i--) {
                bytes[i] = (byte) (255 - bytes[i]);
            }

            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}
