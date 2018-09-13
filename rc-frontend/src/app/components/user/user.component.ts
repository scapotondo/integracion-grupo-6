import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import {UIContext} from '../../ui.context';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['fullName', 'username', 'role', 'action'];

  users: User[];
  constructor(
    public uiContext: UIContext,
    public userService: UserService
  ) {
    this.uiContext.setTittle('Usuarios');
  }

  ngOnInit() {
    this.userService.findAll()
      .subscribe( data => {
        this.users = data;
      });
  }

  deleteUser(user: User): void {
    this.userService.delete(user)
      .subscribe( data => {
        this.users = this.users.filter(u => u !== user);
      });
  }

}
