package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;

public class ConsumerInterceptorAdaptor implements ConsumerInterceptor {

    private final int index;

    public ConsumerInterceptorAdaptor(int index) {
        this.index = index;
    }

    public native void doPreReceive(int index, Message message, KeyValue attributes);

    @Override
    public void preReceive(Message message, KeyValue attributes) {
        doPreReceive(index, message, attributes);
    }

    public native void doPostReceive(int index, Message message, KeyValue attributes);

    @Override
    public void postReceive(Message message, KeyValue attributes) {
        doPostReceive(index, message, attributes);
    }
}
