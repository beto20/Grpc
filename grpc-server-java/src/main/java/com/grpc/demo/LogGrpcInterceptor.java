package com.grpc.demo;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogGrpcInterceptor implements ServerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogGrpcInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info(serverCall.getMethodDescriptor().getFullMethodName());
        log.info(serverCall.getAttributes().toString());
        log.info(serverCall.getMethodDescriptor().getServiceName());
        log.info(serverCall.getMethodDescriptor().getBareMethodName());
        log.info(serverCall.getMethodDescriptor().getSchemaDescriptor().toString());
        var x = Metadata.Key.of("prueba", Metadata.ASCII_STRING_MARSHALLER);
        log.info(metadata.get(x));

        return serverCallHandler.startCall(serverCall, metadata);
    }
}
