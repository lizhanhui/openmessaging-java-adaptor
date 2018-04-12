package io.openmessaging.consumer;

import io.openmessaging.interceptor.ConsumerInterceptor;
import java.util.concurrent.ConcurrentHashMap;

public class PushConsumerAdaptor {

    private final PushConsumer consumer;

    private final ConcurrentHashMap<Integer, ConsumerInterceptor> interceptors;

    public PushConsumerAdaptor(PushConsumer consumer) {
        this.consumer = consumer;
        interceptors = new ConcurrentHashMap<Integer, ConsumerInterceptor>();
    }

    public void addInterceptor(int index, ConsumerInterceptor interceptor) {
        ConsumerInterceptor previous = interceptors.put(index, interceptor);
        if (null != previous) {
            consumer.removeInterceptor(previous);
        }
        consumer.addInterceptor(interceptor);
    }

    public void removeInterceptor(int index) {
        ConsumerInterceptor interceptor = interceptors.remove(index);
        if (null != interceptor) {
            consumer.removeInterceptor(interceptor);
        }
    }
}
