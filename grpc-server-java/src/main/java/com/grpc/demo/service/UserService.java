package com.grpc.demo.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import users.Users;
import users.UsersServiceGrpc;

@GrpcService
public class UserService extends UsersServiceGrpc.UsersServiceImplBase {
    @Override
    public void getUser(Users.GetUserRequest request, StreamObserver<Users.GetUserResponse> responseObserver) {
        var response = Users.GetUserResponse.newBuilder()
                .setEmail(request.getEmail())
                .setName("pedro")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(Users.CreateUserRequest request, StreamObserver<Users.CreateUserResponse> responseObserver) {
        var response = Users.CreateUserResponse.newBuilder()
                .setName(request.getName())
                .setEmail(request.getEmail())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
