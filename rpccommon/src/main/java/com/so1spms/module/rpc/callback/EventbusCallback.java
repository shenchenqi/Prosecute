//package com.etransfar.module.rpc.callback;
//
//import android.util.EventLog;
//
//import com.etransfar.module.rpc.eventbus.BaseResponseEvent;
//
//import org.greenrobot.eventbus.EventBus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by qiaobo on 16/8/12.
// */
//public class EventbusCallback<E extends BaseResponseEvent, T> extends BaseCallback<T> {
//
//    private static final Logger logger = LoggerFactory.getLogger("EventbusCallback");
//
//    private Class<? super E> eventClass;
//    private boolean isSticky;
//    private EventBus eventBus;
//
//    public EventbusCallback(Class<? super E> eventClass, EventBus eventBus, boolean isSticky) {
//        this.eventClass = eventClass;
//        assert eventClass != null : "must set eventClass for target";
//        this.isSticky = isSticky;
//        if(eventBus == null) {
//            this.eventBus = EventBus.getDefault();
//        } else {
//            this.eventBus = eventBus;
//        }
//        logger.info("EventbusCallback be create, eventClass:{}, isSticky:{}, eventBus:{}", eventClass, isSticky, eventBus);
//    }
//
//    @Override
//    public void onResponse(Call<T> call, Response<T> response) {
//        super.onResponse(call, response);
//        onEventPost(call, response, null);
//        if(response == null || !response.isSuccessful()) {
//            logger.info("showServerErrorMsg=>{}", call.request().url().toString());
//            return ;
//        }
//    }
//
//    @Override
//    public void onFailure(Call<T> call, Throwable t) {
//        super.onFailure(call, t);
//        onEventPost(call, null, t);
//    }
//
//    public E makeEvent() {
//        try {
//            return (E) eventClass.newInstance();
//        } catch (InstantiationException e) {
//            logger.error("makeEvent InstantiationException", e);
//        } catch (IllegalAccessException e) {
//            logger.error("makeEvent IllegalAccessException", e);
//        } catch (Exception e) {
//            logger.error("makeEvent Exception", e);
//        }
//        return null;
//    }
//
//    public void onEventPost(Call<T> call, Response<T> response, Throwable t) {
//        BaseResponseEvent event = makeEvent();
//        if(event == null) {
//            logger.warn("event is null, onEventPost fail");
//            return ;
//        }
//
//        event.setCall(call);
//        event.setResponse(response);
//        event.setThrowable(t);
//
//        logger.info("onEventPost : {}, isSticky: {}, eventBus:{}", event, isSticky, eventBus);
//
//        if(isSticky) {
//            eventBus.post(event);
//        } else {
//            eventBus.postSticky(event);
//        }
//    }
//
//    public static Callback create(Class<? extends BaseResponseEvent> eventClass) {
//        return create(eventClass, null, false);
//    }
//
//    public static Callback create(Class<? extends BaseResponseEvent> eventClass, boolean isSticky) {
//        return create(eventClass, null, isSticky);
//    }
//
//    public static Callback create(Class<? extends BaseResponseEvent> eventClass, EventBus eventbus, boolean isSticky) {
//        return new EventbusCallback(eventClass, eventbus, isSticky);
//    }
//}
