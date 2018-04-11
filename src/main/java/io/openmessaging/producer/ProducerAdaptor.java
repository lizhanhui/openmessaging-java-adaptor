package io.openmessaging.producer;

import io.openmessaging.Future;
import io.openmessaging.FutureListener;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;

public class ProducerAdaptor {

    private Producer producer;

    public ProducerAdaptor(Producer producer) {
        this.producer = producer;
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

}
