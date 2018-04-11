package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.exception.OMSException;

public class ProducerInterceptorAdaptor implements ProducerInterceptor {

    private final int index;

    public ProducerInterceptorAdaptor(int index) {
        this.index = index;
    }

    public native void doPreSend(int index, Message message, KeyValue attributes);

    @Override
    public void preSend(Message message, KeyValue attributes) {
        doPreSend(index, message, attributes);
    }

    public native void doPostSend(int index, Message message, KeyValue attributes, OMSException e);

    @Override
    public void postSend(Message message, KeyValue attributes) {
        doPostSend(index, message, attributes, null);
    }

    @Override
    public void postSend(Message message, KeyValue attributes, OMSException sendException) {
        doPostSend(index, message, attributes, sendException);
    }
}
