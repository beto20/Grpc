package com.grpc.demo.service;

import banking.AccountBalanceResponse;
import banking.AccountBalanceServiceGrpc;
import banking.AccountRequest;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BankAccountBalanceService extends AccountBalanceServiceGrpc.AccountBalanceServiceImplBase {
    @Override
    public void getAccountBalance(AccountRequest request, StreamObserver<AccountBalanceResponse> responseObserver) {

        var empResp = AccountBalanceResponse.newBuilder()
                .setAccountNumber(request.getAccountNumber())
                .setBalance(100)
                .build();

        responseObserver.onNext(empResp);
        responseObserver.onCompleted();
    }
}
