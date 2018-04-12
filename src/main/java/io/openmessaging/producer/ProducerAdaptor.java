package io.openmessaging.producer;

import io.openmessaging.Future;
import io.openmessaging.FutureListener;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.interceptor.ProducerInterceptor;
import java.util.concurrent.ConcurrentHashMap;

public class ProducerAdaptor {

    private Producer producer;

    private ConcurrentHashMap<Integer, ProducerInterceptor> interceptors;

    public ProducerAdaptor(Producer producer) {
        this.producer = producer;
        interceptors = new ConcurrentHashMap<Integer, ProducerInterceptor>();
    }

    public native void operationComplete(long invokeId, Future<SendResult> future);

    public void sendAsync(final long invokeId, Message message) {
        final Future<SendResult> future = producer.sendAsync(message);
        future.addListener(new FutureListener<SendResult>() {
            public void operationComplete(Future<SendResult> future) {
                ProducerAdaptor.this.operationComplete(invokeId, future);
            }
        });
    }


    public void sendAsync(final long invokeId, Message message, KeyValue attributes) {
        final Future<SendResult> future = producer.sendAsync(message, attributes);
        future.addListener(new FutureListener<SendResult>() {
            public void operationComplete(Future<SendResult> future) {
                ProducerAdaptor.this.operationComplete(invokeId, future);
            }
        });
    }

    public void addInterceptor(int index, ProducerInterceptor interceptor) {
        interceptors.put(index, interceptor);
        producer.addInterceptor(interceptor);
    }

    public void removeInterceptor(int index) {
        ProducerInterceptor interceptor = interceptors.remove(index);
        if (null != interceptor) {
            producer.removeInterceptor(interceptor);
        }
    }

}
