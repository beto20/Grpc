import { Controller, Get, Inject, OnModuleInit } from '@nestjs/common';
import { ClientGrpc } from '@nestjs/microservices';

@Controller()
export class AppController implements OnModuleInit {

  private usersService;
  private bankService;

  constructor(
    @Inject('USERS_SERVICE') private userClient: ClientGrpc,
    @Inject('BANK_SERVICE') private bankClient: ClientGrpc
  ) {}

  onModuleInit() {
    this.usersService = this.userClient.getService('UsersService');
    this.bankService = this.bankClient.getService('AccountBalanceService');
  }

  @Get('users')
  async getUsers() {
    return this.usersService.GetUser({ email: 'Jon@gmail.com' });
  }

  @Get('create')
  async createUsers() {
    return this.usersService.CreateUser({ name: 'PEDRO123', email: 'Jon@gmail.com' });
  }

  @Get('account')
  async account() {
    return this.bankService.GetAccountBalance({ account_number: '1234' });
  }
}
