package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;

/**
 * JDK动态代理
 */
public class JdkProxyHandler implements InvocationHandler {
    public static void main(String[] args) {
        BaoMa baoMa = new BaoMa();
        Car car = (Car) new JdkProxyHandler(baoMa).getProxy();
        car.run();
    }

    private Object target;

    public JdkProxyHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前时间:{}" + Instant.now());
        Object obj = method.invoke(target, args);
        System.out.println("代理后时间" +Instant.now());
        return obj;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

}
