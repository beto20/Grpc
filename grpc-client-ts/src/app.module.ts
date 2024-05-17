import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ClientsModule, Transport } from '@nestjs/microservices';
import { join } from 'path';

@Module({
  imports: [
    ClientsModule.register([
      {
        name: 'USERS_SERVICE',
        transport: Transport.GRPC,
        options: {
          package: 'users',
          protoPath: join(__dirname, 'protos/users.proto'),
          url: 'localhost:8090',
        },
      },
      {
        name: 'BANK_SERVICE',
        transport: Transport.GRPC,
        options: {
          package: 'banking',
          protoPath: join(__dirname, 'protos/bank-service.proto'),
          url: 'localhost:8090',
        },
      },

    ]),
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
